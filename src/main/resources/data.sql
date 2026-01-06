-- 1. Insertar SERVICIOS (Usamos ON CONFLICT para que no falle si ya existen)
-- Nota: ON CONFLICT evita errores de duplicados al reiniciar la aplicación
INSERT INTO tipos_servicios (nombre_servicio, codigo_icono, disponible) VALUES
    ('Boletas', 'fa-file-invoice', true),
    ('Reclamos', 'fa-bullhorn', true),
    ('Justificación', 'fa-clipboard-check', true),
    ('Informes', 'fa-chart-line', true),
    ('Descanso Temporal', 'fa-coffee', true),
    ('Otros', 'fa-ellipsis-h', true)
ON CONFLICT (nombre_servicio) DO NOTHING;

-- 2. Insertar USUARIOS
-- Nota: Las contraseñas están en texto plano (solo para desarrollo/pruebas)
-- En producción deberías usar BCrypt o similar

-- El Admin
INSERT INTO usuarios (username, password, nombre_completo, rol, activo) VALUES
    ('admin', 'admin', 'Administrador del Sistema', 'ROLE_ADMIN', true)
ON CONFLICT (username) DO NOTHING;

-- Juan Perez (User)
INSERT INTO usuarios (username, password, nombre_completo, rol, activo) VALUES
    ('70505050', '70505050', 'Juan Pérez', 'ROLE_USER', true)
ON CONFLICT (username) DO NOTHING;

-- Maria Lopez (User)
INSERT INTO usuarios (username, password, nombre_completo, rol, activo) VALUES
    ('12345678', '12345678', 'María López', 'ROLE_USER', true)
ON CONFLICT (username) DO NOTHING;

-- Carlos Ruiz (User)
INSERT INTO usuarios (username, password, nombre_completo, rol, activo) VALUES
    ('87654321', '87654321', 'Carlos Ruiz', 'ROLE_USER', true)
ON CONFLICT (username) DO NOTHING;
