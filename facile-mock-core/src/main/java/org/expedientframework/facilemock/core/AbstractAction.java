/********************************************************************
 * File Name:    AbstractAction.java
 *
 * Date Created: Oct 18, 2018
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
public abstract class AbstractAction<T, R> implements Action<T, R>
{
  @Override
  public String toString()
  {
    return this.getClass().getSimpleName();
  }
}

