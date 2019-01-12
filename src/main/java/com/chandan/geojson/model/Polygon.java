package com.chandan.geojson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by chandan on 5/4/16.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Polygon extends Geometry {

    private final List<List<Coordinate>> coordinates;

    @JsonCreator
    public Polygon(@JsonProperty("coordinates") List<List<Coordinate>> coordinates) {
        this.coordinates = coordinates;
    }
}
