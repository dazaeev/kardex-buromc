/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Acceso
 */

function init() {
	console.log('Iniciando Kardex');
	console.log('Bienvenido: ' + jQuery('#email').val());
	// Session email
	sessionStorage.setItem("email", $('#email').val());
	// Session colors
	sessionStorage.setItem("colors", -1);
}

// Cargando acceso
$(document).ready(function($) {
	// Cambio de foco en cuenta
	$("#account").focusout(function(event){
	    var tx = $(this);
	    // Validar si existe usuario
	    validateUser('buscar', $('#account').val());
	});
	// Sacamos el valor de error
	var query = window.location.search.substring(1);
	if(query == 'error') {
		// Mandar error de acceso
		$('#div-error').css('display','block');
		$('#lblError').text('Credenciales no validas *');
	} else {
		$('#div-error').css('display','none');
	}
	//
	$('#password').keypress(function(event) {
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if (keycode == '13') {
			$("#btn-login").click();
		}
	});
	//
	$('#btn-login').on('click', function () {
		showPreloader(true);
		console.log('Validando usuario.');
		var data = {
			email: $('#email').val(),
			keyAccount: $('#password').val()
		}
		// TODO: daza con LDAP --------------------------------------------
		sendValidateAction(LDAP_API + 'processingUser', data, respUser);
		// TODO: daza sin LDAP --------------------------------------------
		// $('#email').val($('#account').val());
		// $("#sign_in").submit();
		// ----------------------------------------------------------------
	});
	//
	$('#sign_in').on('submit', function() {
		init();
		return true;
	});
});

function helpBuro() {
	console.log('HELP BUROMC.');
	$('.widget-content').css('display', 'block');
	$(".widget-content").toggleClass("maximize");
	initBot();
	$(".btn-minimize").click(function() {
		$(".widget-content").toggleClass("maximize");
		$(".widget-content").slideToggle();
	});
}

function validateUser(type, name) {
	// showPreloader(true);
	var data = {
		name: name
	}
	if(type == 'buscar') {
		sendValidateAction(LDAP_API + 'findUser', data, respValidateUser);
	}
}

function respValidateUser(data) {
	// showPreloader(false);
	if(data) {
		if(data.error == '0') {
			console.log('Setear valores de Usuario');
			$('#div-error').css('display','none');
			$('#email').val(data.userPrincipalName);
		} else {
			$('#email').val('');
			$('#div-error').css('display','block');
			$('#lblError').text(data.message);
		}
	} else {
		console.log('Respuesta vacia.');
		$('#div-error').css('display','block');
		$('#lblError').text('Respuesta vacia.');
	}
}

function respUser(data) {
	if(data) {
		if(data[1] == 'Ok'){
			// Process LDAP - Validate
			var data = {
				account: $('#account').val(),
				keyAccount: $('#password').val(),
				email: $('#email').val(),
				active: data[3]
			}
			sendValidateAction(LDAP_API + 'validateUserCredentials', data, respUserLdap);
		} else {
			showPreloader(false);
			$('#div-error').css('display','block');
			$('#lblError').text(data[2]);
		}
	} else {
		showPreloader(false);
		$('#div-error').css('display','block');
		$('#lblError').text('Acceder nuevamente.');
	}
}

function respUserLdap(data) {
	showPreloader(false);
	if(data){
		if(data[1] == 'Ok'){
			// Mostrar "Recorrido"
			if(null != data[3] && data[3] == 'record') {
				sessionStorage.setItem("record", '1');
			} else {
				sessionStorage.setItem("record", '0');
			}
			//
			$("#sign_in").submit();
		} else {
			$('#div-error').css('display','block');
			$('#lblError').text(data[2]);
			//
			sessionStorage.setItem("record", '0');
		}
	} else {
		$('#div-error').css('display','block');
		$('#lblError').text('Error favor de contactar a rh@buromc.com');
		//
		sessionStorage.setItem("record", '0');
	}
}

