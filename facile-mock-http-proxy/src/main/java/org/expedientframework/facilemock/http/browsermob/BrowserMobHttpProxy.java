/********************************************************************
 * File Name:    BrowserMobHttpProxy.java
 *
 * Date Created: Oct 16, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.browsermob;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.littleshoot.proxy.HttpFiltersSource;
import org.littleshoot.proxy.MitmManager;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.mitm.TrustSource;
import net.lightbody.bmp.proxy.BlacklistEntry;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.proxy.auth.AuthType;
import net.lightbody.bmp.proxy.dns.AdvancedHostResolver;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public class BrowserMobHttpProxy implements BrowserMobProxy
{

  @Override
  public void start()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void start(int port)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void start(int port, InetAddress bindAddress)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void start(int port, InetAddress clientBindAddress, InetAddress serverBindAddress)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isStarted()
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void stop()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void abort()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public InetAddress getClientBindAddress()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getPort()
  {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public InetAddress getServerBindAddress()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Har getHar()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Har newHar()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Har newHar(String initialPageRef)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Har newHar(String initialPageRef, String initialPageTitle)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setHarCaptureTypes(Set<CaptureType> captureTypes)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setHarCaptureTypes(CaptureType... captureTypes)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public EnumSet<CaptureType> getHarCaptureTypes()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void enableHarCaptureTypes(Set<CaptureType> captureTypes)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void enableHarCaptureTypes(CaptureType... captureTypes)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void disableHarCaptureTypes(Set<CaptureType> captureTypes)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void disableHarCaptureTypes(CaptureType... captureTypes)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Har newPage()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Har newPage(String pageRef)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Har newPage(String pageRef, String pageTitle)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Har endHar()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setReadBandwidthLimit(long bytesPerSecond)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public long getReadBandwidthLimit()
  {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setWriteBandwidthLimit(long bytesPerSecond)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public long getWriteBandwidthLimit()
  {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setLatency(long latency, TimeUnit timeUnit)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setConnectTimeout(int connectionTimeout, TimeUnit timeUnit)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setIdleConnectionTimeout(int idleConnectionTimeout, TimeUnit timeUnit)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setRequestTimeout(int requestTimeout, TimeUnit timeUnit)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void autoAuthorization(String domain, String username, String password, AuthType authType)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void stopAutoAuthorization(String domain)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void chainedProxyAuthorization(String username, String password, AuthType authType)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void rewriteUrl(String urlPattern, String replacementExpression)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void rewriteUrls(Map<String, String> rewriteRules)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Map<String, String> getRewriteRules()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void removeRewriteRule(String urlPattern)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void clearRewriteRules()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void blacklistRequests(String urlPattern, int statusCode)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void blacklistRequests(String urlPattern, int statusCode, String httpMethodPattern)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setBlacklist(Collection<BlacklistEntry> blacklist)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Collection<BlacklistEntry> getBlacklist()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void clearBlacklist()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void whitelistRequests(Collection<String> urlPatterns, int statusCode)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addWhitelistPattern(String urlPattern)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void enableEmptyWhitelist(int statusCode)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void disableWhitelist()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Collection<String> getWhitelistUrls()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getWhitelistStatusCode()
  {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isWhitelistEnabled()
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void addHeaders(Map<String, String> headers)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addHeader(String name, String value)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeHeader(String name)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeAllHeaders()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Map<String, String> getAllHeaders()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setHostNameResolver(AdvancedHostResolver resolver)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public AdvancedHostResolver getHostNameResolver()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean waitForQuiescence(long quietPeriod, long timeout, TimeUnit timeUnit)
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setChainedProxy(InetSocketAddress chainedProxyAddress)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public InetSocketAddress getChainedProxy()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addFirstHttpFilterFactory(HttpFiltersSource filterFactory)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addLastHttpFilterFactory(HttpFiltersSource filterFactory)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addResponseFilter(ResponseFilter filter)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addRequestFilter(RequestFilter filter)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setMitmDisabled(boolean mitmDisabled)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setMitmManager(MitmManager mitmManager)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setTrustAllServers(boolean trustAllServers)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setTrustSource(TrustSource trustSource)
  {
    // TODO Auto-generated method stub
    
  }

}

