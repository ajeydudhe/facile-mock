/********************************************************************
 * File Name:    HttpProxyManager.java
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
import org.expedientframework.facilemock.core.Executor;
import org.expedientframework.facilemock.core.MockDefinitionDelegate;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public interface HttpProxyManager extends BrowserMobProxy, Executor<HttpRequestContext, HttpResponse>, Closeable
{
  public MockDefinitionDelegate<HttpRequestContext, HttpResponse> when(final Condition<HttpRequestContext> condition);
  
  public HttpMockContext mockContext();
  
  @Override
  public void close();
}

