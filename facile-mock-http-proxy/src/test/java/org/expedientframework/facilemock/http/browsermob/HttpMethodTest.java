/********************************************************************
 * File Name:    HttpMethodTest.java
 *
 * Date Created: Oct 28, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob;

import static org.assertj.core.api.Assertions.assertThat;
import static org.expedientframework.facilemock.http.browsermob.HttpMockHelpers.*;

import org.junit.jupiter.api.Test;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * Tests for http method based condition mocking.
 * 
 * @author ajey_dudhe
 *
 */
class HttpMethodTest extends AbstractTest
{
  @Test
  void post_returnsMockData()
  {
    try(HttpMockContext mock = HttpProxyManagerFactory.createMockContext(unitTest()))
    {
      final int port = mock.getHttpProxyManager().getPort();
      
      final String endpoint = "/dummy";
      
      mock.when(post(urlEquals(endpoint))).then(respondWith("Hello from mock for post !!!"));
      
      assertThat(getResponseStatus(port, endpoint)).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      assertThat(postAndGetResponseBody(port, endpoint)).as("Response").isEqualTo("Hello from mock for post !!!");
      assertThat(postAndGetResponseBody(port, endpoint)).as("Response").isEqualTo("Hello from mock for post !!!");
      assertThat(postAndGetResponseBody(port, endpoint)).as("Response").isEqualTo("Hello from mock for post !!!");
      assertThat(postAndGetResponseBody(port, endpoint)).as("Response").isEqualTo("Hello from mock for post !!!");
    }
  }

  @Test
  void allMethods_returnsMockData()
  {
    try(HttpMockContext mock = HttpProxyManagerFactory.createMockContext(unitTest()))
    {
      final int port = mock.getHttpProxyManager().getPort();
      
      final String endpoint = "/dummy";
      
      mock.when(get(urlEquals(endpoint))).then(respondWith("Hello from mock for get !!!"));
      mock.when(post(urlEquals(endpoint))).then(respondWith("Hello from mock for post !!!"));
      mock.when(put(urlEquals(endpoint))).then(respondWith("Hello from mock for put !!!"));
      mock.when(delete(urlEquals(endpoint))).then(respondWith("Hello from mock for delete !!!"));
      mock.when(head(urlEquals(endpoint))).then(respondWith(HttpResponseStatus.NOT_ACCEPTABLE));
      mock.when(options(urlEquals(endpoint))).then(respondWith("Hello from mock for options !!!"));
      mock.when(trace(urlEquals(endpoint))).then(respondWith(HttpResponseStatus.NOT_IMPLEMENTED));
      mock.when(patch(urlEquals(endpoint))).then(respondWith("Hello from mock for patch !!!"));
      
      assertThat(patchAndGetResponseBody(port, endpoint)).as("Response").isEqualTo("Hello from mock for patch !!!");
      assertThat(getResponseBody(port, endpoint)).as("Response").isEqualTo("Hello from mock for get !!!");
      assertThat(traceAndGetStatus(port, endpoint)).as("Response").isEqualTo(HttpResponseStatus.NOT_IMPLEMENTED);
      assertThat(postAndGetResponseBody(port, endpoint)).as("Response").isEqualTo("Hello from mock for post !!!");
      assertThat(optionsAndGetResponseBody(port, endpoint)).as("Response").isEqualTo("Hello from mock for options !!!");
      assertThat(putAndGetResponseBody(port, endpoint)).as("Response").isEqualTo("Hello from mock for put !!!");
      assertThat(headAndGetStatus(port, endpoint)).as("Response").isEqualTo(HttpResponseStatus.NOT_ACCEPTABLE);
      assertThat(deleteAndGetResponseBody(port, endpoint)).as("Response").isEqualTo("Hello from mock for delete !!!");
    }
  }

  /*
  @Test
  void connect_returnsHttpCode()
  {
    try(HttpMockContext mock = HttpProxyManagerFactory.createMockContext(unitTest()))
    {
      final int port = mock.getHttpProxyManager().getPort();
      
      final String endpoint = "";
      
      mock.when(connect(urlEquals(endpoint))).then(respondWith(HttpResponseStatus.BAD_REQUEST));
      
      assertThat(getHttpStatusWhileFetchingUsers(port)).as("Response").isEqualTo(HttpResponseStatus.EXPECTATION_FAILED);
    }
  }*/
}

