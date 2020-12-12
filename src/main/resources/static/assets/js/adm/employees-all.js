/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Pagina de consulta Empleados todos
 */

// Cargando sistema
$(document).ready(function($) {
	startingSystemMenu();
	// Llenar tabla empleados
	sendPostAction(EMPLOYEE_ALL_CONTROLLER + 'findEmployeeAll', null, respFindAll);
});

function respFindAll(data){
	// $('body').toggleClass('open');
	if(data){
		var tableEmployee =	  ' <table id="bootstrap-data-table"				'
							+ ' 	class="table mb-0 table-striped">			'
							+ ' <thead>                                         '
							+ ' 	<tr>                                        '
							
							+ ' 		<th>Clave													</th>                          '
							+ ' 		<th>Nombre													</th>                          '
							+ ' 		<th>Apellidos												</th>                          '
							+ ' 		<th>Función													</th>                          '
							
							+ ' 		<th>Fecha Nacimiento										</th>                          '
							+ ' 		<th>Sexo					                                </th>                          '
							+ ' 		<th>Estado Civil			                                </th>                          '
							+ ' 		<th>Nacionalidad			                                </th>                          '
							+ ' 		<th>Email Personal			                                </th>                          '
							+ ' 		<th>NSS						                                </th>                          '
							+ ' 		<th>Cartilla Militar		                                </th>                          '
							+ ' 		<th>Telefono Casa			                                </th>                          '
							+ ' 		<th>Telefono Celular		                                </th>                          '
							+ ' 		<th>Telefono Emergencia										</th>                          '
							+ ' 		<th>Llamar a												</th>                          '
							+ ' 		<th>RFC						                                </th>                          '
							+ ' 		<th>CURP					                                </th>                          '
							+ ' 		<th>Escuela						                           	</th>                          '
							+ ' 		<th>Nivel Estudios											</th>                          '
							+ ' 		<th>Estatus Titulo											</th>                          '
							+ ' 		<th>Certificaciones Vigentes                                </th>                          '
							+ ' 		<th>Certificaciones Vencidas                                </th>                          '
							+ ' 		<th>Otros                                                   </th>                          '
							+ ' 		<th>Calle Numero			                                </th>                          '
							+ ' 		<th>Colonia    				                                </th>                          '
							+ ' 		<th>Delegacion Municipio									</th>                          '
							+ ' 		<th>Codigo Postal    		                                </th>                          '
							+ ' 		<th>Estado    				                                </th>                          '
							+ ' 		<th>1er Empleo                                              </th>                          '
							+ ' 		<th>2do Empleo                                              </th>                          '
							+ ' 		<th>3er Empleo                                              </th>                          '
							+ ' 		<th>Fecha Ingreso											</th>                          '
							+ ' 		<th>Area                                                    </th>                          '
							+ ' 		<th>Puesto                                                  </th>                          '
							+ ' 		<th>Historial Puesto										</th>                          '
							+ ' 		<th>Jefe Inmediato                                          </th>                          '
							+ ' 		<th>Vacaciones Tomados(dias)                           		</th>                          '
							+ ' 		<th>Vacaciones Pendientes(dias)	                        	</th>                          '
							+ ' 		<th>SGMM #Poliza											</th>                          '
							+ ' 		<th>Numero Empleado											</th>                          '
							+ ' 		<th>Email                                                   </th>                          '
							+ ' 		<th>Salario							                        </th>                          '
							+ ' 		<th>Historial Salario										</th>                          '
							+ ' 		<th>Historial Bonos											</th>                          '
							+ ' 		<th>Historial Prestamos										</th>                          '
							+ ' 		<th>Descuento Infonavit                                     </th>                          '
							+ ' 		<th>Pensión Alimenticia                           			</th>                          '
							+ ' 		<th>Actas Administrativas									</th>                          '
							+ ' 		<th>Fecha Baja												</th>                          '
							+ ' 		<th>Motivo Baja												</th>                          '
							+ ' 		<th>Password RRHH											</th>                          '
							+ ' 		<th>Pagares Capacitación									</th>                          '
							+ ' 		<th>Competencias Blandas	                                </th>                          '
							+ ' 		<th>Competencias Técnicas                                   </th>                          '
							+ ' 		<th>Conferencias Otros										</th>                          '
							+ ' 		<th>Archivo Estudios					                    </th>                          '
							+ ' 		<th>Archivo Nacimiento                             			</th>                          '
							+ ' 		<th>Archivo Titulo/Cedula									</th>                          '
							+ ' 		<th>Archivo CURP                                            </th>                          '
							+ ' 		<th>Archivo IMSS                                            </th>                          '
							+ ' 		<th>Archivo Infonavit                                       </th>                          '
							+ ' 		<th>Archivo Identificación                                  </th>                          '
							+ ' 		<th>Archivo Pasaporte/Visa									</th>                          '
							+ ' 		<th>Archivo Antecedentes/NoPenales							</th>                          '
							+ ' 		<th>Archivo Domicilio                           			</th>                          '
							+ ' 		<th>Archivo RefPersonales                          			</th>                          '
							+ ' 		<th>Archivo CV												</th>                          '
							+ ' 		<th>Archivo Foto                                            </th>                          '
							+ ' 		<th>Archivo Certificaciones                                 </th>                          '
							+ ' 		<th>Archivo Actas Administrativas							</th>                          '
							+ ' 		<th>Archivo ContratoLaboral			                   		</th>                          '
							+ ' 		<th>Archivo PropuestaTrabajo								</th>                          '
							+ ' 		<th>Archivo PlanCarrera										</th>                          '
							+ ' 		<th>Archivo PruebasPicometricas                           	</th>                          '
							+ ' 		<th>Archivo Vacaciones                                      </th>                          '
							+ ' 		<th>Archivo Permisos                                        </th>                          '
							+ ' 		<th>Archivo Inv.SocioEconomica								</th>                          '
							+ ' 		<th>Archivo ExamTécnicosInternos                       		</th>                          '
							+ ' 		<th>Archivo ResEvalDesempeño								</th>                          '
							+ ' 		<th>Archivo CurriculoEmpresarial                           	</th>                          '
							+ ' 		<th>Archivo Capacitación									</th>                          '
							+ ' 		<th>Archivo Finiquito                                       </th>                          '
							
							+ ' 	</tr>                                       '
							+ ' </thead>                                        '
							+ ' <tbody>                                         ';
		for (var i = 0; i < data.length; i++){
			var rowEmployee = data[i];
			var tr = '<tr id="employee_' + rowEmployee.id + '">';
			tr += '<td>'+ rowEmployee.id +'</td>';
			tr += '<td>'+ rowEmployee.name +'</td>';
			tr += '<td>'+ rowEmployee.lastName +'</td>';
			tr += '<td>'+ rowEmployee.role +'</td>';
			
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.birthdate					  +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.sex                           +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.civilStatus                   +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.nationality                   +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.emailPersonal                 +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.imss                          +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.militaryPrimer                +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.phone                         +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.cellPhone                     +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.emergencyPhone                +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.emergencyPhoneCall            +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.rfc                           +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-gral">'+ rowEmployee.curp                          +'</a></td>';
			tr += '<td class="table-primary"><a href="/adm/employee-studies">'+ rowEmployee.universityCollege            +'</a></td>';
			tr += '<td class="table-primary"><a href="/adm/employee-studies">'+ rowEmployee.educationalLevel             +'</a></td>';
			tr += '<td class="table-primary"><a href="/adm/employee-studies">'+ rowEmployee.titleStatus                  +'</a></td>';
			tr += '<td class="table-primary"><a href="/adm/employee-studies">'+ rowEmployee.certificationsAviable        +'</a></td>';
			tr += '<td class="table-primary"><a href="/adm/employee-studies">'+ rowEmployee.expiredCertificates          +'</a></td>';
			tr += '<td class="table-primary"><a href="/adm/employee-studies">'+ rowEmployee.others                       +'</a></td>';
			tr += '<td class="table-secondary"><a href="/adm/employee-demographics">'+ rowEmployee.streetNumber               +'</a></td>';
			tr += '<td class="table-secondary"><a href="/adm/employee-demographics">'+ rowEmployee.colony                     +'</a></td>';
			tr += '<td class="table-secondary"><a href="/adm/employee-demographics">'+ rowEmployee.delegationMunicipality     +'</a></td>';
			tr += '<td class="table-secondary"><a href="/adm/employee-demographics">'+ rowEmployee.postalCode                 +'</a></td>';
			tr += '<td class="table-secondary"><a href="/adm/employee-demographics">'+ rowEmployee.state                      +'</a></td>';
			tr += '<td class="table-success"><a href="/adm/employee-workexperience">'+ rowEmployee.employmentA                  +'</a></td>';
			tr += '<td class="table-success"><a href="/adm/employee-workexperience">'+ rowEmployee.employmentB                  +'</a></td>';
			tr += '<td class="table-success"><a href="/adm/employee-workexperience">'+ rowEmployee.employmentC                  +'</a></td>';
			tr += '<td class="table-danger"><a href="/adm/employee-labor">'+ rowEmployee.dateAdmission                 +'</a></td>';
			tr += '<td class="table-danger"><a href="/adm/employee-labor">'+ rowEmployee.area                          +'</a></td>';
			tr += '<td class="table-danger"><a href="/adm/employee-labor">'+ rowEmployee.marketStall                   +'</a></td>';
			tr += '<td class="table-danger"><a href="/adm/employee-labor">'+ rowEmployee.jobHistory                    +'</a></td>';
			tr += '<td class="table-danger"><a href="/adm/employee-labor">'+ rowEmployee.immediateBoss                 +'</a></td>';
			tr += '<td class="table-danger"><a href="/adm/employee-labor">'+ rowEmployee.holidaysDaysTaken             +'</a></td>';
			tr += '<td class="table-danger"><a href="/adm/employee-labor">'+ rowEmployee.holidaysDaysPending           +'</a></td>';
			tr += '<td class="table-danger"><a href="/adm/employee-labor">'+ rowEmployee.sgmm                          +'</a></td>';
			tr += '<td class="table-danger"><a href="/adm/employee-labor">'+ rowEmployee.employeeNumber                +'</a></td>';
			tr += '<td class="table-danger"><a href="/adm/employee-labor">'+ rowEmployee.email                         +'</a></td>';
			tr += '<td class="table-warning"><a href="/adm/employee-economics">'+ rowEmployee.salary                       +'</a></td>';
			tr += '<td class="table-warning"><a href="/adm/employee-economics">'+ rowEmployee.salaryHistory                +'</a></td>';
			tr += '<td class="table-warning"><a href="/adm/employee-economics">'+ rowEmployee.bondHistory                  +'</a></td>';
			tr += '<td class="table-warning"><a href="/adm/employee-economics">'+ rowEmployee.loanHistory                  +'</a></td>';
			tr += '<td class="table-warning"><a href="/adm/employee-economics">'+ rowEmployee.discountInfonavit            +'</a></td>';
			tr += '<td class="table-warning"><a href="/adm/employee-economics">'+ rowEmployee.foodAllowanceDiscount        +'</a></td>';
			tr += '<td class="table-info"><a href="/adm/employee-legal">'+ rowEmployee.administrativeActasAttention  	+'</a></td>';
			tr += '<td class="table-info"><a href="/adm/employee-legal">'+ rowEmployee.dischargeDate                   	+'</a></td>';
			tr += '<td class="table-info"><a href="/adm/employee-legal">'+ rowEmployee.reasonLow                       	+'</a></td>';
			tr += '<td class="table-info"><a href="/adm/employee-legal">'+ rowEmployee.passwordGeneratedRrhh           	+'</a></td>';
			tr += '<td class="table-info"><a href="/adm/employee-legal">'+ rowEmployee.trainingPromissoryNotes         	+'</a></td>';
			tr += '<td>'+ rowEmployee.softCompetitions              					 +'</td>';
			tr += '<td>'+ rowEmployee.technicalSkills               					 +'</td>';
			tr += '<td>'+ rowEmployee.otherExposConferences         					 +'</td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.proofStudies                  +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.birthCertificate              +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.titleProfessionalLicense      +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.fcurp                         +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.fimss                         +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.infonavit                     +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.officialIdentification        +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.passportVisa                  +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.noCriminalRecord              +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.proofAddress                  +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.personalReferences            +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.professionalCurriculum        +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.photo                         +'</a></td>';
			tr += '<td class="table-active"><a href="/adm/employee-system-personal">'+ rowEmployee.certifications                +'</a></td>';
			tr += '<td class="table-danger">'+ rowEmployee.administrativeAttention       +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.employmentContract            +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.workProposal                  +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.careerPlanSigned              +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.psychometricTest              +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.holidays                      +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.permits                       +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.socioEconomicResearch         +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.internalTechnicalExams        +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.performanceEvaluationResults  +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.businessCurriculum            +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.training                      +'</td>';
			tr += '<td class="table-danger">'+ rowEmployee.settlement                    +'</td>';
			
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