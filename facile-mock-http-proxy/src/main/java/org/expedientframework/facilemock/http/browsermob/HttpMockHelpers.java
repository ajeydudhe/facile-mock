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

import org.apache.commons.lang3.NotImplementedException;
import org.expedientframework.facilemock.core.TestScope;
import org.expedientframework.facilemock.http.browsermob.actions.Response;
import org.expedientframework.facilemock.http.browsermob.conditions.UrlMatches.UrlEndsWith;
import org.expedientframework.facilemock.http.browsermob.conditions.UrlMatches.UrlEquals;
import org.expedientframework.facilemock.http.browsermob.conditions.UrlMatches.UrlStartsWith;
import org.expedientframework.facilemock.http.conditions.AbstractHttpCondition;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class HttpMockHelpers
{
  // Http Methods
  public static AbstractHttpCondition<HttpRequestContext> get(final AbstractHttpCondition<HttpRequestContext> condition)
  {
    return setHttpMethod(condition, "GET");
  }

  public static AbstractHttpCondition<HttpRequestContext> post(final AbstractHttpCondition<HttpRequestContext> condition)
  {
    return setHttpMethod(condition, "POST");
  }

  public static AbstractHttpCondition<HttpRequestContext> put(final AbstractHttpCondition<HttpRequestContext> condition)
  {
    return setHttpMethod(condition, "PUT");
  }

  public static AbstractHttpCondition<HttpRequestContext> delete(final AbstractHttpCondition<HttpRequestContext> condition)
  {
    return setHttpMethod(condition, "DELETE");
  }

  public static AbstractHttpCondition<HttpRequestContext> head(final AbstractHttpCondition<HttpRequestContext> condition)
  {
    return setHttpMethod(condition, "HEAD");
  }
  
  public static AbstractHttpCondition<HttpRequestContext> options(final AbstractHttpCondition<HttpRequestContext> condition)
  {
    return setHttpMethod(condition, "OPTIONS");
  }

  public static AbstractHttpCondition<HttpRequestContext> connect(final AbstractHttpCondition<HttpRequestContext> condition)
  {
    //return setHttpMethod(condition, "CONNECT");
    throw new NotImplementedException("Currently not supported since there is no use case to mock the connect request.");
  }

  public static AbstractHttpCondition<HttpRequestContext> trace(final AbstractHttpCondition<HttpRequestContext> condition)
  {
    return setHttpMethod(condition, "TRACE");
  }

  public static AbstractHttpCondition<HttpRequestContext> patch(final AbstractHttpCondition<HttpRequestContext> condition)
  {
    return setHttpMethod(condition, "PATCH");
  }

  // Http Url matching
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

  // Http response
  public static Response respondWith(final Object responseBody)
  {
    return new Response(responseBody);
  }
  
  public static Response respondWith(final HttpResponseStatus status)
  {
    return new Response(status);
  }

  // Test scopes
  public static TestScope integrationTest()
  {
    return TestScope.INTEGRATION_TEST;
  }

  public static TestScope unitTest()
  {
    return TestScope.UNIT_TEST;
  }
  
  // Private
  public static AbstractHttpCondition<HttpRequestContext> setHttpMethod(final AbstractHttpCondition<HttpRequestContext> condition, final String method)
  {
    condition.setHttpMethod(method);
    return condition;
  }
}

