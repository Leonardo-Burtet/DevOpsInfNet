package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {
    UserDetails findByLogin(String login);
}
