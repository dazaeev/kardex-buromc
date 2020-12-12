/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de control "employee_labor"
 */
var ID_EMPLOYEE_LABOR = 0;
var ID_HISTORY_JOB = 0;
var ID_EMPLOYEE_VACATIONS = 0;
//
var TYPE_JOB = '';
// Cargando sistema
$(document).ready(function($) {
	// Validar si datos basicos estan llenos
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeGralExist', null, respFindEmployeeGralExist);
	//
	startingSystemMenu();
	// Llenar tabla empleados
	initTable();
	//
	$('#btn-cancel-employee').on('click', function () {
		ID_EMPLOYEE_LABOR = 0;
		ID_HISTORY_JOB = 0;
		cleanForm();
		cleanFormHistory();
	    hideAddEmployee();
	});
	//
	$('#btn-save-employee').on('click', function () {
        console.log('Guardar empleado');
        $("#form-add-employee").submit();
    });
	//
	$('#btn-save-history').on('click', function () {
        console.log('Guardar Puesto y Salario');
        $("#form-add-history-employee").submit();
    });
	//
	$('#btn-save-vacations').on('click', function () {
        console.log('Guardar días de vacaciones');
        $("#form-add-vacations").submit();
    });
	//
	$('#editEmployeeVacations').on('click', function () {
        console.log('Abriendo model días de vacaciones');
        $("#modal-add-vacations").modal({
    		modal:open,
    		showClose: false
    	});
    });
	//
	$('#btn-add-certification').on('click', function () {
        console.log('Historial del Puesto y Salario');
        $('#modal-info-action').text('Agregar');
        $('#modal-info-sub-action').text('Historial del Puesto y Salario');
        openModalAddHistory();
    	cleanFormHistory();
    	ID_HISTORY_JOB = 0;
    	//
    	TYPE_JOB = 'HISTORY';
    });
	//
	$('#btn-add-holiday').on('click', function () {
        console.log('Días de vacaciones');
        $("#modal-add-vacations").modal({
    		modal:open,
    		showClose: false
    	});
    	ID_EMPLOYEE_VACATIONS = 0;
    });
	//
	$("#form-add-employee").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario certificación invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
		} else {
			showPreloader(true);
			saveEmployee();
			cleanForm();
			cleanFormHistory();
			//
	        hideAddEmployee();
	        return false;
		}
	});
	//
	$("#form-add-history-employee").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
			$('#modal-info-action').text('* Completar campos obligatorios');
			$('#modal-info-action').css('color', 'red');
		} else {
			// Validar tipo de certificacion
			if(TYPE_JOB == 'HISTORY') {
				console.log('Proceso de guardado Historial del Puesto y Salario.');
			}
			saveEmployeeHistory();
			cleanFormHistory();
			// 
	        return false;
		}
	});
	//
	$('#load-job-area').on('change', function() {
		loadJobWorkPlace(null);
	});
	$('#load-area').on('change', function() {
		loadWorkPlace(null);
	});
	// Cargar combo de Área
	loadArea();
	loadJobArea();
	// Cargar combo jefe inmediato
	loadBoss();

	//
	$("#form-add-vacations").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
			$('#modal-info-action').text('* Completar campos obligatorios');
			$('#modal-info-action').css('color', 'red');
		} else {
			saveEmployeeVacation();
			cleanFormVacations();
			// 
	        return false;
		}
	});
	//
	$('#datetimepicker12').datetimepicker({
		daysOfWeekDisabled: [0, 6],
		format: 'L',
		locale: 'es',
		inline : true,
		sideBySide : true
	});
	
});

