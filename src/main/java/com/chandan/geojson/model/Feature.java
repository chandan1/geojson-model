package com.chandan.geojson.model;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
					switch (type) {
					case POINT:
						Point point = objectCodec.treeToValue(node.get("geometry"), Point.class);
						if (node.get("properties") != null && !node.get("properties").isNull()) {
							Map<String, Object> nodeProperties = objectCodec.treeToValue(node.get("properties"),
									Map.class);
							feature = new Feature<Point>(id, point, nodeProperties, null);
						} else {
							feature = new Feature<Point>(id, point, null, null);
						}
						break;
					case LINESTRING:
						LineString lineString = objectCodec.treeToValue(node.get("geometry"), LineString.class);
						if (node.get("properties") != null && !node.get("properties").isNull()) {
							Map<String, Object> wayProperties = objectCodec.treeToValue(node.get("properties"),
									Map.class);
							feature = new Feature<LineString>(id, lineString, wayProperties, null);
						} else {
							feature = new Feature<LineString>(id, lineString, null, null);
						}
						break;
					case POLYGON:
						Polygon polygon = objectCodec.treeToValue(node.get("geometry"), Polygon.class);
						BoundingBox bbox = null;
						if (node.get("bbox") != null && !node.get("bbox").isNull()) {
							bbox = objectCodec.treeToValue(node.get("bbox"), BoundingBox.class);
						}
						if (node.get("properties") != null && !node.get("properties").isNull()) {
							Map<String, Object> polygonProperties = objectCodec.treeToValue(node.get("properties"),
									Map.class);
							feature = new Feature<Polygon>(id, polygon, polygonProperties, bbox);
						} else {
							feature = new Feature<Polygon>(id, polygon, null, bbox);
						}
						break;
					case MULTI_POLYGON:
						MultiPolygon multiPolygon = objectCodec.treeToValue(node.get("geometry"), MultiPolygon.class);
						if (node.get("properties") != null && !node.get("properties").isNull()) {
							Map<String, Object> multiPolygonProperties = objectCodec.treeToValue(node.get("properties"),
									Map.class);
							feature = new Feature<MultiPolygon>(id, multiPolygon, multiPolygonProperties, null);
						} else {
							feature = new Feature<MultiPolygon>(id, multiPolygon, null, null);
						}
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
