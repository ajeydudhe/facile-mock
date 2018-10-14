/********************************************************************
 * File Name:    AbstractExecutor.java
 *
 * Date Created: Oct 13, 2018
 *
 * ------------------------------------------------------------------
 * 
 * Copyright (c) 2018 ajeydudhe@gmail.com
 *
 *******************************************************************/

package org.expedientframework.facilemock.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.expedientframework.facilemock.common.ArgumentValidator;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public abstract class AbstractExecutor<T, R> implements Executor<T, R>
{
  protected AbstractExecutor(final Scope executionScope)
  {
    this.executionScope = executionScope;
  }
  
  public void addMockDefinition(final MockDefinition<T, R> mockDefinition)
  {
    ArgumentValidator.notNull(mockDefinition, "mockDefinition");
    
    mockDefinition.validate();
    
    this.mockDefinitions.add(mockDefinition);
  }
  
  @Override
  public R execute(final T input)
  {
    final Iterator<MockDefinition<T, R>> iterator = this.mockDefinitions.iterator();
    while(iterator.hasNext())
    {
      final MockDefinition<T, R> mockDefinition = iterator.next();
      
      // We might be adding the MockDefinitions irrespective of the scope and then remove them here otherwise 
      // while adding the MockDefinition we need to first take the scope as input which can make the flow unreadable.
      if(!mockDefinition.getSupportedScopes().contains(this.executionScope)) //TODO: Ajey - Revisit !!! We cannot chain such calls. But are we using MockDefinition as a POJO ??? 
      {
        iterator.remove();
        continue;
      }
      
      if(! mockDefinition.getCondition().evaluate(input) )
      {
        continue;
      }
      
      final R result = mockDefinition.getAction().excute(input);
      
      if(!mockDefinition.getOccuranceTracker().tick())
      {
        iterator.remove();
      }
      
      return result;
    }
    
    return null;
  }
  
  // Protected methods
  
  protected final Scope executionScope;

  protected final List<MockDefinition<T, R>> mockDefinitions = new ArrayList<>();
}

