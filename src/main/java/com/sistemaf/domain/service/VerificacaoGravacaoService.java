package com.sistemaf.domain.service;

import java.util.Optional;

import javax.validation.Valid;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.repository.dvr.DvrRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.VerificarGravacaoFilter;
import com.sistemaf.domain.model.VerificacaoGravacao;
import com.sistemaf.domain.projection.ResumoVerificacaoGravacao;
import com.sistemaf.domain.repository.verificacao.VerificacaoGravacaoRepository;

@Service
public class VerificacaoGravacaoService {

	@Autowired
	private VerificacaoGravacaoRepository verificacaoRepository;

	@Autowired
	private DvrRepository dvrRepository;

	public Page<VerificacaoGravacao> filtrar(VerificarGravacaoFilter filter, Pageable pageable) {
		return verificacaoRepository.filtrar(filter, pageable);
	}

	public VerificacaoGravacao listaPorCodigo(Long codigo) {
		return getVerificaoOptional(codigo);
	}

	public VerificacaoGravacao salvar(VerificacaoGravacao verificacaoGravacao) {
		verifyDvr(verificacaoGravacao.getDvr().getId());
		return verificacaoRepository.save(verificacaoGravacao);
	}

	public VerificacaoGravacao atualizar(Long codigo, @Valid VerificacaoGravacao verificaroGravacao) {
		verifyDvr(verificaroGravacao.getDvr().getId());
		VerificacaoGravacao verificaoSalva = getVerificaoOptional(codigo);
		BeanUtils.copyProperties(verificaroGravacao, verificaoSalva, "id", "dataTeste");
		return salvar(verificaoSalva);
	}

	public void remover(Long codigo) {
		this.getVerificaoOptional(codigo);
		verificacaoRepository.deleteById(codigo);
	}

	public Page<ResumoVerificacaoGravacao> resumir(VerificarGravacaoFilter filter, Pageable pageable) {
		return verificacaoRepository.resumir(filter,pageable);
	}

	private VerificacaoGravacao getVerificaoOptional(Long codigo) {
		Optional<VerificacaoGravacao> checkOptional = verificacaoRepository.findById(codigo);
		return checkOptional.orElseThrow(()-> new EntityNotFoundException("O relato solicitado não existe"));
	}

	private void verifyDvr(Long dvrId ){
		this.dvrRepository.findById(dvrId).orElseThrow(() -> new BusinessException("O DVR não existe"));
	}

}
