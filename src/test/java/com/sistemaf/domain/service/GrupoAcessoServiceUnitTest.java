package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.projection.ResumoGrupoAcesso;
import com.sistemaf.domain.repository.security.PermissaoRepository;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GrupoAcessoServiceUnitTest {
  @InjectMocks
  @Spy
  private GrupoAcessoService sut;

  @Mock
  private GrupoAcessoRepository grupoAcessoRepository;

  @Mock
  private PermissaoRepository permissaoRepository;

  @Test
  public void given_whenFindAll_thenSuccess() {
    when(grupoAcessoRepository.findAll()).thenReturn(FactoryModels.getListGrupoAcesso());
    List<GrupoAcesso> list = sut.filtrar();
    assertNotNull(list);
    assertEquals(3, list.size());
  }

  @Test
  public void given_whenFindResume_thenSuccess() {
    when(grupoAcessoRepository.listarResumo()).thenReturn(FactoryModels.getListGrupoAcessoResumo());
    List<ResumoGrupoAcesso> list = sut.resumo();
    assertNotNull(list);
    assertEquals(3, list.size());

  }

  @Test
  public void givenInvalidId_whenRemove_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.remover(1L));
    assertEquals("O Grupo de acesso solicitado não existe", exception.getMessage());
  }

  @Test
  public void givenValidID_whenRemove_thenSuccess() {
    when(grupoAcessoRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getGrupoAcesso()));
    sut.remover(1L);
    verify(sut, times(1)).remover(1L);
  }
}
