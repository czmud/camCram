package com.czmud.camcram.apiModels;

import java.io.IOException;
import java.util.HashMap;

import com.czmud.camcram.models.Cam;
import com.czmud.camcram.models.Miscellaneous;
import com.czmud.camcram.models.ProtectionsInVan;
import com.czmud.camcram.models.Stopper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ProtectionSerializer extends StdSerializer<ProtectionsInVan> {

	
	public ProtectionSerializer(Class<ProtectionsInVan> t) {
		super(t);
	}
	
	@Override
	public void serialize(ProtectionsInVan protectionsInVan, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException{
		
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", protectionsInVan.getId());
		
		jsonGenerator.writeNumberField("count", protectionsInVan.getCount());
		
		if( protectionsInVan.getProtection() instanceof Cam ) {
			jsonGenerator.writeStringField("type", "cam" );
		} else if( protectionsInVan.getProtection() instanceof Stopper ) {
			jsonGenerator.writeStringField("type", "stopper" );
		} else if( protectionsInVan.getProtection() instanceof Miscellaneous ) {
			jsonGenerator.writeStringField("type", "misc" );
		} else {
			jsonGenerator.writeStringField("type", "" );
		}
		
		jsonGenerator.writeObjectField("protection", protectionsInVan.getProtection());
		
		HashMap<String, Double> range = new HashMap<String, Double>();
		range.put("min", protectionsInVan.getProtection().getSafeMinRange());
		range.put("max", protectionsInVan.getProtection().getSafeMaxRange());
		range.put("nominal", protectionsInVan.getProtection().getNominalRange());
		
		jsonGenerator.writeObjectField("range", range);
		
		
		jsonGenerator.writeEndObject();
		
	}
	
}
