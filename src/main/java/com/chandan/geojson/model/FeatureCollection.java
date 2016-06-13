package com.chandan.geojson.model;

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
public class FeatureCollection extends GeoJson  {

    private List<Feature<? extends Geometry>> features = new ArrayList<Feature<? extends Geometry>>();

    public FeatureCollection() {
        super(GeoJsonModelType.FEATURE_COLLECTION);
    }

    public void addFeature(Feature<? extends Geometry> feature) {
        features.add(feature);
    }
}
