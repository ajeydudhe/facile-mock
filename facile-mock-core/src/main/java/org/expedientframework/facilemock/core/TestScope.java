/********************************************************************
 * File Name:    TestScope.java
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
public interface TestScope
{
  public final static UnitTestScope UNIT_TEST = new UnitTestScope();
  public final static IntegrationTestScope INTEGRATION_TEST = new IntegrationTestScope();
}

