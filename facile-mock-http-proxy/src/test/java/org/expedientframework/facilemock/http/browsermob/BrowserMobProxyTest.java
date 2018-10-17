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
import static org.expedientframework.facilemock.core.MockDefinitionDelegate.*;
import static org.expedientframework.facilemock.http.browsermob.BrowserMobProxyManager.*;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.expedientframework.facilemock.core.TestScope;
import org.junit.jupiter.api.Test;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
class BrowserMobProxyTest
{
  @Test
  void mockAlwaysByDefault()
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.UNIT_TEST);
    mock.start();
    
    final String endpoint = "/endpoint";
    
    mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!"));
    
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    
    mock.stop();
  }
  
  @Test
  void mockOnce()
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.UNIT_TEST);
    mock.start();
    
    final String endpoint = "/endpoint";
    
    mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!")).perform(once());
    
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    
    assertThat(getResponseStatus(mock.getPort(), endpoint)).as("Response").isEqualTo(HttpResponseStatus.NOT_FOUND.code());
    
    mock.stop();
  }

  @Test
  void mockThreeTimes()
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.UNIT_TEST);
    mock.start();
    
    final String endpoint = "/endpoint";
    
    mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!")).perform(times(3));
    
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    
    assertThat(getResponseStatus(mock.getPort(), endpoint)).as("Response").isEqualTo(HttpResponseStatus.NOT_FOUND.code());
    
    mock.stop();
  }

  private String getResponseBody(final int port, final String endpoint)
  {
    try(CloseableHttpClient httpClient = getHttpClient(port))
    {
      try(CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet("http://sample.host.does.not.exists.com" + endpoint)))
      {
        return IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  private int getResponseStatus(final int port, final String endpoint)
  {
    try(CloseableHttpClient httpClient = getHttpClient(port))
    {
      try(CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet("http://sample.host.does.not.exists.com" + endpoint)))
      {
        return httpResponse.getStatusLine().getStatusCode();
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  private CloseableHttpClient getHttpClient(final int port)
  {
    return HttpClientBuilder.create().setProxy(new HttpHost("localhost", port)).build();
  }
}

