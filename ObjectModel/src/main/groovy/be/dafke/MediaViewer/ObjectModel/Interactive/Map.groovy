package be.dafke.MediaViewer.ObjectModel.Interactive

import be.dafke.MediaViewer.ObjectModel.Media.Picture

class Map {
    final Picture image
    final boolean twoDimensional
    final boolean northOriented

    Map(Picture image, boolean twoDimensional, boolean northOriented) {
        this.image = image
        this.twoDimensional = twoDimensional
        this.northOriented = northOriented
    }
}
