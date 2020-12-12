/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de control "catalog_area"
 */
// Certification
var ID_CERTIFICATION_TRACK = 0;
// Fase
var ID_CATALOG_CATALOG_FASE = 0;
var ID_CATALOG_CATALOG_FASE_DESCRIPTION = '';
var ID_CATALOG_CATALOG_FASE_COLOR = 0;
// Block
var ID_CATALOG_CATALOG_FASE_BLOCK = 0;
var ID_CATALOG_CATALOG_FASE_BLOCK_DESCRIPTION = '';
var ID_CATALOG_CATALOG_FASE_BLOCK_COLOR = 0;
// Technology
var ID_CATALOG_CATALOG_FASE_BLOCK_TECHNOLOGY = 0;
var ID_CATALOG_CATALOG_FASE_BLOCK_TECHNOLOGY_DESCRIPTION = '';
var ID_CATALOG_CATALOG_FASE_BLOCK_TECHNOLOGY_COLOR = 0;
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
		ID_CERTIFICATION_TRACK = 0;
		cleanForm();
		cleanFormCatalogFase();
		//
		showAddEmployee();
		$('#info-catalog-fase').css('display', 'none');
		$('#info-catalog-fase-block').css('display', 'none');
		$('#info-catalog-fase-block-technology').css('display', 'none');
	});
	//
	$('#btn-cancel-employee').on('click', function () {
		ID_CERTIFICATION_TRACK = 0;
		ID_CATALOG_CATALOG_FASE = 0;
		ID_CATALOG_CATALOG_FASE_DESCRIPTION = '';
		cleanForm();
		cleanFormCatalogFase();
	    hideAddEmployee();
	});
	//
	$('#btn-save-employee').on('click', function () {
        console.log('Guardar Plan de Carrera');
        $("#form-add-employee").submit();
    });
	//
	$('#btn-save-catalog-fase').on('click', function () {
        console.log('Guardar Fase');
        $("#form-add-catalog-fase").submit();
    });
	//
	$('#btn-add-catalog-fase').on('click', function () {
        console.log('Agregar Fase');
        $('#modal-info-action').text('Agregar');
        $('#modal-info-sub-action').text('-> Fases');
        $('#btn-view-catalog-fase-block').css('display', 'none');
        openModalAddCatalogFase();
    	cleanFormCatalogFase();
    	ID_CATALOG_CATALOG_FASE = 0;
    	ID_CATALOG_CATALOG_FASE_DESCRIPTION = '';
    	//
    	TYPE_CATALOG = 'CATALOG-FASE';
    });
	//
	$('#btn-add-catalog-fase-block').on('click', function () {
        console.log('Agregar Fase Bloque');
        $('#modal-info-action').text('Agregar');
        $('#modal-info-sub-action').text('-> Bloque');
        $('#btn-view-catalog-fase-block').css('display', 'none');
        openModalAddCatalogFase();
    	cleanFormCatalogFase();
    	ID_CATALOG_CATALOG_FASE_BLOCK = 0;
    	ID_CATALOG_CATALOG_FASE_BLOCK_DESCRIPTION = '';
    	//
    	TYPE_CATALOG = 'CATALOG-FASE-BLOCK';
    });
	//
	$('#btn-add-catalog-fase-block-technology').on('click', function () {
        console.log('Agregar Fase Bloque Technology');
        $('#modal-info-action').text('Agregar');
        $('#modal-info-sub-action').text('-> Tecnologias');
        $('#btn-view-catalog-fase-block').css('display', 'none');
        openModalAddCatalogFase();
    	cleanFormCatalogFase();
    	ID_CATALOG_CATALOG_FASE_BLOCK_TECHNOLOGY = 0;
    	ID_CATALOG_CATALOG_FASE_BLOCK_TECHNOLOGY_DESCRIPTION = '';
    	//
    	TYPE_CATALOG = 'CATALOG-FASE-BLOCK-TECHNOLOGY';
    });
	//
	$('#btn-view-catalog-fase-block').on('click', function () {
		if(TYPE_CATALOG == 'CATALOG-FASE') {
			initTableCatalogFaseBlock();
		}
		if(TYPE_CATALOG == 'CATALOG-FASE-BLOCK') {
			initTableCatalogFaseBlockTechnology();
		}
	});
	
	//
	$("#form-add-employee").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario plan de carrera invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
		} else {
			saveEmployee();
			cleanForm();
			cleanFormCatalogFase();
			//
	        hideAddEmployee();
	        return false;
		}
	});
	//
	$("#form-add-catalog-fase").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
			$('#modal-info-action').text('* Completar campos obligatorios');
			$('#modal-info-action').css('color', 'red');
		} else {
			// Validar tipo de certificacion
			if(TYPE_CATALOG == 'CATALOG-FASE') {
				console.log('Proceso de guardado Catálogo Fase.');
			}
			if(TYPE_CATALOG == 'CATALOG-FASE-BLOCK') {
				console.log('Proceso de guardado Catálogo Fase Block.');
			}
			if(TYPE_CATALOG == 'CATALOG-FASE-BLOCK-TECHNOLOGY') {
				console.log('Proceso de guardado Catálogo Fase Block Technology.');
			}
			saveEmployeeCatalogFase();
			cleanFormCatalogFase();
			// 
	        return false;
		}
	});
	// Cargar combo de Área
	loadArea();
});

