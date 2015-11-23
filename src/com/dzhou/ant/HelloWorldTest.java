package com.dzhou.ant;

import org.apache.tools.ant.BuildFileRule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;

import static org.junit.Assert.*;

import org.apache.tools.ant.AntAssert;
import org.apache.tools.ant.BuildException;

public class HelloWorldTest {

	@Rule
	public final BuildFileRule buildRule = new BuildFileRule();

	@Before
	public void setUp() {
		// initialize Ant
		buildRule.configureProject("build_helloworld.xml");
	}

	@Test
	public void testWithout() {
		buildRule.executeTarget("use.without");
		assertEquals("Message was logged but should not.", buildRule.getLog(), "");
	}

	public void testMessage() {
		// execute target 'use.nestedText' and expect a message
		// 'attribute-text' in the log
		buildRule.executeTarget("use.message");
		Assert.assertEquals("attribute-text", buildRule.getLog());
	}

	@Test
	public void testFail() {
		// execute target 'use.fail' and expect a BuildException
		// with text 'Fail requested.'
		try {
			buildRule.executeTarget("use.fail");
			fail("BuildException should have been thrown as task was set to fail");
		} catch (BuildException ex) {
			Assert.assertEquals("Fail requested.", ex.getMessage());
		}

	}

	@Test
	public void testNestedText() {
		buildRule.executeTarget("use.nestedText");
		Assert.assertEquals("nested-text", buildRule.getLog());
	}

	@Test
	public void testNestedElement() {
		buildRule.executeTarget("use.nestedElement");
		AntAssert.assertContains("Nested Element 1", buildRule.getLog());
		AntAssert.assertContains("Nested Element 2", buildRule.getLog());
	}
}