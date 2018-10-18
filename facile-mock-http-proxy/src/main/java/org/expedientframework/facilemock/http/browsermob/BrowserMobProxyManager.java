/********************************************************************
 * File Name:    BrowserMobHttpProxy.java
 *
 * Date Created: Oct 15, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob;

import org.expedientframework.facilemock.core.AbstractExecutor;
import org.expedientframework.facilemock.core.Condition;
import org.expedientframework.facilemock.core.MockDefinition;
import org.expedientframework.facilemock.core.MockDefinitionDelegate;
import org.expedientframework.facilemock.core.TestScope;
import org.expedientframework.facilemock.http.browsermob.actions.Response;
import org.expedientframework.facilemock.http.browsermob.conditions.UrlEquals;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public class BrowserMobProxyManager extends AbstractExecutor<BrowserMobProxyManager.RequestContext, HttpResponse>
{
  public BrowserMobProxyManager(final TestScope executionScope)
  {
    super(executionScope);
    
    this.httpProxy.addRequestFilter((request, contents, messageInfo) -> {
      
      return this.execute(new RequestContext(request, contents, messageInfo));
    });
  }
  
  public void start()
  {
    this.start(0);
  }
  
  public void start(final int port)
  {
    this.httpProxy.start(port);
  }
  
  public int getPort()
  {
    return this.httpProxy.getPort();
  }
   
  public void stop()
  {
    this.httpProxy.stop();
  }
  
  //TODO: Ajey - Move to another class
  public static UrlEquals urlEquals(final String urlToMatch)
  {
    return new UrlEquals(urlToMatch);
  }
  
  public static Response respondWith(final Object responseBody)
  {
    return new Response(responseBody);
  }
  
  public MockDefinitionDelegate<BrowserMobProxyManager.RequestContext, HttpResponse> when(final Condition<BrowserMobProxyManager.RequestContext> condition)
  {
    final MockDefinition<BrowserMobProxyManager.RequestContext, HttpResponse> mockDefinition = new MockDefinition<>(condition, this.executionScope);
    
    this.addMockDefinition(mockDefinition);
    
    return MockDefinitionDelegate.create(mockDefinition);
  }
  
  public static class RequestContext
  {
    private RequestContext(final HttpRequest request, final HttpMessageContents contents, final HttpMessageInfo messageInfo)
    {
      this.request = request;
      this.contents = contents;
      this.messageInfo = messageInfo;
    }
    
    public HttpRequest getRequest()
    {
      return this.request;
    }
    
    public HttpMessageContents getContents()
    {
      return this.contents;
    }
    
    public HttpMessageInfo getMessageInfo()
    {
      return this.messageInfo;
    }

    // Private members
    private final HttpRequest request;
    private final HttpMessageContents contents;
    private final HttpMessageInfo messageInfo;
  }
  
  // Private members
  private final BrowserMobProxy httpProxy = new BrowserMobProxyServer();
}

