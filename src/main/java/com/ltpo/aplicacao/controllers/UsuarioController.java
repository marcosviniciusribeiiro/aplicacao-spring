package com.ltpo.aplicacao.controllers;

import com.ltpo.aplicacao.models.Usuario;
import com.ltpo.aplicacao.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody Usuario usuario){
        Usuario novoUsuario = (Usuario) usuarioService.cadastrar(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity listar(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    // Outros endpoints (Get por ID, PUT, DELETE)
}
