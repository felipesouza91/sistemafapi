package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.MotivoOs;
import com.sistemaf.domain.repository.motivoos.MotivoOsRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MotivoOsServiceUnitTest {

  @Spy
  @InjectMocks
  private MotivoOsService sut;
  @Mock
  private MotivoOsRepository motivoOsRepository;

  @Test
  public void whenFiltrar_thenThrows() {
    when(motivoOsRepository.filtrar(any(),any())).thenReturn(Page.empty());
    Page<MotivoOs> resutl = sut.filtrar(null,null);
    assertNotNull(resutl);
    assertEquals(0, resutl.getTotalElements());
  }

  @Test
  public void givenInvalidId_whenListPorCodigo_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.listarPorCodigo(1L));
    assertEquals("O Grupo solicitado não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenListPorCodigo_thenSuccess() {
    when(motivoOsRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getMotivoOs()));
    MotivoOs motivoOs = sut.listarPorCodigo(1L);
    assertNotNull(motivoOs);
    assertEquals(1L, motivoOs.getId());
  }

  @Test
  public void givenDuplicateDescricao_whenSave_thenThrows() {
    MotivoOs motivoOs = new MotivoOs();
    motivoOs.setDescricao("Teste");
    when(motivoOsRepository.save(motivoOs)).then(arguments -> {
      MotivoOs mockMotivoOs = arguments.getArgument(0);
      mockMotivoOs.setId(1L);
      return mockMotivoOs;
    });
    MotivoOs savedData = sut.salvar(motivoOs);
    verify(motivoOsRepository, times(1)).save(motivoOs);
    assertNotNull(savedData);
    assertEquals(1L, savedData.getId());
    assertEquals(motivoOs.getDescricao(), savedData.getDescricao());
  }
}
