package com.ca.sample;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class NewTest {
 
@Test(priority = 0)
  public void f() {
	  
	 System.out.println("test1");
  }
  
  @Test(priority = 1)
  public void f1() {
	  
	  System.out.println("test2");
  }
  
  @BeforeTest
  public void beforeMethod() {
	  System.out.println("beforeMethod");
	  
  }

  @AfterTest
  public void afterMethod() {
	  System.out.println("afterMethod");
	  
  }

}
