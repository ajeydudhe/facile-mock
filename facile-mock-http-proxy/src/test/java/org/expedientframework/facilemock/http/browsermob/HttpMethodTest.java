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
 * TODO: Update with a detailed description of the interface/class.
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
}

