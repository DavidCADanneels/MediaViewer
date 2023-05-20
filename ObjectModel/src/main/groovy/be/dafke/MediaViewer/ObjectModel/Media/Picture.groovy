package be.dafke.MediaViewer.ObjectModel.Media

import java.util.function.Predicate

class Picture extends Media {
    int width, height
    /**
     * index of Participant in Story.participants List
     */
//    Integer owner = null
    /**
     * Indices of Participant(s) in Story.participants List
     */
//    Integer [] participantsInForeGround, participantsInMiddleGround, participantsBackGround
//    Boolean localInhabitants = false
//    Boolean landscape = false
//    Boolean inside = false
//    Boolean panorama = false

    Picture() {
//        participantsInForeGround = []
//        participantsInMiddleGround = []
//        participantsBackGround = []
    }

    static Predicate<Picture> inChapter(String prefix){
        { picture -> picture.getChapter() != null && prefix == picture.chapter }
    }
}
