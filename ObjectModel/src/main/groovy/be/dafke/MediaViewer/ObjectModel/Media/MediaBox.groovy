package be.dafke.MediaViewer.ObjectModel.Media

class MediaBox {
    int nr
    String name
    List<Book> books
    List<CD> cds
    List<DVD> dvds
    List<VHS> vhss

    MediaBox() {
        books = []
        cds = []
        dvds = []
        vhss = []
    }

    int getNr() {
        return nr
    }

    void setNr(int nr) {
        this.nr = nr
    }

    List<Book> getBooks() {
        return books
    }

    void setBooks(List<Book> content) {
        this.books = content
    }

    List<CD> getCds() {
        return cds
    }

    void setCds(List<CD> cds) {
        this.cds = cds
    }

    List<DVD> getDvds() {
        return dvds
    }

    void setDvds(List<DVD> dvds) {
        this.dvds = dvds
    }

    List<VHS> getVhss() {
        return vhss
    }

    void setVhss(List<VHS> vhss) {
        this.vhss = vhss
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }
}
