/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de seguimiento PLAN DE CARRERA
 */
var ID_EMPLOYEE 			= 0;
var EMAIL_EMPLOYEE 			= '';
//
var EMPLOYEE_GRAL_USER_ID 	= '';
var ID_EMPLOYEE_GRAL 		= '';
//Archivo
var NAME_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS			= '';
var FILE_EMPLOYEE_FILES_SYSTEM_OPEN_CERTIFICATIONS			= '';
var NAME_FILE_DOWNLOAD										= '';
// Cargando sistema
$(document).ready(function($) {
	// Validar si datos basicos estan llenos
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeGralExist', null, respFindEmployeeGralExist);
	//
	startingSystemMenu();
	// Limpiar variables
	cleanGeneralVariables();
	//Llenar tabla empleados
	initTable();
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
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeTableStatusCoursesByUsers', null, respFindAll);
}

function respFindAll(data){
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table-plan"			'
							+ ' 	class="table mb-0 table-striped">			'
							+ ' <thead>                                         '
										
							+ ' 	<tr>                                        '
							
							+ ' 		<th>Acciones			</th>			'
							
							+ ' 		<th>Nombre				</th>			'
							+ ' 		<th>Correo				</th>			'
							
							+ ' 		<th>Fase				</th>			'
							+ ' 		<th>Bloque				</th>			'
							+ ' 		<th>Tecnologia			</th>			'
							+ ' 		<th>Producto			</th>			'
							+ ' 		<th>Estatus				</th>			'
							+ ' 		<th>Duración			</th>			'
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar solo los que faltan por aprobar
			if(rowEmployee.completed == 'FALTA APROBACION') {
				// Validar botones
				var enableEdit
				enableEdit = '<i class="fa fa-check btn btn-success" title="Aprobar" onclick="approveEmployeeRequestCourse(\''	+ rowEmployee.tec_prod 				+ '\',' 
																																	+ '\'' + rowEmployee.email	+ '\',' 
																																	+ '\'' + rowEmployee.name + ' ' + rowEmployee.last_name	+ '\',' 
																																	+ '\'' + rowEmployee.employee_gral_user_id	+ '\',' 
																																	+ '\'' + rowEmployee.id_employee_gral		+ '\','
																																	+ '\'' + rowEmployee.id_request_of_courses	+ '\''
																																	+ ')" style="cursor: pointer"></i>';
				enableEdit += '<i class="fa fa-times btn btn-danger" title="Rechazar" onclick="reproveEmployeeRequestCourse(\''	+ rowEmployee.tec_prod 				+ '\',' 
																																	+ '\'' + rowEmployee.employee_gral_user_id	+ '\',' 
																																	+ '\'' + rowEmployee.id_employee_gral		+ '\''
																																	+ '\'' + rowEmployee.id_request_of_courses	+ '\''
																																	+ ')" style="cursor: pointer"></i>';
				var actions = enableEdit;

				var tr = '<tr id="employeePlan_' + rowEmployee.id + '">';
				tr += '<td align="center">'+ actions +'</td>';
				
				tr += '<td>'+ rowEmployee.name + ' ' + rowEmployee.last_name	+'</td>';
				tr += '<td>'+ rowEmployee.email									+'</td>';
				
				tr += '<td>'+ rowEmployee.fase_name		+'</td>';
				tr += '<td>'+ rowEmployee.block_name	+'</td>';
				tr += '<td>'+ rowEmployee.tec_name		+'</td>';
				tr += '<td>'+ rowEmployee.tec_prod		+'</td>';
				
				tr += '<td class="table-danger">'+ rowEmployee.completed		+'</td>';
				tr += '<td class="table-danger">'+ rowEmployee.hr_send			+'</td>';
				
				tr += '</tr>';
				tableEmployee += tr;
			}
		}
		var wrapper = $("#seccion-table-employee");
		$(wrapper).append(tableEmployee); // add html
		//
		exportTable('bootstrap-data-table-plan', 
				[
					{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
				]);
		reloadTable('bootstrap-data-table-plan');
		if(data.length == 0) {
			msgEmptyTable('Sin registros - Plan de carrera');
		}
	}
}

function approveEmployeeRequestCourse(tec_prod, email, name, employee_gral_user_id, id_employee_gral, id_request_of_courses) {
	//
	EMPLOYEE_GRAL_USER_ID = employee_gral_user_id;
	ID_EMPLOYEE_GRAL = id_employee_gral;
	confirmSaveEmployeeRequestCourse(tec_prod, email, name, id_request_of_courses);
}

function confirmSaveEmployeeRequestCourse(tec_prod, email, name, id_request_of_courses) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': '¿ Seguro de aprobar tecnologia [<strong>' + tec_prod + '</strong>] de <strong>' + name + '</strong>?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	showPreloader(true);
        	approveRequestCourse(tec_prod, email, name, id_request_of_courses);
        }
    });
}

function approveRequestCourse(tec_prod, email, name, id_request_of_courses) {
	// Procesar a modificar 
	var dataBody = {
		tecProd: tec_prod,
		email: email,
		name: name,
		idRequestOfCourses: id_request_of_courses
	}
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'approveRequestCourse', dataBody, respApproveRequestCourse);
}

function respApproveRequestCourse(data) {
	showPreloader(false);
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 4000);
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}