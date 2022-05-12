package com.sistemaf.api.resource;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Contact")
public class ContatoResource {

	
}
