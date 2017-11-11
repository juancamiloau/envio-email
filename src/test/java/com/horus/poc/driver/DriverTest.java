package com.horus.poc.driver;


import org.junit.Test;
import org.openqa.selenium.WebDriver;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.WebDriverFacade;

public class DriverTest {

	@Managed
	WebDriver remitente;

	@Test
	public void testDriverInstanciatiation() {
		if (remitente instanceof WebDriverFacade) {
			assert true;
		}
	}

}
