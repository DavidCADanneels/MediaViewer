package be.dafke.MediaViewer.ObjectModel

import com.grum.geocalc.Coordinate
import com.grum.geocalc.DegreeCoordinate
import com.grum.geocalc.EarthCalc

import java.awt.Point
import java.awt.geom.Point2D
import org.geotools.referencing.GeodeticCalculator

class GlobalPosition {
    double latitude, longitude
//    Integer height

    GlobalPosition(double latitude, double longitude) {
        this.latitude = latitude
        this.longitude = longitude
//        this(Coordinate.fromDegrees(latitudeX),Coordinate.fromDegrees(longitudeY))
    }

//    GlobalPosition(Coordinate latitude, Coordinate longitude) {
//        this(latitude,longitude,0)
//    }
//
//    GlobalPosition(Coordinate latitude, Coordinate longitude, Integer height) {
//        this.latitude = latitude
//        this.longitude = longitude
//        this.height = height
//    }

    double calculateDistance(GlobalPosition otherPosition){
        com.grum.geocalc.Point origin = new com.grum.geocalc.Point(new DegreeCoordinate(latitude), new DegreeCoordinate(longitude))
        com.grum.geocalc.Point destination = new com.grum.geocalc.Point(otherPosition.latitude, otherPosition.longitude)
        EarthCalc.vincenty.distance(origin,destination)
    }

    double calculateBearing(GlobalPosition otherPosition){
        com.grum.geocalc.Point origin = new com.grum.geocalc.Point(new DegreeCoordinate(latitude), new DegreeCoordinate(longitude))
        com.grum.geocalc.Point destination = new com.grum.geocalc.Point(otherPosition.latitude, otherPosition.longitude)
        EarthCalc.vincenty.bearing(origin,destination)
    }

}
