package com.sistemaf.infrastructure.util.customserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.Cliente_;

public class ClienteSerializer extends JsonSerializer<Cliente>{

	@Override
	public void serialize(Cliente value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField(Cliente_.codigoParticao.getName(), value.getCodigoParticao());
		gen.writeStringField(Cliente_.razaoSocial.getName(), value.getRazaoSocial());
		gen.writeStringField(Cliente_.fantazia.getName(), value.getFantazia());
		gen.writeEndObject();
		
	}

}