function initTable() {
	// iniciar contenido tabla
	$("#seccion-table-employee").empty();
	// Llenar tabla empleados
	sendPostAction(CERTIFICATION_TRACK_CONTROLLER + 'findCertificationTrack', null, respFindAll);
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
							+ ' 		<th>Area													</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información Plan de Carrera"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteCertificationTrack(\'' + rowEmployee.name + '\',' + rowEmployee.id + ',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editCertificationTrack(\'' 	+ rowEmployee.id 										+ '\',' 
																									+ '\'' + rowEmployee.name			 					+ '\','
																									+ '\'' + rowEmployee.area		 						+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			
			tr += '<td>'+ rowEmployee.name					+'</td>';
			tr += '<td>'+ rowEmployee.area					+'</td>';
			
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

function editCertificationTrack(id, name, area) {
	$('#add-mod-employee').text('Información de ' + name + ' (' + area + ')');
	//
	ID_CERTIFICATION_TRACK = id;
	cleanForm();
	cleanFormCatalogFase();
	$('#c_name').val(name);
	$('#c_area').val(area);
	//
	showCatalogFase();
	//
	showAddEmployee();
}


function confirmDeleteCertificationTrack(name, id, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': 'Esta acción eliminara cualquier informacion relacionada con "Plan de Carrera" (Fase - Bloque).</br>' 
        	+ '¿ Seguro de eliminar información del plan ' + name + ' ?',
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

function confirmDeleteCertificationFase(name, id, type, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Precaución',
        'confirmQuestion': '¿ Esta usted seguro de eliminar permanentemente "Fase" de ' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	TYPE_CATALOG = type;
        	activeCertificationFase(id, type, active);
        }
    });
}

function confirmDeleteCertificationFaseBlock(name, id, type, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Precaución',
        'confirmQuestion': '¿ Esta usted seguro de eliminar permanentemente "Bloque" de ' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	TYPE_CATALOG = type;
        	activeCertificationFase(id, type, active);
        }
    });
}

function confirmDeleteCertificationFaseBlockTechnology(name, id, type, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Precaución',
        'confirmQuestion': '¿ Esta usted seguro de eliminar permanentemente "Tecnologia" de ' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	TYPE_CATALOG = type;
        	activeCertificationFase(id, type, active);
        }
    });
}

