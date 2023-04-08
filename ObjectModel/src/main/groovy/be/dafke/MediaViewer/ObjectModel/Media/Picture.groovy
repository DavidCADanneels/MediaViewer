package be.dafke.MediaViewer.ObjectModel.Media

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

    int getWidth() {
        return width
    }

    void setWidth(int width) {
        this.width = width
    }

    int getHeight() {
        return height
    }

    void setHeight(int height) {
        this.height = height
    }
}
