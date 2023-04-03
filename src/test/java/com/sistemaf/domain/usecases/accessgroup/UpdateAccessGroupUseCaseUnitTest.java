package com.sistemaf.domain.usecases.accessgroup;

import com.sistemaf.api.dto.input.AccessGroupInputData;
import com.sistemaf.api.dto.input.PermissionsInput;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.model.Permissao;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.domain.usecases.permission.PermissionMapperUseCase;
import com.sistemaf.util.FactoryModels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class UpdateAccessGroupUseCaseUnitTest {

  @Mock
  private GrupoAcessoRepository repository;

  @Mock
  private PermissionMapperUseCase permissionMapperUseCase;
  @InjectMocks
  private UpdateAccessGroupUseCase sut;

  @BeforeEach
  public void setup() {
    when(repository.findById(any())).thenReturn(Optional.of(FactoryModels.getGrupoAcesso()));
  }

  @Test
  public void giveInvalidAccessGroup_whenExcute_thenError() {
    when(repository.findById(any())).thenReturn(Optional.empty());
    AccessGroupInputData data = AccessGroupInputData.builder().ativo(true).descricao("Tecnico").permissions(FactoryModels.getPermissionsInput()).build();
    Exception exception = assertThrows(EntityNotFoundException.class, () ->  sut.execute(2L, data) );
    assertThat(exception.getMessage(), is("Grupo não encontrado"));
  }

  @Test
  public void giveInvalidPermissionInput_whenExecute_thenError() {
    when(permissionMapperUseCase.toListModel(any())).thenThrow(new BusinessException("Permisao não existe"));
    AccessGroupInputData data = AccessGroupInputData.builder().ativo(true).descricao("Tecnico")
            .permissions(Arrays.asList(PermissionsInput.builder().nameId("INVALID_NAME").build())).build();
    Exception exception = assertThrows(BusinessException.class, () ->  sut.execute(1L, data) );
    assertThat(exception.getMessage(), is("Permisao não existe"));
  }

  @Test
  public void giveEmptyPermissionInput_whenExecute_thenError() {
    when(permissionMapperUseCase.toListModel(any())).thenThrow(new BusinessException("As Permissões são obrigatorias"));
    AccessGroupInputData data = AccessGroupInputData.builder().ativo(true).descricao("Tecnico")
            .permissions(Arrays.asList(PermissionsInput.builder().nameId("INVALID_NAME").build())).build();
    Exception exception = assertThrows(BusinessException.class, () ->  sut.execute(1L, data) );
    assertThat(exception.getMessage(), is("As Permissões são obrigatorias"));
  }

  @Test
  public void giveValidAccesGroupInput_whenExecute_thenSuccess() {
    GrupoAcesso grupoAcesso = new GrupoAcesso();
    grupoAcesso.setId(2L);
    grupoAcesso.setDescricao("Tecnico");
    grupoAcesso.setAtivo(false);
    grupoAcesso.setPermissoes(Arrays.asList(
            Permissao.builder().id(1L).descricao("RL_CAD_CLIENTE").build(),
            Permissao.builder().id(2L).descricao("RL_PES_CLIENTE").build(),
            Permissao.builder().id(3L).descricao("RL_REM_CLIENTE").build(),
            Permissao.builder().id(4L).descricao("RL_CAD_GRUPO").build(),
            Permissao.builder().id(5L).descricao("RL_PES_GRUPO").build(),
            Permissao.builder().id(6L).descricao("RL_REM_GRUPO").build()
    ));


    when(repository.save(any())).thenReturn(grupoAcesso);
    AccessGroupInputData data = AccessGroupInputData.builder().ativo(false).descricao("Tecnico")
            .permissions(
                    Arrays.asList(
                            PermissionsInput.builder().nameId("CLIENTE").read(true).write(true).remove(true).build(),
                            PermissionsInput.builder().nameId("GRUPO").read(true).write(true).remove(true).build()
            )).build();;
    GrupoAcesso result = sut.execute(1L, data);
    assertThat(result.getDescricao(), is("Tecnico"));
    assertThat(result.getAtivo(), is(false));
    assertFalse(result.getPermissoes().stream().anyMatch( item -> item.getId() == 7));
    assertTrue(result.getPermissoes().stream().anyMatch(item-> item.getId() == 1L));
    assertFalse(result.getAtivo());
  }

}
