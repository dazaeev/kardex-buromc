/*
 * @author Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de consulta Empleados todos
 */
var ID_EMPLOYEE_ECONOMICS = 0;
var ID_HISTORY_BONUS = 0;
var ID_HISTORY_LOAN = 0;
//
var TYPE_HISTORY = '';
// Cargando sistema
$(document).ready(function($) {
	startingSystemMenu();
	// Llenar tabla empleados
	initTable();
	//
	$('#btn-cancel-employee').on('click', function () {
		ID_EMPLOYEE_ECONOMICS = 0;
		ID_HISTORY_BONUS = 0;
		ID_HISTORY_LOAN = 0;
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
        console.log('Guardar Historial');
        $("#form-add-history-employee").submit();
    });
	//
	$('#btn-add-history').on('click', function () {
        console.log('Agregar Historial de Bonos');
        $('#modal-info-action').text('Agregar');
        $('#modal-info-sub-action').text('Historial de Bonos');
        openModalAddHistory();
    	cleanFormHistory();
    	ID_HISTORY_BONUS = 0;
    	ID_HISTORY_LOAN = 0;
    	//
    	TYPE_HISTORY = 'BONO';
    });
	//
	$('#btn-add-history-close').on('click', function () {
        console.log('Agregar Historial de Prestamos');
        $('#modal-info-action').text('Agregar');
        $('#modal-info-sub-action').text('Historial de Prestamos');
        openModalAddHistory();
    	cleanFormHistory();
    	ID_HISTORY_BONUS = 0;
    	ID_HISTORY_LOAN = 0;
    	//
    	TYPE_HISTORY = 'PRESTAMO';
    });
	//
	$("#form-add-employee").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario Historial invalido');
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
			$('#modal-info-action').text('* Campos requerido');
			$('#modal-info-action').css('color', 'red');
		} else {
			// Validar tipo de Historial
			if(TYPE_HISTORY == 'BONO') {
				console.log('Proceso de guardado bono.');
			}
			if(TYPE_HISTORY == 'PRESTAMO') {
				console.log('Proceso de guardado prestamo.');
			}
			saveEmployeeHistory();
			cleanFormHistory();
			// 
	        return false;
		}
	});
});

function initTable() {
	// iniciar contenido tabla
	$("#seccion-table-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeEconomics', null, respFindAll);
}

function respFindAll(data){
	// $('body').toggleClass('open');
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							
							+ ' 	<tr>                                       									'
							+ ' 		<th class="table-primary" colspan="2">Información</th>					'
							+ ' 		<th class="table-warning" colspan="1">Salario</th>						'
							+ ' 		<th class="table-info" colspan="2">Descuento Infonavit</th>				'
							+ ' 		<th class="table-danger" colspan="2">Descuento pensión alimenticia</th>	'
							+ ' 	</tr>                                       								'
							
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones			</th>			'
							+ ' 		<th>Nombre Completo		</th>			'
							
							+ ' 		<th>Actual				</th>			'
							+ ' 		<th>Fecha				</th>			'
							+ ' 		<th>Monto				</th>			'
							+ ' 		<th>Fecha				</th>			'
							+ ' 		<th>Monto				</th>			'
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				// enabled = 'style="display: none;"';
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información de Datos Economicos"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployee(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.id + ',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployee(\'' 	+ rowEmployee.id 										+ '\',' 
																									+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\',' 
																									+ '\'' + rowEmployee.salary 				   	+ '\',' 
																									+ '\'' + rowEmployee.dateInfonavit 				+ '\',' 
																									+ '\'' + rowEmployee.amountInfonavit  			+ '\',' 
																									+ '\'' + rowEmployee.dateAlimony 				+ '\',' 
																									+ '\'' + rowEmployee.amountAlimony 				+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			tr += '<td>'+ rowEmployee.name + ' '+ rowEmployee.lastName +'</td>';
			
			tr += '<td>'+ rowEmployee.salary			+'</td>';
			tr += '<td>'+ rowEmployee.dateInfonavit		+'</td>';
			tr += '<td>'+ rowEmployee.amountInfonavit	+'</td>';
			tr += '<td>'+ rowEmployee.dateAlimony		+'</td>';
			tr += '<td>'+ rowEmployee.amountAlimony		+'</td>';
			
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

function editEmployee(id, name, salary, dateInfonavit, amountInfonavit, dateAlimony, amountAlimony) {
	$('#add-mod-employee').text('Información de ' + name);
	//
	ID_EMPLOYEE_ECONOMICS = id;
	cleanForm();
	cleanFormHistory();
	$('#salary').val(salary);
	$('#date-infonavit').val(dateInfonavit);
	$('#amount-infonavit').val(amountInfonavit);
	$('#date-alimony').val(dateAlimony);
	$('#amount-alimony').val(amountAlimony);
	//
	showHistoryBonus();
	showHistoryLoan();
	//
	showAddEmployee();
}


function confirmDeleteEmployee(name, id, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': 'Esta acción eliminara cualquier informacion relacionada con datos economicos (historial de bonos e historial de prestamos).</br>' 
        	+ '¿ Seguro de eliminar información de datos economicos de ' + name + ' ?',
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

function confirmDeleteEmployeeHistory(name, id, type, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Precaución',
        'confirmQuestion': '¿ Esta usted seguro de eliminar permanentemente el registro de ' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	TYPE_HISTORY = type;
        	activeEmployeeHistory(id, type, active);
        }
    });
}

