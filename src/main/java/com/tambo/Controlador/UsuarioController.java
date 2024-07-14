package com.tambo.Controlador;

import com.tambo.Model.Usuario;
import com.tambo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        Usuario existingUser = usuarioService.findByEmail(usuario.getEmail());
        if (existingUser != null && existingUser.getPassword().equals(usuario.getPassword())) {
            return existingUser;
        }
        return null;
    }
    @PutMapping("/updatePassword")
    public Usuario updatePassword(@RequestParam Long id, @RequestParam String newPassword) {
        return usuarioService.updatePassword(id, newPassword);
    }
}