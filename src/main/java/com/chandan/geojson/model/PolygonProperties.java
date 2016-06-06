package com.chandan.geojson.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by chandan on 04/06/16.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PolygonProperties extends CommonProperties<Polygon> {

    /*
    583196 		<tag k="building" v="yes"/>
     715 		<tag k="building" v="apartments"/>
     380 		<tag k="building" v="house"/>
     360 		<tag k="building" v="commercial"/>
     338 		<tag k="building" v="residential"/>
     200 		<tag k="building" v="office"/>
     113 		<tag k="building" v="school"/>
      58 		<tag k="building" v="roof"/>
      41 		<tag k="building" v="college"/>
      30 		<tag k="building" v="warehouse"/>
      30 		<tag k="building" v="hospital"/>
      26 		<tag k="building" v="entrance"/>
      25 		<tag k="building" v="retail"/>
      21 		<tag k="building" v="public"/>
      21 		<tag k="building" v="industrial"/>
      20 		<tag k="building" v="train_station"/>
      20 		<tag k="building" v="manufacture"/>
      20 		<tag k="building" v="construction"/>
      17 		<tag k="building" v="temple"/>
      17 		<tag k="building" v="apartment"/>
      15 		<tag k="building" v="church"/>
      13 		<tag k="building" v="terrace"/>
      12 		<tag k="building" v="hotel"/>
      10 		<tag k="building" v="transportation"/>
       8 		<tag k="building" v="garage"/>
       8 		<tag k="building" v="bank"/>
       7 		<tag k="building" v="university"/>
       7 		<tag k="building" v="company"/>
       6 		<tag k="building" v="shed"/>
       5 		<tag k="building" v="restaurant"/>
       4 		<tag k="building" v="shelter"/>
       4 		<tag k="building" v="service"/>
       4 		<tag k="building" v="no"/>
       3 		<tag k="building" v="kindergarten"/>
       3 		<tag k="building" v="community_centre"/>
       2 		<tag k="building" v="stadium"/>
       2 		<tag k="building" v="mosque"/>
       2 		<tag k="building" v="mall"/>
       2 		<tag k="building" v="community_center"/>
       2 		<tag k="building" v="Sterling Park Apartments"/>
       1 		<tag k="construction" v="building"/>
       1 		<tag k="building" v="yes;apartments"/>
       1 		<tag k="building" v="workshop"/>
       1 		<tag k="building" v="tunnel_passage"/>
       1 		<tag k="building" v="toilets"/>
       1 		<tag k="building" v="supermarket"/>
       1 		<tag k="building" v="software_Company"/>
       1 		<tag k="building" v="shop"/>
       1 		<tag k="building" v="services"/>
       1 		<tag k="building" v="ruins"/>
       1 		<tag k="building" v="publishers"/>
       1 		<tag k="building" v="police_station"/>
       1 		<tag k="building" v="parking"/>
       1 		<tag k="building" v="organisational_office"/>
       1 		<tag k="building" v="library"/>
       1 		<tag k="building" v="landuse=educational"/>
       1 		<tag k="building" v="hut"/>
       1 		<tag k="building" v="hockey_stadium_seating"/>
       1 		<tag k="building" v="hangar"/>
       1 		<tag k="building" v="gym"/>
       1 		<tag k="building" v="gurudwara"/>
       1 		<tag k="building" v="garages"/>
       1 		<tag k="building" v="film_theatre"/>
       1 		<tag k="building" v="farm"/>
       1 		<tag k="building" v="electronics"/>
       1 		<tag k="building" v="dome"/>
       1 		<tag k="building" v="dentist"/>
       1 		<tag k="building" v="cultural_center"/>
       1 		<tag k="building" v="convention hall, marriage hall"/>
       1 		<tag k="building" v="clubhouse"/>
       1 		<tag k="building" v="cinema"/>
       1 		<tag k="building" v="bus_station"/>
       1 		<tag k="building" v="bar"/>
       1 		<tag k="building" v="astrologer"/>
       1 		<tag k="building" v="architect"/>
       1 		<tag k="building" v="apartments;yes"/>
       1 		<tag k="building" v="Thamarai Kannan Road"/>
       1 		<tag k="building" v="Telephone Office"/>
       1 		<tag k="building" v="Retail Shop"/>
       1 		<tag k="building" v="Party_Hall"/>
       1 		<tag k="building" v="Office"/>
       1 		<tag k="building" v="IIITB_New_Hostel"/>
       1 		<tag k="building" v="Godrge apartments"/>
       1 		<tag k="building" v="Delta_Block"/>
       1 		<tag k="building" v="College"/>

     */
    private String building;

    /*
     968 		<tag k="landuse" v="residential"/>   denotes area such as layout
     583 		<tag k="landuse" v="commercial"/>
     274 		<tag k="landuse" v="industrial"/>
     141 		<tag k="landuse" v="grass"/>
     122 		<tag k="landuse" v="construction"/>
      75 		<tag k="landuse" v="recreation_ground"/>
      66 		<tag k="landuse" v="farmland"/>
      61 		<tag k="landuse" v="military"/>
      57 		<tag k="landuse" v="retail"/>
      51 		<tag k="landuse" v="cemetery"/>
      44 		<tag k="landuse" v="reservoir"/>
      33 		<tag k="landuse" v="orchard"/>
      30 		<tag k="landuse" v="farm"/>
      27 		<tag k="landuse" v="quarry"/>
      27 		<tag k="landuse" v="basin"/>
      26 		<tag k="landuse" v="village_green"/>
      24 		<tag k="landuse" v="greenfield"/>
      24 		<tag k="landuse" v="forest"/>
      17 		<tag k="landuse" v="railway"/>
      16 		<tag k="landuse" v="landfill"/>
      16 		<tag k="landuse" v="garages"/>
      15 		<tag k="landuse" v="meadow"/>
       4 		<tag k="landuse" v="plant_nursery"/>
       4 		<tag k="landuse" v="highway"/>
       4 		<tag k="landuse" v="brownfield"/>
       3 		<tag k="landuse" v="farmyard"/>
       2 		<tag k="landuse" v="conservation"/>
       1 		<tag k="landuse" v="vineyard"/>
       1 		<tag k="landuse" v="office"/>
       1 		<tag k="landuse" v="institutional"/>
       1 		<tag k="landuse" v="greenhouse_horticulture"/>
       1 		<tag k="landuse" v="government"/>
       1 		<tag k="landuse" v="church"/>
       1 		<tag k="building" v="landuse=educational"/>
     */
    private String landuse;

    /*
    1514 		<tag k="natural" v="tree"/>
     421 		<tag k="natural" v="water"/>
     108 		<tag k="natural" v="grassland"/>
     106 		<tag k="natural" v="wood"/>
      50 		<tag k="natural" v="wetland"/>
       9 		<tag k="natural" v="scrub"/>
       3 		<tag k="natural" v="peak"/>
       1 		<tag k="natural" v="pond"/>
       1 		<tag k="natural" v="bare_rock"/>
       1 		<tag k="natural" v="Beanstalk"/>
     */
    private String natural;


    /*
    1140 		<tag k="amenity" v="restaurant"/>
     822 		<tag k="amenity" v="place_of_worship"/>
     600 		<tag k="amenity" v="atm"/>
     597 		<tag k="amenity" v="school"/>
     580 		<tag k="amenity" v="bank"/>
     391 		<tag k="amenity" v="hospital"/>
     386 		<tag k="amenity" v="fast_food"/>
     304 		<tag k="amenity" v="college"/>
     296 		<tag k="amenity" v="fuel"/>
     283 		<tag k="amenity" v="pharmacy"/>
     278 		<tag k="amenity" v="parking"/>
     276 		<tag k="amenity" v="cafe"/>
     224 		<tag k="amenity" v="bench"/>
      97 		<tag k="amenity" v="police"/>
      94 		<tag k="amenity" v="post_office"/>
      94 		<tag k="amenity" v="bus_station"/>
      90 		<tag k="amenity" v="toilets"/>
      89 		<tag k="amenity" v="university"/>
      80 		<tag k="amenity" v="clinic"/>
      76 		<tag k="amenity" v="cinema"/>
      76 		<tag k="amenity" v="bar"/>
      68 		<tag k="amenity" v="kindergarten"/>
      66 		<tag k="amenity" v="public_building"/>
      65 		<tag k="amenity" v="swimming_pool"/>
      63 		<tag k="amenity" v="community_centre"/>
      59 		<tag k="amenity" v="pub"/>
      57 		<tag k="amenity" v="library"/>
      56 		<tag k="amenity" v="dentist"/>
      52 		<tag k="amenity" v="waste_basket"/>
      48 		<tag k="amenity" v="theatre"/>
      41 		<tag k="amenity" v="recycling"/>
      41 		<tag k="amenity" v="marketplace"/>
      27 		<tag k="amenity" v="post_box"/>
      24 		<tag k="amenity" v="fountain"/>
      22 		<tag k="amenity" v="bicycle_parking"/>
      17 		<tag k="amenity" v="doctors"/>
      14 		<tag k="amenity" v="taxi"/>
      12 		<tag k="amenity" v="veterinary"/>
      12 		<tag k="amenity" v="telephone"/>
      12 		<tag k="amenity" v="shelter"/>
      10 		<tag k="amenity" v="food_court"/>
      10 		<tag k="amenity" v="fire_station"/>
     */
    private String amenity;


    /*
     645 		<tag k="leisure" v="park"/>
     218 		<tag k="leisure" v="pitch"/>
     121 		<tag k="leisure" v="playground"/>
     111 		<tag k="leisure" v="garden"/>
      91 		<tag k="leisure" v="sports_centre"/>
      57 		<tag k="leisure" v="swimming_pool"/>
      23 		<tag k="leisure" v="common"/>
      21 		<tag k="leisure" v="track"/>
      19 		<tag k="leisure" v="stadium"/>
      13 		<tag k="leisure" v="recreation_ground"/>
     */
    private String leisure;

    /*
      19 		<tag k="water" v="lake"/>
      10 		<tag k="water" v="pond"/>
       7 		<tag k="water" v="drain"/>
       4 		<tag k="type" v="water"/>
       1 		<tag k="water" v="wetlands"/>
       1 		<tag k="water" v="swimming_pool"/>
       1 		<tag k="water" v="reservoir"/>
     */
    private String water;

    /*
     215 		<tag k="waterway" v="drain"/>
      33 		<tag k="waterway" v="stream"/>
      23 		<tag k="waterway" v="river"/>
      11 		<tag k="waterway" v="ditch"/>
       4 		<tag k="waterway" v="canal"/>
       3 		<tag k="waterway" v="weir"/>
       3 		<tag k="waterway" v="dam"/>
       1 		<tag k="type" v="waterway"/>
     */
    private String waterway;

    private String highway;
}
