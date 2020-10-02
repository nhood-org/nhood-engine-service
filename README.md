[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Version](https://img.shields.io/badge/maven-0.0.8-blue.svg?maxAge=2592000)](https://github.com/nhood-org/repository/packages/197515)
[![Version](https://img.shields.io/badge/docker-v0.0.8-blue.svg?maxAge=2592000)](https://github.com/nhood-org/repository/packages/197505)
[![CircleCI](https://circleci.com/gh/nhood-org/nhood-engine-service.svg?style=shield)](https://circleci.com/gh/nhood-org/nhood-engine-service)

# nhood-engine-service

A stand-alone nhood engine application

## Build, test, run

Use pre-defined Makefile tasks

## CI/CD

Project is continuously integrated with `circleCi` pipeline that link to which may be found [here](https://circleci.com/gh/nhood-org/workflows/nhood-engine-service)

Pipeline is fairly simple:

1. Build and test project with a set of jdk `11`.
1. Build docker image.
1. Run [acceptance tests](https://github.com/nhood-org/nhood-engine-service-tests)

Configuration of CI is implemented in `.circleci/config.yml`.

## Versioning

In order to release version of maven artifacts, send the following API request to circleCI:

```bash
export CIRCLE_CI_USER_TOKEN=<CIRCLE_CI_USER_TOKEN>
make trigger-circle-ci-maven-release
```

In order to release version of docker image, send the following API request to circleCI:

```bash
export CIRCLE_CI_USER_TOKEN=<CIRCLE_CI_USER_TOKEN>
export NEW_VERSION=<NEW_VERSION> 
make trigger-circle-ci-docker-release
```

Both artifacts and docker image will be available [here](https://github.com/orgs/nhood-org/packages)

## License

`nhood-engine-service` is released under the MIT license:
- https://opensource.org/licenses/MIT
