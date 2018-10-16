/********************************************************************
 * File Name:    ArgumentValidator.java
 *
 * Date Created: Oct 13, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.common;

import java.util.Collection;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public final class ArgumentValidator //TODO: Ajey - We should have a separate common module.
{
  public static <T> void notNull(final T argument, final String argumentName)
  {
    if(argument == null)
    {
      throwNotNullException(argumentName);
    }
  }
  
  public static <T> void notEmpty(final Collection<T> argument, final String argumentName)
  {
    if(argument == null || argument.isEmpty())
    {
      throwNotEmptyException(argumentName);
    }
  }
  
  public static <T> void notEmpty(final String argument, final String argumentName)
  {
    if(argument == null || argument.trim().isEmpty())
    {
      throwNotEmptyException(argumentName);
    }
  }

  private static void throwNotNullException(final String argumentName)
  {
    throw new IllegalArgumentException(String.format("@%s cannot be null.", argumentName));
  }

  private static void throwNotEmptyException(final String argumentName)
  {
    throw new IllegalArgumentException(String.format("@%s cannot be null or empty.", argumentName));
  }
}

