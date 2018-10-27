/********************************************************************
 * File Name:    AbstractTest.java
 *
 * Date Created: Oct 21, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class AbstractTest
{
  @BeforeEach
  public void beforeTest(final TestInfo testInfo)
  {
    LOGGER.info(">>>>>>>>>>>>>>>>>>> Starting test [{}]", testInfo.getDisplayName());
  }
  
  @AfterEach
  public void afterTest(final TestInfo testInfo)
  {
    LOGGER.info("<<<<<<<<<<<<<<<<<<< Done test [{}]", testInfo.getDisplayName());
  }

  protected String getResponseBody(final int port, final String endpoint)
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

  protected List<Map<String, Object>> getUsers(final int port)
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
 
  protected int getResponseStatus(final int port, final String endpoint)
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

  protected CloseableHttpClient getHttpClient(final int port)
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
  private final static Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);
}

