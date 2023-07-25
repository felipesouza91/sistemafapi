package com.sistemaf.domain.service;


import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Fabricante;
import com.sistemaf.domain.repository.estoque.fabricante.FabricanteRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FabricanteServiceUnitTest {
  @Spy
  @InjectMocks
  private FabricanteService sut;

  @Mock
  private FabricanteRepository fabricanteRepository;

  @Test
  public void givenFabricanteName_whenFilter_thenSuccess() {
    when(fabricanteRepository.findByDescricaoIgnoreCaseContaining(any(),any())).thenReturn(Page.empty());
    Page page = sut.getAll("Test", null);
    assertEquals(0, page.getSize());
  }

  @Test
  public void givenNulName_whenFilter_thenSuccess() {
    Pageable pageable =  PageRequest.of(1, 1);
    when(fabricanteRepository.findAll(pageable)).thenReturn(Page.empty());
    Page page = sut.getAll(null, pageable);
    assertEquals(0, page.getSize());
  }

  @Test
  public void givenInvalidId_whenBuscarPorId_thenThrows() {
    Exception exception  = assertThrows(EntityNotFoundException.class, () -> sut.getById(1L));
    assertEquals("Fabricante não encontrado", exception.getMessage());
  }

  @Test
  public void givenValidId_whenBuscarPorId_thenSuccess() {
    Fabricante mockFabricante = FactoryModels.getFabricante();
    when(fabricanteRepository.findById(1L)).thenReturn(Optional.of(mockFabricante));
    Fabricante fabricante = sut.getById(1L);
    assertEquals(mockFabricante.getId(), fabricante.getId());
    assertEquals(mockFabricante.getDescricao(), fabricante.getDescricao());
  }

  @Test
  public  void givenValidInput_whenSave_thenSuccess() {
    Fabricante inputFabricante = new Fabricante();
    inputFabricante.setDescricao("Fab1");
    when(fabricanteRepository.save(inputFabricante)).then((arguments) -> {
      Fabricante fabricante  = arguments.getArgument(0);
      fabricante.setId(1L);
      fabricante.setCreationDate(OffsetDateTime.now());
      return fabricante;
    });
    Fabricante savedFabricante = sut.save(inputFabricante);
    assertEquals(1L, savedFabricante.getId());
    assertNotNull(savedFabricante.getCreationDate());
    assertEquals("Fab1", savedFabricante.getDescricao());
  }

  @Test
  public void givenInvalidId_whenUpdate_thenThrows() {
    Fabricante updateInput = FactoryModels.getFabricante();
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.update(1L,updateInput));
    assertEquals("Fabricante não encontrado", exception.getMessage());
  }

  @Test
  public  void givenValidId_whenUpdate_thenSuccess() {
    Fabricante updateInput = FactoryModels.getFabricante();
    OffsetDateTime updateTime = OffsetDateTime.now();
    when(fabricanteRepository.findById(updateInput.getId())).thenReturn(Optional.of(updateInput));
    updateInput.setDescricao("Fab 2");
    when(fabricanteRepository.save(any())).then((arguments) -> {
      Fabricante fabricante = arguments.getArgument(0);
      fabricante.setDescricao("Fab 2");
      fabricante.setLastModifiedDate(updateTime);
      return fabricante;
    });
    Fabricante updateFab = sut.update(updateInput.getId(), updateInput);
    assertEquals("Fab 2", updateFab.getDescricao());
    assertEquals(updateTime, updateFab.getLastModifiedDate());
  }

  @Test
  public void givenInvalidId_whenDelete_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.remove(1L));
    assertEquals("Fabricante não encontrado", exception.getMessage());
  }

  @Test
  public void givenUsedId_whenDelete_thenThrows() {
    when(fabricanteRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getFabricante()));
    doThrow(DataIntegrityViolationException.class).when(fabricanteRepository).deleteById(1L);
    Exception exception = assertThrows(BusinessException.class, () -> sut.remove(1L));
    assertEquals("Erro ao excluir fabricante", exception.getMessage());
  }

  @Test
  public void givenValidId_whenDelete_thenSuccess() {
    when(fabricanteRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getFabricante()));
    sut.remove(1L);
    verify(sut, times(1)).remove(1L);
  }
}
