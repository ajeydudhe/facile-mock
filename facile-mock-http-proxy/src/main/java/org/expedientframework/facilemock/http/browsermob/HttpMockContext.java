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
import io.netty.handler.codec.http.HttpResponse;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public class HttpMockContext implements Closeable
{
  HttpMockContext(final HttpProxyManager httpProxyManager, final boolean ownsHttpProxyManager)
  {
    this.httpProxyManager = httpProxyManager;
    this.ownsHttpProxyManager = ownsHttpProxyManager;
  }

  public HttpProxyManager getHttpProxyManager()
  {
    return this.httpProxyManager;
  }
  
  public MockDefinitionDelegate<HttpRequestContext, HttpResponse> when(final Condition<HttpRequestContext> condition)
  {
    return this.httpProxyManager.when(condition);
  }
  
  @Override
  public void close()
  {
    this.httpProxyManager.clear();
    if(this.ownsHttpProxyManager)
    {
      this.httpProxyManager.close();
    }
  }
  
  // Private members
  private final HttpProxyManager httpProxyManager;
  private final boolean ownsHttpProxyManager;
}

