package com.horus.poc.pageobject;

import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://gmail.com/")
public class GmailHomePage extends PageObject {

	public void loguearse(String username, String contraseia) {

		setImplicitTimeout(2, TimeUnit.SECONDS);

		if (existeElemento("//p[contains(.,'" + username + "')]")) {
			findBy("//p[contains(.,'" + username + "')]").click();
			findBy("//input[contains(@name,'identifier')]").waitUntilEnabled().setWindowFocus();
			findBy("//input[contains(@name,'password')]").typeAndEnter(contraseia);
		} else {
			findBy("//input[contains(@name,'identifier')]").waitUntilEnabled().setWindowFocus();
			findBy("//input[contains(@name,'identifier')]").and().typeAndEnter(username);
			findBy("//input[contains(@name,'password')]").waitUntilEnabled().and().typeAndEnter(contraseia);
		}
		resetImplicitTimeout();
	}

	private boolean existeElemento(String xpathElemento) {
		boolean estaPresente;
		try {
			estaPresente = findBy(xpathElemento).isCurrentlyVisible();
		} catch (Exception e) {
			return false;
		}
		return estaPresente;
	}

	public void enviarCorreo(String destinatario, String asunto, String mensaje) {
		try {
			findBy("//div[contains(@gh,'cm')]").click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		findBy("//textarea[@name='to']").waitUntilEnabled().type(destinatario);
		findBy("//input[@name='subjectbox']").waitUntilEnabled().type(asunto);
		findBy("//div[contains(@role,'textbox')]").waitUntilEnabled().type(mensaje);
		findBy("//div[contains(@aria-label,'Enviar')]").click();
		
		findBy("//div[contains(.,'Tu mensaje ha sido enviado. Ver el mensaje')]");
	}

	public boolean validarRecepcionDeCorreo(String remitente, String asunto, String mensaje) {
		boolean emailOK = false;
		setImplicitTimeout(3, TimeUnit.SECONDS);
		emailOK = existeElemento("//b[contains(.,'" + asunto + "')]")
				&& existeElemento("//span[contains(.,'" + mensaje + "')]");
		resetImplicitTimeout();

		restablecerBandejaDeEntrada();

		return emailOK;
	}

	private void restablecerBandejaDeEntrada() {
		try {
			findBy("//div[@class='T-Jo-auh']").click();
			findBy("//div[@class='ar9 T-I-J3 J-J5-Ji']").click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean apareceMensajeDeErrorDestinoIncorrecto(String destinatario) {
		boolean aparece = false;
		try {
			aparece = findBy("//div[@class='Kj-JD-Jz']").containsText("No se reconoce");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aparece;
	}
}