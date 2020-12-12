/*
 * @author Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de consulta Empleados todos
 */
var ID_EMPLOYEE_WORKEXPERIENCE = 0;
// id's
var ID_EMPLOYEE_WORKEXPERIENCE_U = 0;
var ID_EMPLOYEE_WORKEXPERIENCE_P = 0;
var ID_EMPLOYEE_WORKEXPERIENCE_A = 0;

// Cargando sistema
$(document).ready(function($) {
	// Validar si datos basicos estan llenos
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeGralExist', null, respFindEmployeeGralExist);
	//
	startingSystemMenu();
	//Llenar tabla empleados
	initTable();
	//
	$('#btn-cancel-employee').on('click', function () {
		ID_EMPLOYEE_WORKEXPERIENCE = 0;
		ID_EMPLOYEE_WORKEXPERIENCE_U = 0;
		ID_EMPLOYEE_WORKEXPERIENCE_P = 0;
		ID_EMPLOYEE_WORKEXPERIENCE_A = 0;
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
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeWorkexperience', null, respFindAll);
}

function respFindAll(data){
	// $('body').toggleClass('open');
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table"				'
							+ ' 	class="table table-bordered">				'
							+ ' <thead>                                         '
							
							+ ' 	<tr>                                       								'
							+ ' 		<th class="table-primary" colspan="2">Información</th>				'
							+ ' 		<th class="table-warning" colspan="6">Ultimo Empleo</th>			'
							+ ' 		<th class="table-info" colspan="6">Penúltimo Empleo</th>			'
							+ ' 		<th class="table-danger" colspan="6">Antepenúltimo Empleo</th>		'
							+ ' 	</tr>                                       							'
							
							+ ' 	<tr >                                       '
							
							+ ' 		<th>Acciones					</th>	'
							
							+ ' 		<th>Nombre Completo				</th>   '
							
							+ ' 		<th>Empresa                 	</th>   '
							+ ' 		<th>Puesto    					</th>   '
							+ ' 		<th>Salario                  	</th>   '
							+ ' 		<th>Fecha inicio               	</th>   '
							+ ' 		<th>Fecha fin         			</th>   '
							+ ' 		<th>Motivo de salida            </th>   '
							+ ' 		<th>Empresa                    	</th>   '
							+ ' 		<th>Puesto               		</th>   '
							+ ' 		<th>Salario            			</th>   '
							+ ' 		<th>Fecha inicio            	</th>   '
							+ ' 		<th>Fecha fin                   </th>   '
							+ ' 		<th>Motivo de salida            </th>   '
							+ ' 		<th>Empresa                     </th>   '
							+ ' 		<th>Puesto                      </th>   '
							+ ' 		<th>Salario                     </th>   '
							+ ' 		<th>Fecha inicio                </th>   '
							+ ' 		<th>Fecha fin                   </th>   '
							+ ' 		<th>Motivo de salida            </th>   '
								
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
				enabledDelete = '<i class="fa fa-trash-o btn btn-danger" title="Eliminar" onclick="confirmDeleteEmployee(\'' + rowEmployee.name + ' ' + rowEmployee.lastName + '\',' + rowEmployee.idU + ',' + rowEmployee.idP + ',' + rowEmployee.idA + ',' + 0 + ')" style="cursor: pointer"></i>';
			}
			var actions =
				enabledDelete + 
				'<i class="fa fa-pencil btn btn-warning" title="Editar" onclick="editEmployee(\'' 	+ rowEmployee.id 										+ '\',' 
																									+ '\'' + rowEmployee.name + ' '+ rowEmployee.lastName  	+ '\','
																									
																									+ '\'' + rowEmployee.idU	    + '\','
																									+ '\'' + rowEmployee.idP	    + '\','
																									+ '\'' + rowEmployee.idA	    + '\','
																									
																									+ '\'' + rowEmployee.companyU	    + '\','
																									+ '\'' + rowEmployee.positionU      + '\','
																									+ '\'' + rowEmployee.salaryU		+ '\','
																									+ '\'' + rowEmployee.dateAdmissionU + '\','
																									+ '\'' + rowEmployee.departureDateU + '\','
																									+ '\'' + rowEmployee.reasonOfExitU	+ '\','
																									+ '\'' + rowEmployee.typeU			+ '\','
																									+ '\'' + rowEmployee.companyP	    + '\','
																									+ '\'' + rowEmployee.positionP      + '\','
																									+ '\'' + rowEmployee.salaryP		+ '\','
																									+ '\'' + rowEmployee.dateAdmissionP + '\','
																									+ '\'' + rowEmployee.departureDateP + '\','
																									+ '\'' + rowEmployee.reasonOfExitP	+ '\','
																									+ '\'' + rowEmployee.typeP			+ '\','
																									+ '\'' + rowEmployee.companyA	    + '\','
																									+ '\'' + rowEmployee.positionA      + '\','
																									+ '\'' + rowEmployee.salaryA		+ '\','
																									+ '\'' + rowEmployee.dateAdmissionA + '\','
																									+ '\'' + rowEmployee.departureDateA + '\','
																									+ '\'' + rowEmployee.reasonOfExitA	+ '\','
																									+ '\'' + rowEmployee.typeA			+ '\''
																									
																									+ ')" style="cursor: pointer"></i>';			
			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td align="center">'+ actions +'</td>';
			
			tr += '<td>'+ rowEmployee.name +' ' + rowEmployee.lastName +'</td>';
			
			tr += '<td>'+ validateObj(rowEmployee.companyU	      ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.positionU       ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.salaryU		  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.dateAdmissionU  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.departureDateU  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.reasonOfExitU	  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.companyP	      ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.positionP       ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.salaryP		  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.dateAdmissionP  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.departureDateP  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.reasonOfExitP	  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.companyA	      ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.positionA       ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.salaryA		  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.dateAdmissionA  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.departureDateA  ) +'</td>';
			tr += '<td>'+ validateObj(rowEmployee.reasonOfExitA	  ) +'</td>';
			
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

function editEmployee(id, name,
		idU,
		idP,
		idA,
		
		companyU	    ,
		positionU       ,
		salaryU		    ,
		dateAdmissionU  ,
		departureDateU  ,
		reasonOfExitU	,
		typeU			,
		companyP	    ,
		positionP       ,
		salaryP		    ,
		dateAdmissionP  ,
		departureDateP  ,
		reasonOfExitP	,
		typeP			,
		companyA	    ,
		positionA       ,
		salaryA		    ,
		dateAdmissionA  ,
		departureDateA  ,
		reasonOfExitA	,
		typeA			
		) {
	$('#add-mod-employee').text('Información de ' + name);
	//
	ID_EMPLOYEE_WORKEXPERIENCE = id;
	ID_EMPLOYEE_WORKEXPERIENCE_U = validateId(idU);
	ID_EMPLOYEE_WORKEXPERIENCE_P = validateId(idP);
	ID_EMPLOYEE_WORKEXPERIENCE_A = validateId(idA);
	//
	cleanForm();
	//
	$('#empUE'		).val(validateObj(companyU	    ));
	$('#puestoUE'   ).val(validateObj(positionU     ));  
	$('#salUE'      ).val(validateObj(salaryU		));    
	$('#feciUE'     ).val(validateObj(dateAdmissionU));  
	$('#fecfUE'     ).val(validateObj(departureDateU));  
	$('#mdsUE'      ).val(validateObj(reasonOfExitU	));
	$('#empPE'      ).val(validateObj(companyP	    ));
	$('#puestoPE'   ).val(validateObj(positionP     ));  
	$('#salPE'      ).val(validateObj(salaryP		));    
	$('#feciPE'     ).val(validateObj(dateAdmissionP));  
	$('#fecfPE'     ).val(validateObj(departureDateP));  
	$('#mdsPE'      ).val(validateObj(reasonOfExitP	));
	$('#empAE'      ).val(validateObj(companyA	    ));
	$('#puestoAE'   ).val(validateObj(positionA     ));  
	$('#salAE'      ).val(validateObj(salaryA		));    
	$('#feciAE'     ).val(validateObj(dateAdmissionA));  
	$('#fecfAE'     ).val(validateObj(departureDateA));  
	$('#mdsAE'      ).val(validateObj(reasonOfExitA	));
	//
	showAddEmployee();
}

function confirmDeleteEmployee(name, idU, idP, idA, active) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': '¿ Seguro de eliminar información laboral a </br>' + name + ' ?',
        'confirmBtnText': 'Si',
        'denyBtnText': 'No estoy seguro',
        'theme': 'tBeIt',
        'size': 'sm',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
        	activeEmployee(idU, idP, idA, active);
        }
    });
}

