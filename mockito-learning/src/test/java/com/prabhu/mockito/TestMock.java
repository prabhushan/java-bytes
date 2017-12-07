package com.prabhu.mockito;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestMock {

	@InjectMocks
	Parent parent;
	
	@Spy
	ChildA child1;

	
	@Spy
	ChildB child2;
	
	@Test
	public void testParentPositive() {
		ChildA childa = spy(ChildA.class);
		ChildB childb = spy(ChildB.class);
		//parent.setChildA(childa);
		//parent.setChildB(childb);

		assertTrue(parent.getSomething("10").equals("1010"));
	}
	
	@Test
	public void testParentPositiveWithMock() {
		ChildA childa = spy(ChildA.class);
		ChildB child2 = mock(ChildB.class);
		parent = new Parent(childa, child2);
		when(child2.getSomeValue("10")).thenReturn("43");
		assertTrue(parent.getSomething("10").equals("4310"));
	}

}
