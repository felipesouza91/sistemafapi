package com.sistemaf.util;

import com.sistemaf.api.dto.input.PermissionsInput;
import com.sistemaf.api.dto.model.PermissionDto;
import com.sistemaf.domain.model.*;
import org.instancio.Instancio;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    client.setGrupo(getGrupo());
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

  public static Grupo getGrupo() {
    Grupo grupo = new Grupo();
    grupo.setId(1L);
    grupo.setNome("Grupo 1");
    return grupo;
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

  public static GrupoAcesso getGrupoAcesso() {
    GrupoAcesso grupoAcesso = new GrupoAcesso();
    grupoAcesso.setId(1L);
    grupoAcesso.setDescricao("Administrador");
    grupoAcesso.setAtivo(true);
    List<Permissao> permitions = FactoryModels.getListPermissao().subList(2,FactoryModels.getListPermissao().size());
    grupoAcesso.setPermissoes(permitions);
    return grupoAcesso;
  }

  public static Set<PermissionDto> getFormatedPermissinByGroup() {
    return Arrays.asList(
            PermissionDto.builder().nameId("CLIENTE").formattedName("Cliente").read(false).write(false).remove(false).build(),
            PermissionDto.builder().nameId("GRUPO").formattedName("Grupo").read(false).write(false).remove(false).build(),
            PermissionDto.builder().nameId("ORDEM_FECHAMENTO_ORDEM").formattedName("Ordem Fechamento OOrdem").read(false).write(false).remove(false).build(),
            PermissionDto.builder().nameId("MOTIVO_OS").formattedName("Motivo Os").read(false).write(false).remove(false).build()
    ).stream().collect(Collectors.toSet());

  }

  public static List<PermissionsInput> getPermissionsInput() {
    return Arrays.asList(
            PermissionsInput.builder().nameId("CLIENTE").read(true).write(false).remove(false).build(),
            PermissionsInput.builder().nameId("GRUPO").read(true).write(true).remove(true).build(),
            PermissionsInput.builder().nameId("ORDEM_FECHAMENTO_ORDEM").read(true).write(false).remove(false).build(),
            PermissionsInput.builder().nameId("MOTIVO_OS").read(true).write(true).remove(false).build()
    );
  }

  public static Atendimento getAtendimento() {
    Atendimento atendimento = new Atendimento();
    atendimento.setCliente(FactoryModels.getCliente());
    atendimento.setSolicitante("Felipe");
    atendimento.setDataInicio(OffsetDateTime.now());
    atendimento.setDescricaoProblema("Problema");
    return atendimento;
  }

  public static Dvr getDvr() {
    Dvr dvr = Instancio.create(Dvr.class);
    return dvr;
  }

  public static Fabricante getFabricante() {
    return Instancio.create(Fabricante.class);
  }
}
