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
		LineStringProperties wayProperties = new LineStringProperties();
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
		PointProperties nodeProperties = new PointProperties();
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
		Feature<Point> ser = new Feature<Point>(new Point(new Coordinate(77.87, 12.78)), null);
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

	@Test
	public void testFeatureCollectionWithLineStringPointSerdeWithProperties() throws Exception {

		Point point = new Point(new Coordinate(77.87, 12.78));
		PointProperties nodeProperties = new PointProperties();
		nodeProperties.setOsmId(1);
		nodeProperties.setName("way");
		nodeProperties.setAmenity("restaurant");
		Feature<Point> serPoint = new Feature<Point>(point, nodeProperties);

		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2, 2.3), new Coordinate(2.3, 2.5)));
		LineStringProperties wayProperties = new LineStringProperties();
		wayProperties.setStartNodeId(2);
		wayProperties.setEndNodeId(3);
		wayProperties.setBuilding("appartment");
		wayProperties.setHighWay("trunk");
		wayProperties.setOsmId(1);
		wayProperties.setName("way");
		Feature<LineString> serLinestring = new Feature<LineString>(lineString, wayProperties);

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

		Point point = new Point(new Coordinate(77.87, 12.78));
		Feature<Point> serPoint = new Feature<Point>(point, null);

		LineString lineString = new LineString(Arrays.asList(new Coordinate(1.2, 2.3), new Coordinate(2.3, 2.5)));
		Feature<LineString> serLinestring = new Feature<LineString>(lineString, null);

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
				new Coordinate(77.87, 12.78),
				new Coordinate(77.20, 12.20),
				new Coordinate(77.80, 12.50),
				new Coordinate(77.87, 12.78)));
		Polygon polygon = new Polygon(coordinates);
		PolygonProperties polygonProperties = new PolygonProperties();
		polygonProperties.setName("name");
		polygonProperties.setOsmId(1);
		polygonProperties.setBuilding("appartments");
		Feature<Polygon> polygonFeature = new Feature<Polygon>(polygon, polygonProperties);
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
				new Coordinate(77.87, 12.78),
				new Coordinate(77.20, 12.20),
				new Coordinate(77.80, 12.50),
				new Coordinate(77.87, 12.78)));
		Polygon polygon = new Polygon(coordinates);
		Feature<Polygon> polygonFeature = new Feature<Polygon>(polygon, null);
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
						new Coordinate(77.87, 12.78),
						new Coordinate(77.20, 12.20),
						new Coordinate(77.80, 12.50),
						new Coordinate(77.87, 12.78)))
		);

		MultiPolygon multiPolygon = new MultiPolygon(coordinates);

		MultiPolygonProperties multiPolygonProperties = new MultiPolygonProperties();
		multiPolygonProperties.setOsmId(1);
		multiPolygonProperties.setName("name");
		multiPolygonProperties.setBuilding("appartments1");

		Feature<MultiPolygon> multiPolygonFeature = new Feature<MultiPolygon>(multiPolygon, multiPolygonProperties);
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
						new Coordinate(77.87, 12.78),
						new Coordinate(77.20, 12.20),
						new Coordinate(77.80, 12.50),
						new Coordinate(77.87, 12.78)))
		);

		MultiPolygon multiPolygon = new MultiPolygon(coordinates);
		Feature<MultiPolygon> multiPolygonFeature = new Feature<MultiPolygon>(multiPolygon, null);
		List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();

		FeatureCollection ser = new FeatureCollection();
		ser.addFeature(multiPolygonFeature);

		String json = new ObjectMapper().writeValueAsString(ser);
		System.out.println(json);
		FeatureCollection de = new ObjectMapper().readValue(json.getBytes(), new TypeReference<FeatureCollection>() {});
		Assert.assertEquals(ser, de);
	}
}