function activeEmployee(id, active) {
	sendPostAction(CERTIFICATION_TRACK_CONTROLLER + 'enabledCertificationTrack/' + id + '/' + active, null, respActiveCertificationTrack);
}

function activeCertificationFase(id, type, active) {
	sendPostAction(CERTIFICATION_TRACK_CONTROLLER + 'enabledCatalogFase/' + id + '/' + active + '/' + type, null, respActiveCatalogFase);
}

function respActiveCertificationTrack(data) {
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 2000);
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function respActiveCatalogFase(data) {
	// Validar tipo de certificacion
	if(TYPE_CATALOG == 'CATALOG-FASE') {
		// Ocultar sub tablas
		$('#info-catalog-fase-block').css('display', 'none');
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 2000);
			initTableCatalogFase();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	if(TYPE_CATALOG == 'CATALOG-FASE-BLOCK') {
		// Ocultar sub tablas
		$('#info-catalog-fase-block-technology').css('display', 'none');
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 2000);
			initTableCatalogFaseBlock();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	if(TYPE_CATALOG == 'CATALOG-FASE-BLOCK-TECHNOLOGY') {
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 2000);
			initTableCatalogFaseBlockTechnology();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
}

function showAddEmployee() {
	$('#info-employee').css('display', 'none');
	$('#info-catalog-fase').css('display', 'block');
	$('#add-employee').css('display', 'block');
    $("#btn-cancel-employee").show();
	$("#btn-save-employee").show();
}

function hideAddEmployee() {
	$('#info-employee').css('display', 'block');
	$('#info-catalog-fase').css('display', 'none');
	$('#info-catalog-fase-block').css('display', 'none');
	$('#info-catalog-fase-block-technology').css('display', 'none');
	$('#add-employee').css('display', 'none');
    $("#btn-cancel-employee").hide();
	$("#btn-save-employee").hide();
}

function saveEmployee() {
	var certificationtrack = new certificationTrack();
	certificationtrack.id			= ID_CERTIFICATION_TRACK;
	// Llenar Catalogo
	certificationtrack.name			= '' + $('#c_name').val();
	certificationtrack.area			= '' + $('#c_area').val();
	certificationtrack.active		= 1;
	// Setear 
	console.log(certificationtrack);
	//
	sendPostAction(CERTIFICATION_TRACK_CONTROLLER + 'saveCertificationTrack', certificationtrack, respSaveCertificationTrack);
}

function respSaveCertificationTrack(data) {
	if(ID_CERTIFICATION_TRACK == 0) {
		// Proceder a llenar Certificaciones
		alertInfo('Información', 
				'<strong>' + data[2] + '</strong>' + '</br>Proceder a llenar</br>&nbsp;&nbsp;&nbsp;&nbsp;- Fases', 
				'Entendido');
	} else {
		ID_CERTIFICATION_TRACK = 0;
	}
	ID_CATALOG_CATALOG_FASE = 0;
	ID_CATALOG_CATALOG_FASE_DESCRIPTION = '';
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 3000);
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function showCatalogFase() {
	console.log('Iniciando Catalogo Fase.');
	initTableCatalogFase();
}

function initTableCatalogFase() {
	// iniciar contenido tabla
	$("#seccion-catalog-fase-employee").empty();
	// Llenar tabla empleados
	sendPostAction(CERTIFICATION_TRACK_CONTROLLER + 'findCertificationCatalogFase/' + ID_CERTIFICATION_TRACK, null, respFindCertificationCatalogFaseAll);
}

