package com.sistemaf.api.resource.stock;

import com.sistemaf.api.dto.input.StockItemInput;
import com.sistemaf.domain.contracts.stock.AddStockItemService;
import com.sistemaf.util.BaseWebMvcTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockItemResource.class)
public class StockItemResourceTest extends BaseWebMvcTestConfig {

    private static final String STOCK_ITEM_PATH_REQUEST = "/stock/items";

    @MockBean
    public AddStockItemService addStockItemService;

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
}
