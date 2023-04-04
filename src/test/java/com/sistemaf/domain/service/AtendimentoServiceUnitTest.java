package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Atendimento;
import com.sistemaf.domain.repository.atendimento.AtendimentoRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AtendimentoServiceUnitTest {


  @InjectMocks
  private AtendimentoService sut = new AtendimentoService();
  @Mock
  private AtendimentoRepository atendimentoRepository;

  @Test
  public void whenFindById_givenInvalidId_thenThrows() {
    when(atendimentoRepository.findById(any())).thenThrow(EntityNotFoundException.class);
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.buscarPorCodigo(1L));
    assertTrue(exception instanceof  EntityNotFoundException);
  }
  @Test
  public void whenFindById_givenValidId_thenReturnAntedimento() {
    var mockValue = new Atendimento();
    when(atendimentoRepository.findById(any())).thenReturn(Optional.of(mockValue));
    Atendimento atendimento = sut.buscarPorCodigo(1L);
    assertNotNull(atendimento);
  }
  @Test
  public void whenUpdate_givenInvalidId_thenThrows() {
    Atendimento updateAtendimento = new Atendimento();
    updateAtendimento.setId(1L);
    updateAtendimento.setSolicitante("Solicitante");
    updateAtendimento.setDataInicio(OffsetDateTime.now());
    updateAtendimento.setDataTermino(OffsetDateTime.now().plusMinutes(10));
    updateAtendimento.setUsuarioInicio(FactoryModels.getUsuario());
    updateAtendimento.setUsuarioTermino(FactoryModels.getUsuario());
    when(atendimentoRepository.findById(any())).thenThrow(EntityNotFoundException.class);
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.atualizar(1L,updateAtendimento ));
    assertTrue(exception instanceof  EntityNotFoundException);
  }


}
