package be.dafke.MediaViewer.ObjectModel.Media

class MediaBox {
    int nr
    String name
    List<Book> content
//    Media typeOfContent

    MediaBox() {
        content = []
    }

//    void addMedia(Book book){
//        content.add book
//    }

    int getNr() {
        return nr
    }

    void setNr(int nr) {
        this.nr = nr
    }

    List<Book> getContent() {
        return content
    }

    void setContent(List<Book> content) {
        this.content = content
    }

//    Media getTypeOfContent() {
//        return typeOfContent
//    }
//
//    void setTypeOfContent(Media typeOfContent) {
//        this.typeOfContent = typeOfContent
//    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }
}
