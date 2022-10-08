package com.test;

import org.testng.annotations.Test;

import com.base.Base;
import com.engine.KeywordEngine;

public class KeywordTest extends Base{

	@Test
	public void loginTest() {
		keywordEngine = new KeywordEngine();
		keywordEngine.startExecution("TestData");
	}
}