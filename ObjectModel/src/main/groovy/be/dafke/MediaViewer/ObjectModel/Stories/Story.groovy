package be.dafke.MediaViewer.ObjectModel.Stories

import be.dafke.MediaViewer.ObjectModel.Media.MediaBox
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Media.Text
import be.dafke.MediaViewer.ObjectModel.People.Person

class Story {
    String title
    String shortDescription
    String introText
    List<Chapter> chapters
    List<Person> persons
    List<Picture> pictures
    List<Text> textFragments
    List<MediaBox> mediaBoxes
    File projectFolder

    Story() {
        chapters = []
        pictures = []
        persons = []
        textFragments = []
        mediaBoxes = []
    }

    List<Text> getTextFragments() {
        return textFragments
    }

    void setTextFragments(List<Text> textFragments) {
        this.textFragments = textFragments
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

    List<Person> getPersons() {
        return persons
    }

    void setPersons(List<Person> participants) {
        this.persons = participants
    }
}
