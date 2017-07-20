package com.prabhu.designpatterns;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.runners.MockitoJUnitRunner;

import com.prabhu.designpatterns.abstractFactoryPattern.AbstractFactoryTest;
import com.prabhu.designpatterns.abstractFactoryPattern.TestAbstractClass;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class AppTest extends TestCase {

	@Mock
	TestAbstractClass testAbstract;
	
	@Mock
	AbstractFactoryTest test;

	@Spy
	AbstractFactoryTest test1;

	@Mock
	List<String> mockList;

	@Spy
	List<String> spyList = new ArrayList<>();

	@Test
	public void test() {
		when(test.printWorld()).thenReturn("Inside Stub");
		assertEquals("Inside Stub", test.printWorld());
		verify(test, times(1)).printWorld();

	}

	@Test(expected = IllegalArgumentException.class)
	public void testVoid() {
		doThrow(new IllegalArgumentException()).when(test).print();
		test.print();
	}

	@Test
	public void testsometingVoid() {
		doCallRealMethod().when(test).print();
		test.print();
	}

	@Test
	public void testSpyVoid() {
		// doCallRealMethod().when(test).print();
		test1.print();
	}

	@Test
	public void whenNotUseMockAnnotation_thenCorrect() {

		mockList.add("one");
		mockList.add("two");

		Mockito.verify(mockList).add("one");
		Mockito.verify(mockList).add("two");
		when(mockList.size()).thenReturn(2);
		assertEquals(2, mockList.size());
		Mockito.doReturn(100).when(mockList).size();
		assertEquals(100, mockList.size());
	}

	@Test
	public void whenNotUseSpyAnnotation_thenCorrect() {

		spyList.add("one");
		spyList.add("two");

		Mockito.verify(spyList).add("one");
		Mockito.verify(spyList).add("two");
		// when(spyList.size()).thenReturn(3);
		assertEquals(2, spyList.size());
		Mockito.doReturn(100).when(spyList).size();
		assertEquals(100, spyList.size());
	}
	
	@Test
	public void testAbstractClass(){
		doNothing().doThrow(new IllegalArgumentException("test")).when(testAbstract).voidTest();
		testAbstract.voidTest();
		testAbstract.voidTest();
	}
	
	
}
