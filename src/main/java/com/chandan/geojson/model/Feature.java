package com.chandan.geojson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
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
@JsonDeserialize(using = Feature.FeatureDeserializer.class)
@Builder
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Feature<T extends Geometry> extends GeoJson {

	private final long id;
	private final T geometry;
	private final Map<String, Object> properties;
	private final BoundingBox bbox;

	@JsonCreator
	public Feature(@JsonProperty("id") long id,
				   @JsonProperty("geometry") T geometry,
				   @JsonProperty("properties") Map<String, Object> properties,
				   @JsonProperty("bbox") BoundingBox bbox) {
		super(GeoJsonModelType.FEATURE);
		this.id = id;
		this.geometry = geometry;
		this.properties = properties;
		this.bbox = bbox;
	}

	public static class FeatureDeserializer extends JsonDeserializer<Feature<Geometry>> {

		@SuppressWarnings("unchecked")
		@Override
		public Feature<Geometry> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException,
				JsonProcessingException {
			Feature<?> feature = null;
			ObjectCodec objectCodec = p.getCodec();
			JsonNode node = objectCodec.readTree(p);
			if (GeoJsonModelType.findByValue(node.get("type").asText()) == GeoJsonModelType.FEATURE) {
				JsonNode geometryJson = node.get("geometry");
				if (geometryJson.isObject()) {
					GeoJsonModelType type = GeoJsonModelType.findByValue(geometryJson.get("type").asText());
					Long id = null;
					if (node.get("id") != null && !node.get("id").isNull()) {
						id = objectCodec.treeToValue(node.get("id"), Long.class);
					}
					Map<String, Object> properties = null;
					if (node.get("properties") != null && !node.get("properties").isNull()) {
						properties = objectCodec.treeToValue(node.get("properties"), Map.class);
						if (properties.get("FID") != null) {
							id = ((Integer) properties.get("FID")).longValue();
						}
					}

					switch (type) {
					case POINT:
						Point point = objectCodec.treeToValue(node.get("geometry"), Point.class);
						feature = new Feature<Point>(id, point, properties, null);
						break;
					case LINESTRING:
						LineString lineString = objectCodec.treeToValue(node.get("geometry"), LineString.class);
						feature = new Feature<LineString>(id, lineString, properties, null);
						break;
					case POLYGON:
						Polygon polygon = objectCodec.treeToValue(node.get("geometry"), Polygon.class);
						BoundingBox bbox = null;
						if (node.get("bbox") != null && !node.get("bbox").isNull()) {
							bbox = objectCodec.treeToValue(node.get("bbox"), BoundingBox.class);
						}
						feature = new Feature<Polygon>(id, polygon, properties, bbox);
						break;
					case MULTI_POLYGON:
						MultiPolygon multiPolygon = objectCodec.treeToValue(node.get("geometry"), MultiPolygon.class);
						feature = new Feature<MultiPolygon>(id, multiPolygon, properties, null);
						break;
					default:
						break;
					}
				}
			}
			return (Feature<Geometry>) feature;
		}
	}
}
