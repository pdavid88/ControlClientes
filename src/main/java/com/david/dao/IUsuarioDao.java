package com.david.dao;

import com.david.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
