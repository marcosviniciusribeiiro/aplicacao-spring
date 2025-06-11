package com.ltpo.aplicacao.repositories;

import com.ltpo.aplicacao.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nome);
    boolean existsByNome(String nome);
}
