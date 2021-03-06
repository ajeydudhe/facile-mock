/********************************************************************
 * File Name:    Executor.java
 *
 * Date Created: Oct 13, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.core;
  
/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public interface Executor<T, R>
{
  public void addMockDefinition(final MockDefinition<T, R> mockDefinition);
  public R execute(final T input);
  public void clear();
}

