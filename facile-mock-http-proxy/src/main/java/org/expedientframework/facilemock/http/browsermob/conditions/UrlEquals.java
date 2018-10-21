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

import java.util.regex.Pattern;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public class UrlEquals extends AbstractUrlMatch
{
  public UrlEquals(final String urlToMatch)
  {
    super(Pattern.compile(urlToMatch)); //TODO: Ajey - There might be some chars to be escaped !!!
  }
}

