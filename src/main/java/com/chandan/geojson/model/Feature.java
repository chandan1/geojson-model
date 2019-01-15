package com.chandan.geojson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.util.Map;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Feature<T extends Geometry> extends GeoJson {

	private final long id;
	private final T geometry;
	private final Map<String, Object> properties;

	@JsonCreator
	public Feature(@JsonProperty("id") long id,
				   @JsonProperty("geometry") T geometry,
				   @JsonProperty("properties") Map<String, Object> properties,
				   @JsonProperty("bbox") BoundingBox bbox) {
		super(bbox);
		this.id = id;
		this.geometry = geometry;
		this.properties = properties;
	}
}
