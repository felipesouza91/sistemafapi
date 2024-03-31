package com.sistemaf.api.resource.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistemaf.api.dto.input.ProductInput;
import com.sistemaf.api.dto.input.id.ManufacturerInputId;
import com.sistemaf.api.dto.manager.ProductMapper;
import com.sistemaf.core.SistemFApiProperty;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Produto;
import com.sistemaf.domain.projection.ResumoProduto;
import com.sistemaf.domain.service.ProdutoService;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoResource.class)
@EnableConfigurationProperties(value = SistemFApiProperty.class)
@TestPropertySource("classpath:tests.properties")
class ProdutoResourceTest {

    private final static String PRODUTO_PATH = "/produtos";

    @MockBean
    private ProdutoService produtoService;

    private ProductMapper dtoManager = ProductMapper.INSTANCE;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void givenValidId_whenFindById_thenReturnOk() throws Exception {
        Produto produto = FactoryModels.getProduto();
        given(produtoService.listaPorCodigo(any())).willReturn(produto);
        mockMvc.perform(get(PRODUTO_PATH+ "/{clientId}", 1L))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(produto.getId().intValue())))
                .andExpect(jsonPath("$.modelo", is(produto.getModelo())));
    }
    @Test
    @WithMockUser
    public void givenInvalidID_whenFindById_thenReturBadRequest() throws Exception {
        given(produtoService.listaPorCodigo(any())).willThrow(new EntityNotFoundException("O Produto solicitado não existe"));
        mockMvc.perform(get(PRODUTO_PATH+ "/{clientId}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.userMessage", is("O Produto solicitado não existe")))
              ;
    }
    @Test
    @WithAnonymousUser
    public void givenNoCredential_whenFindById_thenUnauthorized() throws Exception {
        mockMvc.perform(get(PRODUTO_PATH+ "/{clientId}", 1L))
                .andExpect(status().isUnauthorized());
        ;
    }

    @Test
    @WithMockUser
    public void given_whenFindAll_thenReturnOk() throws Exception {
        given(produtoService.filtrar(any(), any())).willReturn(new PageImpl<>(Arrays.asList(FactoryModels.getProduto(), FactoryModels.getProduto())));
        mockMvc.perform(get(PRODUTO_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()",is(2)));
    }

    @Test
    @WithAnonymousUser
    public void givenNoCredential_whenFindAll_thenReturnOk() throws Exception {
        mockMvc.perform(get(PRODUTO_PATH))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void givenResumeParam_whenFindAll_thenReturnOk() throws Exception {
        ResumoProduto resumoProduto = new ResumoProduto(1L, "Teste", 0.0f, 10l, "Fabricante");
        given(produtoService.resumo(any(), any())).willReturn(new PageImpl<>(Arrays.asList(resumoProduto)));
        mockMvc.perform(get(PRODUTO_PATH +"?resumo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()",is(1)))
                .andExpect(jsonPath("$.content[0].modelo", is(resumoProduto.getModelo())));
    }

    @Test
    @WithMockUser
    public void givenValidData_whenSave_thenReturnCreated() throws Exception {
        ManufacturerInputId manufacturerInputId = new ManufacturerInputId();
        manufacturerInputId.setId(1L);
        ProductInput productInput = new ProductInput();
        productInput.setDescricao("Some produto");
        productInput.setFabricante(manufacturerInputId);
        productInput.setValorUnitario(0.0f);
        productInput.setModelo("MODEL");
        Produto produto = dtoManager.toModel(productInput);
        produto.setId(1L);
        given(produtoService.salvar(any())).willReturn(produto);
        mockMvc.perform(post(PRODUTO_PATH)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productInput)))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.modelo", is(productInput.getModelo())));
    }

    @Test
    @WithMockUser
    public void givenInValidData_whenSave_thenReturnCreated() throws Exception {
        ProductInput productInput = new ProductInput();
        productInput.setDescricao("Some produto");
        productInput.setValorUnitario(0.0f);
        productInput.setModelo("MODEL");
        Produto produto = dtoManager.toModel(productInput);
        produto.setId(1L);
        given(produtoService.salvar(any())).willReturn(produto);
        mockMvc.perform(post(PRODUTO_PATH)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productInput)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Dados Invalidos")))
                .andExpect(jsonPath("$.detail", is("Um ou mais campos estão invalidos. Faça o preenchimento correto e tente novamente")));
    }

    @Test
    @WithMockUser
    public void givenInvalidInputData_whenUpdateProduct_thenReturnBadRequest() throws Exception {

        ProductInput productInput = new ProductInput();
        productInput.setDescricao("Some produto");
        productInput.setValorUnitario(0.0f);
        productInput.setModelo("MODEL");
        Produto produto = dtoManager.toModel(productInput);
        produto.setId(1L);
        given(produtoService.salvar(any())).willReturn(produto);
        mockMvc.perform(put(PRODUTO_PATH+"/{productId}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productInput)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Dados Invalidos")))
                .andExpect(jsonPath("$.detail", is("Um ou mais campos estão invalidos. Faça o preenchimento correto e tente novamente")));
    }

    @Test
    @WithMockUser
    public void givenInvalidManufactureId_whenUpdateProduct_thenReturnBadRequest() throws Exception {
        ManufacturerInputId manufacturerInputId = new ManufacturerInputId();
        manufacturerInputId.setId(2L);
        ProductInput productInput = new ProductInput();
        productInput.setDescricao("Some produto");
        productInput.setFabricante(manufacturerInputId);
        productInput.setValorUnitario(0.0f);
        productInput.setModelo("MODEL");
        Produto produto = dtoManager.toModel(productInput);
        produto.setId(1L);
        given(produtoService.atualizar(any(), any())).willThrow(new BusinessException("O Fabricante não existe") );
        mockMvc.perform(put(PRODUTO_PATH+"/{productId}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productInput)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Violação de regra de negocio")))
                .andExpect(jsonPath("$.detail", is("O Fabricante não existe")));
    }

    @Test
    @WithMockUser
    public void givenInvalidProductId_whenUpdateProduct_thenReturnBadRequest() throws Exception {
        ManufacturerInputId manufacturerInputId = new ManufacturerInputId();
        manufacturerInputId.setId(1L);
        ProductInput productInput = new ProductInput();
        productInput.setDescricao("Some produto");
        productInput.setFabricante(manufacturerInputId);
        productInput.setValorUnitario(0.0f);
        productInput.setModelo("MODEL");
        Produto produto = dtoManager.toModel(productInput);
        produto.setId(1L);
        given(produtoService.atualizar(any(), any())).willThrow(new EntityNotFoundException("O Produto solicitado não existe"));
        mockMvc.perform(put(PRODUTO_PATH+"/{productId}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productInput)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title", is("Recurso não encontrado")))
                .andExpect(jsonPath("$.detail", is("O Produto solicitado não existe")));
    }

    @Test
    @WithMockUser
    public void givenValidData_whenUpdateProduct_thenReturnOK() throws Exception {
        ManufacturerInputId manufacturerInputId = new ManufacturerInputId();
        manufacturerInputId.setId(1L);
        ProductInput productInput = new ProductInput();
        productInput.setDescricao("Some produto 2");
        productInput.setFabricante(manufacturerInputId);
        productInput.setValorUnitario(0.0f);
        productInput.setModelo("MODEL");

        given(produtoService.atualizar(any(), any())).will(args -> {
            Produto produto = args.getArgument(1);
            produto.setId(1L);
            return produto;
        });

        mockMvc.perform(put(PRODUTO_PATH+"/{productId}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productInput)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao", is(productInput.getDescricao())))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @WithMockUser
    public void givenInvalidProductId_whenDelete_thenReturnBadReques() throws Exception {
        doThrow(new EntityNotFoundException("O Produto solicitado não existe") ).when(produtoService).excluir(1L);
        mockMvc.perform(delete(PRODUTO_PATH+ "/{clientid}", 1L)
                .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title", is("Recurso não encontrado")))
                .andExpect(jsonPath("$.detail", is("O Produto solicitado não existe")));

    }

    @Test
    @WithMockUser
    public void givenValid_whenDelete_thenReturnBadReques() throws Exception {
        willDoNothing().given(produtoService).excluir(1L);
        mockMvc.perform(delete(PRODUTO_PATH+ "/{clientid}", 1L)
                        .with(csrf()))
                .andExpect(status().isNoContent());

    }
}