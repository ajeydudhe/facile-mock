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
public class UrlMatches extends AbstractBrowserMobCondition
{
  public UrlMatches(final String urlToMatch)
  {
    super(Pattern.compile(urlToMatch)); // Consumer should escape any required character.
  }

  public static class UrlEquals extends UrlMatches
  {
    public UrlEquals(final String urlToMatch)
    {
      super(urlToMatch); // Consumer should escape any required character.
    }
  }  

  public static class UrlStartsWith extends UrlMatches
  {
    public UrlStartsWith(final String urlToMatch)
    {
      super("^" + urlToMatch + ".*"); // Consumer should escape any required character.
    }
  }  

  public static class UrlEndsWith extends UrlMatches
  {
    public UrlEndsWith(final String urlToMatch)
    {
      super("^.*" + urlToMatch); // Consumer should escape any required character.
    }
  }
  
  public static class UrlContains extends UrlMatches
  {
    public UrlContains(final String urlToMatch)
    {
      super(urlToMatch); // Consumer should escape any required character.
    }
  }
}

