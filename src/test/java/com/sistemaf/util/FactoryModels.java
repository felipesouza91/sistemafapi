package com.sistemaf.util;

import com.sistemaf.domain.model.*;

import java.time.LocalDateTime;

public class FactoryModels {

    public static Usuario getUsuario( ) {
        Usuario user = new Usuario();
        user.setId(1L);
        user.setAtivo(true);
        user.setApelido("any_username");
        user.setNome("User name");
        user.setSenha("encryptPassword");
        return user;
    }

    public static Cliente getCliente() {
        Cliente client = new Cliente();
        client.setId(1L);
        client.setAtivo(true);
        client.setCodigoParticao("1234");
        client.setCodigoService(1234);
        client.setEndereco(getEndereco());
        client.setDominio("dominio.dominio.com");
        client.setFantazia("Empresa 01");
        client.setRazaoSocial("Empresa 01");
        client.setTelefone1("123456798");
        client.setTelefone2("123456798");
        return client;
    }

    public static Endereco getEndereco() {
        Endereco end = new Endereco();
        end.setRua("Rua 01");
        end.setComplemento("Complemento");
        end.setReferencia("referencia");
        end.setNumero(123L);
        end.setBairro(getBarrio());
        return end;
    }

    public static Bairro getBarrio(){
        Bairro bairro = new Bairro();
        bairro.setId(1L);
        bairro.setNome("Bairro");
        bairro.setCidade(getCidade());
        return bairro;
    }

    public static Cidade getCidade() {
        Cidade cidade = new Cidade();
        cidade.setId(1L);
        cidade.setNome("Cidade");
        return cidade;
    }

    public static ClienteInformacao getClientInfo() {
        ClienteInformacao infoClient = new ClienteInformacao();
        infoClient.setId(1L);
        infoClient.setDescricao("descricao");
        infoClient.setCliente(getCliente());
        infoClient.setLastModifiedBy(getUsuario());
        infoClient.setLastModifiedDate(LocalDateTime.now());
        infoClient.setCreationDate(LocalDateTime.now());
        return infoClient;
    }
}
