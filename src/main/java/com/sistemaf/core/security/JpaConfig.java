package com.sistemaf.core.security;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sistemaf.domain.model.Usuario;
import com.sistemaf.infrastructure.util.auditing.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class JpaConfig {
   
	@Bean
    public AuditorAware<Usuario> auditorProvider() {
        return new AuditorAwareImpl();
    } 

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
   
}