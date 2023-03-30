package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant

class Story {
    String title = ""
    String shortDescription = ""
    String introText = ""
    File dataFile
//    HashMap<String, Chapter> chapters = [:]
    List<Media> media
    List<Participant> participants

    Story() {
        media = []
        participants = []
    }

    List<Participant> getParticipants() {
        return participants
    }

    String getTitle() {
        return title
    }

    File getDataFile() {
        return dataFile
    }

    void setDataFile(File dataFile) {
        this.dataFile = dataFile
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
