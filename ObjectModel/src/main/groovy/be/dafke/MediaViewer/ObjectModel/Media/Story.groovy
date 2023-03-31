package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant


class Story {
    String title
    String shortDescription
    String introText
//    HashMap<String, Chapter> chapters = [:]
    List<String> mediaList
    List<Participant> participants
    File projectFolder

    Story() {
        mediaList = []
        participants = []
    }

    File getProjectFolder() {
        return projectFolder
    }

    void setProjectFolder(File projectFolder) {
        this.projectFolder = projectFolder
    }

    List<String> getMediaList() {
        return mediaList
    }

    void setMediaList(List<String> mediaList) {
        this.mediaList = mediaList
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

    List<Participant> getParticipants() {
        return participants
    }

    void setParticipants(List<Participant> participants) {
        this.participants = participants
    }
}
