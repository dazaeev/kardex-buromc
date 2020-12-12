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
});

function initTable() {
	// Llenar tabla empleados
	var data = {
		search: '' + sessionStorage.getItem('advanced-search')
	}
	sendPostAction(EMPLOYEE_HOME + 'callDynamicSearch', data, respDynamicSearch);
}

function respDynamicSearch(data){
	if(data){
		if(data[1] == 'Ok') {
			//
			$("#info-employee").empty();
			var wrapper = $("#info-employee");
			//
			var arrayTable = data[2];
			for (var i = 0; i < arrayTable.length; i++){
				var rowTable 	= arrayTable[i];
				var nameTable 	= rowTable.file;
				var fieldTable 	= rowTable.fieldTable
				var idTable 	= rowTable.idTable;
				var valueTable 	= rowTable.table;
				// Magia
				var divWrapper = 
					'						<h6>' + 
						'Tabla: ' + nameTable + ', campo : ' + fieldTable +
					'</h6>' +
					'							<div class="row form-group">                                             ' +
					'								<div class="col-md-12">                                              ' +
					'									<div class="card">                                               ' +
					'										<div class="card-body">                                      ' +
					'											<div class="table-responsive">                           ';
				var tableEmployee = valueTable;
				divWrapper = 
					divWrapper + 
					tableEmployee +
					'											</div>' + 
					'										</div>    ' + 
					'									</div>        ' + 
					'								</div>            ' + 
					'							</div>                ';
				$(wrapper).append(divWrapper); // add html
				exportTable(idTable, 
						[
							{extend: 'excelHtml5', text: '<i class="fa fa-file-excel-o" style="font-size:20px;color:green"></i>', titleAttr: 'Exportar a Excel'}
						]);
				reloadTable(idTable);
				//
			}
		} else {
			showDivMessage(data[2], 'alert-info', 6000);
			alertInfo('Información', 
					'<strong>' + data[2] + '</strong>', 
					'Entendido');
		}
	}
}