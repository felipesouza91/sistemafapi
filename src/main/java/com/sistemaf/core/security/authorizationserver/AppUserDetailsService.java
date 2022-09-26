package com.sistemaf.core.security.authorizationserver;

import com.sistemaf.domain.model.Usuario;
import com.sistemaf.domain.repository.security.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String apelido) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByApelido(apelido);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretas"));
		if(!usuario.getAtivo()) {
			throw new UsernameNotFoundException("Usuário desabilitado");
		}
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		//TODO: Aletrar //usuario.getGrupoAcesso().getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getId().toString())));
		usuario.getGrupoAcesso().getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getId().toString())));		
		return authorities;
	}
	
}
