package be.dafke.MediaViewer.ObjectModel.Media

class Picture extends Media {
    int width, heigth

    Picture() {

    }

    int getWidth() {
        return width
    }

    void setWidth(int width) {
        this.width = width
    }

    int getHeigth() {
        return heigth
    }

    void setHeigth(int heigth) {
        this.heigth = heigth
    }
}
