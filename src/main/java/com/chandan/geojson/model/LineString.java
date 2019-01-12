package com.chandan.geojson.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LineString extends Geometry {

	private final List<Coordinate> coordinates;
	
	@JsonCreator
	public LineString(@JsonProperty("coordinates") List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}
}
