package com.czmud.camcram.apiModels;

import java.io.IOException;

import com.czmud.camcram.models.BelayDevicesInVan;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class BelayDeviceSerializer extends StdSerializer<BelayDevicesInVan> {

	public BelayDeviceSerializer(Class<BelayDevicesInVan> t) {
		super(t);
	}
	
	@Override
	public void serialize(BelayDevicesInVan belayDevicesInVan, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", belayDevicesInVan.getId());
		
		jsonGenerator.writeNumberField("count", belayDevicesInVan.getCount());
		
		if( belayDevicesInVan.getBelayDevice().isAssistedBreaking()) {
			jsonGenerator.writeStringField("type", "assisted" );
		} else {
			jsonGenerator.writeStringField("type", "standard" );
		}
		
		jsonGenerator.writeObjectField("belayDevice", belayDevicesInVan.getBelayDevice());
		
		
		jsonGenerator.writeEndObject();
		
		
		
	}
	
	
}
