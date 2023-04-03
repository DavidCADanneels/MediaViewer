package be.dafke.MediaViewer.ObjectModel.Media

class Size2D {
    double width, height

    Size2D(double width, double height) {
        this.width = width
        this.height = height
    }

    @Override
    String toString() {
        return 'test'
//        return "${width} x ${height}"
    }
}
