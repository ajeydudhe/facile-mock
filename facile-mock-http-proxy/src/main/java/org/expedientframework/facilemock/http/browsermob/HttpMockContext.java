/********************************************************************
 * File Name:    HttpMockContext.java
 *
 * Date Created: Oct 21, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob;

import java.io.Closeable;

import org.expedientframework.facilemock.core.Condition;
import org.expedientframework.facilemock.core.MockDefinitionDelegate;
import org.expedientframework.facilemock.http.browsermob.actions.Response;
import org.expedientframework.facilemock.http.browsermob.conditions.UrlEquals;

import io.netty.handler.codec.http.HttpResponse;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public class HttpMockContext implements Closeable
{
  public HttpMockContext(final HttpProxyManager httpProxyManager)
  {
    this.httpProxyManager = httpProxyManager;
  }

  public MockDefinitionDelegate<HttpRequestContext, HttpResponse> when(final Condition<HttpRequestContext> condition)
  {
    return this.httpProxyManager.when(condition);
  }
  
  @Override
  public void close()
  {
    this.httpProxyManager.clear();
  }
  
  public static UrlEquals urlEquals(final String urlToMatch)
  {
    return new UrlEquals(urlToMatch);
  }
  
  public static Response respondWith(final Object responseBody)
  {
    return new Response(responseBody);
  }
  
  // Private members
  private final HttpProxyManager httpProxyManager;
}