function respFindCertificationCatalogFaseAll(data){
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table-sub-catalog"	'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones												</th>                          '
							// + ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Nombre													</th>                          '
							+ ' 		<th>Descripción												</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información para FASES"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteCertificationFase(\'' + rowEmployee.name + '\',' + rowEmployee.idCatalogFase + ',' + '\'' + 'CATALOG-FASE' + '\',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editCertificationFase(\'' + rowEmployee.idCatalogFase + '\',' 
																									+ '\'' + rowEmployee.name				+ '\',' 
																									+ '\'' + rowEmployee.description		+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employeeCatalogFase_' + rowEmployee.idCatalogFase + '">';
			tr += '<td align="center">'+ actions +'</td>';
			
			tr += '<td>'+ rowEmployee.name				+'</td>';
			tr += '<td>'+ rowEmployee.description		+'</td>';
						
			tr += '</tr>';
			tableEmployee += tr;
		}
		tableEmployee += '</tbody>'
						+ ' </table>';
	}
	
	var wrapper = $("#seccion-catalog-fase-employee");
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

function openModalAddCatalogFase() {
	$("#modal-add-catalog-fase").modal({
		modal:open,
		showClose: false
	});
}

function editCertificationFase(idCatalogWorkPlace, name, description) {
	$('#modal-info-action').text(name);
	$('#modal-info-sub-action').text('-> Fases');
	setValueBtnModal(' Ver Bloques', 'Visualizar Bloques de ' + description);
	//
	TYPE_CATALOG = 'CATALOG-FASE';
	//
	openModalAddCatalogFase();
	//
	ID_CATALOG_CATALOG_FASE = idCatalogWorkPlace;
	ID_CATALOG_CATALOG_FASE_DESCRIPTION = description;
	//
	cleanFormCatalogFase();
	$('#sc_name').val(name);
	$('#sc_description').val(description);
	//
}

function editCertificationFaseBlock(idCatalogWorkPlaceBlock, name, description) {
	$('#modal-info-action').text(name);
	$('#modal-info-sub-action').text('-> Bloque');
	setValueBtnModal(' Ver Ternologias', 'Visualizar Tecnologias de ' + description);
	//
	TYPE_CATALOG = 'CATALOG-FASE-BLOCK';
	//
	openModalAddCatalogFase();
	//
	ID_CATALOG_CATALOG_FASE_BLOCK = idCatalogWorkPlaceBlock;
	ID_CATALOG_CATALOG_FASE_BLOCK_DESCRIPTION = description;
	//
	cleanFormCatalogFase();
	$('#sc_name').val(name);
	$('#sc_description').val(description);
	//
}

function editCertificationFaseBlockTechnology(idCatalogFaseBlockTechnology, technology, product) {
	$('#modal-info-action').text(technology);
	$('#modal-info-sub-action').text('-> Tecnologia');
	// setValueBtnModal(' Ver Ternologias', 'Visualizar Tecnologias de ' + product);
	$('#btn-view-catalog-fase-block').css('display', 'none');
	TYPE_CATALOG = 'CATALOG-FASE-BLOCK-TECHNOLOGY';
	//
	openModalAddCatalogFase();
	//
	ID_CATALOG_CATALOG_FASE_BLOCK_TECHNOLOGY = idCatalogFaseBlockTechnology;
	ID_CATALOG_CATALOG_FASE_BLOCK_TECHNOLOGY_DESCRIPTION = product;
	//
	cleanFormCatalogFase();
	$('#sc_name').val(technology);
	$('#sc_description').val(product);
	//
}

