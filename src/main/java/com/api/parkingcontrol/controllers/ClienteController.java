package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ClienteDto;
import com.api.parkingcontrol.models.ClienteModel;
import com.api.parkingcontrol.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/clientes")
public class ClienteController {

    final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid ClienteDto clienteDto, UriComponentsBuilder uriBuilder){
        var clienteModel = new ClienteModel();
        if(clienteService.existByCpf(clienteDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Esse CPF já é cadastrado.");
        }
        BeanUtils.copyProperties(clienteDto, clienteModel);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(clienteModel.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteService.save(clienteModel));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteModel>> listarClientes(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity(clienteService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> listarCliente(@PathVariable(value = "id")Long id) {
        Optional<ClienteModel> clienteModelOptional = clienteService.findById(id);
        if(!clienteModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") Long id) {
        Optional<ClienteModel> modelOptional = clienteService.findById(id);
        if(!modelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        clienteService.delete(modelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarCliente(@PathVariable(value = "id") Long id,
                                                 @RequestBody @Valid ClienteDto clienteDto){
        Optional<ClienteModel> modelOptional = clienteService.findById(id);
        if(!modelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        var clienteModel = new ClienteModel(); //realizar das duas maneiras
        BeanUtils.copyProperties(clienteDto, clienteModel);
        clienteModel.setId(modelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(clienteModel));
    }
}
