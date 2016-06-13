package com.chandan.geojson.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A super class for geometries in GeoJson.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class Geometry extends GeoJson {

	public Geometry(GeoJsonModelType type) {
		super(type);
	}
}
