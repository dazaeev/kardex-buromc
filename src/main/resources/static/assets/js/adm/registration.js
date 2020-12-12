/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de control "employee_legal"
 */

// Variables Globales

// Cargando sistema
$(document).ready(function($) {
	startingSystemMenu();
	$("#email").prop("disabled", true);
	$("#name").prop("disabled", true);
	$("#ape").prop("disabled", true);
	//
	// $("#account").focusout(function(event){
	//     var tx = $(this);
	//     // Validar si existe usuario
	//     validateUser('buscar', $('#account').val());
	// });
	//
	$('#btn-save-user').on('click', function () {
		console.log('AD');
		// 
		validateUser('guardar', $('#load-user').val());
    });
	//
	$("#form-add-user").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
		} else {
			showPreloader(true);
			saveUser();
			//
	        //hideAddEmployee();
	        return false;
		}
	});
	// Add Users
	loadUserAll();
	// Add Rol
	loadRol();
});

function validateUser(type, name) {
	showPreloader(true);
	var data = {
		name: name
	}
	if(type == 'buscar') {
		sendPostAction(LDAP_API + 'findUser', data, respValidateUser);
	}
	if(type == 'guardar') {
		sendPostAction(LDAP_API + 'findUser', data, respUser);
	}
}

function respValidateUser(data) {
	showPreloader(false);
	if(data) {
		if(data.error == '0') {
			console.log('Setear valores de Usuario');
			$('#email').val(data.userPrincipalName);
			$('#name').val(data.givenName);
			$('#ape').val(data.sn);
		} else {
			$('#email').val('');
			$('#name').val('');
			$('#ape').val('');
			showDivMessage(data.message, 'alert-danger', 2000);
		}
	} else {
		console.log('Respuesta vacia.');
		showDivMessage('Respuesta vacia.', 'alert-danger', 2000);
	}
}

function respUser(data) {
	showPreloader(false);
	if(data) {
		if(data.error == '0') {
			console.log('Guardar Usuario');
			$('#email').val(data.userPrincipalName);
			$('#name').val(data.givenName);
			$('#ape').val(data.sn);
	        $("#form-add-user").submit();
		} else {
			$('#email').val('');
			$('#name').val('');
			$('#ape').val('');
			showDivMessage(data.message, 'alert-danger', 2000);
		}
	} else {
		console.log('Respuesta vacia.');
		showDivMessage('Respuesta vacia.', 'alert-danger', 2000);
	}
}

function saveUser() {
	var user = new User();
	user.id										= 0;
	user.uuid									= $('#uuid').val();
	user.account								= '' + $('#load-user').val();
	user.email									= '' + $('#email').val();
	// user.password								= '' + $('#pass').val();
	user.name									= '' + $('#name').val();
	user.lastName								= '' + $('#ape').val();
	// Llenar Role
	var role = new Role();
	role.id										= $("#load-rol").val();
	role.description							= $("#load-rol option:selected").text();
	user.roles 									= [role];
	//
	sendPostAction(EMPLOYEE_REGISTRATION_CONTROLLER + 'add', user, respSaveUser);
}

function respSaveUser(data) {
	showPreloader(false);
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 3000);
		//
		cleanForm();
	} else {
		showDivMessage(data[2], 'alert-danger', 9000);
	}
}

function loadUserAll() {
	showPreloader(true);
	sendPostAction(LDAP_API + 'loadUserAll', null, respUserAll);
}

function respUserAll(data) {
	var opDefault = new Option('Seleccionar usuario ...', '');
    $('#load-user').append(opDefault);
	for (var i = 0; i < data.length; i++){
        var op = new Option(data[i].name + ' <' + data[i].userPrincipalName + '>', data[i].sAMAccountName);
        $('#load-user').append(op);
    }
	showPreloader(false);
}

function loadRol() {
	sendPostAction(EMPLOYEE_REGISTRATION_CONTROLLER + 'loadRol', null, respRol);
}

function respRol(data) {
	var opDefault = new Option('Seleccionar nivel de acceso ...', '');
    $('#load-rol').append(opDefault);
	for (var i = 0; i < data.length; i++){
        var op = new Option(data[i].description, data[i].id);
        $('#load-rol').append(op);
    }
}

function loadUser() {
	console.log('Buscar datos de: ' + $('#load-user').val());
	validateUser('buscar', $('#load-user').val());
}

function cleanForm() {
	$("#form-add-user")[0].reset();
}