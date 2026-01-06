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
