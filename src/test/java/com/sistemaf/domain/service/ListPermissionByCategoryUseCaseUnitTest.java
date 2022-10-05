package com.sistemaf.domain.service;

import com.sistemaf.domain.repository.security.PermissaoRepository;
import com.sistemaf.domain.usecases.ListPermissionByCategoryUseCase;
import com.sistemaf.util.FactoryModels;
import org.junit.Before;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ListPermissionByCategoryUseCaseUnitTest {

  @Mock
  private PermissaoRepository repository;

  @InjectMocks
  private ListPermissionByCategoryUseCase sut;

  @Before
  public void setup(){
    when(repository.findAll()).thenReturn(FactoryModels.getListPermissao());
  }

  @Test
  public void giveFormatedValue_whenCalled_success() {
    sut.execute();

  }

}
