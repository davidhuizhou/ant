package com.dzhou.ant;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.apache.tools.ant.BuildFileRule;

public class FindOneTest {

	@Rule
	public final BuildFileRule buildRule = new BuildFileRule();

	@Before
	public void setUp() {
		buildRule.configureProject("build01.xml");
	}

	@Test
	public void testSimple() {
		buildRule.executeTarget("use.simple");
		Assert.assertEquals("test-value", buildRule.getLog());
	}
}