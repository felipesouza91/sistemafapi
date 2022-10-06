package com.sistemaf.domain.usecases;

import com.sistemaf.api.dto.model.PermissionDto;
import com.sistemaf.domain.repository.security.PermissaoRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.Before;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;
import java.util.stream.Collectors;

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
     Set<PermissionDto> result =  sut.execute();
     assertThat(result.size(), is(4));
     assertThat(result.toArray()[0], instanceOf(PermissionDto.class));
     assertThat(result.stream().filter(item -> item.getNameId().contains("CLIENTE")).collect(Collectors.toSet()).size(),
         not(is(0)));
  }

}