function parse_query_string(query) {
	// Se manda a llamar 
	// var qs = parse_query_string(query);
	// 
	var vars = query.split("&");
	var query_string = {};
	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split("=");
		var key = decodeURIComponent(pair[0]);
		var value = decodeURIComponent(pair[1]);
		// If first entry with this name
		if (typeof query_string[key] === "undefined") {
			query_string[key] = decodeURIComponent(value);
			// If second entry with this name
		} else if (typeof query_string[key] === "string") {
			var arr = [ query_string[key], decodeURIComponent(value) ];
			query_string[key] = arr;
			// If third or later entry with this name
		} else {
			query_string[key].push(decodeURIComponent(value));
		}
	}
	return query_string;
}

function info(title, msg, txtbtn) {
	jQuery.jAlert({
        'title': title,
        'content': msg,
        'btns': [{'text':txtbtn}],
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown'
    });
}

//Ejecuta POST
function sendValidateAction(url, model, callBackFunctionJquery) {
	jQuery.ajax(url, {
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		'type' : 'POST',
		'data' : JSON.stringify(model),
		'dataType' : 'json',
		'success' : callBackFunctionJquery,
		'error' : function(data) {
			var msj = "Error sin mensaje";
			if (data.responseJSON != null) {
				msj = data.responseJSON['status'];
				msj = msj + ' - ' + data.responseJSON['message'];
			}
			info('Error', msj, 'Entendido');
		}
	});
}

var botui = null;
var emailBot = null;
function initBot() {
	// Bot
	var count = 0;
	botui = new BotUI('reminder-bot');
	botui.message.bot('Hola, soy BeITalent, ¿te puedo apoyar con algo?').then(function() {
		return botui.action.button({
			delay : 1000,
			action : [ {
				text : 'Si',
				value : 'yes'
			}, {
				text : 'No',
				value : 'no'
			} ]
		})
	}).then(function(res) {
		if (res.value == 'yes') {
			showReminderEmailInput();
		} else {
			botui.message.bot('Bueno.');
		}
	});
	
	// Correo electronico
	var showReminderEmailInput = function() {
		botui.message.bot({
			delay : 500,
			content : 'Escribe tu correo electronico:'
		}).then(function() {
			return botui.action.text({
				delay : 1000,
				action : {
					size: 30,
					placeholder : 'Ejm: ecedillo@buromc.com'
				}
			})
		}).then(function(res) {
			count++;
			//
			if(count == 1) {
				count = 0;
				// Obtener correo correo
				emailBot = res.value;
				//
				return botui.message.bot({
					delay: 1000,
					content: '¿Que necesitas?'
				})
			}
		}).then(showReminderInput);
	}

	var showReminderInput = function() {
		botui.message.bot({
			delay : 500,
			content : 'Escriba a continuación:'
		}).then(function() {
			return botui.action.text({
				delay : 1000,
				action : {
					size: 30,
					placeholder : 'Ejm: Tengo duda en ...'
				}
			})
		}).then(function(res) {
			count++;
			//
			if(count == 1) {
				count = 0;
				// Mandar correo
				var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			    if(!re.test(String(emailBot).toLowerCase())) {
			    	emailBot = 'Nok';
			    }
				var dataBody = {
					email: emailBot,
					msg: res.value
				}
				sendValidateAction('/home/create/helpPortal', dataBody, respHelpPortal);
				//
				return botui.message.bot({
					delay: 1000,
					content: '¿Alguna otra cosa en la que te pueda apoyar?'
				})
			}
		}).then(showReminderInput);
	}
}

function respHelpPortal(data) {
	if(data && data[1] == 'Ok'){
		botui.message.bot({
			delay : 500,
			content: 'Tu petición ha sido enviada al departamento de Recursos Humanos.'
		});
	} else {
		info('Alerta', data[2], 'Entendido');
		$(".btn-minimize").click();
	}
}

function cleanForm() {
	$("#sign_in")[0].reset();
}

function showPreloader(status) {
	if(status) {
		$('#preloader-process').css('display','block');
	} else {
		$('#preloader-process').css('display','none');
	}
}

LDAP_API = '/cat/ldap/';