package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.People.Author

class Book extends Media {
    String title
    String author
    int nrOfPages
    int boxNr

    Book() {
    }

    String getTitle() {
        return title
    }

    void setTitle(String title) {
        this.title = title
    }

    String getAuthor() {
        return author
    }

    void setAuthor(String author) {
        this.author = author
    }

    int getNrOfPages() {
        return nrOfPages
    }

    void setNrOfPages(int nrOfPages) {
        this.nrOfPages = nrOfPages
    }

    int getBoxNr() {
        return boxNr
    }

    void setBoxNr(int boxNr) {
        this.boxNr = boxNr
    }
}
