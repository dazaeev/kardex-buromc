/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de consulta Empleados todos
 */
var ID_EMPLOYEE_STUDIES = 0;
var ID_OPEN_CERTIFICATIONS = 0;
var ID_CLOSE_CERTIFICATIONS = 0;
// Archivo
var NAME_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS			= '';
var FILE_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS			= '';
var NAME_FILE_DOWNLOAD										= '';
//
var TYPE_CERTIFICATION = '';
// Cargando sistema
$(document).ready(function($) {
	// Validar si datos basicos estan llenos
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeGralExist', null, respFindEmployeeGralExist);
	//
	startingSystemMenu();
	// Limpiar variables
	cleanGeneralVariables();
	// Llenar tabla empleados
	initTable();
	//
	$('#btn-cancel-employee').on('click', function () {
		ID_EMPLOYEE_STUDIES = 0;
		ID_OPEN_CERTIFICATIONS = 0;
		ID_CLOSE_CERTIFICATIONS = 0;
		cleanForm();
		cleanFormCertification();
	    hideAddEmployee();
	});
	//
	$('#btn-save-employee').on('click', function () {
        console.log('Guardar empleado');
        $("#form-add-employee").submit();
    });
	//
	$('#btn-save-certificate').on('click', function () {
        console.log('Guardar Certificación');
        $("#form-add-certificate-employee").submit();
    });
	//
	$('#btn-add-certification').on('click', function () {
        console.log('Agregar Certificación Vigente');
        $('#modal-info-action').text('Agregar');
        $('#modal-info-sub-action').text('Certificaciones Vigentes');
        openModalAddCertificate();
    	cleanFormCertification();
    	ID_OPEN_CERTIFICATIONS = 0;
    	ID_CLOSE_CERTIFICATIONS = 0;
    	//
    	TYPE_CERTIFICATION = 'VIGENTE';
    });
	//
	$('#btn-add-certification-close').on('click', function () {
        console.log('Agregar Certificación Vencida');
        $('#modal-info-action').text('Agregar');
        $('#modal-info-sub-action').text('Certificaciones Vencidas');
        openModalAddCertificate();
    	cleanFormCertification();
    	ID_OPEN_CERTIFICATIONS = 0;
    	ID_CLOSE_CERTIFICATIONS = 0;
    	//
    	TYPE_CERTIFICATION = 'VENCIDA';
    });
	//
	$('#btn-cancel-view-employee').on('click', function () {
        hideViewDocEmployee();
    });
	//
	$('#btn-download-view-employee').on('click', function () {
        viewDocument(0, name, 'download', TYPE_CERTIFICATION, 'download');
    });
	//
	$("#form-add-employee").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario certificación invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
		} else {
			showPreloader(true);
			saveEmployee();
			//
			cleanForm();
			cleanFormCertification();
			//
			showOpenCertifications();
			showCloseCertifications();
			//
			hideAddEmployee();
			return false;
		}
	});
	//
	$("#form-add-certificate-employee").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
			$('#modal-info-action').text('* Archivo requerido');
			$('#modal-info-action').css('color', 'red');
		} else {
			// Validar tipo de certificacion
			if(TYPE_CERTIFICATION == 'VIGENTE') {
				console.log('Proceso de guardado vigente.');
			}
			if(TYPE_CERTIFICATION == 'VENCIDA') {
				console.log('Proceso de guardado vencido.');
			}
			saveEmployeeCertificate();
			cleanFormCertification();
			// 
	        return false;
		}
	});
});

function cleanGeneralVariables() {
	// Nombre
	NAME_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS			= '';
	// Contenido base64
	FILE_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS			= '';
	// Nombre para descarga
	NAME_FILE_DOWNLOAD										= '';
}

