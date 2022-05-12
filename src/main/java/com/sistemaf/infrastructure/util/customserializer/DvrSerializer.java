package com.sistemaf.infrastructure.util.customserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sistemaf.domain.model.Dvr;
import com.sistemaf.domain.model.Dvr_;

public class DvrSerializer extends JsonSerializer<Dvr>{

	@Override
	public void serialize(Dvr value, JsonGenerator jsonGen, SerializerProvider serializers)
			throws IOException {
		 jsonGen.writeStartObject();
		 jsonGen.writeNumberField("id", value.getId());
	     jsonGen.writeStringField(Dvr_.fabricante.getName(), value.getFabricante());
	     jsonGen.writeStringField(Dvr_.modeloDvr.getName(), value.getModeloDvr());
	     jsonGen.writeEndObject();
		
		
	}

	
}
