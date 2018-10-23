/********************************************************************
 * File Name:    AbstractHttpResponseAction.java
 *
 * Date Created: Oct 22, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

import org.expedientframework.facilemock.core.AbstractAction;

//import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.http.HttpHeaders;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class AbstractHttpResponseAction<T, R> extends AbstractAction<T, R>
{
  protected AbstractHttpResponseAction(final Object responseBody, final int statusCode)
  {
    this.responseBody = getResponseBody(responseBody);
    this.statusCode = statusCode;
    
    if(responseBody == null)
    {
      return;
    }
    
    this.addHeader(HttpHeaders.Names.CONTENT_LENGTH, this.responseBody.length);
    this.addHeader(HttpHeaders.Names.CONTENT_TYPE, (responseBody instanceof String ? "text/html" : HttpHeaders.Values.APPLICATION_JSON)); // This is default. We can set the headers explicitly.
  }
  
  public void addHeader(final String name, final Object value)
  {
    this.headers.put(name, value);
  }
  
  //TODO: Ajey - Add logic for passing cookies
  public void addCookie(final String name, final String value)
  {
    this.cookies.put(name, value);
  }
  
  protected void appendDefaultHeaders()
  {
    
  }
  
  private static byte[] getResponseBody(final Object responseBody)
  {
    if(responseBody == null)
    {
      return null;
    }
  
    try
    {
      final String response = (responseBody instanceof String ? responseBody.toString() : OBJECT_MAPPER.writeValueAsString(responseBody));
      
      return response.getBytes("UTF-8");
    }
    catch (JsonProcessingException | UnsupportedEncodingException e)
    {
      LOG.error("An error occurred while converting response body to byte array.", e);
      throw new RuntimeException(e); //TODO: Ajey - Throw custom exception !!!
    }
  }

  // Protected members
  protected final byte[] responseBody;
  protected final int statusCode;
  protected final Map<String, Object> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
  protected final Map<String, String> cookies = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
  
  protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  protected final static Logger LOG = LoggerFactory.getLogger(AbstractHttpResponseAction.class);
}

