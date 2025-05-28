package com.ltpo.aplicacao.repositories;

import com.ltpo.aplicacao.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository {
    Usuario findByEmail(String email);
    boolean existsByEmail(String email);
}
