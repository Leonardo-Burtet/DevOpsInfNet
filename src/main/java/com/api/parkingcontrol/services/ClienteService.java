package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ClienteModel;
import com.api.parkingcontrol.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteService {
    final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<ClienteModel> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public ClienteModel save(ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    public Page<ClienteModel> getAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public boolean existByCpf(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }

    @Transactional
    public void delete(ClienteModel clienteModel) {
        clienteRepository.delete(clienteModel);
    }
}
