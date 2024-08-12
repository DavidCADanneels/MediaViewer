package be.dafke.MediaViewer.ObjectModel.Stories

import be.dafke.MediaViewer.ObjectModel.Media.MediaBox
import be.dafke.MediaViewer.ObjectModel.People.Participant
import be.dafke.MediaViewer.ObjectModel.Media.Picture

class Story {
    String title
    String shortDescription
    String introText
    List<Chapter> chapters
    List<Participant> participants
    List<Picture> pictures
    List<MediaBox> mediaBoxes
    File projectFolder

    Story() {
        chapters = []
        pictures = []
        participants = []
        mediaBoxes = []
    }

    List<MediaBox> getMediaBoxes() {
        return mediaBoxes
    }

    void setMediaBoxes(List<MediaBox> mediaBoxes) {
        this.mediaBoxes = mediaBoxes
    }

    List<Chapter> getChapters() {
        return chapters
    }

    void setChapters(List<Chapter> chapters) {
        this.chapters = chapters
    }

//    Chapter getChapter(String index){
//        List<Chapter> foundChapters = []
////        def allChapters = getChapters()
//        chapters.each {Chapter chapter ->
//            String prefix = chapter.getPrefix()
//            if(index.equals(prefix)){
//                foundChapters.add chapter
//            }
//        }
//        System.out.println"found: ${foundChapters.size()}"
//        if(foundChapters.size()>0){
//            return foundChapters[0]
//        } else return null
////        getChapters().find { Chapter chapter -> chapter.getPrefix().equals(index)}
//    }

    File getProjectFolder() {
        return projectFolder
    }

    void setProjectFolder(File projectFolder) {
        this.projectFolder = projectFolder
    }

    List<Picture> getPictures() {
        return pictures
    }

//    List<Picture> getPictures(Predicate<Picture> filter) {
//        getPictures().stream().filter(filter).collect().toList()
//    }

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
