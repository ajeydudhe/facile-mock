/********************************************************************
 * File Name:    MockDefinitionDelegate.java
 *
 * Date Created: Oct 17, 2018
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
public class MockDefinitionDelegate<T, R>
{
  public MockDefinitionDelegate(final MockDefinition<T, R> mockDefinition)
  {
    this.mockDefinition = mockDefinition;
  }
  
  public static <T, R> MockDefinitionDelegate<T, R> create(final MockDefinition<T, R> mockDefinition)
  {
    return new MockDefinitionDelegate<>(mockDefinition);
  }
  
  public MockDefinitionDelegate<T, R> then(final Action<T, R> action)
  {
    this.mockDefinition.setAction(action);
    
    return this;
  }
  
  public MockDefinitionDelegate<T, R> perform(final Occurs occurs)
  {
    this.mockDefinition.setOccurs(occurs.getOccuranceTracker());
    
    return this;
  }
  
  public static Occurs once()
  {
    return Occurs.ONCE;
  }
  
  public static Occurs always()
  {
    return Occurs.ALWAYS;
  }
  
  public static Occurs times(final int count)
  {
    return new Occurs(count);
  }
  
  // Private members
  private final MockDefinition<T, R> mockDefinition;
}

