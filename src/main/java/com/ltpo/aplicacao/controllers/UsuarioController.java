package com.ltpo.aplicacao.controllers;

import com.ltpo.aplicacao.models.Curso;
import com.ltpo.aplicacao.models.Usuario;
import com.ltpo.aplicacao.services.CursoService;
import com.ltpo.aplicacao.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        List<Curso> cursosProcessados = new ArrayList<>();

        if (usuario.getCursos() != null) {
            for (Curso curso : usuario.getCursos()) {
                Curso existente = cursoService.findByNome(curso.getNome());
                if (existente != null) {
                    cursosProcessados.add(existente);
                } else {
                    cursosProcessados.add(cursoService.save(curso));
                }
            }
            usuario.setCursos(cursosProcessados);
        }

        Usuario novoUsuario = usuarioService.cadastrar(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario dados){
        return usuarioService.findById(id).map(usuario -> {
            usuario.setNome(dados.getNome());
            usuario.setEmail(dados.getEmail());
            usuario.setSenha(dados.getSenha());

            List<Curso> cursosAtualizados = new ArrayList<>();
            if (dados.getCursos() != null) {
                for (Curso curso : dados.getCursos()) {
                    Curso existente = cursoService.findByNome(curso.getNome());
                    if (existente != null) {
                        cursosAtualizados.add(existente);
                    } else {
                        cursosAtualizados.add(cursoService.save(curso));
                    }
                }
                usuario.setCursos(cursosAtualizados);
            }

            return ResponseEntity.ok(usuarioService.save(usuario));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        if(usuarioService.existsById(id)){
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
