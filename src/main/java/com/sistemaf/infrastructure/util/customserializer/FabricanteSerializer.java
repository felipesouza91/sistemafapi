package com.sistemaf.infrastructure.util.customserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sistemaf.domain.model.Fabricante;
import com.sistemaf.domain.model.Fabricante_;

public class FabricanteSerializer extends JsonSerializer<Fabricante>{

	@Override
	public void serialize(Fabricante value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException {
		gen.writeStartObject();
		gen.writeNumberField(Fabricante_.id.getName(), value.getId());
		gen.writeStringField(Fabricante_.descricao.getName(), value.getDescricao());
		gen.writeEndObject();
		
	}

}
