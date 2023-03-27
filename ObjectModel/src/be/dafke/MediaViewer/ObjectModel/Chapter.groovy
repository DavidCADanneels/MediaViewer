package be.dafke.MediaViewer.ObjectModel

class Chapter{
    String prefix
    String description
    String path
    final String story
//    List<Picture> pictures = []
//    HashMap<String, Chapter> subChapters = []

    Chapter(Story story, String prefix, String description, String path) {
        this.story = story
        this.prefix = prefix
        this.description = description
        this.path = path
    }

    String getStory() {
        return story
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