function initTable() {
	// iniciar contenido tabla
	$("#seccion-table-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeStudies', null, respFindAll);
}

function respFindAll(data){
	// $('body').toggleClass('open');
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							
							+ ' 	<tr>                                       								'
							+ ' 		<th class="table-primary" colspan="2">Información</th>				'
							+ ' 		<th class="table-warning" colspan="3">Último</th>					'
							+ ' 		<th class="table-danger" colspan="3">Anterior</th>					'
							+ ' 	</tr>                                       							'
							
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							+ ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Colegio/Instituto/Universidad							</th>                          '
							+ ' 		<th>Nivel de estudios										</th>                          '
							+ ' 		<th>Estatus													</th>                          '
							+ ' 		<th>Colegio/Instituto/Universidad							</th>                          '
							+ ' 		<th>Nivel de estudios										</th>                          '
							+ ' 		<th>Estatus													</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				// enabled = 'style="display: none;"';
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información de Estudios"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployee(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.id + ',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployee(\'' 	+ rowEmployee.id 										+ '\',' 
																									+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\',' 
																									+ '\'' + rowEmployee.idStudies		 				   	+ '\',' 
																									+ '\'' + rowEmployee.schoolLatest 				   		+ '\',' 
																									+ '\'' + rowEmployee.educationalLevelLatest 			+ '\',' 
																									+ '\'' + rowEmployee.statusLatest						+ '\',' 
																									+ '\'' + rowEmployee.schoolPrevious						+ '\',' 
																									+ '\'' + rowEmployee.educationalLevelPrevious			+ '\',' 
																									+ '\'' + rowEmployee.statusPrevious						+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			tr += '<td>'+ rowEmployee.name + ' '+ rowEmployee.lastName +'</td>';
			
			tr += '<td>'+ rowEmployee.schoolLatest				+'</td>';
			tr += '<td>'+ rowEmployee.educationalLevelLatest	+'</td>';
			tr += '<td>'+ rowEmployee.statusLatest				+'</td>';
			tr += '<td>'+ rowEmployee.schoolPrevious			+'</td>';
			tr += '<td>'+ rowEmployee.educationalLevelPrevious	+'</td>';
			tr += '<td>'+ rowEmployee.statusPrevious			+'</td>';
			
			tr += '</tr>';
			tableEmployee += tr;
		}
		tableEmployee += '</tbody>'
						+ ' </table>';
	}
	
	var wrapper = $("#seccion-table-employee");
	$(wrapper).append(tableEmployee); // add html
	//
	exportTable('bootstrap-data-table', 
			[
				{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
			]);
	reloadTable('bootstrap-data-table');
}

function editEmployee(id, name, idStudies, schoolLatest, educationalLevelLatest, statusLatest, schoolPrevious, educationalLevelPrevious, statusPrevious) {
	$('#add-mod-employee').text('Información de ' + name);
	//
	ID_EMPLOYEE_STUDIES = id;
	cleanForm();
	cleanFormCertification();
	//
	$('#school-latest').val(schoolLatest);
	$("#educational-level-latest option[value='"+ educationalLevelLatest +"']").prop('selected', true);
	$("#status-latest option[value='"+ statusLatest +"']").prop('selected', true);
	$('#school-previous').val(schoolPrevious);
	$("#educational-level-previous option[value='"+ educationalLevelPrevious +"']").prop('selected', true);
	$("#status-previous option[value='"+ statusPrevious +"']").prop('selected', true);
	//
	showAddEmployee();
	//
	if(idStudies > 0) {
		showOpenCertifications();
		showCloseCertifications();
		$('#info-certificate-employee').css('display', 'block');
		$('#info-certificate-close-employee').css('display', 'block');
	}
}


function confirmDeleteEmployee(name, id, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': 'Esta acción eliminara cualquier informacion relacionada con estudios (generales, certificaciones vigentes y certificaciones vencidas).</br>' 
        	+ '¿ Seguro de eliminar información de estudios de ' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	showPreloader(true);
        	activeEmployee(id, active);
        }
    });
}

function confirmDeleteEmployeeCertifications(name, id, typeCertification, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Precaución',
        'confirmQuestion': '¿ Esta usted seguro de eliminar permanentemente la certificación de ' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	TYPE_CERTIFICATION = typeCertification;
        	activeEmployeeCertifications(id, typeCertification, active);
        }
    });
}

function activeEmployee(id, active) {
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'enabledUserEmployeeStudies/' + id + '/' + active, null, respActiveEmployeeStudies);
}

function activeEmployeeCertifications(id, typeCertification, active) {
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'enabledUserEmployeeStudiesCertifications/' + id + '/' + active + '/' + typeCertification, null, respActiveEmployeeStudiesCertifications);
}

