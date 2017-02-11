package com.chandan.geojson.model;


import java.util.*;

import com.fasterxml.jackson.core.JsonFactory;
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
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("startNodeId", 2);
		properties.put("endNodId", 3);
		properties.put("building", "appartment");
		properties.put("highway", "trunk");
		properties.put("name", "DSR Cosmos");
		Feature<LineString> ser = new Feature<LineString>(1, lineString, properties, null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<LineString> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<LineString>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeaturePointSerde() throws Exception {
		Point point = new Point(new Coordinate(77.87, 12.78));
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("name", "way");
		properties.put("amenity", "restaurant");
		Feature<Point> ser = new Feature<Point>(1,point, properties, null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<Point> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<Point>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeaturePointWithoutPropertiesSerde() throws Exception {
		Feature<Point> ser = new Feature<Point>(1, new Point(new Coordinate(77.87, 12.78)), null, null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<Point> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<Point>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeatureLinestringWithoutPropertiesSerde() throws Exception {
		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2, 2.3), new Coordinate(2.3, 2.5)));
		Feature<LineString> ser = new Feature<LineString>(1, lineString, null, null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<LineString> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<LineString>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeatureCollectionWithLineStringPointSerdeWithProperties() throws Exception {

		Point point = new Point(new Coordinate(77.87, 12.78));
		Map<String, Object> nodeProperties = new HashMap<String, Object>();
		nodeProperties.put("name", "way");
		nodeProperties.put("amenity", "restaurant");

		Feature<Point> serPoint = new Feature<Point>(Long.MAX_VALUE, point, nodeProperties, null);

		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2, 2.3), new Coordinate(2.3, 2.5)));
		Map<String, Object> wayProperties = new HashMap<String, Object>();
		wayProperties.put("startNodeId", 2);
		wayProperties.put("endNodeId", 3);
		wayProperties.put("building", "appartment");
		wayProperties.put("highway", "trunk");
		wayProperties.put("name", "way");

		Feature<LineString> serLinestring = new Feature<LineString>(1, lineString, wayProperties, null);

		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();
		features.add(serPoint);
		features.add(serLinestring);

		FeatureCollection ser = new FeatureCollection(features);
		String json = new ObjectMapper().writeValueAsString(ser);
		FeatureCollection de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<FeatureCollection>() {});
		Assert.assertEquals(GeoJsonModelType.FEATURE_COLLECTION, de.getType());
		Assert.assertEquals(serPoint, de.getFeatures().get(0));
		Assert.assertEquals(serLinestring, de.getFeatures().get(1));
	}

	@Test
	public void testFeatureCollectionWithLineStringPointSerdeWithoutProperties() throws Exception {

		Point point = new Point(new Coordinate(77.87, 12.78));
		Feature<Point> serPoint = new Feature<Point>(1, point, null, null);

		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2, 2.3), new Coordinate(2.3, 2.5)));
		Feature<LineString> serLinestring = new Feature<LineString>(1, lineString, null, null);

		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();
		features.add(serPoint);
		features.add(serLinestring);

		FeatureCollection ser = new FeatureCollection(features);
		String json = new ObjectMapper().writeValueAsString(ser);
		FeatureCollection de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<FeatureCollection>() {});
		Assert.assertEquals(GeoJsonModelType.FEATURE_COLLECTION, de.getType());
		Assert.assertEquals(serPoint, de.getFeatures().get(0));
		Assert.assertEquals(serLinestring, de.getFeatures().get(1));
	}

	@Test
	public void testFeatureCollectionWithPolygonSerdeWithProperties() throws Exception {

		List<List<Coordinate>> coordinates = Arrays.asList(Arrays.asList(
				new Coordinate(77.87, 12.78),
				new Coordinate(77.20, 12.20),
				new Coordinate(77.80, 12.50),
				new Coordinate(77.87, 12.78)));
		Polygon polygon = new Polygon(coordinates);
		Map<String, Object> polygonProperties = new HashMap<String, Object>();
		polygonProperties.put("name", "name");
		polygonProperties.put("building", "appartments");
		Feature<Polygon> polygonFeature = new Feature<Polygon>(1, polygon, polygonProperties, null);
		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();
		features.add(polygonFeature);

		FeatureCollection ser = new FeatureCollection(features);

		String json = new ObjectMapper().writeValueAsString(ser);
		FeatureCollection de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<FeatureCollection>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeatureCollectionWithPolygonSerdeWithoutProperties() throws Exception {

		List<List<Coordinate>> coordinates = Arrays.asList(Arrays.asList(
				new Coordinate(77.87, 12.78),
				new Coordinate(77.20, 12.20),
				new Coordinate(77.80, 12.50),
				new Coordinate(77.87, 12.78)));
		Polygon polygon = new Polygon(coordinates);
		Feature<Polygon> polygonFeature = new Feature<Polygon>(1, polygon, null, null);
		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();
		features.add(polygonFeature);

		FeatureCollection ser = new FeatureCollection(features);

		String json = new ObjectMapper().writeValueAsString(ser);
		FeatureCollection de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<FeatureCollection>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeatureCollectionWithMultiPolygonWithProperties() throws Exception {

		List<List<List<Coordinate>>> coordinates = Arrays.asList(
				Arrays.asList(Arrays.asList(
						new Coordinate(77.87, 12.78),
						new Coordinate(77.20, 12.20),
						new Coordinate(77.80, 12.50),
						new Coordinate(77.87, 12.78)))
		);

		MultiPolygon multiPolygon = new MultiPolygon(coordinates);

		Map<String, Object> multiPolygonProperties = new HashMap<String, Object>();
		multiPolygonProperties.put("name", "name");
		multiPolygonProperties.put("building", "appartments1");

		Feature<MultiPolygon> multiPolygonFeature = new Feature<MultiPolygon>(1, multiPolygon, multiPolygonProperties, null);
		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();
		features.add(multiPolygonFeature);

		FeatureCollection ser = new FeatureCollection(features);

		String json = new ObjectMapper().writeValueAsString(ser);
		FeatureCollection de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<FeatureCollection>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeatureCollectionWithMultiPolygonWithoutProperties() throws Exception {

		List<List<List<Coordinate>>> coordinates = Arrays.asList(
				Arrays.asList(Arrays.asList(
						new Coordinate(77.87, 12.78),
						new Coordinate(77.20, 12.20),
						new Coordinate(77.80, 12.50),
						new Coordinate(77.87, 12.78)))
		);

		MultiPolygon multiPolygon = new MultiPolygon(coordinates);
		Feature<MultiPolygon> multiPolygonFeature = new Feature<MultiPolygon>(1, multiPolygon, null, null);
		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();
		features.add(multiPolygonFeature);

		FeatureCollection ser = new FeatureCollection(features);

		String json = new ObjectMapper().writeValueAsString(ser);
		FeatureCollection de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<FeatureCollection>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeaturePolygonWithPolygon() throws Exception {
		List<List<Coordinate>> coordinates =
				Arrays.asList(Arrays.asList(
						new Coordinate(-180.0, -90.0),
						new Coordinate(180.0, -90.0),
						new Coordinate(180.0, 90.0),
						new Coordinate(-180.0f, 90.0),
						new Coordinate(-180.0, -90.0)));
		Polygon polygon = new Polygon(coordinates);
		BoundingBox boundingBox = new BoundingBox(-180.0, -90.0, 180.0, 90.0);
		Feature<Polygon> ser = new Feature<Polygon>(0, polygon, null, boundingBox);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<Polygon> de = new ObjectMapper().readValue(json, new TypeReference<Feature<Polygon>>() {});
	}

	@Test
	public void testFeaturePolygonWithLandPolygon() throws Exception {
		String json = "{\n" +
				"  \"geometry\": {\n" +
				"    \"coordinates\": [\n" +
				"      [\n" +
				"        [\n" +
				"          78.80656416249998,\n" +
				"          12.182053918749999\n" +
				"        ],\n" +
				"        [\n" +
				"          76.34827762499998,\n" +
				"          12.182053918749999\n" +
				"        ],\n" +
				"        [\n" +
				"          76.34827762499998,\n" +
				"          16.287100937499996\n" +
				"        ],\n" +
				"        [\n" +
				"          78.80656416249998,\n" +
				"          16.287100937499996\n" +
				"        ],\n" +
				"        [\n" +
				"          78.80656416249998,\n" +
				"          12.182053918749999\n" +
				"        ]\n" +
				"      ]\n" +
				"    ],\n" +
				"    \"type\": \"Polygon\"\n" +
				"  },\n" +
				"  \"properties\": {\n" +
				"    \"FID\": 483450\n" +
				"  },\n" +
				"  \"type\": \"Feature\"\n" +
				"}";
		Feature<Polygon> landPolygon = new ObjectMapper().readValue(json, new TypeReference<Feature<Polygon>>() {});
		Assert.assertEquals(483450, landPolygon.getId());
	}
}
