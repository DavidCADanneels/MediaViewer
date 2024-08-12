package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.People.Author

class Book extends Media {
    String title
    List<Author> authors
    int nrOfPages

    Book(String title) {
        this.title = title
    }

    String getTitle() {
        return title
    }

    void setTitle(String title) {
        this.title = title
    }

    List<Author> getAuthors() {
        return authors
    }

    void addAuthor(Author author){
        authors.add author
    }

    void setAuthors(List<Author> authors) {
        this.authors = authors
    }

    int getNrOfPages() {
        return nrOfPages
    }

    void setNrOfPages(int nrOfPages) {
        this.nrOfPages = nrOfPages
    }
}
