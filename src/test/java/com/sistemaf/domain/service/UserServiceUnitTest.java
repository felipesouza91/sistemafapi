package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.domain.projection.UserSimpleModel;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.domain.repository.security.usuario.UsuarioRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

  @InjectMocks
  public UsuarioService sut;

  @Mock
  private UsuarioRepository usuarioRepository;

  @Mock
  private GrupoAcessoRepository grupoAcessoRepository;

  @Mock
  private PasswordEncoder passwordEncoder;


  @Test
  public void whenFind_thenSuccess() {
    when(usuarioRepository.filtar(any(), any())).thenReturn(Page.empty());
    Page result = sut.filtro(null, null);
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void whenFindResumeUser_thenSuccess() {
    when(usuarioRepository.resumo()).thenReturn(FactoryModels.getUserResumeList());
    List<UserSimpleModel> userSimpleModels = sut.resumo();
    assertNotNull(userSimpleModels);
    assertEquals(3, userSimpleModels.size());
  }

  @Test
  public void givenInvalidUserGroupId_whenCreate_thenThrows() {
    var newUser = FactoryModels.getUsuario();
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(newUser));
    assertEquals( "O Grupo de Acesso não existe", exception.getMessage());
  }

  @Test
  public void givenAExistsUsername_whenCreate_thenThrows() {
    var newUser = FactoryModels.getUsuario();
    when(grupoAcessoRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getGrupoAcesso()));
    when(usuarioRepository.findByApelido(newUser.getApelido())).thenReturn(Optional.of(FactoryModels.getUsuario()));
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(newUser));
    assertEquals( "O apelido já esta em uso", exception.getMessage());
  }


  @Test
  public void ensureToPasswordEncodedWasCalled_whenCreate() {
    var newUser = FactoryModels.getUsuario();
    when(grupoAcessoRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getGrupoAcesso()));
    sut.salvar(newUser);
    verify(passwordEncoder, times(1)).encode("encryptPassword");
  }

  @Test
  public void givenValidData_whenCreat_thenSuccess() {
    var newUser = new Usuario();
    newUser.setSenha("anypassword");
    newUser.setApelido("apelido");
    newUser.setGrupoAcesso(FactoryModels.getGrupoAcesso());
    newUser.setNome("nome");
    when(grupoAcessoRepository.findById(any())).thenReturn(Optional.of(FactoryModels.getGrupoAcesso()));
    when(usuarioRepository.save(any())).then((arguments) -> {
      Usuario user = new Usuario();
      user.setId(1L);
      user.setSenha("encypassword");
      user.setApelido("apelido");
      user.setGrupoAcesso(FactoryModels.getGrupoAcesso());
      user.setNome("nome");
      user.setAtivo(true);
      return user;
    });
    Usuario user = sut.salvar(newUser);
    assertNotNull(user);
    assertNotEquals(newUser.getSenha(), user.getSenha());
  }

  @Test
  public  void givenInvalidId_whenFindUnique_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.buscarPorCodigo(1L));
    assertEquals("O usuario solicitado não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenFindUnique_thenSuccess() {
    when(usuarioRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getUsuario()));
    Usuario usuario = sut.buscarPorCodigo(1L);
    assertNotNull(usuario);
    assertEquals(1L, usuario.getId());
  }

  @Test
  public void givenInvalidGroupId_whenUpdate_thenThrows() {
    Usuario user = FactoryModels.getUsuario();
    Exception exception = assertThrows(BusinessException.class, () -> sut.atualizar(1L,user) );
    assertEquals("O Grupo de Acesso não existe", exception.getMessage());
  }

  @Test
  public void givenInvalidUserId_whenUpdate_thenThrows() {
    Usuario user = FactoryModels.getUsuario();
    when(grupoAcessoRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getGrupoAcesso()));
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.atualizar(1L,user) );
    assertEquals("O usuario solicitado não existe", exception.getMessage());
  }

  @Test
  public void givenValidData_whenUpdate_thenSuccess() {
    Usuario user = FactoryModels.getUsuario();
    when(grupoAcessoRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getGrupoAcesso()));
    when(usuarioRepository.findById(1L)).thenReturn(Optional.of(FactoryModels.getUsuario()));
    when(usuarioRepository.save(any())).thenReturn(FactoryModels.getUsuario());
    Usuario updatedUse = sut.atualizar(1L, user);
    assertNotNull(updatedUse);
  }
}
