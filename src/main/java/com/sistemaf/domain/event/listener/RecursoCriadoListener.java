package com.sistemaf.domain.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistemaf.domain.event.RecursoCriarEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriarEvent> {

	@Override
	public void onApplicationEvent(RecursoCriarEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponde();
		Long codigo = recursoCriadoEvent.getCodigo();
		
		adicionarHeaderLocation(response, codigo);
	}
	
	private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(codigo).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
