package be.dafke.MediaViewer.ObjectModel

class Catalog {
    String pathPrefix = null

    HashMap<String, Picture> pictures = [:]
    HashMap<String, Chapter> chapters = [:]

    Catalog(String pathPrefix) {
        this.pathPrefix = pathPrefix
    }

    void addChapter(Chapter chapter){
        chapters.put(chapter.prefix, chapter)
    }

    void addPicture(Picture picture){
        pictures.put(picture.index, picture)
    }
}
