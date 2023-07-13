package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Dvr;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class DvrServiceUnitTest {

  @Spy
  @InjectMocks
  private DvrService sut;

  @Mock
  private DvrRepository repository;


  @Test
  public void givenFilter_whenFilter_thenSuccess() {
    when(repository.filtrar(any(),any())).thenReturn(Page.empty());
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
    when(repository.findById(1L)).thenReturn(Optional.of(mockDvr));
    Dvr dvr =  sut.buscarPorCodigo(1L);
    assertEquals(mockDvr.getId(), dvr.getId());
  }

}