function initTable() {
	// iniciar contenido tabla
	$("#seccion-table-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeLabor', null, respFindAll);
}

function respFindAll(data){
	// $('body').toggleClass('open');
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							+ ' 		<th>Id Empleado												</th>                          '
							+ ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Fecha de admisión										</th>                          '
							+ ' 		<th>Área													</th>                          '
							+ ' 		<th>SGMM													</th>                          '
							+ ' 		<th>No. de empleado											</th>                          '
							+ ' 		<th>Email corporativo										</th>                          '
							+ ' 		<th>Puesto													</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				// enabled = 'style="display: none;"';
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información Laboral"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployee(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.id + ',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployee(\'' 	+ rowEmployee.id 										+ '\',' 
																									+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\',' 
																									+ '\'' + rowEmployee.dateAdmission 				   		+ '\',' 
																									+ '\'' + rowEmployee.area 								+ '\',' 
																									+ '\'' + rowEmployee.policySgmm  						+ '\',' 
																									+ '\'' + rowEmployee.employeeNumber 					+ '\','
																									+ '\'' + rowEmployee.businessMail	 					+ '\','
																									+ '\'' + rowEmployee.position	 						+ '\','
																									+ '\'' + rowEmployee.dateShowVacations 					+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			tr += '<td>'+ rowEmployee.uuid								+'</td>';
			tr += '<td>'+ rowEmployee.name + ' '+ rowEmployee.lastName +'</td>';
			
			tr += '<td>'+ rowEmployee.dateAdmission			+'</td>';
			tr += '<td>'+ rowEmployee.area					+'</td>';
			tr += '<td>'+ rowEmployee.policySgmm			+'</td>';
			tr += '<td>'+ rowEmployee.employeeNumber		+'</td>';
			tr += '<td>'+ rowEmployee.businessMail			+'</td>';
			tr += '<td>'+ rowEmployee.position				+'</td>';
			
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

function editEmployee(id, name, dateAdmission, area, policySgmm, employeeNumber, businessMail, position, dateShowVacations) {
	$('#add-mod-employee').text('Información de ' + name);
	//
	ID_EMPLOYEE_LABOR = id;
	cleanForm();
	cleanFormHistory();
	$('#name').val(name);
	$('#date-admission').val(dateAdmission);
	$("#load-area option[value='"+ area +"']").prop('selected', true);
	$('#policy-sgmm').val(policySgmm);
	$('#employee-number').val(employeeNumber);
	$('#business-mail').val(businessMail);
	//
	if(position) {
		loadWorkPlace(position);
	} else {
		$('#load-position').empty();
	}
	//
	if(dateShowVacations=='true'){
		$('#info-vacations-employee').css('display', 'block');
	}
	else if(dateShowVacations==''){
		$('#info-vacations-employee').css('display', 'none');
	}
	//
	showHistoryJob();
	//
	showEmployeeVacations();
	//
	showAddEmployee();
	//
}


function confirmDeleteEmployee(name, id, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': 'Esta acción eliminara cualquier informacion relacionada con "Datos Laborales" (Historial del puesto y salario).</br>' 
        	+ '¿ Seguro de eliminar información laboral de ' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	activeEmployee(id, active);
        }
    });
}

function confirmDeleteEmployeeHistory(name, id, type, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Precaución',
        'confirmQuestion': '¿ Esta usted seguro de eliminar permanentemente "Puesto y Salario" de ' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	TYPE_JOB = type;
        	activeEmployeeHistory(id, type, active);
        }
    });
}

function activeEmployee(id, active) {
	showPreloader(true);
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'enabledUserEmployeeLabor/' + id + '/' + active, null, respActiveEmployeeLabor);
}

function activeEmployeeHistory(id, type, active) {
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'enabledUserEmployeeLaborHistory/' + id + '/' + active + '/' + type, null, respActiveEmployeeLaborHistory);
}

