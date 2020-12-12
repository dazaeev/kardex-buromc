/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de control "employee_gral"
 */

// Variables Globales
var ID_EMPLOYEE_GRAL = 0;

// Cargando sistema
$(document).ready(function($) {
	startingSystemMenu();
	//
	initTable();
	//
	$('#btn-cancel-employee').on('click', function () {
		ID_EMPLOYEE_GRAL = 0;
		cleanForm();
	    hideAddEmployee();
	});
	$('#btn-save-employee').on('click', function () {
        console.log('Guardar empleado');
        $("#form-add-employee").submit();
    });

	//
	$("#form-add-employee").validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
			console.log('Formulario invalido');
			showDivMessage('Favor de completar campos obligatorios', 'alert-danger', 2000);
		} else {
			showPreloader(true);
			saveEmployee();
			cleanForm();
			//
	        hideAddEmployee();
	        return false;
		}
	});
});

function initTable() {
	// iniciar contenido tabla
	$("#seccion-table-employee").empty();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeGral', null, respFindAll);
}

function respFindAll(data){
	// $('body').toggleClass('open');
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table"				'
							+ ' 	class="table mb-0">							'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							+ ' 		<th>Acciones    											</th>                          '
							
							+ ' 		<th>Id Empleado												</th>                          '
							+ ' 		<th>Nombre Completo											</th>                          '
							+ ' 		<th>Función													</th>                          '
							
							+ ' 		<th>Fecha Nacimiento										</th>                          '
							+ ' 		<th>Género					                                </th>                          '
							+ ' 		<th>Estado Civil			                                </th>                          '
							+ ' 		<th>Nacionalidad			                                </th>                          '
							+ ' 		<th>Email Personal			                                </th>                          '
							+ ' 		<th>NSS						                                </th>                          '
							+ ' 		<th>Cartilla Militar		                                </th>                          '
							+ ' 		<th>Teléfono Casa			                                </th>                          '
							+ ' 		<th>Teléfono Celular		                                </th>                          '
							+ ' 		<th>Teléfono Emergencia										</th>                          '
							+ ' 		<th>Llamar a												</th>                          '
							+ ' 		<th>RFC						                                </th>                          '
							+ ' 		<th>CURP					                                </th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			// Validar botones
			var enabledDelete = '';
			if(rowEmployee.enabled) {
				// enabled = 'style="display: none;"';
				enabledDelete = '<i class="fa fa-trash-o btn btn-secondary" title="No existe información Básica"></i>';
			} else {
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployee(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.id + ',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployee(\'' + rowEmployee.id + '\',' + '\'' + rowEmployee.name + '\',' +'\'' + rowEmployee.lastName + '\',' +'\'' + rowEmployee.birthdate + '\',' + '\'' +rowEmployee.sex + '\',' +'\'' + rowEmployee.civilStatus + '\',' + '\'' + rowEmployee.nationality + '\',' + '\'' + rowEmployee.emailPersonal + '\',' + '\'' +rowEmployee.imss + '\',' +'\'' + rowEmployee.militaryPrimer + '\',' +'\'' + rowEmployee.phone + '\',' +'\'' + rowEmployee.cellPhone + '\',' + '\'' + rowEmployee.emergencyPhone + '\',' + '\'' + rowEmployee.emergencyPhoneCall + '\',' + '\'' + rowEmployee.rfc + '\',' +'\'' + rowEmployee.curp +'\'' + ')" style="cursor: pointer"></i>';
			
			var tr = '<tr id="employee_' + rowEmployee.id + '">';

			tr += '<td align="center">'+ actions +'</td>';
			tr += '<td>'+ rowEmployee.uuid								+'</td>';
			tr += '<td>'+ rowEmployee.name + ' ' + rowEmployee.lastName +'</td>';
			tr += '<td>'+ rowEmployee.role								+'</td>';
			
			tr += '<td>'+ rowEmployee.birthdate						+'</td>';
			tr += '<td>'+ rowEmployee.sex                           +'</td>';
			tr += '<td>'+ rowEmployee.civilStatus                   +'</td>';
			tr += '<td>'+ rowEmployee.nationality                   +'</td>';
			tr += '<td>'+ rowEmployee.emailPersonal                 +'</td>';
			tr += '<td>'+ rowEmployee.imss                          +'</td>';
			tr += '<td>'+ rowEmployee.militaryPrimer                +'</td>';
			tr += '<td>'+ rowEmployee.phone                         +'</td>';
			tr += '<td>'+ rowEmployee.cellPhone                     +'</td>';
			tr += '<td>'+ rowEmployee.emergencyPhone                +'</td>';
			tr += '<td>'+ rowEmployee.emergencyPhoneCall            +'</td>';
			tr += '<td>'+ rowEmployee.rfc                           +'</td>';
			tr += '<td>'+ rowEmployee.curp                          +'</td>';
			
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

function showAddEmployee() {
	$('#info-employee').css('display', 'none');
    $('#add-employee').css('display', 'block');
    $("#btn-cancel-employee").show();
	$("#btn-save-employee").show();
}

function hideAddEmployee() {
	$('#info-employee').css('display', 'block');
    $('#add-employee').css('display', 'none');
    $("#btn-cancel-employee").hide();
	$("#btn-save-employee").hide();
}

function saveEmployee() {
	var user = new User();
	user.id										= ID_EMPLOYEE_GRAL;
	user.email									= sessionStorage.getItem("email");
	// Llenar Empleado
	var employeegral = new employeeGral();
	employeegral.birthdate 						= '' + $('#birthday').val();
	employeegral.sex                            = $("#load-sex").val()
	employeegral.civilStatus                    = $('#load-civilStatus').val();
	employeegral.nationality                    = '' + $('#nationality').val();
	employeegral.emailPersonal                  = '' + $('#personal-email').val();
	employeegral.imss                    		= '' + $('#nss').val();
	employeegral.militaryPrimer                 = '' + $('#c_militar').val();
	employeegral.phone                          = '' + $('#t_casa').val();
	employeegral.cellPhone                      = '' + $('#t_celular').val();
	employeegral.emergencyPhone                 = '' + $('#t_emergencia').val();
	employeegral.emergencyPhoneCall             = '' + $('#t_emergencia_name').val();
	employeegral.rfc                            = '' + $('#rfc').val();
	employeegral.curp                           = '' + $('#curp').val();
	employeegral.active							= 1;
	
	// Setear Empleado
	user.employee = employeegral;
	console.log(user);
	//
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'saveUserEmployeeGral', user, respSaveEmployeeGral);
}

function respSaveEmployeeGral(data) {
	// cleanGeneralVariables();
	showPreloader(false);
	ID_EMPLOYEE_GRAL = 0;
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 3000);
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function confirmDeleteEmployee(name, id, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': '¿ Seguro de eliminar información basica a </br>' + name + ' ?',
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

function activeEmployee(id, active) {
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'enabledUserEmployeeGral/' + id + '/' + active, null, respActiveEmployeeGral);
}

function respActiveEmployeeGral(data) {
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

function editEmployee(id, name, lastName, birthdate, sex, civilStatus, nationality, emailPersonal, imss, militaryPrimer, phone, cellPhone, emergencyPhone, emergencyPhoneCall, rfc, curp) {
	$('#add-mod-employee').text('Información de ' + name);
	//
	ID_EMPLOYEE_GRAL = id;
	cleanForm();
	$('#name').val(name);
	$('#lastName').val(lastName);
	$('#birthday').val(birthdate);
	$("#load-sex option[value='"+ sex +"']").prop('selected', true);
	$("#load-civilStatus option[value='"+ civilStatus +"']").prop('selected', true);
	$('#nationality').val(nationality);
	$('#personal-email').val(emailPersonal);
	$('#nss').val(imss);
	$('#c_militar').val(militaryPrimer);
	$('#t_casa').val(phone);
	$('#t_celular').val(cellPhone);
	$('#t_emergencia').val(emergencyPhone);
	$('#t_emergencia_name').val(emergencyPhoneCall);
	$('#rfc').val(rfc);
	$('#curp').val(curp);
	//
	showAddEmployee();
}

function cleanForm() {
	$("#form-add-employee")[0].reset();
}