package com.chandan.geojson.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by chandan on 04/06/16.
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MultiPolygon extends Geometry {

    private List<List<List<Coordinate>>> coordinates;

    public MultiPolygon() {
        super(GeoJsonModelType.MULTI_POLYGON);
    }

    public MultiPolygon(List<List<List<Coordinate>>> coordinates) {
        super(GeoJsonModelType.MULTI_POLYGON);
        this.coordinates = coordinates;
    }
}
