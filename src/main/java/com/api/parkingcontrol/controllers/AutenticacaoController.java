package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.AutenticacaoDto;
import com.api.parkingcontrol.dtos.DadosTokenJWT;
import com.api.parkingcontrol.models.FuncionarioModel;
import com.api.parkingcontrol.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDto dados) {
       var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
       var authentication = manager.authenticate(authenticationToken);
       var tokenJWT = tokenService.gerarToken((FuncionarioModel) authentication.getPrincipal());
       return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