function activeEmployee(idU, idP, idA, active) {
	showPreloader(true);
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'enabledUserEmployeeWorkExperience/' 
			+ idU + '/' 
			+ idP + '/'
			+ idA + '/' + active, null, respActiveEmployeeWorkExperience);
}

function respActiveEmployeeWorkExperience(data) {
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
	user.id										= ID_EMPLOYEE_WORKEXPERIENCE;
	user.email									= sessionStorage.getItem("email");
	// Llenar Empleado
	var employeegral = new employeeGral();
	var employeeworkexperienceU = new employeeWorkExperience();
	var employeeworkexperienceP = new employeeWorkExperience();
	var employeeworkexperienceA = new employeeWorkExperience();
	var listEmployee = [];
	// Ultimo
	employeeworkexperienceU.id						= ID_EMPLOYEE_WORKEXPERIENCE_U;
	employeeworkexperienceU.company					= '' + $('#empUE').val();
	employeeworkexperienceU.position				= '' + $('#puestoUE').val();
	employeeworkexperienceU.salary					= '' + $('#salUE').val();
	employeeworkexperienceU.dateAdmission			= '' + $('#feciUE').val();
	employeeworkexperienceU.departureDate			= '' + $('#fecfUE').val();
	employeeworkexperienceU.reasonOfExit			= '' + $('#mdsUE').val();
	employeeworkexperienceU.type					= 'U';
	employeeworkexperienceU.active             		= 1;
	listEmployee[0] 								= employeeworkexperienceU;
	// Penultimo
	employeeworkexperienceP.id						= ID_EMPLOYEE_WORKEXPERIENCE_P;
	employeeworkexperienceP.company					= '' + $('#empPE').val();
	employeeworkexperienceP.position				= '' + $('#puestoPE').val();
	employeeworkexperienceP.salary					= '' + $('#salPE').val();
	employeeworkexperienceP.dateAdmission			= '' + $('#feciPE').val();
	employeeworkexperienceP.departureDate			= '' + $('#fecfPE').val();
	employeeworkexperienceP.reasonOfExit			= '' + $('#mdsPE').val();
	employeeworkexperienceP.type					= 'P';
	employeeworkexperienceP.active             		= 1;
	listEmployee[1] 								= employeeworkexperienceP;
	// Ante Penultimo
	employeeworkexperienceA.id						= ID_EMPLOYEE_WORKEXPERIENCE_A;
	employeeworkexperienceA.company					= '' + $('#empAE').val();
	employeeworkexperienceA.position				= '' + $('#puestoAE').val();
	employeeworkexperienceA.salary					= '' + $('#salAE').val();
	employeeworkexperienceA.dateAdmission			= '' + $('#feciAE').val();
	employeeworkexperienceA.departureDate			= '' + $('#fecfAE').val();
	employeeworkexperienceA.reasonOfExit			= '' + $('#mdsAE').val();
	employeeworkexperienceA.type					= 'A';
	employeeworkexperienceA.active             		= 1;
	listEmployee[2] 								= employeeworkexperienceA;
	// Setear Empleado
	employeegral.employeeWorkExperience = listEmployee;
	user.employee = employeegral;
	
	console.log(user);
	//
	sendPostAction(EMPLOYEE_GRAL_CONTROLLER 
			+ 'saveUserEmployeeWorkExperience/' 
			+ ID_EMPLOYEE_WORKEXPERIENCE_U + '/' 
			+ ID_EMPLOYEE_WORKEXPERIENCE_P + '/'
			+ ID_EMPLOYEE_WORKEXPERIENCE_A, user, respSaveEmployeeWorkExperience);
}

function respSaveEmployeeWorkExperience(data) {
	// cleanGeneralVariables();
	showPreloader(false);
	ID_EMPLOYEE_WORKEXPERIENCE = 0;
	if(data && data[1] == 'Ok'){
		showDivMessage(data[2], 'alert-info', 3000);
		initTable();
	} else {
		showDivMessage(data[2], 'alert-danger', 5000);
	}
}

function cleanForm() {
	$("#form-add-employee")[0].reset();
}