/********************************************************************
 * File Name:    HttpRequestContext.java
 *
 * Date Created: Oct 21, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob;

import io.netty.handler.codec.http.HttpRequest;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public class HttpRequestContext
{
  public HttpRequestContext(final HttpRequest request, final HttpMessageContents contents, final HttpMessageInfo messageInfo)
  {
    this.request = request;
    this.contents = contents;
    this.messageInfo = messageInfo;
  }
  
  public HttpRequest getRequest()
  {
    return this.request;
  }
  
  public HttpMessageContents getContents()
  {
    return this.contents;
  }
  
  public HttpMessageInfo getMessageInfo()
  {
    return this.messageInfo;
  }

  // Private members
  private final HttpRequest request;
  private final HttpMessageContents contents;
  private final HttpMessageInfo messageInfo;
}
