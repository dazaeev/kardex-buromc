/*
 * @author Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de inicio
 */
var TYPE_GRAPH = '';
var WHAT_WIDGET_FOR_AREA ='';
var WHAT_WIDGET_HRS_BY_USER = '';
var WHAT_WIDGET_CERT_BY_USER = '';
// Cargando sistema
jQuery(document).ready(function(jQuery) {
	// ZOOM Div
	// $('#content-main').animate({ 'zoom': 0.7 }, 'slow');
	document.body.style.zoom = "70%";
	//
	startingSystemMenu('home');
	// Creando tableros dinamicos
	createDynamicBoard('4');
	// Iniciar Yammer Social
	yammerSocial('light');
	//
	chargeCharts();
	//
	viewRecord();
	//
	$('#btn-cancel-employee').on('click', function () {
		location.reload();
	});
	//
	$('#btn-cancel-employee-select').on('click', function () {
		location.reload();
	});
	//
	$('a[href="#DashboardCertificationsByAreaBar"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones dadas de alta por Área');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'bar';
		WHAT_WIDGET_FOR_AREA='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsByArea', null, respCertificationsByArea);
	});
	$('a[href="#DashboardCertificationsByAreaCircle"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones dadas de alta por Área');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'pie';
		WHAT_WIDGET_FOR_AREA='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsByArea', null, respCertificationsByArea);
	});
	$('a[href="#DashboardCertificationsByAreaDoughnut"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones dadas de alta por Área');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'doughnut';
		WHAT_WIDGET_FOR_AREA='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsByArea', null, respCertificationsByArea);
	});
	//
	// ######################################	Horas de capacitación registradas por colaborador	######################################
	// ######################################	Certificaciones realizadas por colaborador			######################################
	$('a[href="#DashboardCertificationsHrsByUserBar"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Horas de capacitación registradas por colaborador');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'bar';
		WHAT_WIDGET_HRS_BY_USER='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByUser', null, respCertificationsHrsByUser);
	});
	$('a[href="#DashboardCertificationsHrsByUserCircle"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Horas de capacitación registradas por colaborador');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'pie';
		WHAT_WIDGET_HRS_BY_USER='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByUser', null, respCertificationsHrsByUser);
	});
	$('a[href="#DashboardCertificationsHrsByUserDoughnut"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Horas de capacitación registradas por colaborador');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'doughnut';
		WHAT_WIDGET_HRS_BY_USER='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByUser', null, respCertificationsHrsByUser);
	});
	//
	$('a[href="#DashboardCertificationsTechnologyByUserBar"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones realizadas por colaborador');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'bar';
		WHAT_WIDGET_CERT_BY_USER='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByUser', null, respCertificationsTechnologyByUser);
	});
	$('a[href="#DashboardCertificationsTechnologyByUserCircle"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones realizadas por colaborador');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'pie';
		WHAT_WIDGET_CERT_BY_USER='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByUser', null, respCertificationsTechnologyByUser);
	});
	$('a[href="#DashboardCertificationsTechnologyByUserDoughnut"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones realizadas por colaborador');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'doughnut';
		WHAT_WIDGET_CERT_BY_USER='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByUser', null, respCertificationsTechnologyByUser);
	});
	//
	// ######################################	Horas de capacitación registradas por Área	######################################
	// ######################################	Certificaciones realizadas por Área			######################################
	$('a[href="#DashboardCertificationsHrsByAreaBar"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Horas de capacitación registradas por Área');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'bar';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByArea', null, respCertificationsHrsByUser);
	});
	$('a[href="#DashboardCertificationsHrsByAreaCircle"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Horas de capacitación registradas por Área');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'pie';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByArea', null, respCertificationsHrsByUser);
	});
	$('a[href="#DashboardCertificationsHrsByAreaDoughnut"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Horas de capacitación registradas por Área');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'doughnut';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByArea', null, respCertificationsHrsByUser);
	});
	//
	$('a[href="#DashboardCertificationsTechnologyByAreaBar"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones realizadas por Área');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'bar';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByArea', null, respCertificationsTechnologyByUser);
	});
	$('a[href="#DashboardCertificationsTechnologyByAreaCircle"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones realizadas por Área');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'pie';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByArea', null, respCertificationsTechnologyByUser);
	});
	$('a[href="#DashboardCertificationsTechnologyByAreaDoughnut"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones realizadas por Área');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'doughnut';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByArea', null, respCertificationsTechnologyByUser);
	});
	//
	// ######################################	Horas de capacitación registradas por TI	######################################
	// ######################################	Certificaciones realizadas por TI			######################################
	$('a[href="#DashboardCertificationsHrsByTIBar"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Horas de capacitación registradas por TI');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'bar';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByTI', null, respCertificationsHrsByUser);
	});
	$('a[href="#DashboardCertificationsHrsByTICircle"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Horas de capacitación registradas por TI');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'pie';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByTI', null, respCertificationsHrsByUser);
	});
	$('a[href="#DashboardCertificationsHrsByTIDoughnut"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Horas de capacitación registradas por TI');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'doughnut';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByTI', null, respCertificationsHrsByUser);
	});
	//
	$('a[href="#DashboardCertificationsTechnologyByTIBar"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones realizadas por TI');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'bar';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByTI', null, respCertificationsTechnologyByUser);
	});
	$('a[href="#DashboardCertificationsTechnologyByTICircle"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones realizadas por TI');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'pie';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByTI', null, respCertificationsTechnologyByUser);
	});
	$('a[href="#DashboardCertificationsTechnologyByTIDoughnut"]').click(function(){
		$("#contentHome").empty();
		//
		viewContent('Certificaciones realizadas por TI');
		//
		var viewDoc = '<div class="chart-wrapper px-0" style="height:70px;" height="70"/><canvas id="widgetChart1"></canvas></div>';
		$("#contentHome").append(viewDoc);
		TYPE_GRAPH = 'doughnut';
		WHAT_WIDGET='widgetChart1';
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByTI', null, respCertificationsTechnologyByUser);
	});
	// ######################################	Horas de capacitación registradas Área (todos los colaboradores)	######################################
	$('a[href="#DashboardCertificationsByAreaFaseBloqueTechnologyProductBar"]').click(function(){
		$("#contentHome").empty();
		//
		viewContentSelect('Capacitaciones por colaborador');
		//
		
		TYPE_GRAPH = 'bar';

		$("#doneAndToDo").remove();
		//load-area
		loadarea();
	});
	$('#load-area').on('change', function() {
		loadEmployees($('#load-area').val());
	});
	$('#load-certification').on('change', function() {
		var emp =$('#load-certification').val();
		$("#doneAndToDo").remove();
		//var viewDoc = '<div id ="doneAndToDo" class="chart-wrapper px-0" style="height:70px;" height="70"><canvas id="widgetChart1"></canvas><canvas id="widgetChart2"></canvas></div>';
		var viewDoc = '<div id ="doneAndToDo" class="row" style="padding-top:3%;"><div class="col-md-6"><canvas id="widgetChart1"></canvas></div><div class="col-md-6"><canvas id="widgetChart2"></canvas></div></div>';
		$("#contentHomeSelect").append(viewDoc);
		loadChart(emp);
	});
});

