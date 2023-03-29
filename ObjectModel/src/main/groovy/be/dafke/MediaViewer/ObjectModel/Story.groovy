package be.dafke.MediaViewer.ObjectModel

class Story {
    String title = ""
    String shortDescription = ""
    String introText = ""
//    Chapter rootChapter = null
//    HashMap<String, Chapter> chapters = [:]

    Story() {

    }

//    Story(String title, String shortDescription, String introText) {
//        this.title = title
//        this.shortDescription = shortDescription
//        this.introText = introText
////        rootChapter = null
//    }

    String getTitle() {
        return title
    }

//    Chapter getRootChapter() {
//        return rootChapter
//    }
//
//    void setRootChapter(Chapter rootChapter) {
//        this.rootChapter = rootChapter
//    }

    void setTitle(String title) {
        this.title = title
    }

    String getShortDescription() {
        return shortDescription
    }

    void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription
    }

    String getIntroText() {
        return introText
    }

    void setIntroText(String introText) {
        this.introText = introText
    }
}
