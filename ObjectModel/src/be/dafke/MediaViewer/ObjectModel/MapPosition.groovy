package be.dafke.MediaViewer.ObjectModel

import java.awt.geom.Point2D

class MapPosition {
    Point2D groundPosition

    Double getX(){
        groundPosition.x
    }

    Double getY(){
        groundPosition.y
    }

    MapPosition(Point2D groundPosition) {
        this.groundPosition = groundPosition
    }

    MapPosition(double x, double y) {
        this(new Point2D.Double(x,y))
    }

}
