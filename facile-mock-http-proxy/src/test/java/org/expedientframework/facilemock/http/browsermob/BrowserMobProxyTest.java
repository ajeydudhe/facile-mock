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
import static org.expedientframework.facilemock.http.browsermob.HttpMockHelpers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
class BrowserMobProxyTest extends AbstractTest //TODO: Ajey - Name the tests as per conventions and make more readable !!!
{
  @Test
  void mock_alwaysByDefault_returnsMockData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
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
  void mock_once_returnsMockDataOnceAndThenBadGatewayOrNotFoundError()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy";
        
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!")).and.perform(once());
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        
        assertThat(getResponseStatus(proxy.getPort(), endpoint)).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }    
  }

  @Test
  void mock_threeTimes_returnsMockDataThriceAndThenBadGatewayOrNotFoundError()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy";
        
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!")).and.perform(times(3));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        
        assertThat(getResponseStatus(proxy.getPort(), endpoint)).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }

  @Test
  void mock_multipleResponses_returnsMockDataAndThenBadGatewayOrNotFoundError()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy_01";
        
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock dummy 01 !!!")).perform(once());
        mock.when(urlEquals("/dummy_02")).then(respondWith("Hello from mock dummy 02 !!!")).perform(once());
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock dummy 03 !!!")).perform(once());
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock dummy 01 !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy_02")).as("Response").isEqualTo("Hello from mock dummy 02 !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock dummy 03 !!!");
        
        assertThat(getResponseStatus(proxy.getPort(), endpoint)).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }

  @Test
  void mock_usingIntegrationTestScope_returnsBadGatewayOrNotFoundError()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(integrationTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy_01";
        
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock dummy 01 !!!")).perform(once());

        assertThat(getResponseStatus(proxy.getPort(), endpoint)).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }

  @Test
  void mock_usingIntegrationTestScope_makesHttpCallAndReturnsRealData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(integrationTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/users";
        
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock dummy 01 !!!")).perform(once());

        assertThat(getUsers(proxy.getPort())).as("Response").isNotEmpty();
      }     
    }
  }
 
  @Test
  void mock_usingIntegrationTestScopeWithAlsoMockForIntegrationTestFlagSet_returnsMockData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(integrationTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/users";
        
        final Map<String, Object> user = new HashMap<>();
        user.put("name", "dummy_name");
        user.put("age", 36);
        
        final List<Map<String, Object>> users = new ArrayList<>();
        users.add(user);
        
        mock.when(urlEquals(endpoint)).then(respondWith(users)).and.perform(once()).and.alsoMockFor(integrationTest());

        final List<Map<String, Object>> returnedUsers = getUsers(proxy.getPort());
        assertThat(returnedUsers).as("Response").hasSize(1);

        //user.put("dummy_field", "dummy_value");    
        assertThat(returnedUsers).as("Response").contains(user);
      }     
    }
  }
}

