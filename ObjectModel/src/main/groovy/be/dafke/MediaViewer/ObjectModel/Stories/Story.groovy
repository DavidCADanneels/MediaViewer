package be.dafke.MediaViewer.ObjectModel.Stories

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import be.dafke.MediaViewer.ObjectModel.Media.Media


class Story {
    String title
    String shortDescription
    String introText
//    HashMap<String, Chapter> chapters = [:]
    List<Media> mediaList
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

    List<Media> getMediaList() {
        return mediaList
    }

    void setMediaList(List<Media> mediaList) {
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
