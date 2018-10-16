/********************************************************************
 * File Name:    UrlEquals.java
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

import org.expedientframework.facilemock.common.ArgumentValidator;
import org.expedientframework.facilemock.core.Condition;
import org.expedientframework.facilemock.http.browsermob.BrowserMobProxyManager;
import org.expedientframework.facilemock.http.browsermob.BrowserMobProxyManager.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public class UrlEquals implements Condition<BrowserMobProxyManager.RequestContext>
{
  public UrlEquals(final String urlToMatch)
  {
    ArgumentValidator.notEmpty(urlToMatch, "@urlToMatch");
    
    this.urlToMatch = urlToMatch;
  }
  
  @Override
  public boolean evaluate(final RequestContext input)
  {
    try
    {
      final URL requestUrl = new URL(input.getMessageInfo().getOriginalUrl());
      
      final boolean match = this.urlToMatch.equalsIgnoreCase(requestUrl.getPath()); //TODO: Ajey - Should be case-sensitive???
      
      LOGGER.info("Incoming http request url [{}]. Matches ({})[{}]", requestUrl.getPath(), match, this.urlToMatch);
      
      return match;
    }
    catch (MalformedURLException e)
    {
      LOGGER.error("An error occurred while evaluating url match.", e);
      throw new RuntimeException(e); //TODO: Ajey - Throw custom exception !!!
    }
  }
  
  // Private members
  private final String urlToMatch;
  
  private final static Logger LOGGER = LoggerFactory.getLogger(UrlEquals.class);
}

