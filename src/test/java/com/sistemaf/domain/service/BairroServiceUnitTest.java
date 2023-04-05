package com.sistemaf.domain.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Bairro;
import com.sistemaf.domain.repository.bairro.BairroRepository;
import com.sistemaf.domain.repository.cidade.CidadeRepository;
import com.sistemaf.util.FactoryModels;

@ExtendWith(MockitoExtension.class)
public class BairroServiceUnitTest {

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
