package com.sistemaf.domain.service;


import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Contato;
import com.sistemaf.domain.repository.contato.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired()
    private ContatoRepository repository;

    public Contato findById(Long id) {
      return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contact not exists!"));
    }
    
    public Contato save(Contato contato) {
      var existsContactName = this.repository.findByNome(contato.getNome());
      if (existsContactName.isPresent()) {
        throw new BusinessException("Esse contato já esta cadastrado");
      }
      var existsContactTelefone = this.repository.findByTelefone(contato.getTelefone());
      if (existsContactTelefone.isPresent()) {
        throw new BusinessException("Esse contato já esta cadastrado");
      }
      return this.repository.save(contato);
    }

}
