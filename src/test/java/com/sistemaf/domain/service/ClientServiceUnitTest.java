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
import org.springframework.data.domain.Page;

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
  public void givenInvalidClientId_whenDeleteById_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.deleteById(1L));
    assertTrue(exception instanceof  EntityNotFoundException);
  }

  @Test
  public void givenUsedClientId_whenDeleteById_thenThrows() {
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

  @Test
  public void givenInvalidClientId_whenUpdate_thenThrows() {
    Cliente newClient  = FactoryModels.getCliente();
    newClient.setFantazia("Update fantazia");
    Exception exception = assertThrows(BusinessException.class, () -> sut.atualizar(1L, newClient));
    assertTrue(exception instanceof  BusinessException);
    assertEquals("O Cliente não encontrado ou não existe", exception.getMessage());
  }

  @Test
  public void givenInvalidNeighborhoodId_whenUpdate_thenTrows() {
    Cliente newClient  = FactoryModels.getCliente();
    newClient.setFantazia("Update fantazia");
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCliente()));
    Exception exception = assertThrows(BusinessException.class, () -> sut.atualizar(1L, newClient));
    assertTrue(exception instanceof  BusinessException);
    assertEquals("O Bairro inserido não existe", exception.getMessage());
  }
  @Test
  public void givenInvalidGroupId_whenUpdate_thenTrows() {
    Cliente newClient  = FactoryModels.getCliente();
    newClient.setFantazia("Update fantazia");
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCliente()));
    when(bairroRepository.findById(newClient.getEndereco().getBairro().getId())).thenReturn(Optional.of(FactoryModels.getBarrio()));
    Exception exception = assertThrows(BusinessException.class, () -> sut.atualizar(1L, newClient));
    assertTrue(exception instanceof  BusinessException);
    assertEquals("O Grupo inserido não existe", exception.getMessage());
  }

  @Test
  public void givenDuplicatedPartitionCode_whenUpdate_thenTrows() {
    Cliente newClient  = FactoryModels.getCliente();
    Cliente duplicatedPartitionCodeClient = FactoryModels.getCliente();
    duplicatedPartitionCodeClient.setId(2L);
    duplicatedPartitionCodeClient.setCodigoParticao("1234");
    newClient.setFantazia("Update fantazia");
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCliente()));
    when(bairroRepository.findById(newClient.getEndereco().getBairro().getId())).thenReturn(Optional.of(FactoryModels.getBarrio()));
    when(grupoRepository.findById(newClient.getGrupo().getId())).thenReturn(Optional.of(FactoryModels.getGrupo()));
    when(clienteRepository.findByCodigoParticao(newClient.getCodigoParticao())).thenReturn(Optional.of(duplicatedPartitionCodeClient));
    Exception exception = assertThrows(BusinessException.class, () -> sut.atualizar(1L, newClient));
    assertTrue(exception instanceof  BusinessException);
    assertEquals("Já existe cliente com partição solicitada", exception.getMessage());
  }

  @Test
  public void givenValidData_whenUpdate_thenSuccess() {
    Cliente newClient  = FactoryModels.getCliente();
    newClient.setFantazia("Update fantazia");
    when(clienteRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCliente()));
    when(bairroRepository.findById(newClient.getEndereco().getBairro().getId())).thenReturn(Optional.of(FactoryModels.getBarrio()));
    when(grupoRepository.findById(newClient.getGrupo().getId())).thenReturn(Optional.of(FactoryModels.getGrupo()));
    when(clienteRepository.findByCodigoParticao(newClient.getCodigoParticao())).thenReturn(Optional.of(newClient));
    when(clienteRepository.save(any())).then( arguments -> {
      Cliente cliente = arguments.getArgument(0);
      cliente.setFantazia("Update fantazia");
      return  cliente;
    });
    Cliente updateCliente = sut.atualizar(1L, newClient);
    assertEquals(1L, updateCliente.getId());
    assertEquals("Update fantazia", updateCliente.getFantazia());
  }

  @Test
  public void givenNoFilter_whenFind_thenSuccess() {
    when(clienteRepository.filtrar(any(),any())).thenReturn(Page.empty());
    Page<Cliente> results = sut.filtrar(null, null);
    assertEquals(0, results.getSize());
  }
}
