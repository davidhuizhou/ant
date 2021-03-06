package com.dzhou.ant;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.taskdefs.Property;

import static org.junit.Assert.*;

import java.io.File;

public class FindTwoTest {

	@Rule
	public final BuildFileRule buildRule = new BuildFileRule();

	@Before
	public void setUp() {
		buildRule.configureProject("build02.xml");
	}

	@Test
	public void testMissingFile() {
		FindTwo find = new FindTwo();
		try {
			find.execute();
			fail("No 'no-file'-exception thrown.");
		} catch (Exception e) {
			// exception expected
			String expected = "file not set";
			assertEquals("Wrong exception message.", expected, e.getMessage());
		}
	}

	@Test
	public void testMissingLocation() {
		FindTwo find = new FindTwo();
		find.setFile("ant.jar");
		try {
			find.execute();
			fail("No 'no-location'-exception thrown.");
		} catch (Exception e) {
			// exception expected
			String expected = "location not set";
			assertEquals("Wrong exception message.", expected, e.getMessage());
		}
	}

	@Test
	public void testMissingFileset() {
		FindTwo find = new FindTwo();
		find.setFile("ant.jar");
		find.setLocation("location.ant-jar");
		try {
			find.execute();
			fail("No 'no-fileset'-exception thrown.");
		} catch (Exception e) {
			// exception expected
			String expected = "fileset not set";
			assertEquals("Wrong exception message.", expected, e.getMessage());
		}
	}

	@Test
	public void testFileNotPresent() {
		buildRule.executeTarget("testFileNotPresent");
		String result = buildRule.getProject().getProperty("location.ant-jar");
		assertNull("Property set to wrong value.", result);
	}

	@Test
	public void testFilePresent() {
		buildRule.executeTarget("testFilePresent");
		String result = buildRule.getProject().getProperty("location.ant-jar");
		assertNotNull("Property not set.", result);
		assertTrue("Wrong file found.", result.endsWith("ant.jar"));
	}

}