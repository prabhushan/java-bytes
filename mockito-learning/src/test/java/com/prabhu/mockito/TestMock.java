package com.prabhu.mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestMock {

	@InjectMocks
	Parent parent;

	@Spy
	SpyTest child1;

	@Spy
	MockTest child2;

	@Test
	public void testParentPositive() {
		SpyTest childa = spy(SpyTest.class);
		MockTest childb = spy(MockTest.class);

		assertTrue(parent.getSomething("10").equals("1010"));
	}

	@Test
	public void testParentPositiveWithMock() {
		SpyTest childa = spy(SpyTest.class);
		MockTest child2 = mock(MockTest.class);
		parent = new Parent(childa, child2);
		when(child2.getSomeValue("10")).thenReturn("43");
		System.out.println(parent.getSomething("10"));
		assertTrue(parent.getSomething("10").equals("4310"));
	}

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	/// var/folders/zh/569r66tx25s95fp_tdlckgc40000gp/T/junit5245150425572969651/

	@Test
	public void testFolder() throws IOException {
		File f = tempFolder.newFile("test.txt");
		System.out.println(f.getAbsolutePath());
	}

}
