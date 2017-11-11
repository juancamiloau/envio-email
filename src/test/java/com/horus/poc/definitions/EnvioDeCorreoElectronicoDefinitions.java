package com.horus.poc.definitions;

import java.util.List;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.horus.poc.model.EmailModel;
import com.horus.poc.steps.EnvioDeCorreoElectronicoSteps;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class EnvioDeCorreoElectronicoDefinitions {
	
	@Managed WebDriver remitente;
	@Managed WebDriver destinatario;
	
	private List<EmailModel> correo;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Steps
	EnvioDeCorreoElectronicoSteps envioDeCorreoElectronicoDestinatarioSteps;
	
	@Steps
	EnvioDeCorreoElectronicoSteps envioDeCorreoElectronicoRemitenteSteps;

	@Dado("^que deseo enviar un correo valido en gmail$")
	public void dadoQueHeIngresadoAGmail() {
		envioDeCorreoElectronicoRemitenteSteps.abrir_en_navegador_la_aplicacion_gmail(remitente);
	}
	
	@Cuando("^ingrese a gmail y envie un correo$")
	public void cuandoEnvieCorreo(List<EmailModel> correo) {
		this.correo = correo;
		envioDeCorreoElectronicoRemitenteSteps.loguearse(correo.get(0).getRemitente(), correo.get(0).getContrasenia());
		envioDeCorreoElectronicoRemitenteSteps.enviarCorreo(correo.get(0).getDestinatario(), correo.get(0).getAsunto(), correo.get(0).getMensaje());
		
	}
	
	@Entonces("^validar que el mensaje fue recibido por el destinatario$")
	public void entoncesValidarQueElDestinatarioRecibeElMensaje(List<EmailModel> correo) {
		
		envioDeCorreoElectronicoDestinatarioSteps.abrir_en_navegador_la_aplicacion_gmail(destinatario);
		envioDeCorreoElectronicoDestinatarioSteps.loguearse(correo.get(0).getRemitente(), correo.get(0).getContrasenia());
		boolean fueEnviadoElCorreo = envioDeCorreoElectronicoDestinatarioSteps.validarRecepcionDeCorreo(correo.get(0).getRemitente(), correo.get(0).getAsunto(), correo.get(0).getMensaje());
		assert fueEnviadoElCorreo;
	}
	
	@Entonces("^debe aparecer una ventana de error indicando destino incorrecto$")
	public void entoncesDebeAparecerUnaVentanaDeErrorIndicandoDestinoIncorrecto() {
		boolean fueEnviadoElCorreo = envioDeCorreoElectronicoRemitenteSteps.apareceMensajeDeErrorDestinoIncorrecto(this.correo.get(0).getDestinatario());
		assert fueEnviadoElCorreo;
	}

}
