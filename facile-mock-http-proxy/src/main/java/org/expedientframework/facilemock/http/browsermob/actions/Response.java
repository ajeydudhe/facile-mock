/********************************************************************
 * File Name:    Response.java
 *
 * Date Created: Oct 16, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob.actions;

import java.util.Map.Entry;

import org.expedientframework.facilemock.http.actions.AbstractHttpResponseAction;
import org.expedientframework.facilemock.http.browsermob.HttpRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public class Response extends AbstractHttpResponseAction<HttpRequestContext, HttpResponse>
{
  public Response(final Object responseBody, final HttpResponseStatus statusCode)
  {
    super(responseBody, statusCode.code());
  }

  public Response(final Object responseBody)
  {
    this(responseBody, HttpResponseStatus.OK);
  }

  public Response(final HttpResponseStatus statusCode)
  {
    this(null, statusCode);
  }
  
  @Override
  public HttpResponse execute(final HttpRequestContext input)
  {
    if(this.responseBody == null)
    {
      LOGGER.info("Returning [{}] for [{}]", this.statusCode, input.getMessageInfo().getOriginalUrl());
      return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, this.getHttpStatus());
    }
    
    final ByteBuf buffer = Unpooled.wrappedBuffer(this.responseBody);

    final HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, this.getHttpStatus(), buffer);
    
    setHeaders(response);
    
    LOGGER.info("Returning [{}] for [{}] with mock response: {}", this.getHttpStatus(), input.getMessageInfo().getOriginalUrl(), this.responseBody);

    return response;
  }

  private HttpResponseStatus getHttpStatus()
  {
    return HttpResponseStatus.valueOf(this.statusCode);
  }
  
  private void setHeaders(final HttpResponse response)
  {
    for (Entry<String, Object> header : this.headers.entrySet())
    {
      HttpHeaders.addHeader(response, header.getKey(), header.getValue());
    }
  }

  // Private members
  private final static Logger LOGGER = LoggerFactory.getLogger(Response.class);
}

