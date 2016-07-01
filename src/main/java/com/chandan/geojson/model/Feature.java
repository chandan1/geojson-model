package com.chandan.geojson.model;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
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
public class Feature<T extends Geometry> extends GeoJson {

	private final String id;
	private final T geometry;
	private final Map<String, Object> properties;

	@JsonCreator
	public Feature(@JsonProperty("id") String id,
				   @JsonProperty("geometry") T geometry,
				   @JsonProperty("properties") Map<String, Object> properties) {
		super(GeoJsonModelType.FEATURE);
		this.id = id;
		this.geometry = geometry;
		this.properties = properties;
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
					String id = objectCodec.treeToValue(node.get("id"), String.class);
					switch (type) {
					case POINT:
						Point point = objectCodec.treeToValue(node.get("geometry"), Point.class);
						if (node.get("properties") != null && !node.get("properties").isNull()) {
							Map<String, Object> nodeProperties = objectCodec.treeToValue(node.get("properties"),
									Map.class);
							feature = new Feature<Point>(id, point, nodeProperties);
						} else {
							feature = new Feature<Point>(id, point, null);
						}
						break;
					case LINESTRING:
						LineString lineString = objectCodec.treeToValue(node.get("geometry"), LineString.class);
						if (node.get("properties") != null && !node.get("properties").isNull()) {
							Map<String, Object> wayProperties = objectCodec.treeToValue(node.get("properties"),
									Map.class);
							feature = new Feature<LineString>(id, lineString, wayProperties);
						} else {
							feature = new Feature<LineString>(id, lineString, null);
						}
						break;
					case POLYGON:
						Polygon polygon = objectCodec.treeToValue(node.get("geometry"), Polygon.class);
						if (node.get("properties") != null && !node.get("properties").isNull()) {
							Map<String, Object> polygonProperties = objectCodec.treeToValue(node.get("properties"),
									Map.class);
							feature = new Feature<Polygon>(id, polygon, polygonProperties);
						} else {
							feature = new Feature<Polygon>(id, polygon, null);
						}
						break;
					case MULTI_POLYGON:
						MultiPolygon multiPolygon = objectCodec.treeToValue(node.get("geometry"), MultiPolygon.class);
						if (node.get("properties") != null && !node.get("properties").isNull()) {
							Map<String, Object> multiPolygonProperties = objectCodec.treeToValue(node.get("properties"),
									Map.class);
							feature = new Feature<MultiPolygon>(id, multiPolygon, multiPolygonProperties);
						} else {
							feature = new Feature<MultiPolygon>(id, multiPolygon, null);
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
