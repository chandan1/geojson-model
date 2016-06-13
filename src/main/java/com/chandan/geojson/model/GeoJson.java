package com.chandan.geojson.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by chandan on 13/06/16.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@EqualsAndHashCode
@ToString
public abstract class GeoJson {
    private GeoJsonModelType type;
}
