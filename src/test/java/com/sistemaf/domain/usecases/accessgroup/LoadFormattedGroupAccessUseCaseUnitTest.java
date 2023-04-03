package com.sistemaf.domain.usecases.accessgroup;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.domain.usecases.permission.PermissionMapperUseCase;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.is;
import static org.testcontainers.shaded.org.hamcrest.Matchers.not;


@ExtendWith(MockitoExtension.class)
public class LoadFormattedGroupAccessUseCaseUnitTest {

  @Mock
  private GrupoAcessoRepository repository;

  @Mock
  private PermissionMapperUseCase permissionMapperUseCase;

  @InjectMocks
  private LoadFormattedGroupAccessUseCase sut;


  @Test
  public void giveValidGroupId_whenFindById_success() {
    when(permissionMapperUseCase.toListDto(any())).thenReturn(FactoryModels.getFormatedPermissinByGroup());
    when(repository.findById(any())).thenReturn(Optional.of(FactoryModels.getGrupoAcesso()));
    var permissionGroupDto =  sut.execute(1L);
    assertThat(permissionGroupDto.getId(), is(1L) );
    assertThat(permissionGroupDto.getPermissions().size(), not(is(0)));
    assertThat(permissionGroupDto.getPermissions().stream().anyMatch(item -> item.getNameId().equals("CLIENTE") && !item.getWrite()), is(true));
  }

  @Test()
  public void giveInvalidGroupIp_whenExecute_thenErro() {
    when(repository.findById(any())).thenReturn(Optional.empty());
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.execute(2L));
    assertTrue(exception instanceof  EntityNotFoundException);
  }

}
