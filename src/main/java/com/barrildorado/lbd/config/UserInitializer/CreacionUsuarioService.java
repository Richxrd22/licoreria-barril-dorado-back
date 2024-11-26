package com.barrildorado.lbd.config.UserInitializer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.barrildorado.lbd.model.Empleado;
import com.barrildorado.lbd.model.Rol;
import com.barrildorado.lbd.model.Usuario;
import com.barrildorado.lbd.repository.EmpleadoRepository;
import com.barrildorado.lbd.repository.RolRepository;
import com.barrildorado.lbd.repository.UsuarioRepository;

@Service
public class CreacionUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void crearUsuarioEmpleadoRol() {

        Optional<Rol> rolOptional = rolRepository.findByNombre_rol("ADMIN"); // Buscar el rol por nombre
        Rol rol;
        if (rolOptional.isEmpty()) {
            // Si el rol no existe, crear uno nuevo
            rol = new Rol();
            rol.setNombre_rol("ADMIN");
            rolRepository.save(rol);
            System.out.println("Rol creado.");
        } else {
            rol = rolOptional.get();
            System.out.println("El rol 'ROLE_ADMIN' ya existe.");
        }

        // Paso 2: Crear un Empleado
        Optional<Empleado> empleadoOptional = empleadoRepository.findByCorreo("admin@gmail.com");
        Empleado empleado;
        if (empleadoOptional.isEmpty()) {
            empleado = new Empleado();
            empleado.setNombre("Admin");
            empleado.setApellido("Admin");
            empleado.setDni("12345678");
            empleado.setCorreo("admin@gmail.com");
            empleado.setTelefono("123456789");
            empleado.setDireccion("Calle Falsa 123");
            empleado.setActivo(true);
            empleadoRepository.save(empleado);
            System.out.println("Empleado creado.");
        } else {
            empleado = empleadoOptional.get();
            System.out.println("El empleado ya existe.");
        }
        // Paso 3: Crear un Usuario y asignar rol y empleado
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo("LBD12345678@barrildorado.com");
        if (usuarioOptional.isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setCorreo("LBD12345678@barrildorado.com");
            String contrasenaSinEncriptar = "12345"; // Usualmente esta vendría del cliente
            String contrasenaEncriptada = passwordEncoder.encode(contrasenaSinEncriptar);

            usuario.setId_rol(rol); // Asignar el rol
            usuario.setId_empleado(empleado); // Asignar el empleado
            usuario.setClave(contrasenaEncriptada);
            usuarioRepository.save(usuario);

            System.out.println("Usuario creado.");

        } else {
            System.out.println("El usuario ya existe.");
        }

    }
}