/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Utilerias comunes
 */

var PAGE_HOME = '/view/index.html';
var IS_HOME = false;
// Armando Componentes de inicio
function startingSystemMenu(index){
	// Loader
	$(window).load(function(){
		$('#preloader').fadeOut('slow',function(){
			$(this).remove();
			// Iniciando action para Busqueda
			$('#input-search-portal').keypress(function(event) {
				var keycode = (event.keyCode ? event.keyCode : event.which);
				if (keycode == '13') {
					console.log('Buscando: ' + $("#input-search-portal").val());
					var data = {
						search: '' + $("#input-search-portal").val()
					}
					sessionStorage.setItem("advanced-search", data.search);
					sendPostAction(EMPLOYEE_HOME + 'searchPortal', data, respSearchPortal);
				}
				event.stopPropagation();
			});
		});
	});
	// Recargando Menu
	$('#left-panel').load('/templates/menu_bmc.html', function() {
		console.log('Levantando Menu');
		var user = new User();
		user.email = sessionStorage.getItem('email');
		sendPostAction('/home/menu/createMenu/' + index, user, respCreateMenu);
	});
	// Recargando Header
	$('#header').load('/templates/header_bmc.html', function() {
		console.log('Levantando Header');
		var nip = sessionStorage.getItem('email').split('@');
		if(nip.length == 2) {
			$('#lblEmail').text(nip[0]);
		}
		// Ir por foto
		findPhoto();
		// Levantar Notificaciones
		uploadNotifications();
		// ###### Messages for user, do not remove ######
		// Arrancar main.js
		runMain();
	});
	// Validar si es home
	if(index == 'home') {
		IS_HOME = true;
	} else {
		IS_HOME = false;
	}
	// Pintar Nombre de Usuario en el HEADER
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findUserName', null, respUserName);
	//
	initBot();
	$(this).toggleClass("maximize");
	$(".widget-content").slideToggle();
	$(".btn-minimize").click(function() {
		$(this).toggleClass("maximize");
		$(".widget-content").slideToggle();
	});
}

function findPhoto() {
	sendPostAction(EMPLOYEE_HOME + 'findPhoto', null, respFindPhoto);
}

function respFindPhoto(data) {
	if(data && data[1] == 'Ok') {
		var dataHtml = data[2];
		$("#business-photo").empty();
        var wrapper = $("#business-photo");
    	$(wrapper).append(dataHtml); // add html
	} else {
		showDivMessage(data[2], 'alert-info', 6000);
	}
	
}

function uploadNotifications() {
	$("#div-notification").empty();
	sendPostAction(EMPLOYEE_HOME + 'findNotifications', null, respNotifications);
}

function respSearchPortal(data) {
	var not = 'No se encontro ningun dato.';
	if(data && (data[1] == 'Ok' || data[2] == not)) {
		var dataMenu = data[2];
		// Open Modal
		var content = 
			'	<div class="card-header">								' +
			'		<span id="modal-search-info-action"></span>                ' +
			'	</div>                                                  ' +
			'	<div id="body-modal-search-portal" class="card-body">   ' +
			'		<!-- create dynamic body -->                        ' +
			'		                                                    ';
		if(data[2] == not) {
			content += 
				'<div>													' +
				'	<h5>' + not + '</h5>								' +
				'</div>													';
		} else {
			for (var i = 0; i < dataMenu.length; i++){
				var rowMenu = dataMenu[i];
				content += 
					'<div>																																						' +
					'	<h5>																																					' +
					'		<button class="btn bg-transparent theme-toggle text-light" type="button" onclick="javascript:window.location.href=&#34;' + rowMenu.href + '&#34;">	' +
					'			<img style="height: 25px; width: 25px; -webkit-filter: invert(100%);" src="' + rowMenu.image + '" alt="Avatar">									' +
					'		</button>																																			' +
					'		<a style="color: #010101;" href="' + rowMenu.href + '">' + rowMenu.title + '</a>																	' +
					'	</h5>																																					' +
					'</div>																																						';
			}
		}
        content += 
	        '	</div>                                                                        ' +
	        (
	        		data[3] == 'ADMIN' ? 
				        '	<button id="btn-advanced-search" type="button"                                ' +
						'		onclick="javascript:window.location.href=&#34;/adm/advanced-searchs&#34;" ' +
						'		class="btn btn-info btn-sm">                                              ' +
						'		<i class="fa fa-search"></i> Busqueda avanzada                            ' +
						'	</button>                                                                     '
						: '' 
			) +
			'	<button id="btn-cancel-search" type="button"                                  ' +
			'		onclick="closeModal();"                                                   ' +
			'		class="btn btn-danger btn-sm float-right">                                ' +
			'		<i class="fa fa-close"></i> Cancelar                                      ' +
			'	</button>                                                                     ';
        $("#modal-search-portal").empty();
        var wrapper = $("#modal-search-portal");
    	$(wrapper).append(content); // add html
    	$('#modal-search-info-action').text('Búsquedas encontradas.');
		openModalSearchPortal();
	} else {
		showDivMessage(data[2], 'alert-info', 6000);
	}
}

