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
import static org.expedientframework.facilemock.http.browsermob.BrowserMobProxyManager.*;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.expedientframework.facilemock.core.TestScope;
import org.junit.jupiter.api.Test;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
class BrowserMobProxyTest
{
  @Test
  void test() throws IOException
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.UNIT_TEST);
    mock.start();
    
    mock.when(urlEquals("/post")).then(respondWith("Hello from mock !!!"));
    
    try(CloseableHttpClient httpClient = getHttpClient(mock.getPort()))
    {
      try(CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet("http://sample.host.does.not.exists.com/post")))
      {
        assertThat(IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8")).as("Response").isEqualTo("Hello from mock !!!");
      }      
    }
  }
  
  private CloseableHttpClient getHttpClient(final int port)
  {
    return HttpClientBuilder.create().setProxy(new HttpHost("localhost", port)).build();
  }
}

