[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Version](https://img.shields.io/badge/version-0.0.3-blue.svg?maxAge=2592000)](https://github.com/nhood-org/nhood-engine-service/releases/tag/v0.0.3)
[![CircleCI](https://circleci.com/gh/nhood-org/nhood-engine-service.svg?style=shield)](https://circleci.com/gh/nhood-org/nhood-engine-service)

# nhood-engine-service

A stand-alone nhood engine application

## CI/CD

Project is continuously integrated with `circleCi` pipeline that link to which may be found [here](https://circleci.com/gh/nhood-org/workflows/nhood-engine-service)

Pipeline is fairly simple:

1. Build and test project with a set of jdk `11`.
1. Build docker image.

Configuration of CI is implemented in `.circleci/config.yml`.

## Versioning

In order to release version of maven artifacts, send the following API request to circleCI:

```bash
curl -u $CIRCLE_CI_USER_TOKEN: \
    -d build_parameters[CIRCLE_JOB]=release \
    https://circleci.com/api/v1.1/project/github/nhood-org/nhood-engine-service/tree/master
```

In order to release version of docker image, send the following API request to circleCI:

```bash
curl -u $CIRCLE_CI_USER_TOKEN: \
    -d build_parameters[CIRCLE_JOB]=release-docker \
    -d build_parameters[VERSION]=<NEW_VERSION> \
    https://circleci.com/api/v1.1/project/github/nhood-org/nhood-engine-service/tree/master
```

Both artifacts and docker image will be available [here](https://github.com/orgs/nhood-org/packages)

## License

`nhood-engine-service` is released under the MIT license:
- https://opensource.org/licenses/MIT
