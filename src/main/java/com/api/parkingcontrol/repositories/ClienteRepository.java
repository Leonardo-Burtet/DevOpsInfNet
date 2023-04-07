package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    boolean existsByCpf(String cpf);

    Optional<ClienteModel> findById(Long id);

    Optional<ClienteModel> findByNome(String nome);
}
