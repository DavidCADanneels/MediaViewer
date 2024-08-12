package be.dafke.MediaViewer.ObjectModel.Media

class MediaBox {
    int nr
    String name
    List<Media> content
    Media typeOfContent

    MediaBox(int nr) {
        this.nr = nr
    }

    void addMedia(Media media){
        content.add media
    }

    int getNr() {
        return nr
    }

    void setNr(int nr) {
        this.nr = nr
    }

    List<Media> getContent() {
        return content
    }

    void setContent(List<Media> content) {
        this.content = content
    }

    Media getTypeOfContent() {
        return typeOfContent
    }

    void setTypeOfContent(Media typeOfContent) {
        this.typeOfContent = typeOfContent
    }
}
