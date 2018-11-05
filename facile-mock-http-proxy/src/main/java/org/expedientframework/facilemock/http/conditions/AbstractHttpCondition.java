/********************************************************************
 * File Name:    AbstractHttpCondition.java
 *
 * Date Created: Oct 28, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.conditions;

import java.net.URL;

import org.expedientframework.facilemock.core.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class AbstractHttpCondition<T> implements Condition<T>
{
  protected AbstractHttpCondition(final String expectedHttpMethod)
  {
    this.expectedHttpMethod = expectedHttpMethod;
  }
  
  protected AbstractHttpCondition()
  {
    this("GET");
  }

  @Override
  public boolean evaluate(T request)
  {
    final String requestHttpMethod = this.getHttpMethodForRequest(request);
    LOG.info("Evaluating http request with http method [{}] and URL [{}].", requestHttpMethod, this.getHttpUrlForRequest(request));
    
    if(! this.expectedHttpMethod.equalsIgnoreCase(requestHttpMethod))
    {
      LOG.info("Expected http method [{}] is not equal to current http method [{}].", this.expectedHttpMethod, requestHttpMethod);
      return false;
    }
    
    return this.doEvaluate(request);
  }
  
  public void setHttpMethod(final String method)
  {
    this.expectedHttpMethod = method;
  }
  
  protected abstract String getHttpMethodForRequest(final T request);
  protected abstract URL getHttpUrlForRequest(final T request);
  
  protected abstract boolean doEvaluate(T request);
  
  // Protected members
  protected String expectedHttpMethod;
  
  private final static Logger LOG = LoggerFactory.getLogger(AbstractHttpCondition.class);
}

