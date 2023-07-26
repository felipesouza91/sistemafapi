package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.FechamentoOs;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.repository.fechamentoos.FechamentoOsRepository;
import com.sistemaf.domain.repository.ordemservico.OrdemServicoRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FechamentoOsServiceUnitTest {

  @Spy
  @InjectMocks
  private FechamentoOsService sut;

  @Mock
  private FechamentoOsRepository fechamentoOsRepository;

  @Mock
  private OrdemServicoRepository ordemServicoRepository;


  @Test
  public void givenEmptyFilter_whenFiltrar_thenSuccess() {
    when(fechamentoOsRepository.filtrar(any(), any())).thenReturn(Page.empty());
    Page<FechamentoOs> result = sut.filtrar(null, null);
    assertEquals(0, result.getTotalElements());
  }
  @Test
  public void givenInvalidId_whenFindById_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.listarPorCodigo(1L));
    assertEquals("O registro de fechamento da ordem solicitada não existe", exception.getMessage());
  }
  @Test
  public void givenInvalidServiceOrderId_whenSave_thenThrows() {
    FechamentoOs newFechameto = FactoryModels.getFechamentoOs();
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(newFechameto));
    assertEquals("A ordem de serviço não existe!", exception.getMessage());
  }

  @Test
  public void givenClosedServiceOrderId_whenSave_thenThrows() {
    FechamentoOs newFechameto = FactoryModels.getFechamentoOs();
    OrdemServico ordemServico = newFechameto.getOs();
    ordemServico.setFechado(true);
    when(ordemServicoRepository.findById(ordemServico.getId())).thenReturn(Optional.of(ordemServico));
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(newFechameto));
    assertEquals("A ordem de serviço já está fechada", exception.getMessage());
  }

  @Test
  public void givenValidInput_whenSave_thenSuccess() {
    FechamentoOs newFechameto = FactoryModels.getFechamentoOs();
    newFechameto.setId(null);
    OrdemServico ordemServico = newFechameto.getOs();
    ordemServico.setFechado(false);
    when(ordemServicoRepository.findById(ordemServico.getId())).thenReturn(Optional.of(ordemServico));
    when(fechamentoOsRepository.save(newFechameto)).then((arguments ) -> {
      FechamentoOs fechamentoOsSaved = arguments.getArgument(0);
      fechamentoOsSaved.setId(1L);
      return fechamentoOsSaved;
    });
    FechamentoOs fechamentoOs = sut.salvar(newFechameto);
    assertNotNull(fechamentoOs.getId());
    assertTrue(newFechameto.getOs().getFechado());
  }

  @Test
  public void givenInvalidId_whenRemove_thenThrows() {
    Exception exception = assertThrows(BusinessException.class, () -> sut.remover(1L));
    assertEquals("O registro de fechamento da ordem solicitada não existe", exception.getMessage());
  }

  @Test
  public void givenInvalidServiceOrderId_whenRemove_thenThrows() {
    when(fechamentoOsRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getFechamentoOs()));
    Exception exception = assertThrows(BusinessException.class, () -> sut.remover(1L));
    assertEquals("A ordem de serviço não foi encontrada", exception.getMessage());
  }

  @Test
  public void givenValidId_whenRemove_thenThrows() {
    when(fechamentoOsRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getFechamentoOs()));
    when(ordemServicoRepository.findById(any()))
            .thenReturn(Optional.of(FactoryModels.getFechamentoOs().getOs()));
    doThrow(DataIntegrityViolationException.class).when(fechamentoOsRepository).deleteById(1L);
    Exception exception = assertThrows(BusinessException.class, () -> sut.remover(1L));
    assertEquals("Erro ao excluir fechamento de ordem de serviço", exception.getMessage());
  }

  @Test
  public void givenValidId_whenRemove_thenSuccess( ) {
    FechamentoOs mockFechamento  = FactoryModels.getFechamentoOs();
    OrdemServico mockOrdemServico = mockFechamento.getOs();
    when(fechamentoOsRepository.findById(any())).thenReturn(Optional.of(mockFechamento));
    when(ordemServicoRepository.findById(any()))
            .thenReturn(Optional.of(mockOrdemServico));
    sut.remover(1L);
    verify(sut, times(1)).remover(1L);
  }

}
