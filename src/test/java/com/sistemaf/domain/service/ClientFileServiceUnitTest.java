package com.sistemaf.domain.service;

import com.sistemaf.api.dto.model.UploadFileUrlDTO;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.model.ClientFile;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.definition.FileReference;
import com.sistemaf.domain.repository.cliente.ClientFileRepository;
import com.sistemaf.domain.repository.cliente.ClienteRepository;
import com.sistemaf.util.FactoryModels;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientFileServiceUnitTest {

    @InjectMocks
    private ClientFileService sut;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ClientFileRepository clientFileRepository;
    @Mock
    private FileService fileService;

    @Test
    public void givenInvalidClientID_whenGenerateUrl_thenThrows() {
        FileReference fileReference = Instancio.create(FileReference.class);
        Exception exception = assertThrows(BusinessException.class, () -> sut.generateSignedUrl(1L,fileReference));
        assertEquals("O Cliente não foi encontrado", exception.getMessage());
    }

    @Test
    public void givenValidData_whenGenerateUrl_ensureFileStorageWasCalled() {
        FileReference fileReference = Instancio.create(FileReference.class);
        Cliente cliente = FactoryModels.getCliente();
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clientFileRepository.save(any())).then(arguments -> {
            ClientFile file = new ClientFile();
            file.setId(fileReference.getId());
            file.setClient(cliente);
            return file;
        });
        sut.generateSignedUrl(1L, fileReference);
        verify(fileService, times(1)).generateUploadSignedUrl(fileReference);
    }

    @Test
    public void givenValidData_whenGenerateUrl_thenThrows() {
        FileReference fileReference = Instancio.create(FileReference.class);
        Cliente cliente = FactoryModels.getCliente();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(fileService.generateUploadSignedUrl(any())).thenThrow(new BusinessException("Erro ao gerar URL"));
        Exception exception = assertThrows(BusinessException.class, () -> sut.generateSignedUrl(1L, fileReference));
        assertEquals("Erro ao gerar URL", exception.getMessage());
    }

    @Test
    public void givenValidClientId_whenGenerateUrl_thenSave() throws MalformedURLException {
        FileReference fileReference = Instancio.create(FileReference.class);
        Cliente cliente = FactoryModels.getCliente();
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(fileService.generateUploadSignedUrl(any())).thenReturn(new URL("http://localhost"));
        when(clientFileRepository.save(any())).then(arguments -> {
            ClientFile file = new ClientFile();
            file.setId(fileReference.getId());
            file.setClient(cliente);
            return file;
        });
        UploadFileUrlDTO fileUrlDTO = sut.generateSignedUrl(1L, fileReference);
        assertNotNull(fileUrlDTO);
        assertEquals(fileUrlDTO.getFileReferenceId(), fileReference.getId());
        assertNotNull(fileUrlDTO.getUploadUrl());
    }

}
