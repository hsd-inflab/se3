# HSD FBEI: Software Engineering 3, Prof. Dr. Pedram Nazari

## Information
### How to get your software to your customer/consumer

```mermaid
graph TD;
  step1[Change your code]-->step2[build your app];
  step2--fail-->step1
  step2-->step3[make a release];
  step3-->step4[deploy the release...];
  step4-->step1;

  step1a[Change your code]-->step2a[test your change];
  step2a--fail-->step1a;
  step2a-->step3a[build your app];
  step3a--fail-->step1a;
  step3a-->step4a[make a release];
  step4a-->step5a[deploy the release...];
  step5a-->step1a;

  step1b[Change your code]-->step2b[test your change];
  step2b--fail-->step1b;
  step2b-->step3b[build your app];
  step3b--fail-->step1b;
  step3b-->step4b[test your app];
  step4b--fail-->step1b;
  step4b-->step5b[make a release];
  step5b--fail-->step5b;
  step5b-->step6b[deploy the alpha release...];
  step6b-->step7b[deploy the beta release...];
  step7b-->step8b[deploy the production release...];
  step8b-->step1b;

```

- ...to the (alpha/beta/production) server if it is a server application
- ...to your client distribution platform if it is a client or client-only application
  - android app->play store
  - apple app->app store
  - windows app->windows store
  - ubuntu app->snap store

### Where are those things happening?

```mermaid
stateDiagram
  local: your computer (local)
  localMain: mainBranch
  localFeature: featureXYZBranch
  
  online: GitHub (online)
  pr: pull request of featureXYZBranch
  onlineMain: mainBranch
  onlineRelease: releaseBranch
  containerTest: test
  containerTestCheckout: checkout
  containerTestSetup: setup
  containerTestTest: mvn test
  containerPackage: package
  containerPackageCheckout: checkout
  containerPackageSetup: setup
  containerPackagePackage: mvn package
  containerBuildImage: docker build
  containerBuildImageCheckout: checkout
  containerBuildImageSetup: setup

  state local {
    direction LR
    localMain --> localFeature : branch
    localFeature --> commit : new
  }
  
  local--> online : push
  state online {

    state pr {
      newCommit --> containerTest
      state containerTest {
        direction LR
        containerTestCheckout --> containerTestSetup
        containerTestSetup --> containerTestTest
      }
      containerTest --> containerPackage
      state containerPackage {
        direction LR
        containerPackageCheckout --> containerPackageSetup
        containerPackageSetup --> containerPackagePackage
      }
   
      state containerBuild {
        direction LR
        containerBuildImageCheckout --> containerBuildImageSetup
        containerBuildImageSetup --> containerBuildImage
      }
      containerPackage --> containerBuild
      containerBuild --> alphaTarget : deploy
    }    
    pr --> onlineMain : merge

    onlineMain --> onlineRelease : tag

    state onlineRelease {
      direction LR
      buildImage --> productionTarget : deploy
    }   
  }
  
```

## Course

In this course you will participate in a team development of a web app made with [Java](https://openjdk.org/) and the [Vaadin Framework](https://vaadin.com/).

<img src=".github/images/start_light.png" alt="drawing" width="300"/>


Already provided is...

- a working app skeleton
- CI with [GitHub actions](https://github.com/features/actions)

### Continuous Integration with GitHub Actions

Every commit to the `main` branch will trigger a [GitHub action](https://github.com/features/actions), defined in the [publish.yml](https://github.com/hsd-inflab/se3/blob/main/.github/workflows/publish.yml) workflow file. This action will checkout the source code, install openjdk, test the files in the test directory, create a java archive, create a docker image and deploy it to docker hub:

![Docker Image Version (tag latest semver)](https://img.shields.io/docker/v/feichtmeier/hub/main?logo=docker&label=Deployed%20to%20dockerhub)

In every pull request [another GitHub action is triggered](https://github.com/hsd-inflab/se3/blob/main/.github/workflows/CI.yml) which checks out the source code, installs openjdk, tests the files in the test directory and create a java archive.
If every steps succeeds and the pull request is being marked as approved it is merge-able.

### Simplifications

- the deployment happens after merge to main, not with every commit in the pull request
- no alpha, beta targets
- no integration tests
- main == release

### Setup
1) [Install docker](https://www.docker.com/products/docker-desktop/)
2) [Install Java17+](https://adoptium.net/de/)
3) Install [VsCode](https://code.visualstudio.com/)
4) Install VsCode extensions
   1) [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
   2) [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)
   3) [Docker](https://marketplace.visualstudio.com/items?itemName=ms-azuretools.vscode-docker)
   4) [GitHub Actions](https://marketplace.visualstudio.com/items?itemName=GitHub.vscode-github-actions)
   5) [GitHub Pull Requests And Issues](https://marketplace.visualstudio.com/items?itemName=GitHub.vscode-pull-request-github)

### TODO
1) Assign yourself to an issue
2) Clone this repository
3) Create a branch locally
4) Change code
5) Provide a test for a new feature
6) Stage your changes
7) Commit your changes
8) Publish your branch
9) Make a pull request
10) Check that all status checks are successful
11) Request review
12) Await merge

### Further steps

- analyze step
- format step
- setup versioning strategy
- use codecov action to compare test/code proportion and report if lower than threshold X
- use GitHub projects to improve the development process (kanban? scrum?) 