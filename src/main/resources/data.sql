INSERT INTO estado_inscripcion (id, codigo, nombre, descripcion, activo)
VALUES (1, 'PRE', 'Preinscrito', 'Registrado sin acceso', true)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

INSERT INTO estado_inscripcion (id, codigo, nombre, descripcion, activo)
VALUES (2, 'PEN', 'Pendiente', 'En validación', true)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

INSERT INTO estado_inscripcion (id, codigo, nombre, descripcion, activo)
VALUES (3, 'ACT', 'Inscrito', 'Acceso activo', true)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

INSERT INTO estado_inscripcion (id, codigo, nombre, descripcion, activo)
VALUES (4, 'SUS', 'Suspendido', 'Bloqueado temporalmente', true)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

INSERT INTO estado_inscripcion (id, codigo, nombre, descripcion, activo)
VALUES (5, 'RET', 'Retirado', 'Se retiró del curso', true)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

INSERT INTO estado_inscripcion (id, codigo, nombre, descripcion, activo)
VALUES (6, 'FIN', 'Finalizado', 'Curso completado', true)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

INSERT INTO estado_inscripcion (id, codigo, nombre, descripcion, activo)
VALUES (7, 'ANU', 'Anulado', 'Inscripción cancelada', true)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);