function record() {
	// Modal Recorrido
	openModalRecordPortal();
}

function openModalRecordPortal() {
	$("#modal-record-portal").modal({
		modal:open,
		showClose: false
	});
}

function chargeCharts(){
	WHAT_WIDGET_FOR_AREA='widgetChartPP1';
	TYPE_GRAPH = 'bar';
	sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsByArea', null, respCertificationsByArea);
	WHAT_WIDGET_HRS_BY_USER='widgetChartPP2';
	sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCertificationsHrsByUser', null, respCertificationsHrsByUser);
	
}

function loadEmployees(idArea){
	if(idArea != ''){
		sendPostAction(EMPLOYEE_GRAL_CONTROLLER + 'findEmployeeByArea/' + idArea, null, fillEmp);
	}
}

function loadChart(idEmp){
	if(idEmp != ''){
		// Llenar tabla empleados
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardCountCertificationsDoneAndToDo/'+ idEmp, null, respCertificationsDoneAndToDo);
		sendPostAction(EMPLOYEE_DASHBOARD + 'dashboardPercentageDoneAndToDo/'+ idEmp, null, respPercentageCertificationsDoneAndToDo);
		
	}
}

function fillEmp(data) {
	$('#load-certification').empty();
	var opDefault = new Option('Seleccionar empleado ...', '');
    $('#load-certification').append(opDefault);
	for (var i = 0; i < data.length; i++){
        var op = new Option(data[i].name +' '+ data[i].lastname, data[i].id);
        $('#load-certification').append(op);
    }
}