function respActiveEmployeeStudies(data) {
	showPreloader(false);
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 2000);
		// $('#bootstrap-data-table tr#employee_' + data[3]).remove();
		// window.location = '/adm/employees-gral.html';
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function respActiveEmployeeStudiesCertifications(data) {
	// Validar tipo de certificacion
	if(TYPE_CERTIFICATION == 'VIGENTE') {
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 2000);
			// $('#bootstrap-data-table tr#employee_' + data[3]).remove();
			// window.location = '/adm/employees-gral.html';
			initTableOpenCertifications();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	if(TYPE_CERTIFICATION == 'VENCIDA') {
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 2000);
			// $('#bootstrap-data-table tr#employee_' + data[3]).remove();
			// window.location = '/adm/employees-gral.html';
			initTableCloseCertifications();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
}

function showAddEmployee() {
	$('#info-employee').css('display', 'none');
	// $('#info-certificate-employee').css('display', 'block');
	// $('#info-certificate-close-employee').css('display', 'block');
	$('#add-employee').css('display', 'block');
    $("#btn-cancel-employee").show();
	$("#btn-save-employee").show();
}

function hideAddEmployee() {
	$('#info-employee').css('display', 'block');
	$('#info-certificate-employee').css('display', 'none');
	$('#info-certificate-close-employee').css('display', 'none');
	$('#add-employee').css('display', 'none');
    $("#btn-cancel-employee").hide();
	$("#btn-save-employee").hide();
}

function showViewDocEmployee() {
	$('#info-employee').css('display', 'none');
	$('#info-certificate-employee').css('display', 'none');
	$('#info-certificate-close-employee').css('display', 'none');
	$('#add-employee').css('display', 'none');
    $("#btn-cancel-employee").hide();
	$("#btn-save-employee").hide();
	
    $('#view-employee').css('display', 'block');
    $("#btn-cancel-view-employee").show();
    $("#btn-download-view-employee").show();
}

function hideViewDocEmployee() {
	$('#info-certificate-employee').css('display', 'block');
	$('#info-certificate-close-employee').css('display', 'block');
	$('#add-employee').css('display', 'block');
    $("#btn-cancel-employee").show();
	$("#btn-save-employee").show();
	
    $('#view-employee').css('display', 'none');
    $("#btn-cancel-view-employee").hide();
    $("#btn-download-view-employee").hide();
}

function saveEmployee() {
	var user = new User();
	user.id										= ID_EMPLOYEE_STUDIES;
	user.email									= sessionStorage.getItem("email");
	// Llenar Empleado
	var employeegral = new employeeGral();
	var employeestudies = new employeeStudies();
	employeestudies.schoolLatest 			= '' + $('#school-latest').val();
	employeestudies.educationalLevelLatest	= '' + $('#educational-level-latest').val();
	employeestudies.statusLatest 			= '' + $('#status-latest').val();
	employeestudies.schoolPrevious 			= '' + $('#school-previous').val();
	employeestudies.educationalLevelPrevious= '' + $('#educational-level-previous').val();
	employeestudies.statusPrevious 			= '' + $('#status-previous').val();
	employeestudies.active 					= 1;
	
	// Setear Empleado
	employeegral.employeeStudies = employeestudies;
	user.employee = employeegral;
	
	console.log(user);
	//
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'saveUserEmployeeStudies', user, respSaveEmployeeStudies);
}

function respSaveEmployeeStudies(data) {
	showPreloader(false);
	cleanGeneralVariables();
	//
	ID_EMPLOYEE_STUDIES = 0;
	ID_OPEN_CERTIFICATIONS = 0;
	ID_CLOSE_CERTIFICATIONS = 0;
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 3000);
		initTable();
		// Proceder a llenar Certificaciones
		alertInfo('Información', '<strong>' + data[2] + '</strong>' + '</br>No se olvide llenar alguna certificación</br>Vigente y/o Vencida', 'Entendido');
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function showOpenCertifications() {
	console.log('Iniciando Certificaciones Vigentes.');
	initTableOpenCertifications();
}

function showCloseCertifications() {
	console.log('Iniciando Certificaciones Vencidas.');
	initTableCloseCertifications();
}