function saveEmployeeCatalogFase() {
	console.log('Proceso de guardado para Catálogos');
	//
	var certificationtrack 		= new certificationTrack();
	certificationtrack.id		= ID_CERTIFICATION_TRACK;
	// Llenar Catalogo
	certificationtrack.active	= 1;
	// Validar tipo de -> Fases -----------------------------------------------------------------------------------------
	if(TYPE_CATALOG == 'CATALOG-FASE') {
		console.log('Proceso de guardado Catálogo Fase.');
		// Ocultar sub tablas
		$('#info-catalog-fase-block').css('display', 'none');
		$('#info-catalog-fase-block-technology').css('display', 'none');
		//
		var catalogfase = new catalogFase();
		catalogfase.id				= ID_CATALOG_CATALOG_FASE;
		catalogfase.name			= '' + $('#sc_name').val();
		catalogfase.description		= '' + $('#sc_description').val();
		catalogfase.active			= 1;
		// Setear Empleado
		certificationtrack.catalogFase	= [catalogfase];
		//
		console.log(certificationtrack);
	}
	// Validar tipo de -> Bloques ---------------------------------------------------------------------------------------
	if(TYPE_CATALOG == 'CATALOG-FASE-BLOCK') {
		console.log('Proceso de guardado Catálogo Fase Bloque.');
		// Ocultar sub tablas
		$('#info-catalog-fase-block-technology').css('display', 'none');
		//
		var catalogfase = new catalogFase();
		catalogfase.id				= ID_CATALOG_CATALOG_FASE;
		catalogfase.active			= 1;
		var catalogfaseblock = new catalogFaseBlock();
		catalogfaseblock.id				= ID_CATALOG_CATALOG_FASE_BLOCK;
		catalogfaseblock.name			= '' + $('#sc_name').val();
		catalogfaseblock.description	= '' + $('#sc_description').val();
		catalogfaseblock.active			= 1;
		// Setear bloque
		catalogfase.catalogFaseBlock	= [catalogfaseblock];
		// Setear fase
		certificationtrack.catalogFase	= [catalogfase];
		//
		console.log(certificationtrack);
	}
	// Validar tipo de -> Tecnologias -----------------------------------------------------------------------------------
	if(TYPE_CATALOG == 'CATALOG-FASE-BLOCK-TECHNOLOGY') {
		console.log('Proceso de guardado Catálogo Fase Bloque Technology.');
		var catalogfase = new catalogFase();
		catalogfase.id				= ID_CATALOG_CATALOG_FASE;
		catalogfase.active			= 1;
		var catalogfaseblock = new catalogFaseBlock();
		catalogfaseblock.id				= ID_CATALOG_CATALOG_FASE_BLOCK;
		catalogfaseblock.active			= 1;
		var catalogfaseblocktechnology = new catalogFaseBlockTechnology();
		catalogfaseblocktechnology.id				= ID_CATALOG_CATALOG_FASE_BLOCK_TECHNOLOGY;
		catalogfaseblocktechnology.technology		= '' + $('#sc_name').val();
		catalogfaseblocktechnology.product			= '' + $('#sc_description').val();
		catalogfaseblocktechnology.active			= 1;
		// Setear tecnologia
		catalogfaseblock.catalogFaseBlockTechnology	= [catalogfaseblocktechnology];
		// Setear bloque
		catalogfase.catalogFaseBlock				= [catalogfaseblock];
		// Setear fase
		certificationtrack.catalogFase				= [catalogfase];
		//
		console.log(certificationtrack);
	}
	//
	sendPostAction(CERTIFICATION_TRACK_CONTROLLER + 'saveCertificationCatalogFase/' + TYPE_CATALOG, certificationtrack, respSaveCertificationCatalogFase);
}

function respSaveCertificationCatalogFase(data) {
	// Validar tipo de certificacion
	if(TYPE_CATALOG == 'CATALOG-FASE') {
		ID_CATALOG_CATALOG_FASE = 0;
		ID_CATALOG_CATALOG_FASE_DESCRIPTION = '';
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 3000);
			initTableCatalogFase();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	if(TYPE_CATALOG == 'CATALOG-FASE-BLOCK') {
		ID_CATALOG_CATALOG_FASE_BLOCK = 0;
		ID_CATALOG_CATALOG_FASE_BLOCK_DESCRIPTION = '';
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 3000);
			initTableCatalogFaseBlock();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	if(TYPE_CATALOG == 'CATALOG-FASE-BLOCK-TECHNOLOGY') {
		ID_CATALOG_CATALOG_FASE_BLOCK_TECHNOLOGY = 0;
		ID_CATALOG_CATALOG_FASE_BLOCK_TECHNOLOGY_DESCRIPTION = '';
		if(data && data[1] == 'Ok'){
			showDivMessage(data[2], 'alert-info', 3000);
			initTableCatalogFaseBlockTechnology();
		} else {
			showDivMessage(data[2], 'alert-danger', 5000);
		}
	}
	closeModal();
}