function loadarea() {
	sendPostAction(EMPLOYEE_CATALOGS_CONTROLLER + 'findCatalogArea', null, fillArea);
}

function fillArea(data) {
	var opDefault = new Option('Seleccionar área ...', '');
    $('#load-area').append(opDefault);
	for (var i = 0; i < data.length; i++){
        var op = new Option(data[i].name, data[i].id);
        $('#load-area').append(op);
    }
}

function viewContent(message) {
	$('#content-dashboard').css('display', 'block');
	$('#main-content').css('display', 'none');
	$('#title-image').text(message);
}

function viewContentSelect(message) {
	$('#content-dashboard-select').css('display', 'block');
	$('#main-content').css('display', 'none');
	$('#title-image-select').text(message);
}

function respCertificationsByArea(data) {
	// var donde= ["neza","chima","california","houston"];
	// var cuantos= [10,9,8,7];
	// var color=['rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)', 'rgba(255, 206, 86, 0.6)', 'rgba(75, 192, 192, 0.6)'];
	var donde 	= [];
	var cuantos	= [];
	var color	= [];
	if(data){
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			donde[i]	= rowEmployee.area;
			cuantos[i]	= rowEmployee.number_area;
			color[i]	= dynamicColors();
		}
	}
	
	var labelT="Certificaciones registradas";
	showGraph1(labelT, donde, cuantos, color, TYPE_GRAPH, 'tecnología', 'tecnologías', WHAT_WIDGET_FOR_AREA);
}

function respCertificationsHrsByUser(data) {
	// var donde= ["neza","chima","california","houston"];
	// var cuantos= [10,9,8,7];
	// var color=['rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)', 'rgba(255, 206, 86, 0.6)', 'rgba(75, 192, 192, 0.6)'];
	var donde 	= [];
	var cuantos	= [];
	var color	= [];
	if(data){
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			donde[i]	= rowEmployee.name_all;
			cuantos[i]	= rowEmployee.duration_hr;
			color[i]	= dynamicColors();
		}
	}
	
	var labelT="Horas de capacitación registradas";
	showGraph1(labelT, donde, cuantos, color, TYPE_GRAPH, 'hr', 'hrs', WHAT_WIDGET_HRS_BY_USER);
}

function respCertificationsDoneAndToDo(data) {
	// var donde= ["neza","chima","california","houston"];
	// var cuantos= [10,9,8,7];
	// var color=['rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)', 'rgba(255, 206, 86, 0.6)', 'rgba(75, 192, 192, 0.6)'];
	var donde 	= [];
	var cuantos	= [];
	var color	= [];
	if(data){
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			donde[i]	= rowEmployee.where;
			cuantos[i]	= rowEmployee.howmany;
			color[i]	= dynamicColors();
		}
	}
	
	var labelT="Cursos/Capacitaciones registradas";
	showGraph3(labelT, donde, cuantos, color, TYPE_GRAPH, 'curso/certificacion', 'cursos/certificaciones');
}

