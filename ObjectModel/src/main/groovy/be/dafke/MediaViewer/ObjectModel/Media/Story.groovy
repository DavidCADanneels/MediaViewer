package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant

class Story {
    String title
    String shortDescription
    String introText
//    HashMap<String, Chapter> chapters = [:]
    List<Media> media
    List<Participant> participants

    Story() {
        media = []
        participants = []
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

    List<Media> getMedia() {
        return media
    }

    void setMedia(List<Media> media) {
        this.media = media
    }

    List<Participant> getParticipants() {
        return participants
    }

    void setParticipants(List<Participant> participants) {
        this.participants = participants
    }
}
