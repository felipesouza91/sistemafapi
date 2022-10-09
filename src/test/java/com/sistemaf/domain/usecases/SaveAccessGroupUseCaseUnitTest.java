package com.sistemaf.domain.usecases;

import com.sistemaf.api.dto.input.AccessGroupInputData;
import com.sistemaf.domain.model.Permissao;
import com.sistemaf.domain.repository.security.PermissaoRepository;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SaveAccessGroupUseCaseUnitTest {

  @Mock
  private PermissaoRepository permissaoRepository;

  @Mock
  private GrupoAcessoRepository grupoAcessoRepository;

  @Mock
  private List<Permissao> permissaos;

  @InjectMocks
  private SaveAccessGroupUseCase sut;


  @Before
  public void setup() {
    when(permissaoRepository.findAll()).thenReturn(FactoryModels.getListPermissao());
  }

  @Test
  public void giveValidInputAccessGroup_whenExecute_thenSuccess() {
    AccessGroupInputData data = AccessGroupInputData.builder()
            .ativo(true).descricao("Tecnico").permissions(FactoryModels.getPermissionsInput()).build();
    sut.execute(data);
  }
}
