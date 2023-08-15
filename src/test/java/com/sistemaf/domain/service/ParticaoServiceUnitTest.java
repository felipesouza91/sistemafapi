package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Particao;
import com.sistemaf.domain.model.custompk.ParticaoPk;
import com.sistemaf.domain.repository.ParticaoRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParticaoServiceUnitTest {

  @Spy
  @InjectMocks
  private ParticaoService sut;

  @Mock
  private ParticaoRepository particaoRepository;

  @Test
  public void whenBuscarTodas_thenSuccess() {
    when(particaoRepository.findAll()).thenReturn(Collections.emptyList());
    List<Particao> result = sut.getAll();
    assertNotNull(result);
  }

  @Test
  public void givenInvalidId_whenBuscarPorId_thenThrows() {
    ParticaoPk id = new ParticaoPk();
    id.setClienteId(1L);
    id.setNumeroParticao("0001");
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.getByID(id));
    assertEquals("A partição não foi encontrada", exception.getMessage());
  }

  @Test
  public void givenValidId_whenBuscarPorId_thenThrows() {
    Particao particao = FactoryModels.getParticao();
    when(particaoRepository.findById(particao.getParticaoPk())).thenReturn(Optional.of(particao));
    Particao findParticao = sut.getByID(particao.getParticaoPk());
    assertNotNull(findParticao);
  }

  @Test
  public void givenExitsId_whenSave_thenThrows() {
    Particao particaoMock = FactoryModels.getParticao();
    when(particaoRepository.findById(particaoMock.getParticaoPk())).thenReturn(Optional.of(particaoMock));
    Exception exception = assertThrows(BusinessException.class, () -> sut.save(particaoMock));
    assertEquals("A partição já existe", exception.getMessage());
  }

  @Test
  public void givenValidData_whenSave_thenThorws() {
    Particao particaoMock = FactoryModels.getParticao();
    when(particaoRepository.save(particaoMock)).thenReturn(particaoMock);
    Particao particao = sut.save(particaoMock);
    verify(particaoRepository, times(1)).save(particaoMock);
    assertNotNull(particao);
  }
}
