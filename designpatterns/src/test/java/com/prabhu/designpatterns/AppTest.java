package com.prabhu.designpatterns;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.prabhu.designpatterns.abstractFactoryPattern.AbstractFactoryTest;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class AppTest 
    extends TestCase
{

	@Mock AbstractFactoryTest test;
	
	@Spy AbstractFactoryTest test1;
	
	@Test
	public void test(){
		when(test.printWorld()).thenReturn("Inside Stub");
		assertEquals("Inside Stub", test.printWorld());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testVoid(){
		doThrow(new IllegalArgumentException()).when(test).print();
		test.print();
	}
	
	@Test
	public void testsometingVoid(){
		doCallRealMethod().when(test).print();
		test.print();
	}
	
	@Test
	public void testSpyVoid(){
		//doCallRealMethod().when(test).print();
		test1.print();
	}
}