function respActiveEmployeeLabor(data) {
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

function respActiveEmployeeLaborHistory(data) {
	// Validar tipo de certificacion
	if(TYPE_JOB == 'HISTORY') {
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 2000);
			// $('#bootstrap-data-table tr#employee_' + data[3]).remove();
			// window.location = '/adm/employees-gral.html';
			initTableHistoryCertifications();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
}

function showEmployeeVacations() {
	console.log('Iniciando Vacaciones de empleado.');
	initTableVacations();
}

function initTableVacations() {
	// iniciar contenido tabla
	$("#seccion-table-vacations-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeVacations/'+ ID_EMPLOYEE_LABOR, null, respFindEmployeeVacationsAll);
}

function respFindEmployeeVacationsAll(data){
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table-vacations"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							// + ' 		<th>Nombre Completo											</th>                          '
							
							/*+ ' 		<th>Días por año											</th>                          '
							+ ' 		<th>Días tomados											</th>                          '
							+ ' 		<th>Días restantes											</th>                          '
							*/+ ' 		<th>Día tomado												</th>                          '
							+ ' 		<th>Razón de salida											</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			/*if(rowEmployee.enabled) {
				// enabled = 'style="display: none;"';
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información de Vacaciones"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployeeVacations(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.id + ',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployeeVacations(\'' + rowEmployee.id 						+ '\',' 
																									+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\',' 
																									+ '\'' + rowEmployee.idEmployeeVacations		+ '\',' 
																									+ '\'' + rowEmployee.daysForYear				+ '\',' 
																									+ '\'' + rowEmployee.daysTaken					+ '\',' 
																									+ '\'' + rowEmployee.daysPending				+ '\',' 
																									+ '\'' + rowEmployee.reasonOfExit				+ '\''
																									+ ')" style="cursor: pointer"></i>';*/
			
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" style="cursor: pointer" onclick="editEmployeeVacations(\'' 	+ rowEmployee.id 						+ '\',' 
																														+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\',' 
																														+ '\'' + rowEmployee.idEmployeeVacations		+ '\',' 
																														+ '\'' + rowEmployee.daysForYear				+ '\',' 
																														+ '\'' + rowEmployee.daysTaken					+ '\',' 
																														+ '\'' + rowEmployee.daysPending				+ '\',' 
																														+ '\'' + rowEmployee.holiday					+ '\',' 
																														+ '\'' + rowEmployee.reasonOfExit				+ '\''
																														+ ')" style="cursor: pointer"></i>';
			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			// tr += '<td>'+ rowEmployee.name + ' '+ rowEmployee.lastName +'</td>';
			
			/*tr += '<td>'+ rowEmployee.daysForYear			+'</td>';
			tr += '<td>'+ rowEmployee.daysTaken				+'</td>';
			tr += '<td>'+ rowEmployee.daysPending			+'</td>';
			*/tr += '<td>'+ rowEmployee.holiday				+'</td>';
			tr += '<td>'+ rowEmployee.reasonOfExit			+'</td>';
						
			tr += '</tr>';
			tableEmployee += tr;
		}
		tableEmployee += '</tbody>'
						+ ' </table>';
	}
	
	var wrapper = $("#seccion-table-vacations-employee");
	$(wrapper).append(tableEmployee); // add html
	//
	exportTable('bootstrap-data-table-vacations', 
			[
				{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
			]);
	reloadTable('bootstrap-data-table-vacations');
	//
	$('#idDPA').text("Días por año: "+ "6");
	if(data[0].idEmployeeVacations >0){
		$('#idDT').text("Días tomados: "+ data.length);
		$('#idDR').text("Días restantes: "+ (6-data.length));
	}	
	else if(data[0].idEmployeeVacations == 0){
		$('#idDT').text("Días tomados: "+ (1-data.length));
		$('#idDR').text("Días restantes: "+ (7-data.length));
	}
	//
	tblVtc();
	//
}

function editEmployeeVacations(id,name,idEmployeeVacations,daysForYear,daysTaken,daysPending,reasonOfExit){
	console.log('Abriendo model días de vacaciones');
	ID_EMPLOYEE_VACATIONS = idEmployeeVacations;
    $("#modal-add-vacations").modal({
		modal:open,
		showClose: false
	});
}

function confirmDeleteEmployeeVacations(name, id, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Precaución',
        'confirmQuestion': '¿ Esta usted seguro de eliminar permanentemente "Puesto y Salario" de ' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	activeEmployeeVacations(id, type, active);
        }
    });
}

function activeEmployeeVacations(id, type, active) {
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'enabledUserEmployeeVacations/' + id + '/' + active, null, respActiveEmployeeVacations);
}

function respActiveEmployeeVacations(data) {
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 2000);
		// $('#bootstrap-data-table tr#employee_' + data[3]).remove();
		// window.location = '/adm/employees-gral.html';
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function showAddEmployee() {
	$('#info-employee').css('display', 'none');
	$('#info-history-employee').css('display', 'block');
	$('#add-employee').css('display', 'block');
    $("#btn-cancel-employee").show();
	$("#btn-save-employee").show();
}

function hideAddEmployee() {
	$('#info-employee').css('display', 'block');
	$('#info-history-employee').css('display', 'none');
	$('#info-vacations-employee').css('display', 'none');
	$('#add-employee').css('display', 'none');
    $("#btn-cancel-employee").hide();
	$("#btn-save-employee").hide();
}

