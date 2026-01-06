package com.rrhh.sistema.config;

import com.rrhh.sistema.model.Rol;
import com.rrhh.sistema.model.Usuario;
import com.rrhh.sistema.repository.UsuarioRepository;
import com.rrhh.sistema.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
// import org.springframework.core.io.ClassPathResource;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {
        try {
            usuarioService.buscarPorUsername("admin");
            System.out.println("Los usuarios ya existen, saltando carga masiva.");
        } catch (RuntimeException e) {
            System.out.println("Cargando usuarios iniciales...");
            cargarUsuariosIniciales();
        }
    }

    private  void cargarUsuariosIniciales() {
        // Crear usuario admin
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword("admin"); // El servicio lo encriptará
        admin.setNombreCompleto("Administrador del Sistema");
        admin.setRol(Rol.ROLE_ADMIN);
        usuarioService.guardarUsuario(admin);

        // Simulacion de carga masiva
        List<String> listaDnis = Arrays.asList(
            "70505050", // Juan
            "12345678", // Maria
            "87654321", // Pedro
            "11223344", // Lucia
            "99887766"  // Carlos
        );

        for (String dni : listaDnis) {
            Usuario u = new Usuario();
            u.setUsername(dni);
            u.setNombreCompleto("Empleado " + dni); // O podrías tener un Map con nombres reales
            u.setRol(Rol.ROLE_USER);
            u.setActivo(true);
            usuarioService.guardarUsuario(u);
        }

        /* // --- 3. CÓDIGO PARA CARGA MASIVA REAL DESDE CSV (FUTURO) ---
        // Requisitos:
        // 1. Crear archivo 'usuarios.csv' en src/main/resources
        // 2. Formato del CSV: DNI,NOMBRE COMPLETO (Ej: 70505050,Juan Perez)

        try {
            ClassPathResource resource = new ClassPathResource("usuarios.csv");
            if (resource.exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(","); // Separado por coma

                    if (datos.length >= 2) {
                        String dni = datos[0].trim();
                        String nombre = datos[1].trim();

                        Usuario u = new Usuario();
                        u.setUsername(dni);
                        u.setNombreCompleto(nombre);
                        u.setRol(Rol.ROLE_USER);
                        u.setActivo(true);

                        // Guardamos (El servicio encripta el DNI como password)
                        usuarioService.guardarUsuario(u);
                    }
                }
                br.close();
                System.out.println("Usuarios cargados desde CSV correctamente.");
            } else {
                System.out.println("⚠No se encontró el archivo usuarios.csv");
            }
        } catch (Exception e) {
            System.err.println("Error al leer el CSV: " + e.getMessage());
        }
        */

        System.out.println("Carga masiva completada con éxito.");
    }
}
