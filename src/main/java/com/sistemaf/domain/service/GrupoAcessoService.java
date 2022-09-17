package com.sistemaf.domain.service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.model.Permissao;
import com.sistemaf.domain.projection.ResumoGrupoAcesso;
import com.sistemaf.domain.repository.security.PermissaoRepository;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GrupoAcessoService {
	
	@Autowired
	private GrupoAcessoRepository grupoAcessoRepository;

	@Autowired
	private PermissaoRepository permissionRepository;
	
	public List<GrupoAcesso> filtrar() {
		return grupoAcessoRepository.findAll();
	}
	
	public List<ResumoGrupoAcesso> resumo() {
		return grupoAcessoRepository.listarResumo();
	}
	
	public GrupoAcesso buscarPorCodigo(Long id) {
		return buscarPorCodigoOptional(id);
	}
	
	public GrupoAcesso salvar(GrupoAcesso grupoAcesso) {
		grupoAcesso.getPermissoes().stream().forEach(
			permission -> this.permissionRepository.findById(permission.getId())
				.orElseThrow( () -> new BusinessException("A Permissão não foi valida")));
		//TODO: Change Permissoes type for Set<> bacause they type not accept duplicated values
		for(int i =0 ; i < this.getPermisaoBasica().size(); i++ ) {
			if (!grupoAcesso.getPermissoes().contains(this.getPermisaoBasica().get(i)) ) {
				grupoAcesso.getPermissoes().add(this.getPermisaoBasica().get(i));
			}
		}
		return grupoAcessoRepository.save(grupoAcesso);
	}
	
	public GrupoAcesso atualizar(Long id, GrupoAcesso grupoAcesso) {
		GrupoAcesso grupoAcessoBuscado= buscarPorCodigoOptional(id);
		BeanUtils.copyProperties(grupoAcesso, grupoAcessoBuscado, "id");
		return grupoAcessoRepository.save(grupoAcessoBuscado);
	}
	
	public void remover(Long codigo) {
		this.buscarPorCodigoOptional(codigo);
		grupoAcessoRepository.deleteById(codigo);
	}
	
	private GrupoAcesso buscarPorCodigoOptional(Long id) {
		Optional<GrupoAcesso> accessGroupOptional = grupoAcessoRepository.findById(id);
		
		return accessGroupOptional.orElseThrow(()-> new EntityNotFoundException("O Grupo de acesso solicitado não existe"));
	}
	
	private List<Permissao> getPermisaoBasica() {
		int[] a = { 6,9,12,18,39,42 };
		ArrayList<Permissao> permissoes = new ArrayList<>();
		for (int i = 0 ; i < a.length; i++) {
			Permissao permisao = new Permissao();
			permisao.setId(Long.valueOf(a[i]));
			permissoes.add(permisao);
		}
		return permissoes;
	}
	
}
