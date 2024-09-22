package be.dafke.MediaViewer.ObjectModel.Media

class VHS extends Media {
    String title
    String author
    int boxNr

    VHS() {
    }

    String getTitle() {
        return title
    }

    void setTitle(String titel) {
        this.title = titel
    }

    String getAuthor() {
        return author
    }

    void setAuthor(String author) {
        this.author = author
    }

    int getBoxNr() {
        return boxNr
    }

    void setBoxNr(int boxNr) {
        this.boxNr = boxNr
    }
}
