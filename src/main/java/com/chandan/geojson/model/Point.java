package com.chandan.geojson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Point extends Geometry {
	
	private final Coordinate coordinates;

	@JsonCreator
	public Point(@JsonProperty("coordinates") Coordinate coordinates) {
		super(GeoJsonModelType.POINT);
		this.coordinates = coordinates;
	}
}
