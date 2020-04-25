default: build

ARTIFACT_NAME = nhood-engine-service

.PHONY: clean
clean:
	@echo "Cleaning:"
	mvn clean
	@echo "...done"

.PHONY: build
build:
	@echo "Building maven artifacts:"
	mvn clean install
	@echo "...done"

.PHONY: build-ci
build-ci:
	@test $(GITHUB_USERNAME) || ( echo "GITHUB_USERNAME not set" & exit 1 )
	@test $(GITHUB_TOKEN) || ( echo "GITHUB_TOKEN not set" & exit 2 )
	@echo "Building maven artifacts [CI]:"
	mvn -s .mvn.settings.xml clean install
	@echo "...done"

.PHONY: deploy
deploy:
	@echo "Deploying maven artifacts:"
	mvn deploy -DskipTests
	@echo "...done"

.PHONY: deploy-ci
deploy-ci:
	@test $(GITHUB_USERNAME) || ( echo "GITHUB_USERNAME not set" & exit 1 )
	@test $(GITHUB_TOKEN) || ( echo "GITHUB_TOKEN not set" & exit 2 )
	@echo "Deploying maven artifacts [CI]:"
	mvn -s .mvn.settings.xml deploy -DskipTests
	@echo "...done"

.PHONY: release-ci
release-ci:
	@test $(GITHUB_USERNAME) || ( echo "GITHUB_USERNAME not set" & exit 1 )
	@test $(GITHUB_TOKEN) || ( echo "GITHUB_TOKEN not set" & exit 2 )
	@test $(GITHUB_EMAIL) || ( echo "GITHUB_EMAIL not set" & exit 3 )
	@echo "Releasing maven artifacts [CI]:"
	git config --global user.email ${GITHUB_EMAIL} && \
	git config --global user.name ${GITHUB_USERNAME} && \
	mvn -s .mvn.settings.xml -B release:prepare -Darguments="-DskipTests"
	@echo "...done"

.PHONY: build-docker
build-docker:
	@test $(ARTIFACT_NAME) || ( echo "ARTIFACT_NAME not set" & exit 1 )
	@test $(GITHUB_USERNAME) || ( echo "GITHUB_USERNAME not set" & exit 2 )
	@test $(GITHUB_TOKEN) || ( echo "GITHUB_TOKEN not set" & exit 3 )
	@echo "Building docker image:"
	docker build -t nhood-org/${ARTIFACT_NAME}:local \
		--build-arg GITHUB_USERNAME=${GITHUB_USERNAME} \
		--build-arg GITHUB_TOKEN=${GITHUB_TOKEN} .
	@echo "...done"

.PHONY: build-docker-ci
build-docker-ci:
	@test $(ARTIFACT_NAME) || ( echo "ARTIFACT_NAME not set" & exit 1 )
	@test $(GITHUB_USERNAME) || ( echo "GITHUB_USERNAME not set" & exit 2 )
	@test $(GITHUB_TOKEN) || ( echo "GITHUB_TOKEN not set" & exit 3 )
	@test $(CIRCLE_BRANCH) || ( echo "CIRCLE_BRANCH not set" & exit 4 )
	@echo "Building docker image [CI]:"
	docker build -t nhood-org/${ARTIFACT_NAME}:${CIRCLE_BRANCH} \
		--build-arg GITHUB_USERNAME=${GITHUB_USERNAME} \
		--build-arg GITHUB_TOKEN=${GITHUB_TOKEN} .
	@echo "...done"

.PHONY: release-docker-ci
release-docker-ci: build-docker-ci
	@test $(ARTIFACT_NAME) || ( echo "ARTIFACT_NAME not set" & exit 1 )
	@test $(GITHUB_USERNAME) || ( echo "GITHUB_USERNAME not set" & exit 2 )
	@test $(GITHUB_TOKEN) || ( echo "GITHUB_TOKEN not set" & exit 3 )
	@test $(CIRCLE_BRANCH) || ( echo "CIRCLE_BRANCH not set" & exit 4 )
	@test $(NEW_VERSION) || ( echo "NEW_VERSION not set" & exit 5 )
	@echo "Releasing docker image [CI]:"
	docker login docker.pkg.github.com -u ${GITHUB_USERNAME} -p ${GITHUB_TOKEN} && \
	docker tag nhood-org/${ARTIFACT_NAME}:${CIRCLE_BRANCH} docker.pkg.github.com/nhood-org/repository/${ARTIFACT_NAME}:v${NEW_VERSION} && \
	docker push docker.pkg.github.com/nhood-org/repository/${ARTIFACT_NAME}:v${NEW_VERSION}
	@echo "...done"

.PHONY: run
run: build
	@test $(ARTIFACT_NAME) || ( echo "ARTIFACT_NAME not set" & exit 1 )
	@echo "Running application:"
	java -jar ./${ARTIFACT_NAME}-svc/target/${ARTIFACT_NAME}-svc.jar
	@echo "...done"

.PHONY: run-docker
run-docker: build-docker
	@test $(ARTIFACT_NAME) || ( echo "ARTIFACT_NAME not set" & exit 1 )
	@echo "Running docker:"
	docker run -d -p 8080:8080 nhood-org/${ARTIFACT_NAME}:local
	@echo "...done"

.PHONY: run-acceptance-tests
run-acceptance-tests: build-docker
	@test $(ARTIFACT_NAME) || ( echo "ARTIFACT_NAME not set" & exit 1 )
	@echo "Running acceptance tests:"
	docker-compose up --exit-code-from tests
	@echo "...done"

.PHONY: trigger-circle-ci-maven-release
trigger-circle-ci-maven-release:
	@test $(ARTIFACT_NAME) || ( echo "ARTIFACT_NAME not set" & exit 1 )
	@test $(CIRCLE_CI_USER_TOKEN) || ( echo "CIRCLE_CI_USER_TOKEN not set" & exit 2 )
	@echo "Triggering docker release:"
	curl -u ${CIRCLE_CI_USER_TOKEN}: \
		-d build_parameters[CIRCLE_JOB]=release \
    		https://circleci.com/api/v1.1/project/github/nhood-org/${ARTIFACT_NAME}/tree/master
	@echo "...done"

.PHONY: trigger-circle-ci-docker-release
trigger-circle-ci-docker-release:
	@test $(ARTIFACT_NAME) || ( echo "ARTIFACT_NAME not set" & exit 1 )
	@test $(CIRCLE_CI_USER_TOKEN) || ( echo "CIRCLE_CI_USER_TOKEN not set" & exit 2 )
	@test $(NEW_VERSION) || ( echo "NEW_VERSION not set" & exit 3 )
	@echo "Triggering docker release:"
	curl -u ${CIRCLE_CI_USER_TOKEN}: \
		-d build_parameters[CIRCLE_JOB]=release-docker \
		-d build_parameters[NEW_VERSION]=${NEW_VERSION} \
		https://circleci.com/api/v1.1/project/github/nhood-org/${ARTIFACT_NAME}/tree/master
	@echo "...done"
