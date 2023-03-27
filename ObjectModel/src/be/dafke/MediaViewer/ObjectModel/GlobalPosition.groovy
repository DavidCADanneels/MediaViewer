package be.dafke.MediaViewer.ObjectModel

import com.grum.geocalc.Coordinate

import java.awt.geom.Point2D
import org.geotools.referencing.GeodeticCalculator

class GlobalPosition {
    Coordinate latitude, longitude
    Integer height

    GlobalPosition(double latitudeX, double longitudeY) {
        this(Coordinate.fromDegrees(latitudeX),Coordinate.fromDegrees(longitudeY))
    }

    GlobalPosition(Coordinate latitude, Coordinate longitude) {
        this(latitude,longitude,0)
    }

    GlobalPosition(Coordinate latitude, Coordinate longitude, Integer height) {
        this.latitude = latitude
        this.longitude = longitude
        this.height = height
    }



}
