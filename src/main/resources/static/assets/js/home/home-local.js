/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de inicio
 */

// Cargando sistema
jQuery(document).ready(function(jQuery) {
	// ZOOM Div
	// $('#content-main').animate({ 'zoom': 0.7 }, 'slow');
	document.body.style.zoom = "70%";
	//
	startingSystemMenu('home');
	// Creando tableros dinamicos
	createDynamicBoard('6');
	// Iniciar Yammer Social
	yammerSocial('light');
	//
	viewRecord();
	//
	$('#btn-cancel-employee').on('click', function () {
		$('#content-dashboard').css('display', 'none');
		$('#main-content').css('display', 'block');
	});
	//
	showDivMessage('Para estar actualizado en la red de GrupoBeIT es necesario estar logueado en <a href="https://portal.office.com" target="_blank">Office365</a>', 'alert-info', 15000);
});

function record() {
	// Modal Recorrido
	openModalRecordPortal();
}

function openModalRecordPortal() {
	$("#modal-record-portal").modal({
		modal:open,
		showClose: false
	});
}

function viewContent(message) {
	$('#content-dashboard').css('display', 'block');
	$('#main-content').css('display', 'none');
	$('#title-image').text(message);
}