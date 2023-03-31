package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement


class Story {
    String title
    String shortDescription
    String introText
//    HashMap<String, Chapter> chapters = [:]
    // TODO: use HashMap iso List (is this really needed?)
//    @JacksonXmlRootElement(localName = "headers")
//    @JsonSerialize(using = ApplicationJsonSerializer)
//    @JacksonXmlProperty
    HashMap<String, Media> mediaMap
    List<Participant> participants

    Story() {
        mediaMap = [:]
        participants = []
    }

    HashMap<String, Media> getMediaMap() {
        return mediaMap
    }

    void setMediaMap(HashMap<String, Media> mediaMap) {
        this.mediaMap = mediaMap
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
