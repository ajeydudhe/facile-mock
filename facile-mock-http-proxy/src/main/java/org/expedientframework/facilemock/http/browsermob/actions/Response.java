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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;

import org.expedientframework.facilemock.core.AbstractAction;
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
public class Response extends AbstractAction<HttpRequestContext, HttpResponse>
{
  public Response(final Object responseBody, final HttpResponseStatus statusCode)
  {
    this.responseBody = responseBody;
    this.statusCode = statusCode;
  }

  public Response(final Object responseBody)
  {
    this(responseBody, HttpResponseStatus.OK);
  }
  
  @Override
  public HttpResponse execute(final HttpRequestContext input)
  {
    if(this.responseBody == null)
    {
      LOGGER.info("Returning [{}] for [{}]", this.statusCode, input.getMessageInfo().getOriginalUrl());
      return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, this.statusCode);
    }
    
    final ByteBuf buffer = getByteBuf();

    final HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buffer);
    
    HttpHeaders.setContentLength(response, buffer.readableBytes());
    
    final String contentType = (this.responseBody instanceof String ? "text/html" : "application/json");
    HttpHeaders.setHeader(response, HttpHeaders.Names.CONTENT_TYPE, contentType);
    
    LOGGER.info("Returning [{}] for [{}] with mock response: {}", this.statusCode, input.getMessageInfo().getOriginalUrl(), this.responseBody);

    return response;
  }

  private ByteBuf getByteBuf()
  {
    try
    {
      if(this.responseBody == null)
      {
        return null;
      }
      
      final String response = (this.responseBody instanceof String ? this.responseBody.toString() : OBJECT_MAPPER.writeValueAsString(this.responseBody));
      return Unpooled.wrappedBuffer(response.getBytes("UTF-8"));
    }
    catch (UnsupportedEncodingException | JsonProcessingException e)
    {
      throw new RuntimeException(e); //TODO: Ajey - Logging !!! Throw custom exception !!!
    }
  }
  
  // Private members
  private final Object responseBody;
  private final HttpResponseStatus statusCode;
  private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private final static Logger LOGGER = LoggerFactory.getLogger(Response.class);
}

