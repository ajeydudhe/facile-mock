/********************************************************************
 * File Name:    MockDefinition.java
 *
 * Date Created: Oct 13, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.expedientframework.facilemock.common.ArgumentValidator;
import org.expedientframework.facilemock.core.Occurs.OccuranceTracker;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public class MockDefinition<T, R>
{
  public MockDefinition(final Condition<T> condition, final TestScope...supportedScopes)
  {
    this.condition = condition;
    if(supportedScopes != null)
    {
      for (TestScope scope : supportedScopes)
      {
        this.supportedScopes.add(scope);
      }
    }
  }
  
  public void validate()
  {
    ArgumentValidator.notNull(this.condition, "condition");
    ArgumentValidator.notNull(this.action, "action");
    ArgumentValidator.notNull(this.occuranceTracker, "occuranceTracker");
    ArgumentValidator.notEmpty(this.supportedScopes, "supportedScopes");
  }
      
  @Override
  public String toString()
  {
    return String.format("Condition '%s' : Action '%s' : Occurance '%s' : Scopes %s", this.condition, this.action, this.occuranceTracker, this.supportedScopes);
  }
  
  // Setters
  
  public void setCondition(final Condition<T> condition)
  {
    this.condition = condition;
  }
  
  public void setAction(final Action<T, R> action)
  {
    this.action = action;
  }
  
  public void setOccurs(final OccuranceTracker occuranceTracker)
  {
    this.occuranceTracker = occuranceTracker;
  }
  
  public void addScope(final TestScope scope)
  {
    this.supportedScopes.add(scope);
  }
  
  // Getters
  
  public Condition<T> getCondition()
  {
    return this.condition;
  }
  
  public Action<T, R> getAction()
  {
    return this.action;
  }
  
  public OccuranceTracker getOccuranceTracker()
  {
    return this.occuranceTracker;
  }
  
  public Set<TestScope> getSupportedScopes()
  {
    return Collections.unmodifiableSet(this.supportedScopes);
  }
  
  // Protected members
  protected Condition<T> condition;
  protected Action<T, R> action;
  protected OccuranceTracker occuranceTracker = Occurs.ALWAYS.getOccuranceTracker();
  protected Set<TestScope> supportedScopes = new HashSet<>();
}

