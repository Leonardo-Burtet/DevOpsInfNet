package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.models.ClienteModel;
import com.api.parkingcontrol.services.ClienteService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ClienteModel> dadosRequestJson;

    @Autowired
    private JacksonTester<ClienteModel> dadosResponseJson;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão invalidas")
    @WithMockUser
    void salvar_cenario1() throws Exception {
        var response = mvc.perform(post("/clientes"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão validas")
    @WithMockUser
    void salvar_cenario2() throws Exception {

        var dadosDetalhamento =  new ClienteModel(1L, "Leonardo Burtet", "leonardoburtet@outlook.com", "41998899501", "123456789");

        var response = mvc
                .perform(
                        post("/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosRequestJson.write(
                                        new ClienteModel("Leonardo Burtet", "leonardoburtet@outlook.com", "41998899501", "123456789")
                                ).getJson())
                )
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosResponseJson.write(
             dadosDetalhamento
        ).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}