function initTableOpenCertifications() {
	// iniciar contenido tabla
	$("#seccion-table-certificate-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeStudiesOpenCertifications/' + ID_EMPLOYEE_STUDIES, null, respFindOpenCertificationsAll);
}

function initTableCloseCertifications() {
	// iniciar contenido tabla
	$("#seccion-table-certificate-close-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeStudiesCloseCertifications/' + ID_EMPLOYEE_STUDIES, null, respFindCloseCertificationsAll);
}

function respFindOpenCertificationsAll(data){
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table-certificate"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							// + ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Certificación											</th>                          '
							+ ' 		<th>Descripción												</th>                          '
							+ ' 		<th>Fecha de vencimiento									</th>                          '
							+ ' 		<th>Documento												</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				// enabled = 'style="display: none;"';
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información de Certificaciones Vigentes"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployeeCertifications(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.idOpenCertifications + ',' + '\'' + 'VIGENTE' + '\',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployeeOpenCertifications(\'' + rowEmployee.id + '\',' 
																									+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\',' 
																									+ '\'' + rowEmployee.idOpenCertifications			   	+ '\',' 
																									+ '\'' + rowEmployee.certification	 				   	+ '\',' 
																									+ '\'' + rowEmployee.description						+ '\','
																									+ '\'' + rowEmployee.dateExpiration						+ '\','
																									+ '\'' + rowEmployee.nameCertification					+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			// tr += '<td>'+ rowEmployee.name + ' '+ rowEmployee.lastName +'</td>';
			
			tr += '<td>'+ rowEmployee.certification			+'</td>';
			tr += '<td>'+ rowEmployee.description			+'</td>';
			// Validar fecha de expiracion
			if(rowEmployee.dateExpirationInValid) {
				tr += '<td class="table-danger">'+ rowEmployee.dateExpiration		+'</td>';
			} else {
				tr += '<td>'+ rowEmployee.dateExpiration		+'</td>';
			}
			
			// -----------------------------------------------------------
			// ---------- Validar existencia de archivos -----------------
			// -----------------------------------------------------------
			if(rowEmployee.nameCertification) {
				tr += '<td>' 
						+ '<i class="fa fa-download btn btn-info" title="Ver o descargar documento \n' + splitGeneric(rowEmployee.nameCertification, '____', false, 0) + '" onclick="viewDocument(\'' 
								+ rowEmployee.id + '\','
								+ '\'' + rowEmployee.name + ' ' + rowEmployee.lastName 	+ '\','
								+ '\'' + rowEmployee.nameCertification + '\','
								+ '\'' + 'VIGENTE' + '\','
								+ '\'view\''
								+ ')" style="cursor: pointer"></i>'  
					+'</td>';
			} else {
				tr += '<td>'+ rowEmployee.nameCertification  +'</td>';
			}
			// -----------------------------------------------------------
			
			tr += '</tr>';
			tableEmployee += tr;
		}
		tableEmployee += '</tbody>'
						+ ' </table>';
	}
	
	var wrapper = $("#seccion-table-certificate-employee");
	$(wrapper).append(tableEmployee); // add html
	//
	exportTable('bootstrap-data-table-certificate', 
			[
				{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
			]);
	reloadTable('bootstrap-data-table-certificate');
}

function respFindCloseCertificationsAll(data){
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table-certificate-close"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							// + ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Certificación											</th>                          '
							+ ' 		<th>Descripción												</th>                          '
							+ ' 		<th>Fecha de vencimiento									</th>                          '
							+ ' 		<th>Documento												</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				// enabled = 'style="display: none;"';
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información de Certificaciones Vencidas"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployeeCertifications(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.idCloseCertifications + ',' + '\'' + 'VENCIDA' + '\',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployeeCloseCertifications(\'' + rowEmployee.id + '\',' 
																									+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\',' 
																									+ '\'' + rowEmployee.idCloseCertifications			   	+ '\',' 
																									+ '\'' + rowEmployee.certification	 				   	+ '\',' 
																									+ '\'' + rowEmployee.description						+ '\','
																									+ '\'' + rowEmployee.dateExpiration						+ '\','
																									+ '\'' + rowEmployee.nameCertification					+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			// tr += '<td>'+ rowEmployee.name + ' '+ rowEmployee.lastName +'</td>';
			
			tr += '<td>'+ rowEmployee.certification			+'</td>';
			tr += '<td>'+ rowEmployee.description			+'</td>';
			tr += '<td>'+ rowEmployee.dateExpiration		+'</td>';
			
			// -----------------------------------------------------------
			// ---------- Validar existencia de archivos -----------------
			// -----------------------------------------------------------
			if(rowEmployee.nameCertification) {
				tr += '<td>' 
						+ '<i class="fa fa-download btn btn-info" title="Ver o descargar documento \n' + splitGeneric(rowEmployee.nameCertification, '____', false, 0) + '" onclick="viewDocument(\'' 
								+ rowEmployee.id + '\','
								+ '\'' + rowEmployee.name + ' ' + rowEmployee.lastName 	+ '\','
								+ '\'' + rowEmployee.nameCertification + '\','
								+ '\'' + 'VENCIDA' + '\','
								+ '\'view\''
								+ ')" style="cursor: pointer"></i>'  
					+'</td>';
			} else {
				tr += '<td>'+ rowEmployee.nameCertification  +'</td>';
			}
			// -----------------------------------------------------------
			
			tr += '</tr>';
			tableEmployee += tr;
		}
		tableEmployee += '</tbody>'
						+ ' </table>';
	}
	
	var wrapper = $("#seccion-table-certificate-close-employee");
	$(wrapper).append(tableEmployee); // add html
	//
	exportTable('bootstrap-data-table-certificate-close', 
			[
				{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
			]);
	reloadTable('bootstrap-data-table-certificate-close');
}

