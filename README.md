[![Build Status](https://travis-ci.org/ajeydudhe/facile-mock.svg?branch=master)](https://travis-ci.org/ajeydudhe/facile-mock) [![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
# facile-mock - Simple mocking library for BrowserMobProxy.

**WORK IN PROGRESS** Work in progress.

**facile-mock** is a simple mocking library for [**BrowserMobProxy**](https://github.com/lightbody/browsermob-proxy) to allow mocking the http requests in the tests. It allows running a given test as both unit test and integration test where in case of integration test the mocking will be skipped.

## Adding the library reference
Add the maven dependency to your pom.xml as follows:
```xml
<dependency>
    <groupId>org.expedientframework.facilemock</groupId>
    <artifactId>facile-mock-http-proxy</artifactId>
    <version>1.0.0-M1</version>
</dependency>
```
## Usage
```java
    try(HttpMockContext mock = HttpProxyManagerFactory.createMockContext(unitTest()))
    {
      final int port = mock.getHttpProxyManager().getPort();
      mock.when(post(urlEquals("/dummy"))).then(respondWith("Hello from mock for post !!!"));
      assertThat(postAndGetResponseBody(port, "/dummy")).as("Response").isEqualTo("Hello from mock for post !!!");
    }
```
