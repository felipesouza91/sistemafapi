package com.sistemaf.domain.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriarEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse responde;
	
	private Long codigo;
	
	public RecursoCriarEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.responde = response;
		this.codigo = codigo;
	}

	public HttpServletResponse getResponde() {
		return responde;
	}

	public Long getCodigo() {
		return codigo;
	}
}
