/********************************************************************
 * File Name:    AbstractUrlMatch.java
 *
 * Date Created: Oct 16, 2018
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

import org.expedientframework.facilemock.core.Condition;
import org.expedientframework.facilemock.http.browsermob.HttpRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class AbstractUrlMatch implements Condition<HttpRequestContext>
{
  protected AbstractUrlMatch(final Pattern pattern)
  {
    this.pattern = pattern;
  }
  
  @Override
  public boolean evaluate(final HttpRequestContext request)
  {    
    try
    {
      final URL requestUrl = new URL(request.getMessageInfo().getOriginalUrl());
      
      final boolean result = this.pattern.matcher(requestUrl.getPath()).matches(); //TODO: Ajey - Should be case-sensitive???
      
      LOG.info("Incoming http request url [{}]. Matches ({})[{}]", requestUrl.getPath(), result, this.pattern.toString());
      
      return result;
    }
    catch (MalformedURLException e)
    {
      LOG.error("An error occurred while evaluating url match.", e);
      throw new RuntimeException(e); //TODO: Ajey - Throw custom exception !!!
    }
  }
  
  // Protected members
  protected final Pattern pattern;
  
  private final static Logger LOG = LoggerFactory.getLogger(AbstractUrlMatch.class);
}

