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

package org.expedientframework.facilemock.http.conditions;

import java.net.URL;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class AbstractUrlMatch<T> extends AbstractHttpCondition<T>
{
  protected AbstractUrlMatch(final Pattern pattern)
  {
    super();
    
    this.pattern = pattern;
  }
  
  @Override
  protected boolean doEvaluate(final T request)
  {    
    final URL requestUrl = this.getHttpUrlForRequest(request);
    
    final boolean result = this.pattern.matcher(requestUrl.getPath()).matches(); //TODO: Ajey - Should be case-sensitive???
    
    LOG.info("Incoming http request url [{}]. Matches ({})[{}]", requestUrl.getPath(), result, this.pattern.toString());
    
    return result;
  }
  
  // Protected members
  protected final Pattern pattern;
  
  private final static Logger LOG = LoggerFactory.getLogger(AbstractUrlMatch.class);
}

