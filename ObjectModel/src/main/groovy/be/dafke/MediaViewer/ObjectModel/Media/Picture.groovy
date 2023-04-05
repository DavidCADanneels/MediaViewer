package be.dafke.MediaViewer.ObjectModel.Media

class Picture extends Media {
    int width, heigth
    Date creationDate

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

    Date getCreationDate() {
        return creationDate
    }

    void setCreationDate(Date creationDate) {
        this.creationDate = creationDate
    }
}
