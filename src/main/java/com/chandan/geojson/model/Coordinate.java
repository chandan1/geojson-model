package com.chandan.geojson.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.ARRAY)
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Coordinate {
	
	private float lng;
	private float lat;
}
