package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.InformacaoFilter;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.ClienteInformacao;
import com.sistemaf.domain.repository.cliente.ClienteInformacaoRepository;
import com.sistemaf.util.FactoryModels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientInformationServiceUnitTest {

    @Mock
    private ClienteService clientService;

    Pageable pageRequest = PageRequest.of(0, 1);

    @Mock
    private ClienteInformacaoRepository informacaoDao;

    @InjectMocks
    private ClientInformationService sut;

    @BeforeEach
    void setup() {
        lenient().when(clientService.listarPorCodigo(any())).thenReturn(FactoryModels.getCliente());
        lenient().when(informacaoDao.findById(any())).thenReturn(Optional.of(FactoryModels.getClientInfo()));
        lenient().when(informacaoDao.filtrar(any(),any(), any()))
                .thenReturn(new PageImpl<>(Arrays.asList(FactoryModels.getClientInfo(), FactoryModels.getClientInfo()), pageRequest, 1));
        lenient().when(informacaoDao.save(any())).thenReturn(FactoryModels.getClientInfo());
    }

    @Test()
    public void giveValidClientId_whenFindClientInfoById_success() {
        ClienteInformacao info = sut.getInformacaoById(1L);
        assertNotNull(info);
        assertEquals(info.getId(), 1L);
    }

    @Test
    public void giveInvalidClientInfoId_whenFindById__fails() {
        when(informacaoDao.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () ->  sut.getInformacaoById(1L));
        assertTrue(exception instanceof EntityNotFoundException);
        assertEquals("Informação não foi encontrada", exception.getMessage());
    }

    @Test
    public void giveValidClientId_whenAllClientInfo_success() {
        InformacaoFilter filter = new InformacaoFilter();
        Page<ClienteInformacao> result = sut.filtrarAtendimento(1L, filter, pageRequest);
        assertNotNull(result.getContent());
        assertEquals(result.getContent().get(0).getId(), FactoryModels.getClientInfo().getId());
        assertEquals(result.getTotalElements(), 1L);
    }

    @Test
    public void givenNullClientId_whenAllClientInfo_fails() {
        InformacaoFilter filter = new InformacaoFilter();
        Exception exception = assertThrows(BusinessException.class, () -> sut.filtrarAtendimento(null, filter, pageRequest));
        assertTrue(exception instanceof  BusinessException);
        assertEquals("O Id do cliente é obrigatorio", exception.getMessage());
    }

    @Test()
    public void givenInvalidClientId_whenAllClientInfo_fails() {
        when(clientService.listarPorCodigo(any())).thenThrow(EntityNotFoundException.class);
        InformacaoFilter filter = new InformacaoFilter();
        Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.filtrarAtendimento(2l, filter, pageRequest));
        assertTrue(exception instanceof  EntityNotFoundException);
        assertEquals("O Cliente não encontrado ou não existe", exception.getMessage());
    }

    @Test
    public void giveInvalidClientId_whenRemoveInfoClient_thenFails() {
        doThrow(Exception.class).when(informacaoDao).deleteById(any());
        sut.deleteInfoById(1L);
        Exception exception = assertThrows(Exception.class, () -> sut.deleteInfoById(1L));
        assertTrue(exception instanceof  Exception);
    }

    @Test
    public void giveValidClientId_whenRemoveInfoClient_thenSuccess() {
        sut.deleteInfoById(1L);
    }

    @Test
    public void giveInvalidClientId_whenSaveInfoClient_thenFails() {
        ClienteInformacao info =  FactoryModels.getClientInfo();
        info.setId(null);
        when(clientService.listarPorCodigo(any())).thenThrow(EntityNotFoundException.class);
        Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.saveInfo(1L,info));
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertEquals("O Cliente não encontrado ou não existe", exception.getMessage());
    }

    @Test()
    public void giveValidClientId_whenSaveInfoClient_thenSuccess() {
        ClienteInformacao info =  FactoryModels.getClientInfo();
        info.setId(null);
        ClienteInformacao infoSave = sut.saveInfo(1L,info);
        assertNotNull(infoSave);
        assertEquals(infoSave.getCliente().getId(), 1L);
        assertNotNull(infoSave.getId());
    }

    @Test
    public void giveInvalidClientInfoId_whenUpdateInfoCliente_thenFails() {
        when(informacaoDao.findById(any())).thenReturn(Optional.empty());
        ClienteInformacao info = FactoryModels.getClientInfo();
        info.setDescricao("Outra Descricao");
        Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.atualizarInformacao(1L, 1L,info ));
        assertTrue(exception instanceof  EntityNotFoundException);
        assertEquals("Informação não foi encontrada", exception.getMessage());
    }

    @Test
    public void giveInvalidClientId_whenUpdateInfoCliente_thenFails() {
        ClienteInformacao info = FactoryModels.getClientInfo();
        when(clientService.listarPorCodigo(any())).thenThrow(EntityNotFoundException.class);
        info.setDescricao("Outra Descricao");
        Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.atualizarInformacao(1L, 3L,info ));
        assertTrue(exception instanceof EntityNotFoundException);
        assertEquals("O Cliente não encontrado ou não existe", exception.getMessage());
    }

    @Test
    public void giveValidClientInfoIdAndDifferentClientId_whenUpdateInfoCliente_thenFails() {
        Cliente wrongClient = FactoryModels.getCliente();
        when(clientService.listarPorCodigo(2L)).thenReturn(wrongClient);
        wrongClient.setId(2L);
        ClienteInformacao info = FactoryModels.getClientInfo();
        info.setDescricao("Outra Descricao");
        Exception exception = assertThrows(BusinessException.class, () ->  sut.atualizarInformacao(1L, 2L,info ));
        assertTrue(exception instanceof BusinessException);
        assertEquals("Erro ao realizar atualização!", exception.getMessage());
    }

    @Test
    public void giveValidClientIdAndInfoClient_whenUpdateInfoClient_thenFails() {
        ClienteInformacao info = FactoryModels.getClientInfo();
        info.setDescricao("Outra Descricao");
        when(informacaoDao.save(any())).thenReturn(info);
        ClienteInformacao updateInfo = sut.atualizarInformacao(1L, 1L,info );
        assertEquals(updateInfo.getId(), info.getId());
        assertEquals(updateInfo.getDescricao(), "Outra Descricao");
        assertEquals(updateInfo.getCliente().getId(), info.getCliente().getId());
    }

}
