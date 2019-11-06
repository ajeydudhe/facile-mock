[![Build Status](https://travis-ci.org/ajeydudhe/facile-mock.svg?branch=master)](https://travis-ci.org/ajeydudhe/facile-mock) [![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
# facile-mock - Simple mocking library for BrowserMobProxy.

**WORK IN PROGRESS** Work in progress.

**facile-mock** is a simple mocking library for [**BrowserMobProxy**](https://github.com/lightbody/browsermob-proxy) to allow mocking the http requests in the tests. It allows running a given test with given scope as unit test or integration test. In case of the scope as integration test the mocking will be skipped altogether and run against actual server.

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
  final response = postAndGetResponseBody(port, "/dummy");
  assertThat(response).as("Response").isEqualTo("Hello from mock for post !!!");
}
```
* _**HttpProxyManagerFactory.createMockContext()**_ creates the mocking context indicating that we are executing unit tests so that the mocking should be honoured. If you mark it as integration tests then no mocking will be done and the http calls will be made to live server as per the url. So you can run same tests as unit tests with mocking and integration tests without mocking. You just need to passing in proper parameter to _**createMockContext()**_ at runtime.
* _**mock.when()**_ takes the mocking condition which in this case says that for _**post**_ request with url equal to _**/dummy**_ we should return a mock response.
* _**then()**_ specifies that we should respond with a dummy string value.
* The _**postAndGetResponseBody()**_ method makes a http call with the given _**port**_ on which the http proxy is listening as follows:
```java
public int postAndGetResponseStatus(final int port, final String endpoint)
{
    try(CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(new HttpHost("localhost", port)))
    {
      final HttpPost post = new HttpPost("http://sampleHostDoesNotExistsBlah.com" + endpoint);
      post.setEntity(new StringEntity("Dummy request body."));
      try(CloseableHttpResponse httpResponse = httpClient.execute(post))
      {
        return httpResponse.getStatusLine().getStatusCode();
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
}
```
