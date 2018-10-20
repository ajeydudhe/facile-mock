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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.expedientframework.facilemock.core.TestScope;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
class BrowserMobProxyTest
{
  @Test
  void test()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.create(TestScope.UNIT_TEST))
    {
      proxy.start();
      
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
  void mockAlwaysByDefault_returnsMockData()
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.UNIT_TEST);
    mock.start();
    
    final String endpoint = "/dummy";
    
    mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!"));
    
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    
    mock.stop();
  }
  
  @Test
  void mockOnce_returnsMockData()
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.UNIT_TEST);
    mock.start();
    
    final String endpoint = "/dummy";
    
    mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!")).perform(once());
    
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    
    assertThat(getResponseStatus(mock.getPort(), endpoint)).as("Response").isEqualTo(HttpResponseStatus.NOT_FOUND.code());
    
    mock.stop();
  }

  @Test
  void mockThreeTimes_returnsMockData()
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.UNIT_TEST);
    mock.start();
    
    final String endpoint = "/dummy";
    
    mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!")).perform(times(3));
    
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
    
    assertThat(getResponseStatus(mock.getPort(), endpoint)).as("Response").isEqualTo(HttpResponseStatus.NOT_FOUND.code());
    
    mock.stop();
  }

  @Test
  void mockMultipleResponses_returnsMockDataAndHttpStatusCode()
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.UNIT_TEST);
    mock.start();
    
    final String endpoint = "/dummy_01";
    
    mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock dummy 01 !!!")).perform(once());
    mock.when(urlEquals("/dummy_02")).then(respondWith("Hello from mock dummy 02 !!!")).perform(once());
    mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock dummy 03 !!!")).perform(once());
    
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock dummy 01 !!!");
    assertThat(getResponseBody(mock.getPort(), "/dummy_02")).as("Response").isEqualTo("Hello from mock dummy 02 !!!");
    assertThat(getResponseBody(mock.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock dummy 03 !!!");
    
    assertThat(getResponseStatus(mock.getPort(), endpoint)).as("Response").isEqualTo(HttpResponseStatus.NOT_FOUND.code());
    
    mock.stop();
  }

  @Test
  void mockUsingIntegrationTestScope_returnsNotFound()
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.INTEGRATION_TEST);
    mock.start();
    
    final String endpoint = "/dummy_01";
    
    mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock dummy 01 !!!")).perform(once());

    assertThat(getResponseStatus(mock.getPort(), endpoint)).as("Response").isEqualTo(HttpResponseStatus.NOT_FOUND.code());
    
    mock.stop();
  }

  @Test
  void mockUsingIntegrationTestScope_returnsRealData()
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.INTEGRATION_TEST);
    mock.start();
    
    final String endpoint = "/dummy_01";
    
    mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock dummy 01 !!!")).perform(once());

    assertThat(getUsers(mock.getPort())).as("Response").isNotEmpty();
    
    mock.stop();
  }
 
  @Test
  void mockUsingUnitTestScope_returnsMockData()
  {
    final BrowserMobProxyManager mock = new BrowserMobProxyManager(TestScope.INTEGRATION_TEST);
    mock.start();
    
    final String endpoint = "/users";
    
    final Map<String, Object> user = new HashMap<>();
    user.put("name", "dummy_name");
    user.put("age", 36);
    
    final List<Map<String, Object>> users = new ArrayList<>();
    users.add(user);
    
    mock.when(urlEquals(endpoint)).then(respondWith(users)).perform(once()).scope(TestScope.INTEGRATION_TEST);

    final List<Map<String, Object>> returnedUsers = getUsers(mock.getPort());
    assertThat(returnedUsers).as("Response").hasSize(1);

    //user.put("dummy_field", "dummy_value");    
    assertThat(returnedUsers).as("Response").contains(user);
    
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

  private List<Map<String, Object>> getUsers(final int port)
  {
    try(CloseableHttpClient httpClient = getHttpClient(port))
    {
      try(CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet("https://jsonplaceholder.typicode.com/users")))
      {
        final String json = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
        
        LOGGER.info(json);
        
        return OBJECT_MAPPER.readValue(json, new TypeReference<List<Map<String, Object>>>() {
          
        });
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
    try
    {
      final SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
      sslContextBuilder.loadTrustMaterial(new TrustStrategy()
      {      
        @Override
        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException
        {
          return true; // Trust for test but not in production !!!
        }
      });

      return HttpClientBuilder.create().setProxy(new HttpHost("localhost", port)).setSSLContext(sslContextBuilder.build()).build();
    }
    catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e)
    {
      throw new RuntimeException(e); // Should log and throw custom exception !!!
    }
  }
  
  private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private final static Logger LOGGER = LoggerFactory.getLogger(BrowserMobProxyTest.class);
}

