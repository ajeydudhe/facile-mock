/********************************************************************
 * File Name:    AbstractBrowserMobCondition.java
 *
 * Date Created: Nov 5, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob.conditions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import org.expedientframework.facilemock.http.browsermob.HttpRequestContext;
import org.expedientframework.facilemock.http.conditions.AbstractUrlMatch;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class AbstractBrowserMobCondition extends AbstractUrlMatch<HttpRequestContext>
{
  protected AbstractBrowserMobCondition(final Pattern pattern)
  {
    super(pattern);
  }

  @Override
  protected String getHttpMethodForRequest(final HttpRequestContext request)
  {
    return request.getRequest().getMethod().name();
  }

  @Override
  protected URL getHttpUrlForRequest(final HttpRequestContext request)
  {
    try
    {
      return new URL(request.getMessageInfo().getOriginalUrl());
    }
    catch (MalformedURLException e)
    {
      throw new RuntimeException(e); //TODO: Ajey - Throw custom exception !!!
    }
  }
}

