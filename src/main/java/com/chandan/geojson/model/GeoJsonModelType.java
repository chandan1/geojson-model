package com.chandan.geojson.model;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE, suppressConstructorProperties = true)
public enum GeoJsonModelType {

	POINT("Point"),LINESTRING("LineString"),POLYGON("Polygon"),FEATURE_COLLECTION("FeatureCollection"), FEATURE("Feature"),
    MULTI_POLYGON("MultiPolygon");
	
	private final String name;

	@JsonValue
	public String getName() {
		return name;
	}
	
	public static GeoJsonModelType findByValue(String name) {
		for (GeoJsonModelType type : values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}
		return null;
	}
}
