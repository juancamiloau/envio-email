# encoding: iso-8859-1
# language: es

Característica: Envio de correo

Escenario: Envio de correo valido
	Dado que deseo enviar un correo valido en gmail
	Cuando ingrese a gmail y envie un correo
	|remitente				|destinatario				|contrasenia			|asunto	|mensaje|
	|remitentesura@gmail.com|destinatariosura@gmail.com	|0123456789*			|Saludo	|PSI	|
	Entonces validar que el mensaje fue recibido por el destinatario
	|remitente					|destinatario				|contrasenia			|asunto	|mensaje|
	|destinatariosura@gmail.com	|							|0123456789*			|Saludo	|PSI	|
	
	
Escenario: Envio de correo no valido por correo destino incorrecto
	Dado que deseo enviar un correo valido en gmail
	Cuando ingrese a gmail y envie un correo
	|remitente				|destinatario				|contrasenia			|asunto	|mensaje|
	|remitentesura@gmail.com|#@%^%#$@#$@#.com			|0123456789*			|Saludo	|PSI	|
	Entonces debe aparecer una ventana de error indicando destino incorrecto