function activeEmployee(id, active) {
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'enabledUserEmployeeEconomics/' + id + '/' + active, null, respActiveEmployeeEconomics);
}

function activeEmployeeHistory(id, type, active) {
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'enabledUserEmployeeEconomicsHistory/' + id + '/' + active + '/' + type, null, respActiveEmployeeEconomicsHistory);
}

function respActiveEmployeeEconomics(data) {
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

function respActiveEmployeeEconomicsHistory(data) {
	// Validar tipo de Historial
	if(TYPE_HISTORY == 'BONO') {
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 2000);
			// $('#bootstrap-data-table tr#employee_' + data[3]).remove();
			// window.location = '/adm/employees-gral.html';
			initTableHistoryBonus();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	if(TYPE_HISTORY == 'PRESTAMO') {
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 2000);
			// $('#bootstrap-data-table tr#employee_' + data[3]).remove();
			// window.location = '/adm/employees-gral.html';
			initTableHistoryLoan();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
}

function showAddEmployee() {
	$('#info-employee').css('display', 'none');
	$('#info-history-employee').css('display', 'block');
	$('#info-history-close-employee').css('display', 'block');
	$('#add-employee').css('display', 'block');
    $("#btn-cancel-employee").show();
	$("#btn-save-employee").show();
}

function hideAddEmployee() {
	$('#info-employee').css('display', 'block');
	$('#info-history-employee').css('display', 'none');
	$('#info-history-close-employee').css('display', 'none');
	$('#add-employee').css('display', 'none');
    $("#btn-cancel-employee").hide();
	$("#btn-save-employee").hide();
}

function saveEmployee() {
	var user = new User();
	user.id										= ID_EMPLOYEE_ECONOMICS;
	user.email									= sessionStorage.getItem("email");
	// Llenar Empleado
	var employeegral = new employeeGral();
	var employeeeconomics = new employeeEconomics();
	employeeeconomics.salary			= '' + $('#salary').val();
	employeeeconomics.dateInfonavit		= '' + $('#date-infonavit').val();
	employeeeconomics.amountInfonavit	= '' + $('#amount-infonavit').val();
	employeeeconomics.dateAlimony		= '' + $('#date-alimony').val();
	employeeeconomics.amountAlimony		= '' + $('#amount-alimony').val();
	employeeeconomics.active			= 1;
	
	// Setear Empleado
	employeegral.employeeEconomics = employeeeconomics;
	user.employee = employeegral;
	
	console.log(user);
	//
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'saveUserEmployeeEconomics', user, respSaveEmployeeEconomics);
}

function respSaveEmployeeEconomics(data) {
	showPreloader(false);
	ID_EMPLOYEE_ECONOMICS = 0;
	ID_HISTORY_BONUS = 0;
	ID_HISTORY_LOAN = 0;
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 3000);
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function showHistoryBonus() {
	console.log('Iniciando Historial de bonos.');
	initTableHistoryBonus();
}

