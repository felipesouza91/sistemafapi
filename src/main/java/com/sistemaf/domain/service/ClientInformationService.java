package com.sistemaf.domain.service;

import java.util.Optional;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.InformacaoFilter;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.ClienteInformacao;
import com.sistemaf.domain.repository.cliente.ClienteInformacaoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientInformationService {

  @Autowired
  private ClienteInformacaoRepository informacaoDao;

  @Autowired
  private ClienteService clientService;

  public ClienteInformacao getInformacaoById(Long idInfo) {
		return this.getInformacaoOptional(idInfo);
	}

	public Page<ClienteInformacao> filtrarAtendimento(Long clientId, InformacaoFilter filter, Pageable pageable) {
  	if(clientId == null ){
  		throw new BusinessException("O Id do cliente é obrigatorio");
		}
  	this.clientService.listarPorCodigo(clientId);
		return informacaoDao.filtrar(clientId, filter, pageable);
	}

	public ClienteInformacao atualizarInformacao(Long codigo, Long idCliente, ClienteInformacao info) {
  	Cliente client = this.clientService.listarPorCodigo(idCliente);
		ClienteInformacao infoSalva = this.getInformacaoById(codigo);
		if(!client.getId().equals(infoSalva.getCliente().getId())) {
			throw  new BusinessException("Erro ao realizar atualização!");
		}
		BeanUtils.copyProperties(info, infoSalva, "id", "cliente","createdBy", "creationDate" );
		return this.informacaoDao.save(infoSalva);
	}
	public void deleteInfoById(Long codigo) {
  	this.getInformacaoOptional(codigo);
		informacaoDao.deleteById(codigo);
	}
		
	public ClienteInformacao saveInfo(Long idCliente, ClienteInformacao info) {
		Cliente cliente = clientService.listarPorCodigo(idCliente);
		info.setCliente(cliente);
		return this.informacaoDao.save(info);
	}

	private ClienteInformacao getInformacaoOptional(Long codigo) {
		Optional<ClienteInformacao> clientInfoOptional = informacaoDao.findById(codigo);
    return clientInfoOptional.orElseThrow(()->new EntityNotFoundException("Informação não foi encontrada"));
  };
}
  