function openModalSearchPortal() {
	$("#modal-search-portal").modal({
		modal:open,
		showClose: false
	});
}

var botui = null;
function initBot() {
	// Bot
	var count = 0;
	botui = new BotUI('reminder-bot');
	botui.message.bot('Buen día, es un placer atenderte, ¿te puedo ayudar en algo?').then(function() {
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
			showReminderInput();
		} else {
			botui.message.bot('Bueno.');
		}
	});

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
				var dataBody = {
					msg: res.value
				}
				sendPostAction(EMPLOYEE_HOME + 'helpPortal', dataBody, respHelpPortal);
				//
				return botui.message.bot({
					delay: 1000,
					content: '¿Alguna otra cosa en la que te puedamos ayudar?'
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
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

// Si es ADMIN no valida llenado de "Datos Basicos (Información Básica)" 
// y arrojara un mensaje de precaucion (para realizar el proceso correctamente)
function respFindEmployeeGralExist(data) {
	var process = true;
	if(data) {
		if(data.process == '0') {
			process = false;
			console.log('No continuo');
		} else {
			console.log('Si continuo');
		}
	} else {
		process = false;
		console.log('No continuo');
	}
	if(process) {
		// Administrador
		// Validar si el mensaje de atencion en que estatus esta (active 0 o 1)
		var dataMessage = {
        	message: 'MESSAGE_BASIC_ADMIN' // mensaje asignado para esta accion
		}
		sendPostAction(EMPLOYEE_HOME + 'viewMessage', dataMessage, respViewMessage);
	} else {
		// Usuario
		alertInfoEmployeeGralExist('Información', 
				'Es necesario llenar</br>' + '<strong>Información Básica</strong>', 
				'Acceder', 
				'No estoy seguro', 
				'tBeIt',
				1,
				2);
	}
}

function respViewMessage(data) {
	if(data){
		if(data[1] == 'Ok'){
			// active = 1 (mostrar mensaje)
			// active = 0 (no mostrar mensaje)
			if(data[2] == '1') {
				messageAtentionAdmin();
			} else {
				console.log('No mostrar mensaje de administrador.');
			}
		} else {
			showDivMessage("Error al crear menu", "alert-danger", 5000);
		}
	} else {
		showDivMessage("Valores vacios en Kardex", "alert-danger", 5000);
	}
}

function messageAtentionAdmin() {
	alertInfoEmployeeGralExist('Atención', 
			'Estás tomando el control de todos los usuarios, antes de modificar cualquier registro debes verificar su</br><strong>Información Básica</strong>.'
				+ '<div class="form-check">' 
					+ '</br>'
					+ '<input id="messageAlert" type="checkbox" class="form-check-input">' 
					+ '<span for="messageAlert" style="color: #010101;">No volver a mostrar</span>' 
				+ '</div>', 
			'Ir a "Información Básica"', 
			'Cerrar', 
			'tBuro_warning',
			1,
			3);
	// Quita boton de Cerrar
	$('.denyBtn').css('display', 'none');
	// Validar si esta activo el check
	$('#messageAlert').change(function() {
        console.log('No volver a mostrar: ' + this.checked);
        //
        var data = {
        	message: 'MESSAGE_BASIC_ADMIN', // mensaje asignado para esta accion
        	check: '' + this.checked
		}
		sendPostAction(EMPLOYEE_HOME + 'validateMessage', data, respValidateMessage);
    });
}

function respValidateMessage(data) {
	if(data && data[1] == 'Nok'){
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function onConfirmDenyClose(action) {
	if(action == 1) {
		// Proceder a llenar Datos Básicos
    	window.location.href = "/adm/employee-gral";
	}
	if(action == 2) {
		// Quita boton de edicion
    	$('.fa-pencil').css('display', 'none');
	}
	if(action == 3) {
		// "No volver a mostrar" activar o desactivar
		console.log('El MESSAGE_BASIC_ADMIN desde BD esta activo o inactivo.');
	}
}

function alertInfoEmployeeGralExist(title, confirmQuestion, confirmBtnText, denyBtnText, theme, onConfirm, onDenyClose) {
	$.jAlert({
        'type': 'confirm',
        'title': title,
        'confirmQuestion': confirmQuestion,
        'confirmBtnText': confirmBtnText,
        'denyBtnText': denyBtnText,
        'theme': theme,
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	// Proceder a llenar Datos Básicos
        	onConfirmDenyClose(onConfirm);
        },
        'onDeny': function (e, btn) {
        	// Quita boton de edicion
        	onConfirmDenyClose(onDenyClose);
        },
        'onClose': function(alertElem) {
        	// Quita boton de edicion
        	onConfirmDenyClose(onDenyClose);
        }
    });
}

// Respuesta de Controller
function respCreateMenu(data){
	if (data){
		if(data[1] == 'Ok'){
			var valueHtmlMenu = data[2];
			var wrapper = $(".main-menu");
			$(wrapper).append(valueHtmlMenu); // add html
		} else {
			showDivMessage("Error al crear menu", "alert-danger", 5000);
		}
	} else {
		showDivMessage("Valores vacios en Kardex", "alert-danger", 5000);
	}
	// Validar si es home
	if(IS_HOME) {
		$('#menuToggle').css('display', 'none');
	} else {
		$('#menuToggle').css('display', 'block');
	}
}

function respNotifications(data) {
	if(data && data[1] == 'Ok'){
		$('#count-notification').text(data[3]);
		var valueHtmlNotification = data[2];
		var wrapper = $("#div-notification");
		$(wrapper).append(valueHtmlNotification); // add html
	} else {
		showDivMessage(data[2], "alert-danger", 5000);
	}
}

function callNotification(id) {
	sendPostAction(EMPLOYEE_HOME + 'callNotification/' + id, null, respCallNotification);
}

function respCallNotification(data) {
	if(data && data[1] == 'Ok'){
		uploadNotifications();
	} else {
		showDivMessage(data[2], "alert-danger", 5000);
	}
}

function createDynamicBoard(columnLarge) {
	sendPostAction(EMPLOYEE_HOME + 'createHomeUsers/' + columnLarge, null, respCreateDynamicBoard);
}

function respCreateDynamicBoard(data) {
	if (data){
		if(data[1] == 'Ok'){
			var valueHtmlMenu = data[2];
			var wrapper = $("#main-content-dynamic");
			$(wrapper).append(valueHtmlMenu); // add html
		} else {
			showDivMessage("Error al crear home", "alert-danger", 5000);
		}
	} else {
		showDivMessage("Valores vacios en Kardex HOME", "alert-danger", 5000);
	}
}

function yammerSocial(style) {
	yam.connect.embedFeed({
		"feedType" : "user",
		"config" : {
			"use_sso" : false,
			"header" : false,
			"footer" : true,
			"showOpenGraphPreview" : false,
			"defaultToCanonical" : false,
			"hideNetworkName" : false,
			"promptText" : "Cuentanos ¿En qué estás trabajando?",
			"theme" : style
		},
		"container" : "#yammer-social"
	});
}

function respUserName(data) {
	if(data) {
		$('#lblUserName').text(data.USERNAME.trim());
	}
	changeColor(false);
}

// Romper session
function callLogout(){
	try{
		sessionStorage.clear();
		window.location.href = "/logout";
	} catch (e) {
		showDivMessage("Error al cerrar sesion: " + e, "alert-danger", 5000);
	}
}

// Mostrar mensaje
function showDivMessage(message, divclass, time) {
	var div_message = $('#div-message');
	$('#message-content').html(message);
	div_message.addClass(divclass);
	div_message.css('display', 'block');
	setTimeout(function() {
		div_message.css('display', 'none');
	}, time);
}

//Ejecuta POST
function sendPostAction(url, model, callBackFunctionJquery) {
	$.ajax(url, {
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
			showDivMessage(msj, 'alert-danger', 4000);
		}
	});
}

// Exportar Tabla
function exportTable(name, type) {
	$("#" + name).DataTable({
        dom: 'lBfrtip',
        lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
        buttons: [
        	type
        ]
    });
}

// Iniciando tabla
function reloadTable(name) {
	$("#" + name).DataTable();
	// Desactivar componentes de la TABLA
	$("#" + name + "_length").hide();
	$("#" + name + "_info").hide();
}

// Mensaje para tabla vacia
function msgEmptyTable(msg) {
	$('.dataTables_empty').html(msg);
}

// Union de nombre archivo y tipo archivo
function castNameFileToTypeFile(input) {
	var result = '';
	try {
		var castFile = $('#' + input)[0].files[0];
		result = castFile.name + '____' + castFile.type;
	} catch (e) {
		result = '';
	}
	return result;
}

// Formato de Pesos
var formatter = new Intl.NumberFormat('en-US', {
	style: 'currency',
	currency: 'USD',
	minimumFractionDigits: 2,
	// the default value for minimumFractionDigits depends on the currency
	// and is usually already 2
});

// Validar objeto
function validateObj(data) {
	if(data == null || data == 'null' || data == 'undefined') {
		return '';
	} else {
		return data;
	}
}
function validateObjOut(data, out) {
	if(data == null || data == 'null' || data == 'undefined') {
		return out;
	} else {
		return data;
	}
}

// Validar id's
function validateId(data) {
	if(data == '' || data == null || data == 'null' || data == 'undefined') {
		return '0';
	} else {
		return data;
	}
}

// Cerrar modal de jQuery
function closeModal() {
	$.modal.close();
}

// Ver documento
function respViewFiles(data) {
	if(data) {
		showViewDocEmployee();
		if(data.status == 'Ok') {
			$("#contentDocEmployee").empty();
			var dataUri = data.base64Document;
			if (dataUri.includes('application/xml') || 
					dataUri.includes('text/xml') || 
					dataUri.includes('text/plain') || 
					dataUri.includes('application/javascript') ||
					dataUri.includes('application/json') ||
					dataUri.includes('text/css') ||
					dataUri.includes('text/csv')) {
				$.ajax({
					url : dataUri,
					dataType : "text",
					success : function(data) {
						$("#contentDocEmployee").append('<textarea id="areaText" class="form-control rounded-0" style="overflow:hidden" rows="30"></textarea>');
						$("#areaText").text(data);
					}
				});
			} else {
			    var viewDoc = '<object style="width: 100%;height: 1000px;" class="w100" data="' + dataUri + '"></object>';
			    $("#contentDocEmployee").append(viewDoc);
			}
		} else {
			showDivMessage(data.error, 'alert-danger', 4000);
		}
	} else {
		showDivMessage('Valores vacios', 'alert-danger', 3000);
	}
}

// Descargar documento
function respDownloadFiles(data) {
	if(data) {
		if(data.status == 'Ok') {
			$("#contentDocEmployee").empty();
			var dataUri = data.base64Document;
			var link = document.createElement("a");
			link.setAttribute("target","_blank");
			link.setAttribute("href",dataUri);
			link.setAttribute("download","DocumentBeIT " + data.email + " " + data.nameOriginal);
			document.body.appendChild(link);
			link.click();
			document.body.removeChild(link);
		} else {
			showDivMessage(data.error, 'alert-danger', 4000);
		}
	} else {
		showDivMessage('Valores vacios', 'alert-danger', 3000);
	}
}

// Separador generico
function splitGeneric(value, separetor, vector, index) {
	var splitGral = value.split(separetor);
	if(vector) {
		return splitGral;
	} else{
		return splitGral[index];
	}
}

function alertInfo(title, msg, txtbtn) {
	$.jAlert({
        'title': title,
        'content': msg,
        'btns': [{'text':txtbtn}],
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown'
    });
}

function scrollTo(hash) {
    location.hash = "#" + hash;
}

function validateDate(date) {
	return (new Date(date) !== "Invalid Date" && !isNaN(new Date(date)) ) ? true : false;
}

function changeColor(add) {
	var CONST_COLORS_HEADER = [
			'#333333', 
			'#0078d4', 
			'#1c1c1c', 
			'#1c1c1c', 
			'#ffffff'
	];
	var CONST_COLORS_MAIN = [
			'#333333', 
			'#ffffff', 
			'#ffffff', 
			'#ffffff', 
			'#ffffff'
	];
	var CONST_COLORS_PANEL = [
			'#1c1c1c', 
			'#e4e4e4', 
			'#215b7d', 
			'#e4e4e4', 
			'#ffffff'
	];
	var CONST_COLORS_TEXT = [
			'#ffffff', 
			'#ffffff', 
			'#ffffff', 
			'#ffffff', 
			'#00999d'
	];
	var indexColor = sessionStorage.getItem("colors");
	if(indexColor) {
		if(add) {
			indexColor++;
		}
		$("#header").css('background-color', CONST_COLORS_HEADER[indexColor]);
		$("#content-sub-main").css('background-color', CONST_COLORS_MAIN[indexColor]);
		$("#right-panel").css('background-color', CONST_COLORS_PANEL[indexColor]);
		$("body").css('background-color', CONST_COLORS_PANEL[indexColor]);
		//
		$("h6").css('color', CONST_COLORS_TEXT[indexColor]);
		//
		if(indexColor == 0) {
			$("h3").css('color', '#ffffff');
			$("#lblUserName").css('color', '#ffffff');
			$("#lblEmail").css('color', '#00999d');
			// icon
			$(".fa-search").css('color', '#ffffff');
			$(".fa-bell").css('color', '#ffffff');
		}
		if(indexColor == 1 || indexColor == 2 || indexColor == 3) {
			$("h3").css('color', '#00999d');
			$("#lblUserName").css('color', '#ffffff');
			$("#lblEmail").css('color', '#00999d');
			// icon
			$(".fa-search").css('color', '#ffffff');
			$(".fa-bell").css('color', '#ffffff');
		}
		if(indexColor == 4) {
			$("h3").css('color', '#00999d');
			$("#lblUserName").css('color', '#000000');
			$("#lblEmail").css('color', '#00999d');
			// icon
			$(".fa-search").css('color', '#1c1c1c');
			$(".fa-bell").css('color', '#1c1c1c');
		}
		//
		if(indexColor == 4) {
			indexColor = -1;
		}
		sessionStorage.setItem("colors", indexColor);
	}
}
$("#right-panel").dblclick(function() {
	changeColor(true);
});

function helpBuro() {
	console.log('HELP BUROMC.');
	$(this).toggleClass("maximize");
	$(".widget-content").slideToggle();
}

function setLanguage() {
	var lgNav = window.navigator.languages ? window.navigator.languages[0]
			: (window.navigator.language || window.navigator.userLanguage);
	console.log('Lenguaje detectado:' + lgNav);
}

// Mostrar Loader
function showPreloader(status) {
	if(status) {
		$('#preloader-process').css('display','block');
	} else {
		$('#preloader-process').css('display','none');
	}
}

function viewRecord() {
	var r = sessionStorage.getItem('record');
	if(null != r && r == '1') {
		// Ver "Recorrido"
		record();
	}
}

// Variables Globales
var EMPLOYEE_CONTROLLER = '/adm/employee/';
var EMPLOYEE_ALL_CONTROLLER = '/adm/employee-all/';
var EMPLOYEE_GRAL_CONTROLLER = '/adm/employee-gral/';
var EMPLOYEE_REGISTRATION_CONTROLLER = '/adm/registration/';
var EMPLOYEE_CATALOGS_CONTROLLER = '/adm/catalogs/';
var CERTIFICATION_TRACK_CONTROLLER = '/adm/certification-track/';
var EMPLOYEE_DASHBOARD = '/adm/dashboard/';
var EMPLOYEE_HOME = '/home/create/';
var LDAP_API = '/cat/ldap/';