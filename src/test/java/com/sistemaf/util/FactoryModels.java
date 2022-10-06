package com.sistemaf.util;

import com.sistemaf.domain.model.*;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

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
        infoClient.setLastModifiedDate(OffsetDateTime.now());
        infoClient.setCreationDate(OffsetDateTime.now());
        return infoClient;
    }

    public static List<Permissao> getListPermissao() {
        return Arrays.asList(
             Permissao.builder().id(1L).descricao("RL_CAD_CLIENTE").build(),
             Permissao.builder().id(2L).descricao("RL_PES_CLIENTE").build(),
             Permissao.builder().id(3L).descricao("RL_REM_CLIENTE").build(),
             Permissao.builder().id(4L).descricao("RL_CAD_GRUPO").build(),
             Permissao.builder().id(5L).descricao("RL_PES_GRUPO").build(),
             Permissao.builder().id(6L).descricao("RL_REM_GRUPO").build(),
            Permissao.builder().id(7L).descricao("RL_CAD_ORDEM_FECHAMENTO_ORDEM").build(),
            Permissao.builder().id(8L).descricao("RL_PES_ORDEM_FECHAMENTO_ORDEM").build(),
            Permissao.builder().id(9L).descricao("RL_REM_ORDEM_FECHAMENTO_ORDEM").build(),
            Permissao.builder().id(10L).descricao("RL_CAD_MOTIVO_OS").build(),
            Permissao.builder().id(11L).descricao("RL_PES_MOTIVO_OS").build(),
            Permissao.builder().id(11L).descricao("RL_REM_MOTIVO_OS").build()
        );
    }

    public static Permissao getPermissao() {
        return  new Permissao().builder().id(1L).descricao("RL_CAD_CLIENTE").build();
    }
}
