/********************************************************************
 * File Name:    Occurs.java
 *
 * Date Created: Oct 14, 2018
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
public class Occurs
{
  public Occurs(final int count)
  {
    this.count = count;
  }
  
  public OccuranceTracker getOccuranceTracker()
  {
    return new OccuranceTracker();
  }

  public static final Occurs ONCE = new Occurs(1);
  
  public static final Occurs ALWAYS = new Occurs(-1);
  
  // Private members
  private final int count;
  
  public class OccuranceTracker
  {
    private OccuranceTracker()
    {
      this.count = Occurs.this.count;
    }
    
    /**
     * Called to indicate that we have executed the action. Used to keep track of occurrences.
     * 
     * @return True if we should continue calling the action. False if we should no more call the action.
     */
    public boolean tick()
    {
      return this.decrement() > 0;
    }
    
    protected int decrement()
    {
      if(count > 0)
      {
        return --count;
      }
      
      return count;
    }
    
    // Private members
    private int count;
  }  
}

