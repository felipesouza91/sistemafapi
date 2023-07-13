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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CidadeServiceUnitTest {

  @Spy
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
  public void givenFilte_whenFilter_thenSuccess() {
    when(repository.filtrar(any(),any())).thenReturn(Page.empty());
    Page page = sut.filtrar(null,null);
    assertEquals(0, page.getSize());
  }

  @Test
  public void givenInvalidId_whenFindById_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, ( ) -> sut.buscaPorCodigo(1L));
    assertEquals("A cidade buscado não foi encontrada ou não existe", exception.getMessage());
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
  public void givenCidadeData_whenSave_thenThrows() {
    when(repository.save(any())).then(( arguments) -> {
      Cidade cidade = arguments.getArgument(0);
      cidade.setId(1L);
      return cidade;
    });
    Cidade cidade = sut.salvar(FactoryModels.getCidade());
    assertEquals(cidade.getId(), 1L);
  }

  @Test
  public void givenInvalidCidadeId_whenDeleteById_thenThrows() {
    Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> sut.deletar(1L));
    assertEquals("A cidade buscado não foi encontrada ou não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenDeleteBuId_thenSuccess() {
    when(repository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCidade()));
    sut.deletar(1L);
    verify(sut, times(1)).deletar(1L);
  }

  @Test
  public void givenUsedCidadeId_whenDeleteById_thenThrows() {
    when(repository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCidade()));
    doThrow(new DataIntegrityViolationException("")).when(repository).deleteById(1L);
    Exception exception = Assertions.assertThrows(BusinessException.class, () -> sut.deletar(1L));
    assertEquals("Não foi possivel excluir a cidades solicitada", exception.getMessage());
  }
}
