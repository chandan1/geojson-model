# geojson-model

This repository contains Java classes which are representation of GeoJSON entities. For more detail refer [GeoJson Spec](http://geojson.org/geojson-spec.html) .


Entities supporteds :

* Points
* LineString
* Polygon
* MultiPoint
* MultiLineString
* MultiPolygon
* GeometryCollection
* FeatureCollection

Example usage :

```
String json =   "{\n" +
                "  \"type\": \"Feature\",\n" +
                "  \"geometry\": {\n" +
                "    \"type\": \"GeometryCollection\",\n" +
                "    \"geometries\": [{\n" +
                "      \"type\": \"Point\",\n" +
                "      \"coordinates\": [0, 0]\n" +
                "    }, {\n" +
                "      \"type\": \"LineString\",\n" +
                "      \"coordinates\": [[0, 0], [1, 0]]\n" +
                "    }]\n" +
                "  },\n" +
                "  \"properties\": {\n" +
                "    \"name\": \"null island\"\n" +
                "  }\n" +
                "}";
               
GeoJson geoJson = new ObjectMapper().readValue(json, GeoJson.class);
```