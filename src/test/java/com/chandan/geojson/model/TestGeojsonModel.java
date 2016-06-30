package com.chandan.geojson.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		LineStringProperties wayProperties = new LineStringProperties();
		wayProperties.setStartNodeId(2);
		wayProperties.setEndNodeId(3);
		wayProperties.setBuilding("appartment");
		wayProperties.setHighway("trunk");
		wayProperties.setName("way");
		Feature<LineString> ser = new Feature<LineString>("1", lineString, wayProperties);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<LineString> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<LineString>>() {});
		Assert.assertEquals(ser, de);
	}
	
	@Test
	public void testFeaturePointSerde() throws Exception {
		Point point = new Point(new Coordinate(77.87f, 12.78f));
		PointProperties nodeProperties = new PointProperties();
		nodeProperties.setName("way");
		nodeProperties.setAmenity("restaurant");
		Feature<Point> ser = new Feature<Point>("1",point, nodeProperties);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<Point> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<Point>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeaturePointWithoutPropertiesSerde() throws Exception {
		Feature<Point> ser = new Feature<Point>("1", new Point(new Coordinate(77.87f, 12.78f)), null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<Point> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<Point>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeatureLinestringWithoutPropertiesSerde() throws Exception {
		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2f, 2.3f), new Coordinate(2.3f, 2.5f)));
		Feature<LineString> ser = new Feature<LineString>("1", lineString, null);
		String json = new ObjectMapper().writeValueAsString(ser);
		Feature<LineString> de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<Feature<LineString>>() {});
		Assert.assertEquals(ser, de);
	}

	@Test
	public void testFeatureCollectionWithLineStringPointSerdeWithProperties() throws Exception {

		Point point = new Point(new Coordinate(77.87f, 12.78f));
		PointProperties nodeProperties = new PointProperties();
		nodeProperties.setName("way");
		nodeProperties.setAmenity("restaurant");
		Feature<Point> serPoint = new Feature<Point>("1", point, nodeProperties);

		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2f, 2.3f), new Coordinate(2.3f, 2.5f)));
		LineStringProperties wayProperties = new LineStringProperties();
		wayProperties.setStartNodeId(2);
		wayProperties.setEndNodeId(3);
		wayProperties.setBuilding("appartment");
		wayProperties.setHighway("trunk");
		wayProperties.setName("way");
		Feature<LineString> serLinestring = new Feature<LineString>("1", lineString, wayProperties);

		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();
		features.add(serLinestring);
		features.add(serPoint);

		FeatureCollection ser = new FeatureCollection();
		ser.addFeature(serPoint);
		ser.addFeature(serLinestring);
		String json = new ObjectMapper().writeValueAsString(ser);
		FeatureCollection de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<FeatureCollection>() {});
		Assert.assertEquals(GeoJsonModelType.FEATURE_COLLECTION, de.getType());
		Assert.assertEquals(serPoint, de.getFeatures().get(0));
		Assert.assertEquals(serLinestring, de.getFeatures().get(1));
	}

	@Test
	public void testFeatureCollectionWithLineStringPointSerdeWithoutProperties() throws Exception {

		Point point = new Point(new Coordinate(77.87f, 12.78f));
		Feature<Point> serPoint = new Feature<Point>("1", point, null);

		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2f, 2.3f), new Coordinate(2.3f, 2.5f)));
		Feature<LineString> serLinestring = new Feature<LineString>("1", lineString, null);

		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();
		features.add(serLinestring);
		features.add(serPoint);

		FeatureCollection ser = new FeatureCollection();
		ser.addFeature(serPoint);
		ser.addFeature(serLinestring);
		String json = new ObjectMapper().writeValueAsString(ser);
		System.out.println(json);
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
		PolygonProperties polygonProperties = new PolygonProperties();
		polygonProperties.setName("name");
		polygonProperties.setBuilding("appartments");
		Feature<Polygon> polygonFeature = new Feature<Polygon>("1", polygon, polygonProperties);
		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();
		features.add(polygonFeature);

		FeatureCollection ser = new FeatureCollection();
		ser.addFeature(polygonFeature);

		String json = new ObjectMapper().writeValueAsString(ser);
		System.out.println(json);
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
		Feature<Polygon> polygonFeature = new Feature<Polygon>("1", polygon, null);
		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();
		features.add(polygonFeature);

		FeatureCollection ser = new FeatureCollection();
		ser.addFeature(polygonFeature);

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

		MultiPolygonProperties multiPolygonProperties = new MultiPolygonProperties();
		multiPolygonProperties.setName("name");
		multiPolygonProperties.setBuilding("appartments1");

		Feature<MultiPolygon> multiPolygonFeature = new Feature<MultiPolygon>("1", multiPolygon, multiPolygonProperties);
		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();

		FeatureCollection ser = new FeatureCollection();
		ser.addFeature(multiPolygonFeature);

		String json = new ObjectMapper().writeValueAsString(ser);
		System.out.println(json);
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
		Feature<MultiPolygon> multiPolygonFeature = new Feature<MultiPolygon>("1", multiPolygon, null);
		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();

		FeatureCollection ser = new FeatureCollection();
		ser.addFeature(multiPolygonFeature);

		String json = new ObjectMapper().writeValueAsString(ser);
		System.out.println(json);
		FeatureCollection de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<FeatureCollection>() {});
		Assert.assertEquals(ser, de);
	}
}