function saveEmployee() {
	var user = new User();
	user.id										= ID_EMPLOYEE_LABOR;
	user.email									= sessionStorage.getItem("email");
	// Llenar Empleado
	var employeegral = new employeeGral();
	var employeelabor = new employeeLabor();
	employeelabor.dateAdmission		= '' + $('#date-admission').val();
	employeelabor.area				= '' + $('#load-area').val();
	employeelabor.policySgmm		= '' + $('#policy-sgmm').val();
	employeelabor.employeeNumber	= '' + $('#employee-number').val();
	employeelabor.businessMail		= '' + $('#business-mail').val();
	employeelabor.position			= '' + $("#load-position").val();
	employeelabor.active			= 1;
	
	// Setear Empleado
	employeegral.employeeLabor = employeelabor;
	user.employee = employeegral;
	
	console.log(user);
	//
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'saveUserEmployeeLabor', user, respSaveEmployeeLabor);
}

function respSaveEmployeeLabor(data) {
	showPreloader(false);
	ID_EMPLOYEE_LABOR = 0;
	ID_HISTORY_JOB = 0;
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 3000);
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function showHistoryJob() {
	console.log('Iniciando Historial del puesto y salario.');
	initTableHistoryCertifications();
}

function initTableHistoryCertifications() {
	// iniciar contenido tabla
	$("#seccion-table-history-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeLaborHistory/' + ID_EMPLOYEE_LABOR, null, respFindHistoryJobAll);
}

function respFindHistoryJobAll(data){
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table-history"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							// + ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Nombre del puesto										</th>                          '
							+ ' 		<th>Fecha inicio											</th>                          '
							+ ' 		<th>Fecha fin												</th>                          '
							+ ' 		<th>Área													</th>                          '
							+ ' 		<th>Jefe inmediato											</th>                          '
							+ ' 		<th>Salario inicial											</th>                          '
							+ ' 		<th>Salario final											</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				// enabled = 'style="display: none;"';
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información de Historial del Puesto y Salario"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployeeHistory(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.idHistoryJob + ',' + '\'' + 'HISTORY' + '\',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployeeHistoryJob(\'' + rowEmployee.id + '\',' 
																									+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\',' 
																									+ '\'' + rowEmployee.idHistoryJob		+ '\',' 
																									+ '\'' + rowEmployee.jobTitle			+ '\',' 
																									+ '\'' + rowEmployee.dateIn				+ '\',' 
																									+ '\'' + rowEmployee.dateOu				+ '\',' 
																									+ '\'' + rowEmployee.jobArea			+ '\',' 
																									+ '\'' + rowEmployee.immediateBoss		+ '\',' 
																									+ '\'' + rowEmployee.salaryIn			+ '\',' 
																									+ '\'' + rowEmployee.salaryOu			+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			// tr += '<td>'+ rowEmployee.name + ' '+ rowEmployee.lastName +'</td>';
			
			tr += '<td>'+ rowEmployee.jobTitle			+'</td>';
			tr += '<td>'+ rowEmployee.dateIn			+'</td>';
			tr += '<td>'+ rowEmployee.dateOu			+'</td>';
			tr += '<td>'+ rowEmployee.jobArea			+'</td>';
			tr += '<td>'+ rowEmployee.immediateBoss		+'</td>';
			tr += '<td>'+ rowEmployee.salaryIn			+'</td>';
			tr += '<td>'+ rowEmployee.salaryOu			+'</td>';
						
			tr += '</tr>';
			tableEmployee += tr;
		}
		tableEmployee += '</tbody>'
						+ ' </table>';
	}
	
	var wrapper = $("#seccion-table-history-employee");
	$(wrapper).append(tableEmployee); // add html
	//
	exportTable('bootstrap-data-table-history', 
			[
				{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
			]);
	reloadTable('bootstrap-data-table-history');
}

function openModalAddHistory() {
	$("#modal-add-history").modal({
		modal:open,
		showClose: false
	});
}

function editEmployeeHistoryJob(id, name, idHistoryJob, jobTitle, dateIn, dateOu, jobArea, immediateBoss, salaryIn, salaryOu) {
	$('#modal-info-action').text(jobTitle);
	$('#modal-info-sub-action').text('Puesto y Salario');
	//
	TYPE_JOB = 'HISTORY';
	//
	openModalAddHistory();
	//
	ID_HISTORY_JOB = idHistoryJob;
	//
	cleanFormHistory();
	$('#date-in').val(dateIn);
	$('#date-ou').val(dateOu);
	$("#load-job-area option[value='"+ jobArea +"']").prop('selected', true);
	$("#load-immediate-boss option[value='"+ immediateBoss +"']").prop('selected', true);
	$('#salary-in').val(salaryIn);
	$('#salary-ou').val(salaryOu);
	//
	if(jobTitle) {
		loadJobWorkPlace(jobTitle);
	} else {
		$('#load-job-title').empty();
	}
}

