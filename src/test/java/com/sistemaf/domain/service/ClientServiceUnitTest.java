package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.repository.bairro.BairroRepository;
import com.sistemaf.domain.repository.cliente.ClienteRepository;
import com.sistemaf.domain.repository.grupo.GrupoRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceUnitTest {

  @Spy
  @InjectMocks
  private ClienteService sut;

  @Mock
  private ClienteRepository clienteRepository;

  @Mock
  private BairroRepository bairroRepository;

  @Mock
  private GrupoRepository grupoRepository;

  @Test
  public void givenInvalidClientId_whenFindById_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.listarPorCodigo(1L));
    assertTrue(exception instanceof  EntityNotFoundException);
    assertEquals("O Cliente não encontrado ou não existe", exception.getMessage());
  }

  @Test
  public void givenValidClientId_whenFindById_thenSuccess() {
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCliente()));
    Cliente cliente = sut.listarPorCodigo(1L);
    assertNotNull(cliente);
    assertEquals(1L, cliente.getId());
    assertEquals("Empresa 01", cliente.getFantazia());
  }

  @Test
  public void givenInvalidNeighborhoodId_whenSave_thenThrows() {
    when(bairroRepository.findById(any())).thenReturn(Optional.empty());
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(FactoryModels.getCliente()));
    assertTrue(exception instanceof  BusinessException);
    assertEquals("O Bairro inserido não existe", exception.getMessage());
  }

  @Test
  public void givenInvalidGroupId_whenSave_thenThrows() {
    when(bairroRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getBarrio()));
    when(grupoRepository.findById(any())).thenReturn(Optional.empty());
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(FactoryModels.getCliente()));
    assertTrue(exception instanceof  BusinessException);
    assertEquals("O Grupo inserido não existe", exception.getMessage());
  }

  @Test
  public void givenDuplicatedPartitionCode_whenSave_thenThrows() {
    when(bairroRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getBarrio()));
    when(grupoRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getGrupo()));
    when(clienteRepository.findByCodigoParticao(any())).thenReturn(Optional.of(FactoryModels.getCliente()));
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(FactoryModels.getCliente()));
    assertTrue(exception instanceof  BusinessException);
    assertEquals("Já existe cliente com partição solicitada", exception.getMessage());
  }

  @Test
  public void givenValidaClientInputData_whenSave_thenSuccess() {
    when(bairroRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getBarrio()));
    when(grupoRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getGrupo()));
    when(clienteRepository.save(any())).thenReturn(FactoryModels.getCliente());
    Cliente client = sut.salvar(FactoryModels.getCliente());
    assertNotNull(client);
    assertEquals(FactoryModels.getCliente().getFantazia(), client.getFantazia());
  }


  @Test
  public void givenInvalidClienteId_whenDeleteById_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.deleteById(1L));
    assertTrue(exception instanceof  EntityNotFoundException);
  }

  @Test
  public void givenUsedClienteId_whenDeleteById_thenThrows() {
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCliente()));
    doThrow(new BusinessException("Erro ao realizar exclusão de cliente") ).when(clienteRepository).deleteById(any());
    Exception exception = assertThrows(BusinessException.class, () -> sut.deleteById(1L));
    assertTrue(exception instanceof  BusinessException);
    assertEquals("Erro ao realizar exclusão de cliente", exception.getMessage());
  }

  @Test
  public void givenValidClientId_whenDeleteById_thenSuccess() {
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCliente()));
    sut.deleteById(1L);
    verify(sut, times(1)).deleteById(any());
  }
}
