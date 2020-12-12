INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(1, 'date-expiration', 'Tiempo de expiración en certificación', '92', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(2, 'date-vacation', 'Tiempo para poder solicitar vacaciones (dias)', '331', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(100, 'EMAIL-SETHOSTNAME', 'IP de Office 365', 'smtp.office365.com', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(101, 'EMAIL-SETSMTPPORT', 'Puerto del correo', '587', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(102, 'EMAIL-USERNAME', 'Usuario de Office 365', 'ngonzalez@buromc.com', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(103, 'EMAIL-KEY', 'Contraseña de Office 365', 'NazarioDaza33v', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(104, 'EMAIL-SETSTARTTLSENABLED', 'TLS', '1', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(105, 'EMAIL-SETFROM', 'Origen de envio', 'ngonzalez@buromc.com', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(106, 'EMAIL-SETDEBUG', 'Muestra en consola el proceso de envio de correo', '1', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(107, 'EMAIL-CORREO', 'Plantilla para vencimiento de correo', 'Estimado colaborador:</br>Se te informa que el registro de tus certificaciones estan prontas a vencer.</br>Sin más por el momento quedo a sus órdenes.', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(200, 'JOB-CLOSE-CERTIFICATION', 'Certificaciones prontas a vencer', 'Certificaciones prontas a vencer', 1);
INSERT INTO test.`parameter`
(id, name, description, value, active)
VALUES(201, 'JOB-CLOSE-CERTIFICATION-EXPIRATION', 'Certificaciones Vencidas', 'Certificaciones Vencidas', 1);
