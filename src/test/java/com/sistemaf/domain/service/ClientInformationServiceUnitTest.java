package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.InformacaoFilter;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.ClienteInformacao;
import com.sistemaf.domain.repository.cliente.ClienteInformacaoRepository;

import com.sistemaf.util.FactoryModels;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.*;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ClientInformationServiceUnitTest {

    @Mock
    private ClienteService clientService;

    Pageable pageRequest = PageRequest.of(0, 1);

    @Mock
    private ClienteInformacaoRepository informacaoDao;

    @InjectMocks
    private ClientInformationService sut;

    @Before
    public void setup() {
        when(clientService.listarPorCodigo(any())).thenReturn(FactoryModels.getCliente());
        when(informacaoDao.findById(any())).thenReturn(Optional.of(FactoryModels.getClientInfo()));
        when(informacaoDao.filtrar(any(),any(), any()))
                .thenReturn(new PageImpl<>(Arrays.asList(FactoryModels.getClientInfo(), FactoryModels.getClientInfo()), pageRequest, 1));
        when(informacaoDao.save(any())).thenReturn(FactoryModels.getClientInfo());
    }

    @Test
    public void giveValidClientId_whenFindClientInfoById_success() {
        ClienteInformacao info = sut.getInformacaoById(1L);
        Assert.assertNotNull(info);
        assertThat(info.getId(), is(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void giveInvalidClientInfoId_whenFindById__fails() {
        when(informacaoDao.findById(any())).thenReturn(Optional.empty());
        sut.getInformacaoById(1L);
    }

    @Test
    public void giveValidClientId_whenAllClientInfo_success() {
        InformacaoFilter filter = new InformacaoFilter();
        Page<ClienteInformacao> result = sut.filtrarAtendimento(1L, filter, pageRequest);
        Assert.assertNotNull(result.getContent());
        assertThat(result.getContent().get(0).getId(), is(FactoryModels.getClientInfo().getId()));
        assertThat(result.getTotalElements(), is(1L));
    }

    @Test(expected = BusinessException.class)
    public void givenNullClientId_whenAllClientInfo_fails() {
        InformacaoFilter filter = new InformacaoFilter();
        Page<ClienteInformacao> result = sut.filtrarAtendimento(null, filter, pageRequest);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidClientId_whenAllClientInfo_fails() {
        when(clientService.listarPorCodigo(any())).thenThrow(EntityNotFoundException.class);
        InformacaoFilter filter = new InformacaoFilter();
        Page<ClienteInformacao> result = sut.filtrarAtendimento(2l, filter, pageRequest);
    }

    @Test(expected = Exception.class)
    public void giveInvalidClientId_whenRemoveInfoClient_thenFails() {
        doThrow(Exception.class).when(informacaoDao).deleteById(any());
        sut.deleteInfoById(1L);
    }

    @Test
    public void giveValidClientId_whenRemoveInfoClient_thenSuccess() {
        sut.deleteInfoById(1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void giveInvalidClientId_whenSaveInfoClient_thenFails() {
        ClienteInformacao info =  FactoryModels.getClientInfo();
        info.setId(null);
        when(clientService.listarPorCodigo(any())).thenThrow(EntityNotFoundException.class);
        sut.saveInfo(1L,info);
    }

    @Test()
    public void giveValidClientId_whenSaveInfoClient_thenSuccess() {
        ClienteInformacao info =  FactoryModels.getClientInfo();
        info.setId(null);
        ClienteInformacao infoSave = sut.saveInfo(1L,info);
        Assert.assertNotNull(infoSave);
        assertThat(infoSave.getCliente().getId(), is(1L));
        Assert.assertNotNull(infoSave.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void giveInvalidClientInfoId_whenUpdateInfoCliente_thenFails() {
        ClienteInformacao info = FactoryModels.getClientInfo();
        info.setDescricao("Outra Descricao");
        when(informacaoDao.findById(any())).thenReturn(Optional.empty());
        sut.atualizarInformacao(1L, 1L,info );
    }

    @Test(expected = EntityNotFoundException.class)
    public void giveInvalidClientId_whenUpdateInfoCliente_thenFails() {
        ClienteInformacao info = FactoryModels.getClientInfo();
        info.setDescricao("Outra Descricao");
        when(clientService.listarPorCodigo(any())).thenThrow(EntityNotFoundException.class);
        sut.atualizarInformacao(1L, 3L,info );
    }

    @Test(expected = BusinessException.class)
    public void giveValidClientInfoIdAndDifferentClientId_whenUpdateInfoCliente_thenFails() {
        Cliente wrongClient = FactoryModels.getCliente();
        wrongClient.setId(2L);
        ClienteInformacao info = FactoryModels.getClientInfo();
        info.setDescricao("Outra Descricao");
        when(clientService.listarPorCodigo(2L)).thenReturn(wrongClient);
        sut.atualizarInformacao(1L, 2L,info );
    }

    @Test
    public void giveValidClientIdAndInfoClient_whenUpdateInfoClient_thenFails() {
        ClienteInformacao info = FactoryModels.getClientInfo();
        info.setDescricao("Outra Descricao");
        when(informacaoDao.save(any())).thenReturn(info);
        ClienteInformacao updateInfo = sut.atualizarInformacao(1L, 1L,info );
        assertThat(updateInfo.getId(), is(info.getId()));
        assertThat(updateInfo.getDescricao(), is("Outra Descricao"));
        assertThat(updateInfo.getCliente().getId(), is(info.getCliente().getId()));
    }

}
