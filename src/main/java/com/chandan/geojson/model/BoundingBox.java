package com.chandan.geojson.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

/**
 * Created by chandan on 06/07/16.
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({"minLng", "minLat", "maxLng", "maxLat"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BoundingBox {

    private float minLng;
    private float minLat;
    private float maxLng;
    private float maxLat;

    public static void main(String[] args) throws Exception {
        BoundingBox bbox = new BoundingBox(-180.0f, -90.0f, 180.0f, 90.0f);
        String json = new ObjectMapper().writeValueAsString(bbox);
        System.out.println(json);
        BoundingBox bbox1 = new ObjectMapper().readValue(json, BoundingBox.class);
        System.out.println(bbox1);
    }
}
