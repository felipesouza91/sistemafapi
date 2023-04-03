package com.sistemaf.domain.usecases.permissions;

import com.sistemaf.api.dto.model.PermissionDto;
import com.sistemaf.domain.repository.security.PermissaoRepository;
import com.sistemaf.domain.usecases.permission.ListPermissionByCategoryUseCase;
import com.sistemaf.util.FactoryModels;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListPermissionByCategoryUseCaseUnitTest {

  @Mock
  private PermissaoRepository repository;

  @InjectMocks
  private ListPermissionByCategoryUseCase sut;

  @Test
  public void giveFormatedValue_whenCalled_success() {
    when(repository.findAll()).thenReturn(FactoryModels.getListPermissao());
     Set<PermissionDto> result =  sut.execute();
     assertThat(result.size(), is(4));
     assertThat(result.toArray()[0], instanceOf(PermissionDto.class));
     assertThat(result.stream().filter(item -> item.getNameId().contains("CLIENTE")).collect(Collectors.toSet()).size(),
         not(is(0)));
  }

}
