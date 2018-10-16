/********************************************************************
 * File Name:    AbstractUrlMatches.java
 *
 * Date Created: Oct 16, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.http.conditions;

import org.expedientframework.facilemock.core.Condition;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class AbstractUrlMatches<T> implements Condition<T>
{
  //protected AbstractUrlEquals(final RegEx)
  protected boolean evaluate(final String url)
  {
    return false;
  }
}

