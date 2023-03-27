package be.dafke.MediaViewer.ObjectModel

class Story {
    String title = ""
    String shortDescription = ""
    String introText = ""
//    ArrayList<Chapter> chapters = []

    Story(String name) {
        this(name, "","")
    }

    Story(String name, String desc) {
        this(name, desc, "")
    }

    Story(String title, String shortDescription, String introText) {
        this.title = title
        this.shortDescription = shortDescription
        this.introText = introText
    }

    String getTitle() {
        return title
    }

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
