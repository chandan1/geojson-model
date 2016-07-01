package com.chandan.geojson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by chandan on 04/06/16.
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MultiPolygon extends Geometry {

    private final List<List<List<Coordinate>>> coordinates;

    @JsonCreator
    public MultiPolygon(@JsonProperty("coordinates") List<List<List<Coordinate>>> coordinates) {
        super(GeoJsonModelType.MULTI_POLYGON);
        this.coordinates = coordinates;
    }
}