function openModalAddCertificate() {
	$("#modal-add-certificate").modal({
		modal:open,
		showClose: false
	});
}

function editEmployeeOpenCertifications(id, name, idOpenCertifications, certification, description, dateExpiration, nameCertification) {
	$('#modal-info-action').text(certification);
	$('#modal-info-sub-action').text('Certificaciones Vigentes');
	//
	TYPE_CERTIFICATION = 'VIGENTE';
	//
	openModalAddCertificate();
	//
	ID_OPEN_CERTIFICATIONS = idOpenCertifications;
	//
	cleanFormCertification();
	$('#certification').val(certification);
	$('#description').val(description);
	$('#date-expiration').val(dateExpiration);
	//
	NAME_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS = nameCertification;
	//
}

function editEmployeeCloseCertifications(id, name, idCloseCertifications, certification, description, dateExpiration, nameCertification) {
	$('#modal-info-action').text(certification);
	$('#modal-info-sub-action').text('Certificaciones Vencidas');
	//
	TYPE_CERTIFICATION = 'VENCIDA';
	//
	openModalAddCertificate();
	//
	ID_CLOSE_CERTIFICATIONS = idCloseCertifications;
	//
	cleanFormCertification();
	$('#certification').val(certification);
	$('#description').val(description);
	$('#date-expiration').val(dateExpiration);
	//
	NAME_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS = nameCertification;
	//
}

