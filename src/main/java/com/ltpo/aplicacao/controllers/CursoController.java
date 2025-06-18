package com.ltpo.aplicacao.controllers;

import com.ltpo.aplicacao.models.Curso;
import com.ltpo.aplicacao.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody Curso curso){
        Curso novoCurso = cursoService.cadastrar(curso);
        return new ResponseEntity<>(novoCurso, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos(){
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id){
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso dados){
        return cursoService.findById(id).map(curso -> {
            curso.setNome(dados.getNome());
            curso.setUsuario(dados.getUsuario());
            return  ResponseEntity.ok(cursoService.save(curso));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id){
        if (cursoService.existsById(id)){
            cursoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
