package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.repository.cliente.ClienteRepository;
import com.sistemaf.domain.repository.motivoos.MotivoOsRepository;
import com.sistemaf.domain.repository.ordemservico.OrdemServicoRepository;
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
public class ServiceOrderServiceUnitTest {

  @Spy
  @InjectMocks
  private OrdemServicoService sut;

  @Mock
  private OrdemServicoRepository ordemServicoRepository;

  @Mock
  private ClienteRepository clienteRepository;

  @Mock
  private MotivoOsRepository motivoOsRepository;

  @Test
  public void whenFiltro_thenSuccess() {
    when(ordemServicoRepository.filtrar(any(),any())).thenReturn(Page.empty());
    Page result = sut.filtrar(null,null);
    assertNotNull(result);
  }

  @Test
  public void whenFiltroResumo_thenSuccess() {
    when(ordemServicoRepository.resumo(any(),any())).thenReturn(Page.empty());
    Page result = sut.resumir(null,null);
    assertNotNull(result);
  }

  @Test
  public void givenInvalidId_whenBuscarPorId_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.listaPorCodigo(1L));
    assertEquals("A ordem de serviço não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenBuscarPorId_thenSuccess() {
    when(ordemServicoRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getOrdemServico()));
    OrdemServico ordemServico = sut.listaPorCodigo(1L);
    assertNotNull(ordemServico);
    assertNotNull(ordemServico.getCliente());
  }

  @Test
  public void givenInvalidClientId_whenSave_thenThrows() {
    Exception exception = assertThrows(BusinessException.class,
            () -> sut.salvar(FactoryModels.getOrdemServico()));
    assertEquals("O Cliente não existe", exception.getMessage());
  }

  @Test
  public void givenInvalidMotivoOs_whenSave_thenThrows() {
    OrdemServico mockOrdemServico = FactoryModels.getOrdemServico();
    when(clienteRepository.findById(mockOrdemServico.getCliente().getId()))
            .thenReturn(Optional.of(mockOrdemServico.getCliente()));
    Exception exception = assertThrows(BusinessException.class,
              () -> sut.salvar(mockOrdemServico));
    assertEquals("O Motivo da Ordem não existe", exception.getMessage());
  }

  @Test
  public void givenValidData_whenSave_thenSuccess() {
    OrdemServico mockOrdemServico = FactoryModels.getOrdemServico();
    when(clienteRepository.findById(mockOrdemServico.getCliente().getId()))
            .thenReturn(Optional.of(mockOrdemServico.getCliente()));
    when(motivoOsRepository.findById(mockOrdemServico.getMotivoOs().getId())).thenReturn(Optional.of(mockOrdemServico.getMotivoOs()));
    when(ordemServicoRepository.save(mockOrdemServico)).thenReturn(mockOrdemServico);
    OrdemServico ordemServico = sut.salvar(mockOrdemServico);
    verify(ordemServicoRepository, times(1)).save(mockOrdemServico);
    assertNotNull(ordemServico);
  }

  @Test
  public void givenInvalidId_whenUpdate_thenThrows() {
    OrdemServico mockOrdemServico = FactoryModels.getOrdemServico();
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.atualizar(mockOrdemServico.getId(),
            mockOrdemServico));
    assertEquals("A ordem de serviço não existe", exception.getMessage());
  }

  @Test
  public void givenClosedOrdem_whenUpdate_thenThrows() {
    OrdemServico mockOrdemServico = FactoryModels.getOrdemServico();
    mockOrdemServico.setFechado(true);
    when(clienteRepository.findById(mockOrdemServico.getCliente().getId()))
            .thenReturn(Optional.of(mockOrdemServico.getCliente()));
    when(motivoOsRepository.findById(mockOrdemServico.getMotivoOs().getId())).thenReturn(Optional.of(mockOrdemServico.getMotivoOs()));
    when(ordemServicoRepository.findById(mockOrdemServico.getId())).thenReturn(Optional.of(mockOrdemServico));
    Exception exception = assertThrows(BusinessException.class, () -> sut.atualizar(mockOrdemServico.getId(),
            mockOrdemServico));
    assertEquals("A ordem já encontra-se fechada, não é possivel atualizar", exception.getMessage());
  }

  @Test
  public void givenData_whenUpdate_thenSuccess() {
    OrdemServico mockOrdemServico = FactoryModels.getOrdemServico();
    mockOrdemServico.setFechado(false);
    when(ordemServicoRepository.findById(mockOrdemServico.getId())).thenReturn(Optional.of(mockOrdemServico));
    when(clienteRepository.findById(mockOrdemServico.getCliente().getId()))
            .thenReturn(Optional.of(mockOrdemServico.getCliente()));
    when(motivoOsRepository.findById(mockOrdemServico.getMotivoOs().getId())).thenReturn(Optional.of(mockOrdemServico.getMotivoOs()));
    when(ordemServicoRepository.save(mockOrdemServico)).thenReturn(mockOrdemServico);
    OrdemServico ordemServico = sut.atualizar(mockOrdemServico.getId(), mockOrdemServico);
    verify(ordemServicoRepository, times(1)).save(mockOrdemServico);
    assertNotNull(mockOrdemServico);
  }


  @Test
  public void givenInvalidId_whenDelete_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.remover(1L));
    assertEquals("A ordem de serviço não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenDelete_thenSuccess() {
    OrdemServico ordemservicoMock = FactoryModels.getOrdemServico();
    when(ordemServicoRepository.findById(ordemservicoMock.getId())).thenReturn(Optional.of(ordemservicoMock));
    sut.remover(ordemservicoMock.getId());
    verify(ordemServicoRepository, times(1)).deleteById(ordemservicoMock.getId());
  }
}
