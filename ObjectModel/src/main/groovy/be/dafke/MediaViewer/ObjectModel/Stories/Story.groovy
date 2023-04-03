package be.dafke.MediaViewer.ObjectModel.Stories

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import be.dafke.MediaViewer.ObjectModel.Media.Media
import be.dafke.MediaViewer.ObjectModel.Media.Picture


class Story {
    String title
    String shortDescription
    String introText
//    List<Chapter> chapters
    List<Participant> participants
    List<Picture> pictures
    File projectFolder

    Story() {
//        chapters = []
        pictures = []
        participants = []
    }

    File getProjectFolder() {
        return projectFolder
    }

    void setProjectFolder(File projectFolder) {
        this.projectFolder = projectFolder
    }

    List<Picture> getPictures() {
        return pictures
    }

    void setPictures(List<Picture> pictures) {
        this.pictures = pictures
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
