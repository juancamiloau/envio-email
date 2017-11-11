package com.horus.poc.steps;


import org.openqa.selenium.WebDriver;

import com.horus.poc.pageobject.GmailHomePage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class EnvioDeCorreoElectronicoSteps extends ScenarioSteps{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GmailHomePage gmailPage;
	
	@Step
	public void abrir_en_navegador_la_aplicacion_gmail(WebDriver driver) {
		gmailPage = new GmailHomePage();
		gmailPage.setDriver(driver);
		gmailPage.open();
	}
	
	@Step
	public void loguearse(String usuario, String contrasenia) {
		gmailPage.loguearse(usuario, contrasenia);
	}

	@Step
	public void enviarCorreo(String destinatario, String asunto, String mensaje) {
		gmailPage.enviarCorreo(destinatario, asunto, mensaje);
	}

	public boolean validarRecepcionDeCorreo(String remitente, String asunto, String mensaje) {
		return gmailPage.validarRecepcionDeCorreo(remitente, asunto, mensaje);
	}

	public boolean apareceMensajeDeErrorDestinoIncorrecto(String destinatario) {
		return gmailPage.apareceMensajeDeErrorDestinoIncorrecto(destinatario);
	}
}
