package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Cidade;
import com.sistemaf.domain.repository.cidade.CidadeRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CidadeServiceUnitTest {

  @InjectMocks
  private CidadeService sut;
  @Mock
  private CidadeRepository repository;

  @Test
  public void givenInvalidCidadeId_whenFindById_thenThrows() {
    Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> sut.buscaPorCodigo(1L));
    assertTrue(exception instanceof  EntityNotFoundException);
  }

  @Test
  public void givenValidCidadeId_whenFindById_thenReturnCidade() {
    when(repository.findById(any())).thenReturn(Optional.of(FactoryModels.getCidade()));
    Cidade cidade = sut.buscaPorCodigo(1L);
    assertNotNull(cidade);
    assertEquals("Cidade", cidade.getNome());
  }
  @Test
  public void givenInvalidCidadeId_whenUpdate_thenThrows(){
    Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> sut.atualizar(1L,
            FactoryModels.getCidade()));
    assertTrue(exception instanceof  EntityNotFoundException);
  }

  @Test
  public void givenValidaData_whenUpdate_thenSuccess() {
    Cidade cidade = new Cidade();
    cidade.setNome("Test");
    when(repository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCidade()));
    when(repository.save(any())).thenReturn(new Cidade(1L, "Test"));
    Cidade updatedCidade = sut.atualizar(1L, cidade);
    assertNotNull(updatedCidade);
    assertEquals("Test", updatedCidade.getNome());
    assertThat(updatedCidade.getId(), is(1L));
  }

  @Test
  public void givenInvalidCidadeId_whenDeleteById_thenThrows() {
    Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> sut.deletar(1L));
    assertTrue(exception instanceof  EntityNotFoundException);
  }

  @Test
  public void givenUsedCidadeId_whenDeleteById_thenThrows() {
    when(repository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCidade()));
    doThrow(new BusinessException("Não foi possivel excluir a cidades solicitada")).when(repository).deleteById(any());
    Exception exception = Assertions.assertThrows(BusinessException.class, () -> sut.deletar(1L));
    assertTrue(exception instanceof  BusinessException);
    assertEquals("Não foi possivel excluir a cidades solicitada", exception.getMessage());
  }
}