function initTableCatalogFaseBlock() {
	console.log('Iniciar contenido tabla - Bloques.');
	closeModal();
	// remove color
	$('#employeeCatalogFase_' + ID_CATALOG_CATALOG_FASE_COLOR).removeClass('table-success');
	// add color
	$('#employeeCatalogFase_' + ID_CATALOG_CATALOG_FASE).addClass('table-success');
	ID_CATALOG_CATALOG_FASE_COLOR = ID_CATALOG_CATALOG_FASE;
	//
	$('#lblFaseBlock').text('Bloque\'s de: ' + ID_CATALOG_CATALOG_FASE_DESCRIPTION);
	// iniciar contenido tabla
	$("#seccion-catalog-fase-block-employee").empty();
	// Llenar tabla empleados
	sendPostAction(CERTIFICATION_TRACK_CONTROLLER + 'findCertificationCatalogFaseBlock/' + ID_CATALOG_CATALOG_FASE, null, respFindCertificationCatalogFaseBlockAll);
}

function initTableCatalogFaseBlockTechnology() {
	console.log('Iniciar contenido tabla - Tecnologias.');
	closeModal();
	// remove color
	$('#employeeCatalogFaseBlock_' + ID_CATALOG_CATALOG_FASE_BLOCK_COLOR).removeClass('table-warning');
	// add color
	$('#employeeCatalogFaseBlock_' + ID_CATALOG_CATALOG_FASE_BLOCK).addClass('table-warning');
	ID_CATALOG_CATALOG_FASE_BLOCK_COLOR = ID_CATALOG_CATALOG_FASE_BLOCK;
	//
	$('#lblFaseBlockTechnology').text('Tecnologias\'s de: ' + ID_CATALOG_CATALOG_FASE_BLOCK_DESCRIPTION);
	// iniciar contenido tabla
	$("#seccion-catalog-fase-block-technology-employee").empty();
	// Llenar tabla empleados
	sendPostAction(CERTIFICATION_TRACK_CONTROLLER + 'findCertificationCatalogFaseBlockTechnology/' + ID_CATALOG_CATALOG_FASE_BLOCK, null, respFindCertificationCatalogFaseBlockTechnologyAll);
}

function respFindCertificationCatalogFaseBlockAll(data){
	if(data){
		// Ocultar sub tablas
		$('#info-catalog-fase-block-technology').css('display', 'none');
		// 
		$('#info-catalog-fase-block').css('display', 'block');
		//
		var tableEmployee =	  ' <table id="bootstrap-data-table-sub-catalog-fase-block"				'
							+ ' 	class="table mb-0">												'
							+ ' <thead>                                         					'
							+ ' 	<tr>                                        					'
							+ ' 		<th>Acciones												</th>                          '
							// + ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Nombre													</th>                          '
							+ ' 		<th>Descripción												</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información para BLOQUES"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteCertificationFaseBlock(\'' + rowEmployee.name + '\',' + rowEmployee.idCatalogFaseBlock + ',' + '\'' + 'CATALOG-FASE-BLOCK' + '\',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editCertificationFaseBlock(\'' + rowEmployee.idCatalogFaseBlock + '\',' 
																									+ '\'' + rowEmployee.name				+ '\',' 
																									+ '\'' + rowEmployee.description		+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employeeCatalogFaseBlock_' + rowEmployee.idCatalogFaseBlock + '">';
			tr += '<td align="center">'+ actions +'</td>';
			
			tr += '<td>'+ rowEmployee.name				+'</td>';
			tr += '<td>'+ rowEmployee.description		+'</td>';
						
			tr += '</tr>';
			tableEmployee += tr;
		}
		tableEmployee += '</tbody>'
						+ ' </table>';
	}
	
	var wrapper = $("#seccion-catalog-fase-block-employee");
	$(wrapper).append(tableEmployee); // add html
	//
	exportTable('bootstrap-data-table-sub-catalog-fase-block', 
			[
				{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
			]);
	reloadTable('bootstrap-data-table-sub-catalog-fase-block');
	if(data.length == 0) {
		msgEmptyTable('Agregar uno o varios registros.');
	}
	// set color table-success
	$('#bootstrap-data-table-sub-catalog-fase-block td').addClass('table-success');
	window.location = '#info-catalog-fase-block';
}

