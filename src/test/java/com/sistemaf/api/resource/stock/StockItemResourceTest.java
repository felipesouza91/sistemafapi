package com.sistemaf.api.resource.stock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistemaf.api.dto.input.StockItemInput;
import com.sistemaf.domain.contracts.stock.*;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Produto;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.util.BaseWebMvcTestConfig;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(StockItemResource.class)
public class StockItemResourceTest extends BaseWebMvcTestConfig {
    private static final String STOCK_ITEM_PATH_REQUEST = "/stock/items";

    @MockBean
    private AddStockItemService addStockItemService;

    @MockBean
    private FindStockItemServices findStockItemServices;

    @MockBean
    private FindStockItemByIdService findStockItemByIdService;

    @MockBean
    private UpdateStockItemService updateStockItemService;

    @MockBean
    private ChangeActiveStockItemService changeActiveStockItemService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should return 400 when data is invalid")
    @WithMockUser
    public void givenInvalidInput_whenSaveItem_thenReturnBadRequest() throws Exception {
        StockItemInput input = new StockItemInput();
        mockMvc.perform(post(STOCK_ITEM_PATH_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(super.objectMapper.writeValueAsString(input))
                        .with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Dados Invalidos")))
                .andExpect(jsonPath("$.detail", is("Um ou mais campos estão invalidos. Faça o preenchimento correto e tente novamente")));
    }

    @Test
    @DisplayName("should call create method in Service Class")
    @WithMockUser
    public void givenValidInput_whenSaveItem_thenCallServiceWithCorrectValues() throws Exception {
        StockItemInput input = new StockItemInput("ASDADAD", 1L);
        mockMvc.perform(post(STOCK_ITEM_PATH_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(input))
                        .with(csrf()));
        verify(addStockItemService, times(1)).perform(any());
    }

    @Test
    @WithMockUser
    @DisplayName("should return 400 if product id not exists")
    public void givenInvalidProductId_whenSaveItem_thenReturnBadRequest() throws Exception {
        StockItemInput input = new StockItemInput("ASDADAD", 1L);
        given(addStockItemService.perform(any())).willThrow(new BusinessException("O produto não foi encontrado ou esta desabilitado"));
        mockMvc.perform(post(STOCK_ITEM_PATH_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(super.objectMapper.writeValueAsString(input))
                .with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Violação de regra de negocio")))
                .andExpect(jsonPath("$.detail", is("O produto não foi encontrado ou esta desabilitado")));
    }

    @Test
    @WithMockUser
    @DisplayName("should return 201 with stock item data")
    public void givenValidInput_whenSaveItem_thenReturnCreated() throws Exception {
        Produto produto = Instancio.create(Produto.class);
        StockItem stockItem = Instancio.create(StockItem.class);
        stockItem.setProduto(produto);
        StockItemInput input = new StockItemInput("ASDADAD", produto.getId());
        given(addStockItemService.perform(any())).willReturn(stockItem);
        mockMvc.perform(post(STOCK_ITEM_PATH_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(input))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(stockItem.getId().toString())))
                .andExpect(jsonPath("$.product.id", is(produto.getId().intValue())));
    }


    @Test
    @WithMockUser
    @DisplayName("should return 200 when get Stock Item with resume params")
    public void given_whenFindResumeParam_thenReturnOk() throws Exception {
        List<StockItem> list = Instancio.ofList(StockItem.class).create();
        given(findStockItemServices.perform(any(),any())).willReturn(new PageImpl<>(list));
        mockMvc.perform(get(STOCK_ITEM_PATH_REQUEST)
                .queryParam("resume",""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].productModel", is(list.get(0).getProduto().getModelo())))
                .andExpect(jsonPath("$.content.[0].manufactureName", is(list.get(0).getProduto().getFabricante().getDescricao())));
    }

    @Test
    @WithMockUser
    @DisplayName("should call findStockItemServices whit correct Values")
    public void givenInputData_whenFindResumeParam_thenCallServiceWithCorrectValues() throws Exception {
        given(findStockItemServices.perform(any(),any())).willReturn(new PageImpl<>(Collections.emptyList() ));
        mockMvc.perform(get(STOCK_ITEM_PATH_REQUEST)
                .queryParam("resume", "")
                        .param("active", "true"));
        verify(findStockItemServices, times(1)).perform(any(),any());
    }

    @Test
    @WithMockUser
    @DisplayName("should return 200 with a body")
    public void givenInputData_whenFindResumeParam_thenReturn200() throws Exception {
        given(findStockItemServices.perform(any(),any())).willReturn(new PageImpl<>(Collections.emptyList() ));
        mockMvc.perform(get(STOCK_ITEM_PATH_REQUEST)
                .queryParam("resume", "")
                .param("active", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(0)));
    }

    @Test
    @WithMockUser
    @DisplayName("should call FindStockItemByIdService with correct value")
    public void givenStockItemId_whenFindById_thenCallFindStockItemByIdService() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(get(STOCK_ITEM_PATH_REQUEST +"/{id}", id));
        verify(findStockItemByIdService, times(1)).perform(id);
    }

    @Test
    @WithMockUser
    @DisplayName("should return 404 when stock item not exists")
    public void givenInvalidID_wheFindById_thenReturnNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        given(findStockItemByIdService.perform(id)).willThrow(new EntityNotFoundException("O item não foi encontrado"));
        mockMvc.perform(get(STOCK_ITEM_PATH_REQUEST +"/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title", is("Recurso não encontrado")));

    }
    @Test
    @WithMockUser
    @DisplayName("should return 200 when stock item exist")
    public void givenValidId_whenFindById_thenReturnOk() throws Exception {
        StockItem stockItem = Instancio.create(StockItem.class);
        String id = stockItem.getId().toString();
        given(findStockItemByIdService.perform(stockItem.getId())).willReturn(stockItem);
        mockMvc.perform(get(STOCK_ITEM_PATH_REQUEST +"/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(id)))
                .andExpect(jsonPath("$.serial", is(stockItem.getSerial())));
    }

    @Test
    @WithMockUser
    @DisplayName("should call service when update item")
    public void given_whenUpdateById_thenCallServiceWithCorrectData() throws Exception {
        StockItemInput stockItemInput = Instancio.create(StockItemInput.class);
        UUID id = UUID.randomUUID();
        mockMvc.perform(put(String.format("%s/{id}", STOCK_ITEM_PATH_REQUEST), id)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(stockItemInput))
                        .with(csrf()));
        verify(updateStockItemService, times(1)).perform(eq(id), any());
    }

    @Test
    @WithMockUser
    @DisplayName("should return 400 when invalid data is provide")
    public void givenInvalidDatA_whenUpdateById_thenReturnBadRequest()  throws Exception {
        StockItemInput stockItemInput = new StockItemInput() ;
        UUID id = UUID.randomUUID();
        mockMvc.perform(put(String.format("%s/{id}", STOCK_ITEM_PATH_REQUEST), id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(super.objectMapper.writeValueAsString(stockItemInput))
                .with(csrf())
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Dados Invalidos")))
                .andExpect(jsonPath("$.detail", is("Um ou mais campos estão invalidos. Faça o preenchimento correto e tente novamente")));
    }

    @Test
    @WithMockUser
    @DisplayName("should return 400 when invalid product id is provide")
    public void givenInvalidProductId_whenUpdateById_thenReturnBadRequest()  throws Exception {
        StockItemInput stockItemInput = Instancio.create(StockItemInput.class) ;
        UUID id = UUID.randomUUID();
        given(updateStockItemService.perform(eq(id), any())).willThrow(new BusinessException("O produto informado não existe"));
        mockMvc.perform(put(String.format("%s/{id}", STOCK_ITEM_PATH_REQUEST), id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(stockItemInput))
                        .with(csrf())
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Violação de regra de negocio")))
                .andExpect(jsonPath("$.detail", is("O produto informado não existe")));
    }

    @Test
    @WithMockUser
    @DisplayName("should return 404 when stock item not exits")
    public void givenInvalidId_whenUpdateById_thenReturnNotFound() throws Exception {
        StockItemInput stockItemInput = Instancio.create(StockItemInput.class);
        UUID id = UUID.randomUUID();
        given(updateStockItemService.perform(eq(id), any())).willThrow(new EntityNotFoundException("O item não foi encontrado"));
        mockMvc.perform(put(String.format("%s/{id}", STOCK_ITEM_PATH_REQUEST), id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(stockItemInput))
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title", is("Recurso não encontrado")))
                .andExpect(jsonPath("$.detail", is("O item não foi encontrado")));
    }

    @Test
    @WithMockUser
    @DisplayName("should return 200 when stock item is updated")
    public void givenValiData_whenUpdateById_thenReturnOk() throws Exception {
        StockItem stockItem = Instancio.create(StockItem.class);
        StockItemInput stockItemInput = new StockItemInput(stockItem.getSerial(), stockItem.getProduto().getId());
        UUID id = stockItem.getId();
        given(updateStockItemService.perform(eq(id), any())).willReturn(stockItem);
        mockMvc.perform(put(String.format("%s/{id}", STOCK_ITEM_PATH_REQUEST), id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(stockItemInput))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.toString())))
                .andExpect(jsonPath("$.product.id", is(stockItem.getProduto().getId().intValue())));
    }

    @Test
    @WithMockUser
    @DisplayName("should return 400 when field not provided when path method")
    public void givenInvalidBody_whenPatchActiveItem_thenReturnBadRequest() throws Exception {
        Map<String, Boolean> input = new HashMap<>();

        UUID id = UUID.randomUUID();
        mockMvc.perform(patch(String.format("%s/{id}/active", STOCK_ITEM_PATH_REQUEST), id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(input))
                        .with(csrf())
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Dados Invalidos")))
                .andExpect(jsonPath("$.detail", is("Um ou mais campos estão invalidos. Faça o preenchimento correto e tente novamente")));
    }

    @Test
    @WithMockUser
    @DisplayName("should return 404 when field not provided when path method")
    public void givenInvalidId_whenPatchActiveItem_thenReturnNotFound() throws Exception {
        Map<String, Boolean> input = new HashMap<>();
        input.put("active", true);
        UUID id = UUID.randomUUID();
        doThrow(new EntityNotFoundException("O item do stock nao foi encontrado")).when(changeActiveStockItemService).perform(any(),any());
        mockMvc.perform(patch(String.format("%s/{id}/active", STOCK_ITEM_PATH_REQUEST), id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(input))
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title", is("Recurso não encontrado")))
                .andExpect(jsonPath("$.detail", is("O item do stock nao foi encontrado")));
    }

    @Test
    @WithMockUser
    @DisplayName("should return 204 when success")
    public void givenValid_whenPatchActiveItem_thenReturnNotFound() throws Exception {
        Map<String, Boolean> input = new HashMap<>();
        input.put("active", true);
        UUID id = UUID.randomUUID();
        doNothing().when(changeActiveStockItemService).perform(id, true);
        mockMvc.perform(patch(String.format("%s/{id}/active", STOCK_ITEM_PATH_REQUEST), id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(super.objectMapper.writeValueAsString(input))
                        .with(csrf()))
                .andExpect(status().isNoContent());

    }
}
