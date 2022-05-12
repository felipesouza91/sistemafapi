package com.sistemaf.infrastructure.util.customserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sistemaf.domain.model.GrupoAcesso;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.domain.model.Usuario_;

public class UsuarioSerializer  extends JsonSerializer<Usuario>{

	@Override
	public void serialize(Usuario value, JsonGenerator jsonGen, SerializerProvider serializers)
				throws IOException {
		 jsonGen.writeStartObject();
		 jsonGen.writeNumberField("id", value.getId());
	     jsonGen.writeStringField("nome", value.getNome());
	     jsonGen.writeObjectFieldStart(Usuario_.grupoAcesso.getName());
	     jsonGen.writeObjectField("id",  this.getValue(value.getGrupoAcesso()));
	     jsonGen.writeEndObject();
	     jsonGen.writeEndObject();
	}

	private String getValue(GrupoAcesso obj) {
		return obj == null ? "null" : obj.getId().toString();
	}
}
