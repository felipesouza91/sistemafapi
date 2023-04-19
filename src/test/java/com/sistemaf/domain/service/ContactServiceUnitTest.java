package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Contato;
import com.sistemaf.domain.repository.contato.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceUnitTest {

    @Mock
    private ContatoRepository contactRepository;

    @InjectMocks
    private ContactService sut;




    @Test()
    public void giveInvalidContactId_whenFindContactById_throwError() {
        when(contactRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () ->  sut.findById(1L));
        assertTrue(exception instanceof EntityNotFoundException);
        assertEquals("Contact not exists!", exception.getMessage());
    }

    @Test()
    public void giveValidContactId_whenFindContactById_success()  {
        when(contactRepository.findById(any())).thenReturn(Optional.of(getMockContact()));
        var result = sut.findById(1L);
        assertNotNull(result);
        assertThat(result.getId(), is(1L));
    }

    @Test()
    public void giveAExistsContactName_whenSave_error() {
        Contato inputContact = new Contato();
        inputContact.setCelular("219999999");
        inputContact.setContraSenha("any password");
        inputContact.setNome("Any name");
        when(contactRepository.findByNome(any())).thenReturn(Optional.of(getMockContact()));
        Exception exception = assertThrows(BusinessException.class, () ->  sut.save(inputContact));
        assertTrue(exception instanceof BusinessException);
        assertEquals("Esse contato já esta cadastrado", exception.getMessage());
    }

    @Test()
    public void giveAExistsPhoneNumber_whenSave_error() {
        Contato inputContact = new Contato();
        inputContact.setCelular("219999999");
        inputContact.setContraSenha("any password");
        inputContact.setNome("Any name");
        when(contactRepository.findByTelefone(any())).thenReturn(Optional.of(getMockContact()));
        Exception exception = assertThrows(BusinessException.class, () ->  sut.save(inputContact));
        assertTrue(exception instanceof BusinessException);
        assertEquals("Esse contato já esta cadastrado", exception.getMessage());

    }

    @Test()
    public void giveAValidContact_whenSave_success() {
        when(this.contactRepository.findByNome(any())).thenReturn(Optional.empty());
        when(this.contactRepository.findByTelefone(any())).thenReturn(Optional.empty());
        when(this.contactRepository.save(any())).thenReturn(getMockContact());
        Contato inputContact = new Contato();
        inputContact.setCelular("219999999");
        inputContact.setContraSenha("any password");
        inputContact.setNome("Any name");
        Contato result = sut.save(inputContact);
        assertThat(result.getId(), notNullValue());
        assertThat(result.getNome(), is("Any name"));
    }

    private Contato getMockContact() {
        Contato contact = new Contato();
        contact.setId(1L);
        contact.setCelular("219999999");
        contact.setContraSenha("any password");
        contact.setNome("Any name");
        return contact;
    }
}
