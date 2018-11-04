/********************************************************************
 * File Name:    UrlMatchConditionTest.java
 *
 * Date Created: Oct 27, 2018
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

import org.junit.jupiter.api.Test;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
class UrlMatchConditionTest extends AbstractTest
{
  @Test
  void urlEquals_singleLevelPath_returnsMockData()
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
  void urlEquals_singleLevelPathButInputHasPathInBetween_returnsBadGatewayOrNotFound()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy";
        
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock !!!");
        assertThat(getResponseStatus(proxy.getPort(), "/abc/dummy/xyz")).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }

  @Test
  void urlEquals_twoLevelPaths_returnsMockData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy1/dummy2";
        
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock dummy !!!")).perform(once());
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock dummy !!!");
        assertThat(getResponseStatus(proxy.getPort(), endpoint)).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }

  @Test
  void urlEquals_singleAndMultipleLevelPaths_returnsMockData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint1 = "/dummy1";
        final String endpoint2 = "/dummy1/dummy2";
        final String endpoint3 = "/dummy1/dummy2/dummy3";
        
        mock.when(urlEquals(endpoint1)).then(respondWith("Hello from mock dummy 1 !!!")).perform(once());
        mock.when(urlEquals(endpoint2)).then(respondWith("Hello from mock dummy 2 !!!"));
        mock.when(urlEquals(endpoint3)).then(respondWith("Hello from mock dummy 3 !!!")).perform(once());
        
        assertThat(getResponseBody(proxy.getPort(), endpoint1)).as("Response").isEqualTo("Hello from mock dummy 1 !!!");
        assertThat(getResponseStatus(proxy.getPort(), endpoint1)).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());

        assertThat(getResponseBody(proxy.getPort(), endpoint2)).as("Response").isEqualTo("Hello from mock dummy 2 !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint2)).as("Response").isEqualTo("Hello from mock dummy 2 !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint2)).as("Response").isEqualTo("Hello from mock dummy 2 !!!");

        assertThat(getResponseBody(proxy.getPort(), endpoint3)).as("Response").isEqualTo("Hello from mock dummy 3 !!!");
        assertThat(getResponseStatus(proxy.getPort(), endpoint3)).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy1/dummy2";
        
        mock.when(urlEquals(endpoint)).then(respondWith("Hello from mock dummy 2 !!!")).perform(once());
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock dummy 2 !!!");
        assertThat(getResponseStatus(proxy.getPort(), endpoint)).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }
  
  @Test
  void urlStartsWith_singleLevelPath_returnsMockData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy";
        
        mock.when(urlStartsWith(endpoint)).then(respondWith("Hello from mock starts with !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock starts with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy/dummy2")).as("Response").isEqualTo("Hello from mock starts with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy/dummy23")).as("Response").isEqualTo("Hello from mock starts with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy/dummy2/dummy3")).as("Response").isEqualTo("Hello from mock starts with !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock starts with !!!");
        assertThat(getResponseStatus(proxy.getPort(), "/abc/dummy2/dummy3")).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }  

  @Test
  void urlStartsWith_twoLevelPath_returnsMockData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy1/dummy2";
        
        mock.when(urlStartsWith(endpoint)).then(respondWith("Hello from mock starts with !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock starts with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy1/dummy2345")).as("Response").isEqualTo("Hello from mock starts with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy1/dummy2/dummy3")).as("Response").isEqualTo("Hello from mock starts with !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock starts with !!!");
        assertThat(getResponseStatus(proxy.getPort(), "/abc/dummy1/dummy2")).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }  

  @Test
  void urlEndsWith_singleLevelPath_returnsMockData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy";
        
        mock.when(urlEndsWith(endpoint)).then(respondWith("Hello from mock ends with !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock ends with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/abc/dummy")).as("Response").isEqualTo("Hello from mock ends with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy/dummy/dummy")).as("Response").isEqualTo("Hello from mock ends with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy1/dummy2/dummy")).as("Response").isEqualTo("Hello from mock ends with !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock ends with !!!");
        assertThat(getResponseStatus(proxy.getPort(), "/abc/dummy2/dummy/dummy2")).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }  

  @Test
  void urlEndsWith_simpleWord_returnsMockData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "blah";
        
        mock.when(urlEndsWith(endpoint)).then(respondWith("Hello from mock ends with blah !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), "/abc/dummyblah")).as("Response").isEqualTo("Hello from mock ends with blah !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy/dummy/dummy/blah")).as("Response").isEqualTo("Hello from mock ends with blah !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy1/dummy2/dummyblah")).as("Response").isEqualTo("Hello from mock ends with blah !!!");
        assertThat(getResponseStatus(proxy.getPort(), "/abc/dummy2/dummy/dummy2blah2")).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }  

  @Test
  void urlContains_singleLevelPath_returnsMockData()
  {
    try(final HttpProxyManager proxy = HttpProxyManagerFactory.createHttpProxy(unitTest()))
    {
      try(HttpMockContext mock = proxy.mockContext())
      {
        final String endpoint = "/dummy";
        
        mock.when(urlEndsWith(endpoint)).then(respondWith("Hello from mock ends with !!!"));
        
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock ends with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/abc/dummy")).as("Response").isEqualTo("Hello from mock ends with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy/dummy/dummy")).as("Response").isEqualTo("Hello from mock ends with !!!");
        assertThat(getResponseBody(proxy.getPort(), "/dummy/dummy2/dummy")).as("Response").isEqualTo("Hello from mock ends with !!!");
        assertThat(getResponseBody(proxy.getPort(), endpoint)).as("Response").isEqualTo("Hello from mock ends with !!!");
        assertThat(getResponseStatus(proxy.getPort(), "/abc/dummy2/dummy/dummy2")).as("Response").isIn(HttpResponseStatus.BAD_GATEWAY.code(), HttpResponseStatus.NOT_FOUND.code());
      }     
    }
  }  
}

