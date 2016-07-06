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
		Coordinate ser = new Coordinate(1.2f, 2.3f);
		String json = new ObjectMapper().writeValueAsString(ser);
		Coordinate de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Coordinate>() {});
		Assert.assertEquals(ser, de);
	}
	
	@Test
	public void testPointSerde() throws Exception {
		Point ser = new Point(new Coordinate(1.2f, 2.3f));
		String json = new ObjectMapper().writeValueAsString(ser);
		Point de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Point>() {});
		Assert.assertEquals(ser, de);
	}
	
	@Test
	public void testFeatureLinestringSerde() throws Exception {
		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2f, 2.3f), new Coordinate(2.3f, 2.5f)));
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("startNodeId", 2);
		properties.put("endNodId", 3);
		properties.put("building", "appartment");
		properties.put("highway", "trunk");
		properties.put("name", "DSR Cosmos");
		Feature<LineString> ser = new Feature<LineString>("1", lineString, properties, null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<LineString> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<LineString>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeaturePointSerde() throws Exception {
		Point point = new Point(new Coordinate(77.87f, 12.78f));
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("name", "way");
		properties.put("amenity", "restaurant");
		Feature<Point> ser = new Feature<Point>("1",point, properties, null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<Point> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<Point>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeaturePointWithoutPropertiesSerde() throws Exception {
		Feature<Point> ser = new Feature<Point>("1", new Point(new Coordinate(77.87f, 12.78f)), null, null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<Point> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<Point>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeatureLinestringWithoutPropertiesSerde() throws Exception {
		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2f, 2.3f), new Coordinate(2.3f, 2.5f)));
		Feature<LineString> ser = new Feature<LineString>("1", lineString, null, null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<LineString> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<LineString>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeatureCollectionWithLineStringPointSerdeWithProperties() throws Exception {

		Point point = new Point(new Coordinate(77.87f, 12.78f));
		Map<String, Object> nodeProperties = new HashMap<String, Object>();
		nodeProperties.put("name", "way");
		nodeProperties.put("amenity", "restaurant");

		Feature<Point> serPoint = new Feature<Point>("1", point, nodeProperties, null);

		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2f, 2.3f), new Coordinate(2.3f, 2.5f)));
		Map<String, Object> wayProperties = new HashMap<String, Object>();
		wayProperties.put("startNodeId", 2);
		wayProperties.put("endNodeId", 3);
		wayProperties.put("building", "appartment");
		wayProperties.put("highway", "trunk");
		wayProperties.put("name", "way");

		Feature<LineString> serLinestring = new Feature<LineString>("1", lineString, wayProperties, null);

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

		Point point = new Point(new Coordinate(77.87f, 12.78f));
		Feature<Point> serPoint = new Feature<Point>("1", point, null, null);

		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2f, 2.3f), new Coordinate(2.3f, 2.5f)));
		Feature<LineString> serLinestring = new Feature<LineString>("1", lineString, null, null);

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
				new Coordinate(77.87f, 12.78f),
				new Coordinate(77.20f, 12.20f),
				new Coordinate(77.80f, 12.50f),
				new Coordinate(77.87f, 12.78f)));
		Polygon polygon = new Polygon(coordinates);
		Map<String, Object> polygonProperties = new HashMap<String, Object>();
		polygonProperties.put("name", "name");
		polygonProperties.put("building", "appartments");
		Feature<Polygon> polygonFeature = new Feature<Polygon>("1", polygon, polygonProperties, null);
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
				new Coordinate(77.87f, 12.78f),
				new Coordinate(77.20f, 12.20f),
				new Coordinate(77.80f, 12.50f),
				new Coordinate(77.87f, 12.78f)));
		Polygon polygon = new Polygon(coordinates);
		Feature<Polygon> polygonFeature = new Feature<Polygon>("1", polygon, null, null);
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
						new Coordinate(77.87f, 12.78f),
						new Coordinate(77.20f, 12.20f),
						new Coordinate(77.80f, 12.50f),
						new Coordinate(77.87f, 12.78f)))
		);

		MultiPolygon multiPolygon = new MultiPolygon(coordinates);

		Map<String, Object> multiPolygonProperties = new HashMap<String, Object>();
		multiPolygonProperties.put("name", "name");
		multiPolygonProperties.put("building", "appartments1");

		Feature<MultiPolygon> multiPolygonFeature = new Feature<MultiPolygon>("1", multiPolygon, multiPolygonProperties, null);
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
						new Coordinate(77.87f, 12.78f),
						new Coordinate(77.20f, 12.20f),
						new Coordinate(77.80f, 12.50f),
						new Coordinate(77.87f, 12.78f)))
		);

		MultiPolygon multiPolygon = new MultiPolygon(coordinates);
		Feature<MultiPolygon> multiPolygonFeature = new Feature<MultiPolygon>("1", multiPolygon, null, null);
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
						new Coordinate(-180.0f, -90.0f),
						new Coordinate(180.0f, -90.0f),
						new Coordinate(180.0f, 90.0f),
						new Coordinate(-180.0f, 90.0f),
						new Coordinate(-180.0f, -90.0f)));
		Polygon polygon = new Polygon(coordinates);
		BoundingBox boundingBox = new BoundingBox(-180.0f, -90.0f, 180.0f, 90.0f);
		Feature<Polygon> ser = new Feature<Polygon>(null, polygon, null, boundingBox);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<Polygon> de = new ObjectMapper().readValue(json, new TypeReference<Feature<Polygon>>() {});
	}
}
