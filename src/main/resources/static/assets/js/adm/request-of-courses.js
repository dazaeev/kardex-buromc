/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de solicitud de cursos
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
	//
	$('#btn-cancel-employee').on('click', function () {
		//
		ID_EMPLOYEE = 0;
		EMPLOYEE_GRAL_USER_ID 	= '';
		ID_EMPLOYEE_GRAL 		= '';
		//
		hideAddEmployee();
		hideAddRequestEmployee();
	});
	//
	$('#btn-cancel-request').on('click', function () {
		cleanForm();
		hideAddRequestEmployee();
		$('#info-career-plan').css('display', 'block');
		$("#btn-cancel-employee").show();
		initTableValidateTechnologies(EMAIL_EMPLOYEE);
	});
	$('#btn-save-request').on('click', function () {
        console.log('Guardar solicitud');
        $("#form-add-request").submit();
    });
	$("#form-add-request").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario solicitud invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
		} else {
			showPreloader(true);
			$('#info-career-plan').css('display', 'block');
			saveRequest();
			cleanForm();
			//
			hideAddRequestEmployee();
	        return false;
		}
	});
	//
	$('#btn-save-certificate').on('click', function () {
        console.log('Guardar Certificación');
        $("#form-add-certificate-employee").submit();
    });
	$("#form-add-certificate-employee").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
			$('#modal-info-action').text('* Archivo requerido');
			$('#modal-info-action').css('color', 'red');
			return true;
		} else {
			//
			saveEmployeeCertificate();
			cleanFormCertification();
			// 
	        return false;
		}
	});
	//
});

function saveRequest() {
	// Llenar Empleado
	var employeegral = new employeeGral();
	employeegral.id = ID_EMPLOYEE_GRAL;
	//
	var requestofcourses = new requestOfCourses();
	requestofcourses.nameRequestProgram			        = '' + $('#name-request-program').val();
	requestofcourses.duration					        = '' + $('#duration').val();
	requestofcourses.modality       			        = '' + $('#modality').val();
	requestofcourses.nameProvider   			        = '' + $('#name-provider').val();
	requestofcourses.rfcProvider    			        = '' + $('#rfc-provider').val();
	requestofcourses.taxResidence   			        = '' + $('#tax-residence').val();
	requestofcourses.contactInfo    			        = '' + $('#contact-info').val();
	requestofcourses.startDate      			        = '' + $('#start-date').val();
	requestofcourses.endDate        			        = '' + $('#end-date').val();
	requestofcourses.placeCurse		     		        = '' + $('#place-curse').val();
	requestofcourses.costWoTax     		 		        = '' + $('#cost-wo-tax').val();
	requestofcourses.costWTax       			        = '' + $('#cost-w-tax').val();
	requestofcourses.objetivesCourse			        = '' + $('#objetives-course').val();
	requestofcourses.objetivesCourseRelatedJobPlace		= '' + $('#objetives-course-related-job-place').val();
	requestofcourses.technicalJustification		        = '' + $('#technical-justification').val();
	requestofcourses.company1					        = '' + $('#company1').val();
	requestofcourses.justification1				        = '' + $('#justification1').val();
	requestofcourses.company2		      		        = '' + $('#company2').val();
	requestofcourses.justification2				        = '' + $('#justification2').val();
	requestofcourses.company3 		     		        = '' + $('#company3').val();
	requestofcourses.justification3				        = '' + $('#justification3').val();
	requestofcourses.active						        = 1;
	requestofcourses.employeeGral						= employeegral;
	//
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'saveRequestOfCourses/' + EMPLOYEE_GRAL_USER_ID, requestofcourses, respSaveRequestOfCourses);
}

function respSaveRequestOfCourses(data) {
	showPreloader(false);
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 3000);
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
	cleanForm();
	hideAddRequestEmployee();
	$('#info-career-plan').css('display', 'block');
	$("#btn-cancel-employee").show();
	initTableValidateTechnologies(EMAIL_EMPLOYEE);
}


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
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeRequestOfCourses', null, respFindAll);
}

