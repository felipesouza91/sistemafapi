package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Contato;
import com.sistemaf.domain.repository.contato.ContatoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceUnitTest {

    @Mock
    private ContatoRepository contactRepository;

    @InjectMocks
    private ContactService sut;

    @Before
    public void setup() {
        when(contactRepository.findByNome(any()))
                .thenReturn(Optional.empty());
        when(contactRepository.findByTelefone(any()))
            .thenReturn(Optional.empty());
        when(contactRepository.save(any()))
            .thenReturn(getMockContact());
    }


    @Test(expected = EntityNotFoundException.class)
    public void giveInvalidContactId_whenFindContactById_throwError() {
        sut.findById(1L);
    }

    @Test()
    public void giveValidContactId_whenFindContactById_success()  {
        when(contactRepository.findById(any())).thenReturn(Optional.of(getMockContact()));
        var result = sut.findById(1L);
        Assert.assertNotNull(result);
        assertThat(result.getId(), is(1L));
    }

    @Test(expected = BusinessException.class)
    public void giveAExistsContactName_whenSave_error() {
        Contato inputContact = new Contato();
        inputContact.setCelular("219999999");
        inputContact.setContraSenha("any password");
        inputContact.setNome("Any name");
        when(contactRepository.findByNome(any())).thenReturn(Optional.of(getMockContact()));
        sut.save(inputContact);
    }

    @Test(expected = BusinessException.class)
    public void giveAExistsPhoneNumber_whenSave_error() {
        Contato inputContact = new Contato();
        inputContact.setCelular("219999999");
        inputContact.setContraSenha("any password");
        inputContact.setNome("Any name");
        when(contactRepository.findByTelefone(any())).thenReturn(Optional.of(getMockContact()));
        sut.save(inputContact);
    }

    @Test()
    public void giveAValidContact_whenSave_success() {
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
