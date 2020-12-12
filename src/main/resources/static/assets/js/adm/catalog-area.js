/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de control "catalog_area"
 */
var ID_EMPLOYEE_CATALOG_AREA = 0;
var ID_CATALOG_WORK_PLACE = 0;
//
var TYPE_CATALOG = '';
// Cargando sistema
$(document).ready(function($) {
	startingSystemMenu();
	// Llenar tabla empleados
	initTable();
	//
	$('#btn-add-catalog').on('click', function () {
		$('#add-mod-employee').text('Agregar registro');
		//
		ID_EMPLOYEE_CATALOG_AREA = 0;
		cleanForm();
		cleanFormWorkPlace();
		//
		showAddEmployee();
		$('#info-work-place-employee').css('display', 'none');
	});
	//
	$('#btn-cancel-employee').on('click', function () {
		ID_EMPLOYEE_CATALOG_AREA = 0;
		ID_CATALOG_WORK_PLACE = 0;
		cleanForm();
		cleanFormWorkPlace();
	    hideAddEmployee();
	});
	//
	$('#btn-save-employee').on('click', function () {
        console.log('Guardar empleado');
        $("#form-add-employee").submit();
    });
	//
	$('#btn-save-catalog').on('click', function () {
        console.log('Guardar Puesto de Trabajo');
        $("#form-add-work-place-employee").submit();
    });
	//
	$('#btn-add-work-place').on('click', function () {
        console.log('Puesto de Trabajo');
        $('#modal-info-action').text('Agregar');
        $('#modal-info-sub-action').text('Puesto de Trabajo');
        openModalAddWorkPlace();
    	cleanFormWorkPlace();
    	ID_CATALOG_WORK_PLACE = 0;
    	//
    	TYPE_CATALOG = 'WORK-PLACE';
    });
	//
	$("#form-add-employee").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario catálogo invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
		} else {
			showPreloader(true);
			saveEmployee();
			cleanForm();
			cleanFormWorkPlace();
			//
	        hideAddEmployee();
	        return false;
		}
	});
	//
	$("#form-add-work-place-employee").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
			$('#modal-info-action').text('* Completar campos obligatorios');
			$('#modal-info-action').css('color', 'red');
		} else {
			// Validar tipo de certificacion
			if(TYPE_CATALOG == 'WORK-PLACE') {
				console.log('Proceso de guardado Puesto de Trabajo.');
			}
			saveEmployeeWorkPlace();
			cleanFormWorkPlace();
			// 
	        return false;
		}
	});
});

function initTable() {
	// iniciar contenido tabla
	$("#seccion-table-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'findCatalogArea', null, respFindAll);
}

function respFindAll(data){
	// $('body').toggleClass('open');
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							
							+ ' 		<th>Nombre													</th>                          '
							+ ' 		<th>Departamento											</th>                          '
							+ ' 		<th>Descripción												</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información Catálogo Área"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployee(\'' + rowEmployee.name + '\',' + rowEmployee.id + ',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployee(\'' 	+ rowEmployee.id 										+ '\',' 
																									+ '\'' + rowEmployee.name			 					+ '\','
																									+ '\'' + rowEmployee.value			 					+ '\','
																									+ '\'' + rowEmployee.description 						+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			
			tr += '<td>'+ rowEmployee.name					+'</td>';
			tr += '<td>'+ rowEmployee.value					+'</td>';
			tr += '<td>'+ rowEmployee.description			+'</td>';
			
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

function editEmployee(id, name, value, description) {
	$('#add-mod-employee').text('Información de ' + name);
	//
	ID_EMPLOYEE_CATALOG_AREA = id;
	cleanForm();
	cleanFormWorkPlace();
	$('#c_name').val(name);
	$('#c_value').val(value);
	$('#c_description').val(description);
	//
	showWorkPlace();
	//
	showAddEmployee();
}


function confirmDeleteEmployee(name, id, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': 'Esta acción eliminara cualquier informacion relacionada con "Catálogo Área" (Puesto de Trabajo).</br>' 
        	+ '¿ Seguro de eliminar información del catálogo ' + name + ' ?',
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

function confirmDeleteWorkPlace(name, id, type, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Precaución',
        'confirmQuestion': '¿ Esta usted seguro de eliminar permanentemente "Puesto de Trabajo" de ' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	TYPE_CATALOG = type;
        	activeWorkPlace(id, type, active);
        }
    });
}

function activeEmployee(id, active) {
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'enabledCatalogArea/' + id + '/' + active, null, respActiveCatalogArea);
}

function activeWorkPlace(id, type, active) {
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'enabledCatalogAreaWorkPlace/' + id + '/' + active + '/' + type, null, respActiveCatalogAreaWorkPlace);
}

function respActiveCatalogArea(data) {
	showPreloader(false);
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 2000);
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function respActiveCatalogAreaWorkPlace(data) {
	// Validar tipo de certificacion
	if(TYPE_CATALOG == 'WORK-PLACE') {
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 2000);
			initTableWorkPlace();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
}

function showAddEmployee() {
	$('#info-employee').css('display', 'none');
	$('#info-work-place-employee').css('display', 'block');
	$('#add-employee').css('display', 'block');
    $("#btn-cancel-employee").show();
	$("#btn-save-employee").show();
}

function hideAddEmployee() {
	$('#info-employee').css('display', 'block');
	$('#info-work-place-employee').css('display', 'none');
	$('#add-employee').css('display', 'none');
    $("#btn-cancel-employee").hide();
	$("#btn-save-employee").hide();
}

