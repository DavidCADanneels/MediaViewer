package be.dafke.MediaViewer.ObjectModel

class Chapter{
    String prefix
    String description
    String path
//    List<Picture> pictures = []
//    HashMap<String, Chapter> subChapters = []

    Chapter(String prefix, String description, String path) {
        this.prefix = prefix
        this.description = description
        this.path = path
    }

//    List<Picture> getPictures() {
//        return pictures
//    }

//    List<Picture> getPictures(boolean recursive) {
//        if(recursive){
//            // TODO
//        } else {
//            pictures
//        }
//    }

//    HashMap<String, Chapter> getSubChapters() {
//        return subChapters
//    }
//
//    void addSubChapter(Chapter chapter){
//        subChapters.put(chapter.prefix, chapter)
//    }
}