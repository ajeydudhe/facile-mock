/********************************************************************
 * File Name:    HttpMockHelpers.java
 *
 * Date Created: Nov 4, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob;

import org.expedientframework.facilemock.core.TestScope;
import org.expedientframework.facilemock.http.browsermob.actions.Response;
import org.expedientframework.facilemock.http.browsermob.conditions.UrlMatches.UrlEndsWith;
import org.expedientframework.facilemock.http.browsermob.conditions.UrlMatches.UrlEquals;
import org.expedientframework.facilemock.http.browsermob.conditions.UrlMatches.UrlStartsWith;
import org.expedientframework.facilemock.http.conditions.AbstractHttpCondition;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class HttpMockHelpers
{
  public static AbstractHttpCondition<HttpRequestContext> post(final AbstractHttpCondition<HttpRequestContext> condition)
  {
    condition.setHttpMethod("POST");
    return condition;
  }

  public static UrlEquals urlEquals(final String urlToMatch)
  {
    return new UrlEquals(urlToMatch);
  }
  
  public static UrlStartsWith urlStartsWith(final String urlToMatch)
  {
    return new UrlStartsWith(urlToMatch);
  }

  public static UrlEndsWith urlEndsWith(final String urlToMatch)
  {
    return new UrlEndsWith(urlToMatch);
  }

  public static Response respondWith(final Object responseBody)
  {
    return new Response(responseBody);
  }
  
  public static TestScope integrationTest()
  {
    return TestScope.INTEGRATION_TEST;
  }

  public static TestScope unitTest()
  {
    return TestScope.UNIT_TEST;
  }
}

