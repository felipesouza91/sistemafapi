package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Bairro;
import com.sistemaf.domain.repository.bairro.BairroRepository;
import com.sistemaf.domain.repository.cidade.CidadeRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BairroServiceUnitTest {

  @Spy
  @InjectMocks
  private BairroService sut;

  @Mock
  private BairroRepository bairroRepository;

  @Mock
  private CidadeRepository cidadeRepository;

  @Test
  public void givenInvalidId_whenFindById_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.buscarPorCodigo(1L));
    assertTrue(exception instanceof EntityNotFoundException);
  }

  @Test
  public void givenEmptyFilter_whenPesquisar_thenSuccess() {
    when(bairroRepository.filtrar(any(), any())).thenReturn(Page.empty());
    Page page = sut.pesquisar(null, null);
    assertEquals(0 , page.getSize());
  }

  @Test
  public void givenInvalidCidadeId_whenAtualizar_thenThrows() {
    when(cidadeRepository.findById(any())).thenReturn(Optional.empty());
    Bairro newBairro = FactoryModels.getBarrio();
    newBairro.getCidade().setId(10L);
    Exception exception = assertThrows(BusinessException.class, ( ) -> sut.atualizar(1L, newBairro));
    assertEquals("Cidade invalida", exception.getMessage());
  }

  @Test
  public void givenInvalidBairroId_whenAtualizar_thenThrows() {
    when(cidadeRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCidade()));
    when(bairroRepository.findById(any())).thenReturn(Optional.empty());
    Bairro newBairro = FactoryModels.getBarrio();
    newBairro.getCidade().setId(1L);
    Exception exception = assertThrows(BusinessException.class, ( ) -> sut.atualizar(1L, newBairro));
    assertEquals("O Bairro não foi encontrado ou não existe", exception.getMessage());
  }

  @Test
  public void givenDuplicatedNeighborhoodInCity_whenAtualizar_thenThrows() {
    when(cidadeRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCidade()));
    when(bairroRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getBarrio()));
    when(bairroRepository.findByCidadeAndNome(any(), any())).thenReturn(Optional.of(FactoryModels.getBarrio()));
    Bairro newBairro = FactoryModels.getBarrio();
    newBairro.getCidade().setId(1L);
    Exception exception = assertThrows(BusinessException.class, ( ) -> sut.atualizar(1L, newBairro));
    assertEquals("O Bairro já existe na cidade selecionada", exception.getMessage());
  }

  @Test
  public void givenValidData_whenAtualizar_thenSuccess() {
    when(cidadeRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getCidade()));
    when(bairroRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getBarrio()));
    when(bairroRepository.findByCidadeAndNome(any(), any())).thenReturn(Optional.empty());
    when(bairroRepository.save(any())).then((arguments) -> {
      Bairro bairro = arguments.getArgument(0);
      bairro.setNome("Novo Bairro");
      return bairro;
    });
    Bairro newBairro = FactoryModels.getBarrio();
    newBairro.setNome("Novo Bairro");
    newBairro.getCidade().setId(1L);
    Bairro savedBairro  = sut.atualizar(1L, newBairro);
    assertEquals(newBairro.getId(), savedBairro.getId());
    assertEquals("Novo Bairro", savedBairro.getNome());
  }

  @Test
  public void givenInvalidId_whenDelete_thenThrows() {
    when(bairroRepository.findById(1L)).thenReturn(Optional.empty());
    Exception exception = assertThrows(BusinessException.class, () -> sut.deletar(1L));
    assertEquals("O Bairro não foi encontrado ou não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenDelete_thenSuccess() {
    when(bairroRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getBarrio()));
    sut.deletar(1L);
    verify(sut, times(1)).deletar(1L);
  }
  @Test
  public void givenInvalidCityId_whenSave_thenThrows() {
    Bairro inputBairro = FactoryModels.getBarrio();
    inputBairro.getCidade().setId(2222L);
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(inputBairro));
    assertTrue(exception instanceof BusinessException);
    assertEquals("Cidade invalida", exception.getMessage());
  }

  @Test
  public void givenExistsBairroName_whenSave_thenThrows() {
    Bairro inputBairro = FactoryModels.getBarrio();
    when(cidadeRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getCidade()));
    when(bairroRepository.findByCidadeAndNome(any(), any())).thenReturn(Optional.of(FactoryModels.getBarrio()));
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(inputBairro));
    assertTrue(exception instanceof BusinessException);
    assertEquals("O Bairro já existe", exception.getMessage());
  }

  @Test
  public void givenValidInputData_whenSave_thenSuccess() {
    Bairro inputBairro = FactoryModels.getBarrio();
    when(cidadeRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getCidade()));
    when(bairroRepository.save(any())).thenReturn(inputBairro);
    Bairro savedBairro = sut.salvar(inputBairro);
    assertNotNull(savedBairro);
    assertEquals(inputBairro.getNome(), savedBairro.getNome());
  }

}
