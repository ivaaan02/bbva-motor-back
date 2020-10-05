# Overview

This section covers the basics of how to use the Java 8 archetype for BBVA back applications deployed on Google App Engine using the [Java Standard Environment]. This archetype contains a set of classes and configuration files that will help you with the development of this kind of applications.

## Prerequisites

These are the necessary tools in order to use this archetype.

* [Docker]
* [Artifactory]
* [Bitbucket]
* [Google Project] (by environment)

## Project creation

First of all, we need to create a new project based on the archetype. For that, we'll use the `fga-cli` command line.

* [FGA-CLI Project creation]

In the wizard, we'll choose `Backend application` and `Java 1.8` options.

## Configuration

Once that you have the archetype code, you need to customize your pom.xml file.

* Artifact. You need to specify your artifact id and version
  ```xml
  <project>
    ...
    <artifactId>my-artifact</artifactId>
    <version>version</version>
    ...
  </project>
  ```

* Name and description
  ```xml
  <project>
    ...
    <name>My Application</name>
    <description>Awesome description about my application</description>
    ...
  </project>
  ```

* Google projects identifiers for each environment
  ```xml
  <project>
    ...
    <properties>
      ...
      <dev.appengine.app.id>dev-bbva-app-id</dev.appengine.app.id>
      <au.appengine.app.id>au-bbva-app-id</au.appengine.app.id>
      <pr.appengine.app.id>bbva-app-id</pr.appengine.app.id>
      ...
    </properties>
    ...
  </project>
  ```

## REST Services registration

After create new web services, you need to register them into
`com.bbva.config.Application` class.

```java
...
public App() {
  classes.add(ExampleWS.class);
  ...
}
...
```

## Swagger

In order to use swagger, you need to do the configuration as follows:
* Be sure that the Swagger Client ID is configured in [pom.xml](pom.xml) file.
  ```xml
  <project>
    ...
    <properties>
      ...
      <dev.bbva.swagger.clientid>923825376202-gnjka6hi9mk7o3vd3gcn3eire40pim5f.apps.googleusercontent.com</dev.bbva.swagger.clientid>
      ...
    </properties>
    ...
  </project>
  ```
* Request to GCP Team with this [procedure] that the Authorized redirect URI for your project be configured. This URL should match one of these patterns.
  ```http
  https://{SERVICE}-dot-{PROJECT_ID}.{REGION}.r.appspot.com/ws-doc/o2c.html

  https://{PROJECT_ID}.{REGION}.r.appspot.com/ws-doc/o2c.html

  https://{PROJECT_ID}.appspot.com/ws-doc/o2c.html
  ```

  One example:
  ```http
  https://console-dot-dev-bbva-testing.ew.r.appspot.com/ws-doc/o2c.html
  ```
  Once this would be configured, you can access swagger by using one of this URIs (format should match with the Authorized redirect URI):

  ```http
  https://{SERVICE}-dot-{PROJECT_ID}.{REGION}.r.appspot.com/ws-doc/index.html

  https://{PROJECT_ID}.{REGION}.r.appspot.com/ws-doc/index.html

  https://{PROJECT_ID}.appspot.com/ws-doc/index.html
  ```

## Local environment

In order to work with our project in the local environment, we need `fga-cli` command line.

* [FGA-CLI Running on local environment]
* [FGA-CLI Deploying to App Engine]

## CI/CD

In order to use CI/CD, you have to configure the [Jenkinsfile](Jenkinsfile) file.
* Set the “UUAA” environment variable to the project UUAA.
* Set the “SAMUEL_PROJECT_NAME” environment variable to the name that it will have in the Samuel console.

The global timeout is set by default to 60 minutes for the entire pipeline. You can modify it if necessary.

The `fga-cli` image version used by default is `latest`. If you prefer, you can set a [specific version]. This change
has to be applied in both [Jenkinsfile](Jenkinsfile) and [cloudbuild.yaml](cloudbuild.yaml) files.

## Versioning Rules

Given a version number MAJOR.MINOR.PATCH, increment the:

* MAJOR version when you make incompatible API changes,
* MINOR version when you add functionality in a backwards-compatible manner, and
* PATCH version when you make backwards-compatible bug fixes.

Additional labels for pre-release and build metadata are available as extensions to the MAJOR.MINOR.PATCH format.
Snapshot versions should have '-SNAPSHOT' suffix.

For more information, see [SemVer].

## Change log

See [CHANGELOG](CHANGELOG.md).

## Support

For any problem or bug, please contact with BBVA Google Cloud Platform Team following this [procedure].


[Java Standard Environment]: https://cloud.google.com/appengine/docs/standard/java/
[Docker]: https://docs.docker.com/get-docker/
[Artifactory]: https://platform.bbva.com/en-us/developers/engines/gcp/documentation/aditional-documentation/procedures/artifactory
[Bitbucket]: https://platform.bbva.com/gcp/documentation/1ORjud_IkSWnbawHbSpggSY2Uk0RrH1iTFQqPDQ7F-04/developer-tools/bitbucket
[Google Project]: https://cloud.google.com/docs/overview#projects
[FGA-CLI Running on local environment]: https://docs.google.com/document/d/1Gm9zsfKE5DtY7IipECjP6SBWgffBjQrfC9OD-8sqqgs/edit#heading=h.asos8mhjvuxq
[FGA-CLI Deploying to App Engine]: https://docs.google.com/document/d/1Gm9zsfKE5DtY7IipECjP6SBWgffBjQrfC9OD-8sqqgs/edit#heading=h.9r69xn7gegu2
[FGA-CLI Project creation]: https://docs.google.com/document/d/1Gm9zsfKE5DtY7IipECjP6SBWgffBjQrfC9OD-8sqqgs/edit#heading=h.ibaocs9u3dga
[specific version]: https://docs.google.com/document/d/1tRa9_N4Pk8vsJpgkvzu6baEfSo-vBMSsuAnz_B2EzaM
[SemVer]: http://semver.org/
[procedure]: https://platform.bbva.com/en-us/developers/engines/gcp/documentation/procedures/issue-support-request