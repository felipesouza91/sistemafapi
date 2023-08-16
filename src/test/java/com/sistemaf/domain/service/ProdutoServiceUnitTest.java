package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Fabricante;
import com.sistemaf.domain.model.Produto;
import com.sistemaf.domain.repository.estoque.fabricante.FabricanteRepository;
import com.sistemaf.domain.repository.estoque.produto.ProdutoRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceUnitTest {

  @Spy
  @InjectMocks
  private ProdutoService sut;

  @Mock
  private ProdutoRepository produtoRepository;

  @Mock
  private FabricanteRepository fabricanteRepository;

  @Test
  public void whenFilter_thenSuccess() {
    when(produtoRepository.filtrar(null,null)).thenReturn(Page.empty());
    Page result = sut.filtrar(null,null);
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void whenFilterResume_thenSuccess() {
    when(produtoRepository.resumo(null,null)).thenReturn(Page.empty());
    Page result = sut.resumo(null,null);
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Test
  public void givenInvalidId_whenBucarPorId_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.listaPorCodigo(1L));
    assertEquals("O Produto solicitado não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenBuscarPorId_thenSuccess() {
    Produto mockData = FactoryModels.getProduto();
    when(produtoRepository.findById(mockData.getId())).thenReturn(Optional.of(mockData));
    Produto searchData = sut.listaPorCodigo(mockData.getId());
    assertNotNull(searchData);
    assertEquals(mockData.getId(),searchData.getId());
  }

  @Test
  public void givenInvalidFabricanteId_whenSave_thenThrows() {
    Fabricante fabricante = FactoryModels.getFabricante();
    Produto data = new Produto();
    data.setDescricao("Teste");
    data.setModelo("Modelo");
    data.setValorUnitario(0F);
    data.setFabricante(fabricante);
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(data));
    assertEquals("O Fabricante não existe", exception.getMessage());
  }

  @Test
  public void givenExistsProductModel_whenSave_thenThrows() {
    Fabricante fabricante = FactoryModels.getFabricante();
    Produto mockDuplicatedModelo = FactoryModels.getProduto();
    Produto data = new Produto();
    data.setDescricao("Teste");
    data.setModelo(mockDuplicatedModelo.getModelo());
    data.setValorUnitario(0F);
    data.setFabricante(fabricante);
    when(fabricanteRepository.findById(fabricante.getId())).thenReturn(Optional.of(fabricante));
    when(produtoRepository.findByModeloWithoutSpaces(mockDuplicatedModelo.getModelo())).thenReturn(Optional.of(mockDuplicatedModelo));
    Exception exception = assertThrows(BusinessException.class, () -> sut.salvar(data));
    assertEquals("O produto já encontra-se cadastrado", exception.getMessage());
  }

  @Test
  public void givenValidData_whenSave_thenSuccess() {
    Fabricante fabricante = FactoryModels.getFabricante();
    Produto mockDuplicatedModelo = FactoryModels.getProduto();
    Produto data = new Produto();
    data.setDescricao("Teste");
    data.setModelo(mockDuplicatedModelo.getModelo());
    data.setValorUnitario(0F);
    data.setFabricante(fabricante);
    when(fabricanteRepository.findById(fabricante.getId())).thenReturn(Optional.of(fabricante));
    when(produtoRepository.save(data)).then( arguments -> {
      Produto produto = arguments.getArgument(0);
      produto.setId(1L);
      return produto;
    });
    Produto produtoSave = sut.salvar(data);
    verify(produtoRepository, times(1)).save(data);
    assertNotNull(produtoSave);
    assertEquals(1L, produtoSave.getId());
  }

  @Test
  public void givenInvalidId_whenExcluir_thenThrows() {
    Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.excluir(1L));
    assertEquals("O Produto solicitado não existe", exception.getMessage());
  }

  @Test
  public void givenValidId_whenExcluir_thenSuccess() {
    Produto produto = FactoryModels.getProduto();
    when(produtoRepository.findById(produto.getId())).thenReturn(Optional.of(produto));
    sut.excluir(produto.getId());
    verify(produtoRepository, times(1)).deleteById(produto.getId());
  }

  @Test
  public void givenInvaliFabricanteId_whenAtualizar_thenThrows() {
    Fabricante fabricante = FactoryModels.getFabricante();
    Produto data = FactoryModels.getProduto();
    data.setDescricao("Teste 2");
    Exception exception = assertThrows(BusinessException.class, () -> sut.atualizar(data.getId(), data));
    assertEquals("O Fabricante não existe", exception.getMessage());
  }

  @Test
  public void givenInvaliProdutoId_whenAtualizar_thenThrows() {
    Fabricante fabricante = FactoryModels.getFabricante();
    Produto data = FactoryModels.getProduto();
    data.setFabricante(fabricante);
    data.setDescricao("Teste 2");
    when(fabricanteRepository.findById(fabricante.getId())).thenReturn(Optional.of(fabricante));
    Exception exception = assertThrows(BusinessException.class, () -> sut.atualizar(data.getId(), data));
    assertEquals("O Produto solicitado não existe", exception.getMessage());
  }

  @Test
  public void givenValidData_whenAtualizar_thenSuccess() {
    Fabricante fabricante = FactoryModels.getFabricante();
    Produto data = FactoryModels.getProduto();
    data.setFabricante(fabricante);
    data.setDescricao("Teste 2");
    when(fabricanteRepository.findById(fabricante.getId())).thenReturn(Optional.of(fabricante));
    when(produtoRepository.findById(data.getId())).thenReturn(Optional.of(data));
    when(produtoRepository.save(data)).thenReturn(data);
    Produto updateProduto = sut.atualizar(data.getId(), data);
    assertNotNull(updateProduto);
    assertEquals("Teste 2", updateProduto.getDescricao());
  }

}
