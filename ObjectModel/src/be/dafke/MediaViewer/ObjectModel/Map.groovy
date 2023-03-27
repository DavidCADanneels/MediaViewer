package be.dafke.MediaViewer.ObjectModel

class Map {
    final Picture image
    final boolean twoDimensional
    final boolean northOriented
    Size2D size

    Map(Picture image, boolean twoDimensional, boolean northOriented) {
        this.image = image
        this.twoDimensional = twoDimensional
        this.northOriented = northOriented
    }

    void setSize(Size2D size) {
        this.size = size
    }
}
