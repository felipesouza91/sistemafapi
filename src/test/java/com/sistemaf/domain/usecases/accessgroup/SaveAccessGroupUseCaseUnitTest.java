package com.sistemaf.domain.usecases.accessgroup;

import com.sistemaf.api.dto.input.AccessGroupInputData;
import com.sistemaf.api.dto.input.PermissionsInput;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.domain.usecases.permission.PermissionMapperUseCase;
import com.sistemaf.util.FactoryModels;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.is;


@RunWith(MockitoJUnitRunner.class)
public class SaveAccessGroupUseCaseUnitTest {

  @Mock
  private PermissionMapperUseCase permissionMapperUseCase;

  @Mock
  private GrupoAcessoRepository grupoAcessoRepository;

  @InjectMocks
  private SaveAccessGroupUseCase sut;


  @Before
  public void setup() {
    when(permissionMapperUseCase.toListModel(any())).thenReturn(FactoryModels.getListPermissao());
  }

  @Test
  public void giveValidInputAccessGroup_whenExecute_thenSuccess() {
    GrupoAcesso grupoAcesso = new GrupoAcesso();
    grupoAcesso.setId(2L);
    grupoAcesso.setDescricao("Tecnico");
    grupoAcesso.setAtivo(true);
    grupoAcesso.setPermissoes(FactoryModels.getListPermissao());
    when(grupoAcessoRepository.save(any(GrupoAcesso.class))).thenReturn(grupoAcesso);
    AccessGroupInputData data = AccessGroupInputData.builder()
            .ativo(true).descricao("Tecnico").permissions(FactoryModels.getPermissionsInput()).build();
    GrupoAcesso result = sut.execute(data);
    assertThat(result.getId(), is(2L));
    assertThat(result.getDescricao(), is("Tecnico"));
  }

  @Test(expected = BusinessException.class)
  public void giveEmptyPermissionListInInputAccessGroup_whenExecute_thenError() {
    AccessGroupInputData data = AccessGroupInputData.builder().ativo(true).descricao("Tecnico").build();
    sut.execute(data);

  }

  @Test(expected = BusinessException.class)
  public void giveEnvalidPermissionNameInInputAccessGrou_whenExecute_thenError() {
    when(permissionMapperUseCase.toListModel(any())).thenThrow(BusinessException.class);
    AccessGroupInputData data = AccessGroupInputData.builder().ativo(true).descricao("Tecnico")
            .permissions(Arrays.asList(PermissionsInput.builder().nameId("INVALID_PERMISION").read(true).remove(true).write(true).build()))
            .build();
    sut.execute(data);
  }
}