function respFindCertificationCatalogFaseBlockTechnologyAll(data){
	if(data){
		$('#info-catalog-fase-block-technology').css('display', 'block');
		//
		var tableEmployee =	  ' <table id="bootstrap-data-table-sub-catalog-fase-block-technology"	'
							+ ' 	class="table mb-0">												'
							+ ' <thead>                                         					'
							+ ' 	<tr>                                        					'
							+ ' 		<th>Acciones												</th>                          '
							// + ' 		<th>Nombre Completo											</th>                          '
							
							+ ' 		<th>Tecnologia												</th>                          '
							+ ' 		<th>Marca/Proveedor											</th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información para BLOQUES"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteCertificationFaseBlockTechnology(\'' + rowEmployee.technology + '\',' + rowEmployee.idCatalogFaseBlockTechnology + ',' + '\'' + 'CATALOG-FASE-BLOCK-TECHNOLOGY' + '\',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editCertificationFaseBlockTechnology(\'' + rowEmployee.idCatalogFaseBlockTechnology + '\',' 
																									+ '\'' + rowEmployee.technology		+ '\',' 
																									+ '\'' + rowEmployee.product		+ '\''
																									+ ')" style="cursor: pointer"></i>';

			var tr = '<tr id="employeeCatalogFaseBlockTechnology_' + rowEmployee.idCatalogFaseBlockTechnology + '">';
			tr += '<td align="center">'+ actions +'</td>';
			
			tr += '<td>'+ rowEmployee.technology	+'</td>';
			tr += '<td>'+ rowEmployee.product		+'</td>';
						
			tr += '</tr>';
			tableEmployee += tr;
		}
		tableEmployee += '</tbody>'
						+ ' </table>';
	}
	
	var wrapper = $("#seccion-catalog-fase-block-technology-employee");
	$(wrapper).append(tableEmployee); // add html
	//
	exportTable('bootstrap-data-table-sub-catalog-fase-block-technology', 
			[
				{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
			]);
	reloadTable('bootstrap-data-table-sub-catalog-fase-block-technology');
	if(data.length == 0) {
		msgEmptyTable('Agregar uno o varios registros.');
	}
	// set color table-warning
	$('#bootstrap-data-table-sub-catalog-fase-block-technology td').addClass('table-warning');
	window.location = '#info-catalog-fase-block-technology';
}

function loadArea() {
	$('#c_area').empty();
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'findCatalogArea', null, respFindAreaAll);
}

function respFindAreaAll(data){
	if(data){
		var opIn = new Option('Seleccionar área', '');
        $('#c_area').append(opIn);
		for (var i = 0; i < data.length; i++){
			var op = new Option(data[i].value, data[i].value);
	        $('#c_area').append(op);
		}
	}
}

function cleanForm() {
	$("#form-add-employee")[0].reset();
}

function cleanFormCatalogFase() {
	$("#form-add-catalog-fase")[0].reset();
}

function setValueBtnModal(lbl, title) {
	$('#btn-view-catalog-fase-block').css('display', 'block');
	$('#lblBtnView').text(lbl);
    $('#btn-view-catalog-fase-block').prop('title', title);
}