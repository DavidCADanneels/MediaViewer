package be.dafke.MediaViewer.ObjectModel.Media

class Picture extends Media {
    Size2D size

    Picture() {

    }

    Size2D getSize() {
        return size
    }

    void setSize(Size2D size) {
        this.size = size
    }
}
