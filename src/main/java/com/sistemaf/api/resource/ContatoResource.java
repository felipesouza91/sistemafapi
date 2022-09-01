package com.sistemaf.api.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Contact")
public class ContatoResource {

	
}
