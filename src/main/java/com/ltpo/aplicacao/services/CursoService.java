package com.ltpo.aplicacao.services;

import com.ltpo.aplicacao.models.Curso;
import com.ltpo.aplicacao.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Curso cadastrar(Curso curso){
        if(cursoRepository.existsByNome((curso.getNome()))){
            throw new RuntimeException("Curso ja cadastrado");
        }
        return cursoRepository.save(curso);
    }

    public List<Curso> listarTodos(){
        return cursoRepository.findAll();
    }
    public Optional<Curso> findById(Long id){
        return cursoRepository.findById(id);
    }
    public Curso findByNome(String nome){ return cursoRepository.findByNome(nome); }

    public Curso save(Curso curso){
        return  cursoRepository.save(curso);
    }
    public boolean existsById(Long id){
        return cursoRepository.existsById(id);
    }
    public void deleteById(Long id){
        cursoRepository.deleteById(id);
    }
}
