package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Grupo;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GrupoServiceUnitTest {

  @Spy
  @InjectMocks
  private GrupoService sut;

  @Mock
  private GrupoRepository grupoRepository;

  @Test
  public void whenFind_thenSuccess() {
    when(grupoRepository.filtrar(any(),any())).thenReturn(Page.empty());
    Page<Grupo> result =  sut.filtrar(null, null);
    assertNotNull(result);
    assertEquals(0, result.getSize());
  }

  @Test
  public void givenInvalidId_whenFindById_thenThrows() {
    Exception exception = assertThrows( EntityNotFoundException.class, () -> sut.listaPorCodigo(1L));
    assertEquals("O Grupo solicitado não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenFindById_thenThrows() {
    when(grupoRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getGrupo()));
    Grupo grupo = sut.listaPorCodigo(1L);
    assertNotNull(grupo);
    assertNotNull(grupo.getId());
    assertEquals("Grupo 1", grupo.getNome());
  }

  @Test
  public void givenValidData_whenSave_thenThrows() {
    Grupo grupo = new Grupo();
    grupo.setNome("Grupo 1");
    when(grupoRepository.save(grupo)).thenReturn(FactoryModels.getGrupo());
    Grupo result = sut.salvar(grupo);
    verify(grupoRepository, times(1)).save(grupo);
    assertNotNull(result);
  }

  @Test
  public void givenInvalidId_whenDelete_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.excluir(1L));
    assertEquals("O Grupo solicitado não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenDelete_thenSuccess() {
    when(grupoRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getGrupo()));
    sut.excluir(1L);
    verify(grupoRepository, times(1)).deleteById(1L);
  }

  @Test
  public void givenInvalidId_whenUpdate_thenThrows() {
    Grupo newData = new Grupo();
    newData.setId(1L);
    newData.setNome("Grupo 1 atualizado");
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.atualizar(1L,newData));
    assertEquals("O Grupo solicitado não existe", exception.getMessage());
  }

  @Test
  public  void givenValidaData_whenUpdate_thenThrows() {
    Grupo newData = new Grupo();
    newData.setId(1L);
    newData.setNome("Grupo 1 atualizado");
    when(grupoRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getGrupo()));
    when(grupoRepository.save(any())).then( arguments -> {
      Grupo grupo = arguments.getArgument(0);
      grupo.setNome("Grupo 1 atualizado");
      grupo.setId(1L);
      return grupo;
    });
    Grupo updated = sut.atualizar(1L, newData);
    verify(grupoRepository, times(1)).save(newData);
    assertEquals("Grupo 1 atualizado", updated.getNome());
    assertEquals(1L, updated.getId());
  }
}
