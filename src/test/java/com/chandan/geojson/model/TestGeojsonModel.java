package com.chandan.geojson.model;


import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

public class TestGeojsonModel {

	@Test
	public void testCoordinateSerde() throws Exception {
		Coordinate ser = new Coordinate(1.2, 2.3);
		String json = new ObjectMapper().writeValueAsString(ser);
		Coordinate de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Coordinate>() {});
		Assert.assertEquals(ser, de);
	}
	
	@Test
	public void testPointSerde() throws Exception {
		Point ser = new Point(new Coordinate(1.2, 2.3));
		String json = new ObjectMapper().writeValueAsString(ser);
		Point de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Point>() {});
		Assert.assertEquals(ser, de);
	}
	
	@Test
	public void testFeatureLinestringSerde() throws Exception {
		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2, 2.3), new Coordinate(2.3, 2.5)));
		WayProperties wayProperties = new WayProperties();
		wayProperties.setStartNodeId(2);
		wayProperties.setEndNodeId(3);
		wayProperties.setBuilding("appartment");
		wayProperties.setHighWay("trunk");
		wayProperties.setOsmId(1);
		wayProperties.setName("way");
		Feature<LineString> ser = new Feature<LineString>(lineString, wayProperties);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<LineString> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<LineString>>() {});
		Assert.assertEquals(ser, de);
	}
	
	@Test
	public void testFeaturePointSerde() throws Exception {
		Point point = new Point(new Coordinate(77.87, 12.78));
		NodeProperties nodeProperties = new NodeProperties();
		nodeProperties.setOsmId(1);
		nodeProperties.setName("way");
		nodeProperties.setAmenity("restaurant");
		Feature<Point> ser = new Feature<Point>(point, nodeProperties);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<Point> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<Point>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeaturePointWithoutPropertiesSerde() throws Exception {
		Feature<Point> ser = new Feature<>(new Point(new Coordinate(77.87, 12.78)), null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<Point> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<Point>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeatureLinestringWithoutPropertiesSerde() throws Exception {
		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2, 2.3), new Coordinate(2.3, 2.5)));
		Feature<LineString> ser = new Feature<LineString>(lineString, null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<LineString> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<LineString>>() {});
		Assert.assertEquals(ser, de);
	}
}
