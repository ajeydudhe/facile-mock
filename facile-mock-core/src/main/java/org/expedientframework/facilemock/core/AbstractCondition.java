/********************************************************************
 * File Name:    AbstractCondition.java
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
public abstract class AbstractCondition<T> implements Condition<T>
{
  @Override
  public String toString()
  {
    return this.getClass().getSimpleName();
  }
}