function saveEmployeeVacation() {
	if($("#bootstrap-data-table-vacations tr").length<7 || ID_EMPLOYEE_VACATIONS > 0){
		console.log('Proceso de guardado de vacaciones');
		//
		var user = new User();
		user.id										= ID_EMPLOYEE_LABOR;
		user.email									= sessionStorage.getItem("email");
		// Llenar Empleado
		var employeegral = new employeeGral();
		var employeelabor = new employeeLabor();
		employeelabor.active = 1;
		console.log('Proceso de guardado vacaciones.');
		var employeevacations = new employeeVacations();
		employeevacations.id = ID_EMPLOYEE_VACATIONS;
		employeevacations.reasonOfExit = '' + $('#reason-of-exit').val();
		if(ID_EMPLOYEE_VACATIONS>0){
			employeevacations.daysTaken = $("#bootstrap-data-table-vacations")[0].rows["employee_1_"+ID_EMPLOYEE_VACATIONS].cells[2].innerText;
			employeevacations.daysPending = $("#bootstrap-data-table-vacations")[0].rows["employee_1_"+ID_EMPLOYEE_VACATIONS].cells[3].innerText;
		}
		else{
			if($("#bootstrap-data-table-vacations tr").length <= 2){
				if($("#bootstrap-data-table-vacations")[0].rows[1].innerText !=""){
					employeevacations.daysTaken = $("#bootstrap-data-table-vacations tr").length;	
					employeevacations.daysPending = 6-($("#bootstrap-data-table-vacations tr").length);	
				}
				else{
					employeevacations.daysTaken = $("#bootstrap-data-table-vacations tr").length -1;
					employeevacations.daysPending = 6-($("#bootstrap-data-table-vacations tr").length-1);		
				}
			}
			else{
				employeevacations.daysTaken = $("#bootstrap-data-table-vacations tr").length;	
				employeevacations.daysPending = 6-($("#bootstrap-data-table-vacations tr").length);			
			}
		}
		
		employeevacations.daysForYear = 6;
		employeevacations.holiday = '' +$('#datetimepicker12').data("DateTimePicker").date().format('YYYY/MM/DD');
		employeevacations.active = 1;
		// Setear Empleado
		employeelabor.employeeVacations = [ employeevacations ];
		employeegral.employeeLabor = employeelabor;
		user.employee = employeegral;
		//
		console.log(user);
		sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'saveUserEmployeeVacations', user, respSaveEmployeeVacations);
	}
	else if ($("#bootstrap-data-table-vacations tr").length==7 && ID_EMPLOYEE_VACATIONS == 0){
		alertInfo('Días maximos alcanzados.', 'Has registrado tu numero maximo de días vacacionales.','Aceptar');
		closeModal();
	}
	else{
		closeModal();
	}
}

function respSaveEmployeeVacations(data) {
	// Validar tipo de certificacion
	if (data && data[1] == 'Ok') {
		showDivMessage(data[2], 'alert-info', 3000);
		initTableVacations();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
	closeModal();
}

function saveEmployeeHistory() {
	console.log('Proceso de guardado para Puesto y Salario');
	//
	var user = new User();
	user.id										= ID_EMPLOYEE_LABOR;
	user.email									= sessionStorage.getItem("email");
	// Llenar Empleado
	var employeegral = new employeeGral();
	var employeelabor = new employeeLabor();
	employeelabor.active						= 1;
	// Validar tipo de Puesto y Salario -----------------------------------------------------------------------------------------
	if(TYPE_JOB == 'HISTORY') {
		console.log('Proceso de guardado Puesto y Salario.');
		var historyjob = new historyJob();
		historyjob.id						= ID_HISTORY_JOB;
		historyjob.jobTitle			= '' + $('#load-job-title').val();
		historyjob.dateIn			= '' + $('#date-in').val();
		historyjob.dateOu			= '' + $('#date-ou').val();
		historyjob.jobArea			= '' + $('#load-job-area').val();
		historyjob.immediateBoss	= '' + $('#load-immediate-boss').val();
		historyjob.salaryIn			= '' + $('#salary-in').val();
		historyjob.salaryOu			= '' + $('#salary-ou').val();
		historyjob.active			= 1;
		// Setear Empleado
		employeelabor.historyJob			= [historyjob];
		employeegral.employeeLabor = employeelabor;
		user.employee = employeegral;
		//
		console.log(user);
	}
	//
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'saveUserEmployeeLaborHistory/' + TYPE_JOB, user, respSaveEmployeeLaborHistory);
}