function saveEmployee() {
	var catalogarea = new catalogArea();
	catalogarea.id			= ID_EMPLOYEE_CATALOG_AREA;
	// Llenar Catalogo
	catalogarea.name		= '' + $('#c_name').val();
	catalogarea.value		= '' + $('#c_value').val();
	catalogarea.description	= '' + $('#c_description').val();
	catalogarea.active	= 1;
	// Setear 
	console.log(catalogarea);
	//
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'saveCatalogArea', catalogarea, respSaveCatalogArea);
}

function respSaveCatalogArea(data) {
	showPreloader(false);
	if(ID_EMPLOYEE_CATALOG_AREA == 0) {
		// Proceder a llenar Certificaciones
		alertInfo('Información', 
				'<strong>' + data[2] + '</strong>' + '</br>Proceder a llenar</br>&nbsp;&nbsp;&nbsp;&nbsp;- Puesto de Trabajo', 
				'Entendido');
	} else {
		ID_EMPLOYEE_CATALOG_AREA = 0;
	}
	ID_CATALOG_WORK_PLACE = 0;
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 3000);
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function showWorkPlace() {
	console.log('Iniciando Puesto de Trabajo.');
	initTableWorkPlace();
}

function initTableWorkPlace() {
	// iniciar contenido tabla
	$("#seccion-work-place-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'findEmployeeCatalogWorkPlace/' + ID_EMPLOYEE_CATALOG_AREA, null, respFindWorkPlaceAll);
}

function respFindWorkPlaceAll(data){
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table-sub-catalog"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							// + ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Nombre													</th>                          '
							+ ' 		<th>Departamento											</th>                          '
							+ ' 		<th>Descripción												</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información de Puesto de Trabajo"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteWorkPlace(\'' + rowEmployee.name + '\',' + rowEmployee.idCatalogWorkPlace + ',' + '\'' + 'WORK-PLACE' + '\',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployeeWorkPlace(\'' + rowEmployee.idCatalogWorkPlace + '\',' 
																									+ '\'' + rowEmployee.name				+ '\',' 
																									+ '\'' + rowEmployee.value				+ '\',' 
																									+ '\'' + rowEmployee.description		+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.idCatalogWorkPlace + '">';
			tr += '<td align="center">'+ actions +'</td>';
			
			tr += '<td>'+ rowEmployee.name				+'</td>';
			tr += '<td>'+ rowEmployee.value				+'</td>';
			tr += '<td>'+ rowEmployee.description		+'</td>';
						
			tr += '</tr>';
			tableEmployee += tr;
		}
		tableEmployee += '</tbody>'
						+ ' </table>';
	}
	
	var wrapper = $("#seccion-work-place-employee");
	$(wrapper).append(tableEmployee); // add html
	//
	exportTable('bootstrap-data-table-sub-catalog', 
			[
				{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
			]);
	reloadTable('bootstrap-data-table-sub-catalog');
	if(data.length == 0) {
		msgEmptyTable('Agregar uno o varios registros.');
	}
}

function openModalAddWorkPlace() {
	$("#modal-add-work-place").modal({
		modal:open,
		showClose: false
	});
}

function editEmployeeWorkPlace(idCatalogWorkPlace, name, value, description) {
	$('#modal-info-action').text(name);
	$('#modal-info-sub-action').text('Puesto de Trabajo');
	//
	TYPE_CATALOG = 'WORK-PLACE';
	//
	openModalAddWorkPlace();
	//
	ID_CATALOG_WORK_PLACE = idCatalogWorkPlace;
	//
	cleanFormWorkPlace();
	$('#sc_name').val(name);
	$('#sc_value').val(value);
	$('#sc_description').val(description);
	//
}

function saveEmployeeWorkPlace() {
	console.log('Proceso de guardado para Puesto de Trabajo');
	//
	var catalogarea = new catalogArea();
	catalogarea.id			= ID_EMPLOYEE_CATALOG_AREA;
	// Llenar Catalogo
	catalogarea.active	= 1;
	// Validar tipo de Puesto de Trabajo -----------------------------------------------------------------------------------------
	if(TYPE_CATALOG == 'WORK-PLACE') {
		console.log('Proceso de guardado Puesto de Trabajo.');
		var catalogworkplace = new catalogWorkPlace();
		catalogworkplace.id				= ID_CATALOG_WORK_PLACE;
		catalogworkplace.name			= '' + $('#sc_name').val();
		catalogworkplace.value			= '' + $('#sc_value').val();
		catalogworkplace.description	= '' + $('#sc_description').val();
		catalogworkplace.active			= 1;
		// Setear Empleado
		catalogarea.catalogWorkPlace	= [catalogworkplace];
		//
		console.log(catalogarea);
	}
	//
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'saveUserEmployeeCatalogWorkPlace/' + TYPE_CATALOG, catalogarea, respSaveCatalogWorkPlace);
}

function respSaveCatalogWorkPlace(data) {
	// Validar tipo de certificacion
	if(TYPE_CATALOG == 'WORK-PLACE') {
		ID_CATALOG_WORK_PLACE = 0;
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 3000);
			initTableWorkPlace();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	closeModal();
}

function cleanForm() {
	$("#form-add-employee")[0].reset();
}

function cleanFormWorkPlace() {
	$("#form-add-work-place-employee")[0].reset();
}