/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Resetar Acceso
 */

function sendAccess() {
	console.log('Envia correo para su clave de acceso.');
	//
	$('#lblInfo').text('Procesando Información, asignando token.');
	$('.spinner').css('display','block');
	$('.spinner').css('top','70%');
	
	$("#btn-send").attr("disabled", true);
	//
	var data = {
		id: 0,
		email: $('#email').val(),
		token: '',
		cve: '',
		active: 1
	}
	sendForgetAction(FORGET_API + 'sendAccess', data, respSendAccess);
	//
}

function resetAccess() {
	console.log('Reseteando contraseña');
	//
	$('#lblInfo').text('Procesando Información, Validando token.');
	$('.spinner').css('display','block');
	$('.spinner').css('top','83%');
	$("#btn-send").attr("disabled", true);
	//
	var data = {
		id: 0,
		email: $('#email').val(),
		token: $('#token').val(),
		cve: '',
		active: 1
	}
	sendForgetAction(FORGET_API + 'validateAccess', data, respValidateAccess);
}

function resetCve() {
	console.log('Nueva clave de acceso');
	//
	$('#lblInfo').text('Procesando Información, Reseteando Cve.');
	$('.spinner').css('display','block');
	$('.spinner').css('top','95%');
	$("#btn-send").attr("disabled", true);
	//
	var data = {
		id: 0,
		email: $('#email').val(),
		token: $('#token').val(),
		cve: $('#acess-cve').val(),
		active: 1
	}
	sendForgetAction(FORGET_API + 'resetCve', data, respResetCve);
}

function respSendAccess(data) {
	if(data && data[1] == 'Ok') {
		$('#div-token').css('display', 'block');
		info('Información', '<strong>' + data[2] + '</strong>' + '</br>Buscar en su bandeja de entrada.', 'Entendido');
		$('.spinner').css('display','none');
		$('#btn-send').attr("disabled", false);
	} else {
		info('Información', '<strong>' + data[2] + '</strong>', 'Entendido');
		$('.spinner').css('display','none');
		$('#btn-send').attr("disabled", false);
	}
}

function respValidateAccess(data) {
	if(data && data[1] == 'Ok') {
		$('#div-token').css('display', 'block');
		$('#div-acess-cve').css('display', 'block');
		// Empezar por resetar clave de acceso
		info('Información', data[2], 'Entendido');
		//
		$('.spinner').css('display','none');
		$('#btn-send').attr("disabled", false);
	} else {
		info('Información', '<strong>' + data[2] + '</strong>', 'Entendido');
		$('.spinner').css('display','none');
		$('#btn-send').attr("disabled", false);
	}
}

function respResetCve(data) {
	if(data && data[1] == 'Ok') {
		$('#div-token').css('display', 'block');
		$('#div-acess-cve').css('display', 'block');
		//
		$('.spinner').css('display','none');
		$('#btn-send').attr("disabled", false);
		//
		confirm('Información', data[2] + '</br>¿Acceder a portal?');
	} else {
		info('Información', '<strong>' + data[2] + '</strong>', 'Entendido');
		$('.spinner').css('display','none');
		$('#btn-send').attr("disabled", false);
	}
}

// Cargando acceso
$(document).ready(function($) {
	// Loader
	$(window).load(function(){
		$('#preloader').fadeOut('slow', function() {
			$(this).remove();
		});
	});
	//
	$('#email').val("");
	$('#token').val("");
	$('#acess-cve').val("");
	//
	$('#check_in').on('submit', function() {
		if($('#email').val() != '' && $('#token').val() == '' && $('#acess-cve').val() == '') {
			sendAccess();
		}
		if($('#email').val() != '' && $('#token').val() != '' && $('#acess-cve').val() == '') {
			resetAccess();
		}
		if($('#email').val() != '' && $('#token').val() != '' && $('#acess-cve').val() != '') {
			resetCve();
		}
		return false;
	});
});

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

function confirm(title, msg) {
	jQuery.jAlert({
        'type': 'confirm',
        'title': title,
        'confirmQuestion': msg,
        'confirmBtnText': 'Acceder',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	window.location.href = "/";
        }
    });
}

//Ejecuta POST
function sendForgetAction(url, model, callBackFunctionJquery) {
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

FORGET_API = '/forget/access/';