function respSaveEmployeeLaborHistory(data) {
	// Validar tipo de certificacion
	if(TYPE_JOB == 'HISTORY') {
		ID_HISTORY_JOB = 0;
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 3000);
			initTableHistoryCertifications();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	closeModal();
}

function loadArea() {
	$('#load-area').empty();
	$('#load-position').empty();
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'findCatalogArea', null, respFindAreaAll);
}

function loadJobArea() {
	$('#load-job-area').empty();
	$('#load-job-title').empty();
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'findCatalogArea', null, respFindJobAreaAll);
}

function loadBoss() {
	$('#load-immediate-boss').empty();
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'findUserAll', null, respFindUserAll);
}

function loadWorkPlace(isValue) {
	$('#load-position').empty();
	var model = {
			value: '' + $("#load-area").val(),
			isValue: isValue
	}
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'findEmployeeCatalogWorkPlaceValue', model, respFindWorkPlaceAll);
}

function loadJobWorkPlace(isValue) {
	$('#load-job-title').empty();
	var model = {
			value: '' + $("#load-job-area").val(),
			isValue: isValue
	}
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'findEmployeeCatalogWorkPlaceValue', model, respFindJobWorkPlaceAll);
}

function respFindAreaAll(data){
	if(data){
		var opIn = new Option('Seleccionar área', '');
        $('#load-area').append(opIn);
		for (var i = 0; i < data.length; i++){
			var op = new Option(data[i].value, data[i].value);
	        $('#load-area').append(op);
		}
	}
}

function respFindJobAreaAll(data) {
	if(data){
		var opIn = new Option('Seleccionar área', '');
        $('#load-job-area').append(opIn);
		for (var i = 0; i < data.length; i++){
			var op = new Option(data[i].value, data[i].value);
	        $('#load-job-area').append(op);
		}
	}
}

function respFindUserAll(data){
	if(data){
		var opIn = new Option('Seleccionar jefe inmediato', '');
        $('#load-immediate-boss').append(opIn);
		for (var i = 0; i < data.length; i++){
			var nip = data[i].name + ' [' + data[i].email + ']';
			var op = new Option(nip, nip);
	        $('#load-immediate-boss').append(op);
		}
	}
}

function respFindWorkPlaceAll(data){
	if(data){
		var isValue = null;
		var opIn = new Option('Seleccionar Puesto', '');
        $('#load-position').append(opIn);
		for (var i = 0; i < data.length; i++){
			// Validar si la accion viene de "editEmployee"
			if(data[i].isValue) {
				isValue = data[i].isValue;
			} else {
				var op = new Option(data[i].value, data[i].value);
		        $('#load-position').append(op);
			}
		}
		if(isValue) {
			$("#load-position option[value='"+ isValue +"']").prop('selected', true);
		}
	}
}

function respFindJobWorkPlaceAll(data){
	if(data){
		var isValue = null;
		var opIn = new Option('Seleccionar Puesto', '');
        $('#load-job-title').append(opIn);
		for (var i = 0; i < data.length; i++){
			// Validar si la accion viene de "editEmployee"
			if(data[i].isValue) {
				isValue = data[i].isValue;
			} else {
				var op = new Option(data[i].value, data[i].value);
		        $('#load-job-title').append(op);
			}
		}
		if(isValue) {
			$("#load-job-title option[value='"+ isValue +"']").prop('selected', true);
		}
	}
}

function cleanForm() {
	$("#form-add-employee")[0].reset();
}

function cleanFormHistory() {
	$("#form-add-history-employee")[0].reset();
}

function cleanFormVacations() {
	$("#form-add-vacations")[0].reset();
}

function tblVtc(){

	if ($("#bootstrap-data-table-vacations tr").length==7){
		$('#btn-add-holiday').css('display', 'none');
	}
	else{
		$('#btn-add-holiday').css('display', 'block');
	}
}