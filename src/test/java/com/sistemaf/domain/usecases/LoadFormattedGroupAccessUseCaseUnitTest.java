package com.sistemaf.domain.usecases;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.is;
import static org.testcontainers.shaded.org.hamcrest.Matchers.not;


@RunWith(MockitoJUnitRunner.class)
public class LoadFormattedGroupAccessUseCaseUnitTest {

  @Mock
  private GrupoAcessoRepository repository;

  @Mock
  private ListPermissionByCategoryUseCase listPermissionByCategoryUseCase;

  @InjectMocks
  private LoadFormattedGroupAccessUseCase sut;

  @Before
  public void setup() {
    when(listPermissionByCategoryUseCase.execute()).thenReturn(FactoryModels.getFormatedPermissinByGroup());
    when(repository.findById(any())).thenReturn(Optional.of(FactoryModels.getGrupoAcesso()));
  }

  @Test
  public void giveValidGroupId_whenFindById_success() {
    var permissionGroupDto =  sut.execute(1L);
    assertThat(permissionGroupDto.getId(), is(1L) );
    assertThat(permissionGroupDto.getPermissions().size(), not(is(0)));
    assertThat(permissionGroupDto.getPermissions().stream().anyMatch(item -> item.getNameId().equals("CLIENTE") && !item.getWrite()), is(true));
  }

  @Test(expected = EntityNotFoundException.class)
  public void giveInvalidGroupIp_whenExecute_thenErro() {
    when(repository.findById(any())).thenReturn(Optional.empty());
    sut.execute(2L);
  }

}
