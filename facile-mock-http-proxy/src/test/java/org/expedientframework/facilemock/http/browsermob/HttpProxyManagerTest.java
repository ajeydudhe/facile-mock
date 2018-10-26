/********************************************************************
 * File Name:    BrowserMobProxyTest.java
 *
 * Date Created: Oct 16, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob;

import static org.assertj.core.api.Assertions.assertThat;
import static org.expedientframework.facilemock.http.browsermob.HttpMockContext.*;

import org.junit.jupiter.api.Test;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.lightbody.bmp.BrowserMobProxyServer;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
class HttpProxyManagerTest extends AbstractTest //TODO: Ajey - Name the tests as per conventions and make more readable !!!
{
  @Test
  void httpProxyManager_creatingProxy_returnsMockResponse()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.create(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy";
        
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
      }
    }
  }
  
  @Test
  void httpMockContext_makeHttpCallOutsideHttpContext_returnsBadGatewayOrNotFound()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.create(unitTest()))
    {
      final String endpoint = "/dummy";
      
      try(HttpMockContext mock = proxy.mockContext())
      {
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
      }
      
      assertThat(getResponseStatus(proxy.getPort(), endpoint)).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
    }
  }

  @Test
  void httpMockContext_multipleHttpContexts_returnsMockData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.create(unitTest()))
    {
      final String endpoint = "/dummy";
      
      try(HttpMockContext mock = proxy.mockContext())
      {
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
      }
      
      try(HttpMockContext mock = proxy.mockContext())
      {
        mock.when(urlEquals(endpoint)).then(respondWith("Bye from mock !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Bye from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Bye from mock !!!");
      }
    }
  }

  @Test
  void httpProxyManager_passingProxyManually_doesNotAutoCloseProxy()
  {
    final BrowserMobProxyServer httpProxy = new BrowserMobProxyServer();
    httpProxy.start();
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.create(httpProxy, unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy";
        
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
      }
      
      assertThat(httpProxy.isStopped()).as("Is proxy stopped?").isEqualTo(false);
    }
    finally
    {
      httpProxy.stop();
    }

    assertThat(httpProxy.isStopped()).as("Is proxy stopped?").isEqualTo(true);
  }  
}