function respPercentageCertificationsDoneAndToDo(data) {
	var donde 	= [];
	var cuantos	= [];
	var color	= [];
	if(data){
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			donde[i]	= rowEmployee.type;
			cuantos[i]	= rowEmployee.percentage;
			color[i]	= dynamicColors();
		}
	}
	var TYPE_GRAPH = 'pie';
	var labelT="Porcentaje de avance";
	showGraph2(labelT, donde, cuantos, color, TYPE_GRAPH, 'curso/certificacion', 'cursos/certificaciones');
}

function respCertificationsTechnologyByUser(data) {
	// var donde= ["neza","chima","california","houston"];
	// var cuantos= [10,9,8,7];
	// var color=['rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)', 'rgba(255, 206, 86, 0.6)', 'rgba(75, 192, 192, 0.6)'];
	var donde 	= [];
	var cuantos	= [];
	var color	= [];
	if(data){
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			donde[i]	= rowEmployee.name_all;
			cuantos[i]	= rowEmployee.count_technology;
			color[i]	= dynamicColors();
		}
	}
	
	var labelT="Tecnologias registradas";
	showGraph1(labelT, donde, cuantos, color, TYPE_GRAPH, 'Tecnologia', 'Tecnologias',WHAT_WIDGET_CERT_BY_USER);
}

function showGraph1(labelT, donde, cuantos, color, type, labelTop, labelTops, widget) {
	var ctx = document.getElementById(widget);
	ctx.height = 90;
	var barChart = null;
	if(type == 'bar') {
		barChart = new Chart(ctx, {
			type : type,
			data : {
				labels : donde,
				datasets : [ {
					label : labelT,
					data : cuantos,
					backgroundColor : color
				} ]
			},
			options : {
				scales : {
					xAxes : [ {
						stacked : true
					} ],
					yAxes : [ {
						stacked : true
					} ]
				},
				// caso de grafica de barras
				tooltips: {
	                enabled: true
	            },
	            hover: {
	                animationDuration: 1
	            },
	            animation: {
	                duration: 3000,
	                onComplete: function () {
	                    var chartInstance = this.chart,
	                        ctx = chartInstance.ctx;
	                    ctx.textAlign = 'center';
	                    ctx.fillStyle = "rgba(0, 0, 0, 1)";
	                    ctx.textBaseline = 'bottom';
	                    this.data.datasets.forEach(function (dataset, i) {
	                        var meta = chartInstance.controller.getDatasetMeta(i);
	                        meta.data.forEach(function (bar, index) {
	                        	var enviroment = '';
	                        	if(dataset.data[index] == 1) {
	                        		enviroment = ' ' + labelTop;
	                        	} else {
	                        		enviroment = ' ' + labelTops;
	                        	}
	                            var data = dataset.data[index] + enviroment;
	                            ctx.fillText(data, bar._model.x, bar._model.y + 20);
	                        });
	                    });
	                }
	            }
			}
		});
	} else {
		Chart.defaults.global.animation.duration = 3000;
		barChart = new Chart(ctx, {
			type : type,
			data : {
				labels : donde,
				datasets : [ {
					label : labelT,
					data : cuantos,
					backgroundColor : color
				} ]
			},
			options : {
				scales : {
					xAxes : [ {
						stacked : true
					} ],
					yAxes : [ {
						stacked : true
					} ]
				}
			}
		});
	}
}