function respFindAll(data){
	// $('body').toggleClass('open');
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							
							+ ' 		<th>Nombre Completo 										</th>                          '
							
							+ ' 		<th>Área			</th>				'
							+ ' 		<th>Plan de Carrera	</th>				'
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var actions =
				'<i class="fa fa-search btn btn-info" title="Ir a plan de carrera" onclick="viewEmployee(\''	+ rowEmployee.id 										+ '\',' 
																												+ '\'' + rowEmployee.name + ' ' + rowEmployee.lastName  + '\',' 
																												+ '\'' + rowEmployee.descriptionCatalogAreaAux 			+ '\',' 
																												+ '\'' + rowEmployee.nameCertificationTrackAux 			+ '\',' 
																												+ '\'' + rowEmployee.email					 			+ '\''
																												+ ')" style="cursor: pointer"></i>';
			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			tr += '<td>'+ rowEmployee.name + ' ' + rowEmployee.lastName +'</td>';
			
			tr += '<td>'+ rowEmployee.descriptionCatalogArea		+'</td>';
			tr += '<td>'+ rowEmployee.nameCertificationTrack		+'</td>';
			
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

function viewEmployee(id, name, descriptionCatalogArea, nameCertificationTrack, email) {
	$('#add-mod-employee').text(descriptionCatalogArea + ': ' + name);
	//
	ID_EMPLOYEE = id;
	showAddEmployee();
	//
	EMAIL_EMPLOYEE = email;
	initTableValidateTechnologies(email);
	//
}

function initTableValidateTechnologies(email) {
	// iniciar contenido tabla
	$("#seccion-career-plan").empty();
	// Llenar tabla empleados
	var dataBody = {
		email: email
	}
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeTableValidateTechnologies', dataBody, respFindEmployeeTableValidateTechnologies);
}

function respFindEmployeeTableValidateTechnologies(data) {
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table-plan"			'
							+ ' 	class="table mb-0 table-striped">			'
							+ ' <thead>                                         '
										
							+ ' 	<tr>                                        '
							
							+ ' 		<th>Acciones			</th>			'
							
							+ ' 		<th>Fase				</th>			'
							+ ' 		<th>Bloque				</th>			'
							+ ' 		<th>Tecnologia			</th>			'
							+ ' 		<th>Producto			</th>			'
							+ ' 		<th>Estatus				</th>			'
							+ ' 		<th>Hr completadas		</th>			'
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enableEdit
			if(rowEmployee.completed == 'SI') {
				// enabled = 'style="display: none;"';
				enableEdit = '<i class="fa fa-check btn btn-default" title="Tu curso se a completado."></i>';
			} else if(rowEmployee.completed == 'AGREGAR ARCHIVO') {
				enableEdit = '<i class="fa fa-upload btn btn-warning" title="Subir certificación." onclick="addFileCertificate(\''	+ rowEmployee.tec_prod 				+ '\',' 
																																+ '\'' + rowEmployee.employee_gral_user_id	+ '\',' 
																																+ '\'' + rowEmployee.id_employee_gral		+ '\''
																																+ ')" style="cursor: pointer"></i>';
			} else if(rowEmployee.completed == 'FALTA APROBACION') {
				enableEdit = '<i class="fa fa-legal btn btn-default" title="Falta aprovación."></i>';
			} else {
				enableEdit = '<i class="fa fa-mail-forward btn btn-info" title="Solicitar." onclick="editEmployeeRequestCourse(\''	+ rowEmployee.tec_prod 				+ '\',' 
																																	+ '\'' + rowEmployee.employee_gral_user_id	+ '\',' 
																																	+ '\'' + rowEmployee.id_employee_gral		+ '\''
																																	+ ')" style="cursor: pointer"></i>';
			}
			var actions = enableEdit;

			var tr = '<tr id="employeePlan_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			
			tr += '<td>'+ rowEmployee.fase_name		+'</td>';
			tr += '<td>'+ rowEmployee.block_name	+'</td>';
			tr += '<td>'+ rowEmployee.tec_name		+'</td>';
			tr += '<td>'+ rowEmployee.tec_prod		+'</td>';
			if(rowEmployee.completed == 'SI') {
				tr += '<td class="table-success">'+ 'COMPLETADO' 				+'</td>';
				tr += '<td class="table-success">'+ rowEmployee.hr_completed	+'</td>';
			} else if(rowEmployee.completed == 'AGREGAR ARCHIVO') {
				tr += '<td class="table-warning">'+ rowEmployee.completed		+'</td>';
				tr += '<td class="table-warning">'+ rowEmployee.hr_completed	+'</td>';
			} else if(rowEmployee.completed == 'FALTA APROBACION') {
				tr += '<td class="table-danger">'+ rowEmployee.completed		+'</td>';
				tr += '<td class="table-danger">'+ rowEmployee.hr_completed		+'</td>';
			} else {
				tr += '<td class="table-secondary">'+ rowEmployee.completed		+'</td>';
				tr += '<td class="table-secondary">'+ rowEmployee.hr_completed	+'</td>';
			}
			
			tr += '</tr>';
			tableEmployee += tr;
		}
		var wrapper = $("#seccion-career-plan");
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

function addFileCertificate(tec_prod, employee_gral_user_id, id_employee_gral) {
	//
	EMPLOYEE_GRAL_USER_ID = employee_gral_user_id;
	ID_EMPLOYEE_GRAL = id_employee_gral;
	//
	$('#modal-info-action').text('Anexar');
    $('#modal-info-sub-action').text('Documentación de ' + tec_prod);
	openModalAddCertificate();
}

function openModalAddCertificate() {
	$("#modal-add-certificate").modal({
		modal:open,
		showClose: false
	});
}

function editEmployeeRequestCourse(tec_prod, employee_gral_user_id, id_employee_gral) {
	//
	EMPLOYEE_GRAL_USER_ID = employee_gral_user_id;
	ID_EMPLOYEE_GRAL = id_employee_gral;
	$('#name-request-program').val(tec_prod);
	//
	$('#info-career-plan').css('display', 'none');
	showAddRequestEmployee();
	$("#btn-cancel-employee").hide();
}

function showAddEmployee() {
	$('#info-employee').css('display', 'none');
	$('#info-career-plan').css('display', 'block');
    $('#add-employee').css('display', 'block');
    $("#btn-cancel-employee").show();
}

function hideAddEmployee() {
	$('#info-employee').css('display', 'block');
	$('#info-career-plan').css('display', 'none');
    $('#add-employee').css('display', 'none');
    $("#btn-cancel-employee").hide();
}

function showAddRequestEmployee() {
	$('#add-request').css('display', 'block');
    $("#btn-cancel-request").show();
    $("#btn-save-request").show();
}

function hideAddRequestEmployee() {
	$('#add-request').css('display', 'none');
    $("#btn-cancel-request").hide();
    $("#btn-save-request").hide();
    cleanForm();
}

function cleanForm() {
	$("#form-add-request")[0].reset();
}

function priceCurse(){
	var x = $("#free-or-paid").val();
	if(x =="0" || x=="1"){
		$('#nm-cost-wo-tax').css('display', 'none');
		$('#ta-cost-wo-tax').css('display', 'none');
		$('#nm-cost-w-tax').css('display', 'none');
		$('#ta-cost-w-tax').css('display', 'none');
		$("#cost-w-tax").prop('required', false);
		$("#cost-wo-tax").prop('required', false);
	}
	else if(x =="2"){
		$('#nm-cost-wo-tax').css('display', 'block');
		$('#ta-cost-wo-tax').css('display', 'block');
		$('#nm-cost-w-tax').css('display', 'block');
		$('#ta-cost-w-tax').css('display', 'block');
		$("#cost-w-tax").prop('required',true);
		$("#cost-wo-tax").prop('required',true);
	}
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

function cleanFormCertification() {
	$("#form-add-certificate-employee")[0].reset();
}