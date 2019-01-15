package com.chandan.geojson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandan on 5/14/16.
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FeatureCollection extends GeoJson {

    private final List<Feature<? extends Geometry>> features;

    @JsonCreator
    public FeatureCollection(@JsonProperty("features") List<Feature<? extends Geometry>> features,
                             @JsonProperty("bbox") BoundingBox bbox) {
        super(bbox);
        this.features = features;
    }
}
