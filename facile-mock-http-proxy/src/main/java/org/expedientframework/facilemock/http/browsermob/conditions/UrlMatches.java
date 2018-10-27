/********************************************************************
 * File Name:    UrlMatches.java
 *
 * Date Created: Oct 27, 2018
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
public class UrlMatches extends AbstractUrlMatch
{
  public UrlMatches(final String urlToMatch)
  {
    super(Pattern.compile(urlToMatch)); //TODO: Ajey - There might be some chars to be escaped !!!
  }

  public static class UrlEquals extends UrlMatches
  {
    public UrlEquals(final String urlToMatch)
    {
      super(urlToMatch); //TODO: Ajey - There might be some chars to be escaped !!!
    }
  }  

  public static class UrlStartsWith extends UrlMatches
  {
    public UrlStartsWith(final String urlToMatch)
    {
      super(urlToMatch); //TODO: Ajey - There might be some chars to be escaped !!!
    }
  }  

  public static class UrlEndsWith extends UrlMatches
  {
    public UrlEndsWith(final String urlToMatch)
    {
      super(urlToMatch); //TODO: Ajey - There might be some chars to be escaped !!!
    }
  }
  
  public static class UrlContains extends UrlMatches
  {
    public UrlContains(final String urlToMatch)
    {
      super(urlToMatch); //TODO: Ajey - There might be some chars to be escaped !!!
    }
  }
}