function showGraph2(labelT, donde, cuantos, color, type, labelTop, labelTops) {
	var ctx = document.getElementById("widgetChart2");
	ctx.height = 210;
	var barChart = null;
	if(type == 'bar') {
		barChart = new Chart(ctx, {
			type : type,
			data : {
				labels : donde,
				datasets : [ {
					label : labelT,
					data : cuantos,
					backgroundColor : color
				} ]
			},
			options : {
				scales : {
					xAxes : [ {
						stacked : true
					} ],
					yAxes : [ {
						stacked : true
					} ]
				},
				// caso de grafica de barras
				tooltips: {
	                enabled: true
	            },
	            hover: {
	                animationDuration: 1
	            },
	            animation: {
	                duration: 3000,
	                onComplete: function () {
	                    var chartInstance = this.chart,
	                        ctx = chartInstance.ctx;
	                    ctx.textAlign = 'center';
	                    ctx.fillStyle = "rgba(0, 0, 0, 1)";
	                    ctx.textBaseline = 'bottom';
	                    this.data.datasets.forEach(function (dataset, i) {
	                        var meta = chartInstance.controller.getDatasetMeta(i);
	                        meta.data.forEach(function (bar, index) {
	                        	var enviroment = '';
	                        	if(dataset.data[index] == 1) {
	                        		enviroment = ' ' + labelTop;
	                        	} else {
	                        		enviroment = ' ' + labelTops;
	                        	}
	                            var data = dataset.data[index] + enviroment;
	                            if(dataset.data.length > 1) {
	                            	ctx.fillText(data, bar._model.x, bar._model.y - 10);
	                            } else {
	                            	ctx.fillText(data, bar._model.x, bar._model.y + 20);
	                            }
	                        });
	                    });
	                }
	            }
			}
		});
	} else {
		Chart.defaults.global.animation.duration = 3000;
		barChart = new Chart(ctx, {
			type : type,
			data : {
				labels : donde,
				datasets : [ {
					label : labelT,
					data : cuantos,
					backgroundColor : color
				} ]
			},
			options : {
				scales : {
					xAxes : [ {
						stacked : true
					} ],
					yAxes : [ {
						stacked : true
					} ]
				}
			}
		});
	}
}

function showGraph3(labelT, donde, cuantos, color, type, labelTop, labelTops) {
	var ctx = document.getElementById("widgetChart1");
	ctx.height = 210;
	var barChart = null;
	if(type == 'bar') {
		barChart = new Chart(ctx, {
			type : type,
			data : {
				labels : donde,
				datasets : [ {
					label : labelT,
					data : cuantos,
					backgroundColor : color
				} ]
			},
			options : {
				scales : {
					xAxes : [ {
						stacked : true
					} ],
					yAxes : [ {
						stacked : true
					} ]
				},
				// caso de grafica de barras
				tooltips: {
	                enabled: true
	            },
	            hover: {
	                animationDuration: 1
	            },
	            animation: {
	                duration: 3000,
	                onComplete: function () {
	                    var chartInstance = this.chart,
	                        ctx = chartInstance.ctx;
	                    ctx.textAlign = 'center';
	                    ctx.fillStyle = "rgba(0, 0, 0, 1)";
	                    ctx.textBaseline = 'bottom';
	                    this.data.datasets.forEach(function (dataset, i) {
	                        var meta = chartInstance.controller.getDatasetMeta(i);
	                        meta.data.forEach(function (bar, index) {
	                        	var enviroment = '';
	                        	if(dataset.data[index] == 1) {
	                        		enviroment = ' ' + labelTop;
	                        	} else {
	                        		enviroment = ' ' + labelTops;
	                        	}
	                            var data = dataset.data[index] + enviroment;
	                            ctx.fillText(data, bar._model.x, bar._model.y + 20);
	                        });
	                    });
	                }
	            }
			}
		});
	} else {
		Chart.defaults.global.animation.duration = 3000;
		barChart = new Chart(ctx, {
			type : type,
			data : {
				labels : donde,
				datasets : [ {
					label : labelT,
					data : cuantos,
					backgroundColor : color
				} ]
			},
			options : {
				scales : {
					xAxes : [ {
						stacked : true
					} ],
					yAxes : [ {
						stacked : true
					} ]
				}
			}
		});
	}
}

function dynamicColors() {
	var r = Math.floor(Math.random() * 255);
    var g = Math.floor(Math.random() * 255);
    var b = Math.floor(Math.random() * 255);
    return "rgb(" + r + "," + g + "," + b + ", 0.5)";
}

function e(data) {
	window.location = data;
}