function showHistoryLoan() {
	console.log('Iniciando Historial de prestamos.');
	initTableHistoryLoan();
}

function initTableHistoryBonus() {
	// iniciar contenido tabla
	$("#seccion-table-history-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeEconomicsHistoryBonus/' + ID_EMPLOYEE_ECONOMICS, null, respFindHistoryBonusAll);
}

function initTableHistoryLoan() {
	// iniciar contenido tabla
	$("#seccion-table-history-close-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeEconomicsHistoryLoan/' + ID_EMPLOYEE_ECONOMICS, null, respFindHistoryLoanAll);
}

function respFindHistoryBonusAll(data){
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table-history"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							// + ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Fecha													</th>                          '
							+ ' 		<th>Monto													</th>                          '
							+ ' 		<th>Concepto												</th>                          '
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
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información de Historial de Bonos"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployeeHistory(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.idHistoryBonus + ',' + '\'' + 'BONO' + '\',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployeeHistoryBonus(\'' + rowEmployee.id + '\',' 
																									+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\',' 
																									+ '\'' + rowEmployee.idHistoryBonus			   	+ '\',' 
																									+ '\'' + rowEmployee.dateBonus					+ '\',' 
																									+ '\'' + rowEmployee.amountBonus				+ '\','
																									+ '\'' + rowEmployee.conceptBonus				+ '\','
																									+ '\'' + rowEmployee.status				+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			// tr += '<td>'+ rowEmployee.name + ' '+ rowEmployee.lastName +'</td>';
			
			tr += '<td>'+ rowEmployee.dateBonus			+'</td>';
			tr += '<td>'+ rowEmployee.amountBonus		+'</td>';
			tr += '<td>'+ rowEmployee.conceptBonus		+'</td>';
			var statusNotFound = true;
			if(rowEmployee.status == 'Pendiente') {
				statusNotFound = false;
				tr += '<td class="table-danger">'+ rowEmployee.status			+'</td>';
			}
			if(rowEmployee.status == 'Pagado') {
				statusNotFound = false;
				tr += '<td class="table-success">'+ rowEmployee.status			+'</td>';
			}
			if(statusNotFound){
				tr += '<td></td>';
			}
			
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

function respFindHistoryLoanAll(data){
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table-history-close"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							// + ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Fecha													</th>                          '
							+ ' 		<th>Monto													</th>                          '
							+ ' 		<th>Concepto												</th>                          '
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
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información de Historial de Prestamos"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployeeHistory(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.idHistoryLoan + ',' + '\'' + 'PRESTAMO' + '\',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployeeHistoryLoan(\'' + rowEmployee.id + '\',' 
																									+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\',' 
																									+ '\'' + rowEmployee.idHistoryLoan			   	+ '\',' 
																									+ '\'' + rowEmployee.dateLoan				   	+ '\',' 
																									+ '\'' + rowEmployee.amountLoan					+ '\','
																									+ '\'' + rowEmployee.conceptLoan				+ '\','
																									+ '\'' + rowEmployee.status						+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			// tr += '<td>'+ rowEmployee.name + ' '+ rowEmployee.lastName +'</td>';
			
			tr += '<td>'+ rowEmployee.dateLoan				+'</td>';
			tr += '<td>'+ rowEmployee.amountLoan			+'</td>';
			tr += '<td>'+ rowEmployee.conceptLoan			+'</td>';
			var statusNotFound = true;
			if(rowEmployee.status == 'Pendiente') {
				statusNotFound = false;
				tr += '<td class="table-danger">'+ rowEmployee.status			+'</td>';
			}
			if(rowEmployee.status == 'Pagado') {
				statusNotFound = false;
				tr += '<td class="table-success">'+ rowEmployee.status			+'</td>';
			}
			if(statusNotFound){
				tr += '<td></td>';
			}
			
			tr += '</tr>';
			tableEmployee += tr;
		}
		tableEmployee += '</tbody>'
						+ ' </table>';
	}
	
	var wrapper = $("#seccion-table-history-close-employee");
	$(wrapper).append(tableEmployee); // add html
	//
	exportTable('bootstrap-data-table-history-close', 
			[
				{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
			]);
	reloadTable('bootstrap-data-table-history-close');
}

function openModalAddHistory() {
	$("#modal-add-history").modal({
		modal:open,
		showClose: false
	});
}

function editEmployeeHistoryBonus(id, name, idHistoryBonus, dateBonus, amountBonus, conceptBonus, status) {
	$('#modal-info-action').text('Bono ' + conceptBonus);
	$('#modal-info-sub-action').text('Historial de bonos');
	//
	TYPE_HISTORY = 'BONO';
	//
	openModalAddHistory();
	//
	ID_HISTORY_BONUS = idHistoryBonus;
	//
	cleanFormHistory();
	$('#date-history').val(dateBonus);
	$('#amount-history').val(amountBonus);
	$('#concept-history').val(conceptBonus);
	$("#load-status option[value='"+ status +"']").prop('selected', true);
	//
}

function editEmployeeHistoryLoan(id, name, idHistoryLoan, dateLoan, amountLoan, conceptLoan, status) {
	$('#modal-info-action').text('Prestamo ' + conceptLoan);
	$('#modal-info-sub-action').text('Historial de prestamos');
	//
	TYPE_HISTORY = 'PRESTAMO';
	//
	openModalAddHistory();
	//
	ID_HISTORY_LOAN = idHistoryLoan;
	//
	cleanFormHistory();
	$('#date-history').val(dateLoan);
	$('#amount-history').val(amountLoan);
	$('#concept-history').val(conceptLoan);
	$("#load-status option[value='"+ status +"']").prop('selected', true);
	//
}

function saveEmployeeHistory() {
	console.log('Proceso de guardado para Historial');
	//
	var user = new User();
	user.id										= ID_EMPLOYEE_ECONOMICS;
	user.email									= sessionStorage.getItem("email");
	// Llenar Empleado
	var employeegral = new employeeGral();
	var employeeeconomics = new employeeEconomics();
	employeeeconomics.active						= 1;
	// Validar tipo de Historial -----------------------------------------------------------------------------------------
	if(TYPE_HISTORY == 'BONO') {
		console.log('Proceso de guardado historial de bonos.');
		var historybonus = new historyBonus();
		historybonus.id					= validateObjOut(ID_HISTORY_BONUS, 0);
		historybonus.dateBonus			= '' + $('#date-history').val();
		historybonus.amountBonus		= '' + $('#amount-history').val();
		historybonus.conceptBonus		= '' + $('#concept-history').val();
		historybonus.status				= '' + $('#load-status').val();
		historybonus.active					= 1;
		// Setear Empleado
		employeeeconomics.historyBonus	= [historybonus];
		employeegral.employeeEconomics	= employeeeconomics;
		user.employee = employeegral;
		//
		console.log(user);
	}
	if(TYPE_HISTORY == 'PRESTAMO') {
		console.log('Proceso de guardado historial de prestamos.');
		var historyloan = new historyLoan();
		historyloan.id					= validateObjOut(ID_HISTORY_LOAN, 0);
		historyloan.dateLoan			= '' + $('#date-history').val();
		historyloan.amountLoan			= '' + $('#amount-history').val();
		historyloan.conceptLoan			= '' + $('#concept-history').val();
		historyloan.status				= '' + $('#load-status').val();
		historyloan.active				= 1;
		// Setear Empleado
		employeeeconomics.historyLoan	= [historyloan];
		employeegral.employeeEconomics	= employeeeconomics;
		user.employee = employeegral;
		//
		console.log(user);
	}
	//
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'saveUserEmployeeEconomicsHistory/' + TYPE_HISTORY, user, respSaveEmployeeEconomicsHistory);
}

function respSaveEmployeeEconomicsHistory(data) {
	// Validar tipo de Historial
	if(TYPE_HISTORY == 'BONO') {
		ID_HISTORY_BONUS = 0;
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 3000);
			initTableHistoryBonus();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	if(TYPE_HISTORY == 'PRESTAMO') {
		ID_HISTORY_LOAN = 0;
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 3000);
			initTableHistoryLoan();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	closeModal();
}

function cleanForm() {
	$("#form-add-employee")[0].reset();
}

function cleanFormHistory() {
	$("#form-add-history-employee")[0].reset();
}