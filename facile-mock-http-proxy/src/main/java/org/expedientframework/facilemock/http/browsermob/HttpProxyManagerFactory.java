/********************************************************************
 * File Name:    HttpProxyManagerFactory.java
 *
 * Date Created: Oct 21, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.expedientframework.facilemock.core.AbstractExecutor;
import org.expedientframework.facilemock.core.Condition;
import org.expedientframework.facilemock.core.Executor;
import org.expedientframework.facilemock.core.MockDefinition;
import org.expedientframework.facilemock.core.MockDefinitionDelegate;
import org.expedientframework.facilemock.core.TestScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class HttpProxyManagerFactory
{
  public static HttpProxyManager create(final TestScope executionScope)
  {
    final BrowserMobProxy httpProxy = new BrowserMobProxyServer();
    
    httpProxy.start();
    
    return create(httpProxy, true, executionScope);
  }
  
  public static HttpProxyManager create(final BrowserMobProxy httpProxy, final TestScope executionScope)
  {
    return create(httpProxy, false, executionScope);
  }

  private static HttpProxyManager create(final BrowserMobProxy httpProxy, final boolean ownsHttpProxy, final TestScope executionScope)
  {
    return (HttpProxyManager) Proxy.newProxyInstance(HttpProxyManagerFactory.class.getClassLoader(), 
                                                     new Class<?>[] {HttpProxyManager.class}, 
                                                     new ProxyInvocationHandler(httpProxy, ownsHttpProxy, executionScope));
  }
  
  private static class ProxyInvocationHandler implements InvocationHandler
  {
    public ProxyInvocationHandler(final BrowserMobProxy httpProxy, final boolean ownsHttpProxy, final TestScope executionScope)
    {
      this.executionScope = executionScope;
      
      this.httpProxy = httpProxy;
      this.ownsHttpProxy = ownsHttpProxy;
      
      this.executor = new AbstractExecutor<HttpRequestContext, HttpResponse>(this.executionScope)
      {
      };
      
      this.httpProxy.addRequestFilter((request, contents, messageInfo) -> {
        
        return this.executor.execute(new HttpRequestContext(request, contents, messageInfo));
      });      
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable
    {
      final Class<?> declaringClass = method.getDeclaringClass();
      LOGGER.info("Invoking proxy method [{}] for interface [{}]", method, declaringClass);
      
      if(declaringClass.equals(BrowserMobProxy.class))
      {
        return method.invoke(this.httpProxy, args);
      }
      
      if(declaringClass.equals(Executor.class))
      {
        return method.invoke(this.executor, args);
      }
      
      if(!declaringClass.equals(HttpProxyManager.class))
      {
        return null;
      }
      
      switch (method.getName())
      {
        case "when":
          return this.when((Condition<HttpRequestContext>) args[0]);

        case "close": 
        {
          LOGGER.info("Stopping the http proxy [{}]", this.ownsHttpProxy);
          if(this.ownsHttpProxy)
          {
            this.httpProxy.stop();
          }
          return null;
        }
          
        case "mockContext":
          return new HttpMockContext((HttpProxyManager) proxy);
      }

      return null;
    }

    private MockDefinitionDelegate<HttpRequestContext, HttpResponse> when(final Condition<HttpRequestContext> condition)
    {
      final MockDefinition<HttpRequestContext, HttpResponse> mockDefinition = new MockDefinition<>(condition, TestScope.UNIT_TEST);
      
      this.executor.addMockDefinition(mockDefinition);
      
      return MockDefinitionDelegate.create(mockDefinition);
    }
    
    // Private members
    private final BrowserMobProxy httpProxy;
    private final boolean ownsHttpProxy;
    private final TestScope executionScope;
    private final Executor<HttpRequestContext, HttpResponse> executor;
    
    private final static Logger LOGGER = LoggerFactory.getLogger(ProxyInvocationHandler.class);
  }
}

