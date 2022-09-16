package com.czmud.camcram.apiModels;

import java.io.IOException;

import com.czmud.camcram.models.DrawsInVan;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DrawSerializer extends StdSerializer<DrawsInVan> {

	public DrawSerializer(Class<DrawsInVan> t ) {
		super(t);
	}
	
	@Override
	public void serialize(DrawsInVan drawsInVan, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", drawsInVan.getId());
		
		jsonGenerator.writeNumberField("count", drawsInVan.getCount());
		
		if( drawsInVan.getDraw().isQuickDraw()) {
			jsonGenerator.writeStringField("type", "quickdraw" );
		} else {
			jsonGenerator.writeStringField("type", "alpinedraw" );
		}
		
		jsonGenerator.writeObjectField("draw", drawsInVan.getDraw());
		
		
		jsonGenerator.writeEndObject();
		
		
		
	}
	
}