function saveEmployeeCertificate() {
	console.log('Proceso de guardado para certificación');
	//
	var user = new User();
	user.id										= ID_EMPLOYEE_STUDIES;
	user.email									= sessionStorage.getItem("email");
	// Llenar Empleado
	var employeegral = new employeeGral();
	var employeestudies = new employeeStudies();
	employeestudies.active						= 1;
	// Validar tipo de certificacion -----------------------------------------------------------------------------------------
	if(TYPE_CERTIFICATION == 'VIGENTE') {
		console.log('Proceso de guardado vigente.');
		var opencertifications = new openCertifications();
		opencertifications.id						= ID_OPEN_CERTIFICATIONS;
		opencertifications.certification			= '' + $('#certification').val();
		opencertifications.description				= '' + $('#description').val();
		opencertifications.dateExpiration			= '' + $('#date-expiration').val();
		opencertifications.active					= 1;
		//
		if(NAME_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS) {
			// Editar
			if(castNameFileToTypeFile('file-certificate')) {
				opencertifications.nameCertification	= castNameFileToTypeFile('file-certificate');
			} else {
				opencertifications.nameCertification	= NAME_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS;
			}
		} else {
			// Guardar
			opencertifications.nameCertification	= castNameFileToTypeFile('file-certificate');
		}
		opencertifications.fileCertification	= FILE_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS;
		// Setear Empleado
		employeestudies.openCertifications			= [opencertifications];
		employeegral.employeeStudies = employeestudies;
		user.employee = employeegral;
		//
		console.log(user);
	}
	if(TYPE_CERTIFICATION == 'VENCIDA') {
		console.log('Proceso de guardado vencido.');
		var closecertifications = new closeCertifications();
		closecertifications.id						= ID_CLOSE_CERTIFICATIONS;
		closecertifications.certification			= '' + $('#certification').val();
		closecertifications.description				= '' + $('#description').val();
		closecertifications.dateExpiration			= '' + $('#date-expiration').val();
		closecertifications.active					= 1;
		//
		if(NAME_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS) {
			// Editar
			if(castNameFileToTypeFile('file-certificate')) {
				closecertifications.nameCertification	= castNameFileToTypeFile('file-certificate');
			} else {
				closecertifications.nameCertification	= NAME_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS;
			}
		} else {
			// Guardar
			closecertifications.nameCertification	= castNameFileToTypeFile('file-certificate');
		}
		closecertifications.fileCertification	= FILE_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS;
		// Setear Empleado
		employeestudies.closeCertifications			= [closecertifications];
		employeegral.employeeStudies = employeestudies;
		user.employee = employeegral;
		//
		console.log(user);
	}
	//
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'saveUserEmployeeStudiesCertificate/' + TYPE_CERTIFICATION, user, respSaveEmployeeStudiesCertificate);
}

function respSaveEmployeeStudiesCertificate(data) {
	cleanGeneralVariables();
	//
	// Validar tipo de certificacion
	if(TYPE_CERTIFICATION == 'VIGENTE') {
		ID_OPEN_CERTIFICATIONS = 0;
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 3000);
			initTableOpenCertifications();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	if(TYPE_CERTIFICATION == 'VENCIDA') {
		ID_CLOSE_CERTIFICATIONS = 0;
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 3000);
			initTableCloseCertifications();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	closeModal();
}

function setValueFile(inputFile) {
	// ----------------------------------------------------------------------------------------
	if (inputFile == 'file-certificate') {
		var local = $('#' + inputFile)[0].files[0];
		var readerFiles = new FileReader();
		readerFiles.onloadend = function() {
			FILE_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS = readerFiles.result;
		}
		if (local) {
			readerFiles.readAsDataURL(local);
		} else {
			FILE_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS = '';
		}
	}
	// ----------------------------------------------------------------------------------------
}

function viewDocument(id, name, documentEmployee, typeCertification, action) {
	// Ver o descargar documento
	$('#lTypeDocument').empty();
	//
	if(action == 'view') {
		NAME_FILE_DOWNLOAD = documentEmployee;
		TYPE_CERTIFICATION = typeCertification;
		// Ver documento
		var splitName = documentEmployee.split('____');
		var nameFile = '';
		if(splitName.length == 2) {
			nameFile = splitName[0];
		} else {
			nameFile = splitName[1];
		}
		$('#lTypeDocument').text(name + ' (' + nameFile + ')');
		//
		console.log('Ver o descargar documento: ' + documentEmployee);
		//
		$("#contentDocEmployee").empty();
		var splitId = id.split('_');
		if(splitId.length == 2) {
			ID_EMPLOYEE_STUDIES = splitId[0];
			ID_OPEN_CERTIFICATIONS = splitId[1];
			var model = {
					nameFile: documentEmployee,
					idUser: ID_EMPLOYEE_STUDIES,
					idOpenCertifications: ID_OPEN_CERTIFICATIONS
			}
			sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'viewEmployeeStudiesCertificate/' + typeCertification, model, respViewFiles);
		} else {
			showDivMessage('Error en visualizar archivo', 'alert-danger', 4000);
		}
	}
	if(action == 'download') {
		// Descargar documento
		var model = {
				nameFile: NAME_FILE_DOWNLOAD,
				idUser: ID_EMPLOYEE_STUDIES,
				idOpenCertifications: ID_OPEN_CERTIFICATIONS
		}
		sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'viewEmployeeStudiesCertificate/' + typeCertification, model, respDownloadFiles);
	}
}

function cleanForm() {
	$("#form-add-employee")[0].reset();
}

function cleanFormCertification() {
	$("#form-add-certificate-employee")[0].reset();
}