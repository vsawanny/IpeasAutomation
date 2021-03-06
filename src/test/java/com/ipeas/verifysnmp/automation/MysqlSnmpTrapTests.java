package com.ipeas.verifysnmp.automation;

import static com.ipeas.config.GlueServerConfig.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ipeas.base.SNMPBaseTestCase;
import com.ipeas.util.Util;
import com.ipeas.util.WebDriverUtil;

public class MysqlSnmpTrapTests  {
	
	@Test
	public void checkMysqlTrap() throws InterruptedException {
		System.out.println("Testing checkMysqlTrap for East");
		Util.simulateStopMysql(eastAutomationAgent);
		WebDriverUtil.findElementByXpath(searchFieldBoxInSplunk).clear();
		WebDriverUtil.insertInTextBox(searchFieldBoxInSplunk, searchMysqldSnmp);
		WebDriverUtil.clickButtonUsingXpath(selectTimeInSplunkDropDown);
		WebDriverUtil.clickButtonUsingXpath(searchButtonInSplunk);
		WebDriverUtil.waitForElementToAppear();
		WebDriverUtil.waitForElementToAppear();
		List<WebElement> tables = WebDriverUtil.getTables(splunkTable);
		List<WebElement> tr_collection = tables.get(1).findElements(By.xpath(".//tbody/tr"));
		Assert.assertEquals(true,  (WebDriverUtil.getSingleRow(tr_collection.get(0)).get(4).getText().contains(searchMysqldSnmp)));
		Util.simulateStartMysql(eastAutomationAgent);
		WebDriverUtil.findElementByXpath(searchFieldBoxInSplunk).clear();
		System.out.println("checkMysqlTrap done for East Glue Server successfully");
	}
}
