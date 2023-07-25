package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.Dvr;
import com.sistemaf.domain.repository.cliente.ClienteRepository;
import com.sistemaf.domain.repository.dvr.DvrRepository;
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
public class DvrServiceUnitTest {

  @Spy
  @InjectMocks
  private DvrService sut;

  @Mock
  private DvrRepository dvrRepository;

  @Mock
  private ClienteRepository clienteRepository;

  @Test
  public void givenFilter_whenFilter_thenSuccess() {
    when(dvrRepository.filtrar(any(),any())).thenReturn(Page.empty());
    Page page = sut.filtrar(null, null);
    assertEquals(0, page.getSize());
  }

  @Test
  public void givenInvalidId_whenBuscaPorId_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.buscarPorCodigo(1L));
    assertEquals("O DVR não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenBuscarPorId_thenSuccess() {
    Dvr mockDvr = FactoryModels.getDvr();
    when(dvrRepository.findById(1L)).thenReturn(Optional.of(mockDvr));
    Dvr dvr =  sut.buscarPorCodigo(1L);
    assertEquals(mockDvr.getId(), dvr.getId());
  }

  @Test
  public void givenInvalidClientID_whenSave_thenThrows() {
    Dvr dvrData = FactoryModels.getDvr();
    dvrData.getCliente().setId(3L);
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(dvrData));
    assertEquals("O Cliente não foi encontrado", exception.getMessage());
  }

  @Test
  public void givenDisabledClient_whenSave_thenThrows() {
    Dvr dvrData = FactoryModels.getDvr();
    dvrData.getCliente().setId(3L);
    Cliente disabledClient = dvrData.getCliente();
    disabledClient.setAtivo(false);
    when(clienteRepository.findById(dvrData.getCliente().getId())).thenReturn(Optional.of(disabledClient));
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(dvrData));
    assertEquals("O Cliente selecionado esta desativado", exception.getMessage());
  }

  @Test
  public void givenValidDvrData_whenSave_thenSuccess() {
    Dvr dvrData = FactoryModels.getDvr();
    dvrData.setId(null);
    Cliente activeClient = dvrData.getCliente();
    activeClient.setAtivo(true);
    when(clienteRepository.findById(dvrData.getCliente().getId())).thenReturn(Optional.of(activeClient));
    when(dvrRepository.save(any())).then((arguments) -> {
      Dvr dvr = arguments.getArgument(0);
      dvr.setId(1L);
      return dvr;
    });
    Dvr saveDvr = sut.salvar(dvrData);
    assertEquals(1L, saveDvr.getId());
    assertEquals(dvrData.getCliente().getId(), saveDvr.getCliente().getId());
    assertTrue(dvrData.getCliente().getAtivo());
  }

  @Test
  public void givenInvalidDvrID_whenAtualiza_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, ( ) -> sut.atualizar(1L, FactoryModels.getDvr()));
    assertEquals("O DVR não existe", exception.getMessage());
  }

  @Test
  public void givenValidID_whenUpdate_success( ) {
    Dvr updateDvrData = FactoryModels.getDvr();
    updateDvrData.setPortaHttp(15000);
    Cliente activeClient = updateDvrData.getCliente();
    activeClient.setAtivo(true);
    when(clienteRepository.findById(updateDvrData.getCliente().getId())).thenReturn(Optional.of(activeClient));
    when(dvrRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getDvr()));
    when(dvrRepository.save(any())).then((arguments) -> {
      Dvr dvr= arguments.getArgument(0);
      dvr.setPortaHttp(15000);
      return dvr;
    });
    Dvr updatedDvr = sut.atualizar(1L, updateDvrData);
    assertEquals(updateDvrData.getId(), updatedDvr.getId());
    assertEquals(15000, updatedDvr.getPortaHttp());
  }

  @Test
  public void givenClientDisable_whenUpdate_thenThrows( ) {
    Dvr updateDvrData = FactoryModels.getDvr();
    updateDvrData.setPortaHttp(15000);
    Cliente activeClient = updateDvrData.getCliente();
    activeClient.setAtivo(false);
    when(clienteRepository.findById(updateDvrData.getCliente().getId())).thenReturn(Optional.of(activeClient));
    when(dvrRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getDvr()));
    Exception exception = assertThrows(BusinessException.class, () -> sut.atualizar(1L, updateDvrData));
    assertEquals("O Cliente selecionado esta desativado", exception.getMessage());
  }

  @Test
  public void givenInvalidId_whenRemove_thenThrows() {
    Exception exception = assertThrows(BusinessException.class, () -> sut.remover(1L));
    assertEquals("O DVR não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenRemove_thenSuccess() {
    when(dvrRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getDvr()));
    sut.remover(1L);
    verify(sut, times(1)).remover(1L);
  }
}
