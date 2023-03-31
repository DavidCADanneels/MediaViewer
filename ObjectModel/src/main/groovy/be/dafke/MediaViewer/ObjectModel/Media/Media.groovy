package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant

abstract class Media {
    File dataStorage
    String fileName
    Participant author

    Media() {

    }

    String getFileName() {
        return fileName
    }

    void setFileName(String fileName) {
        this.fileName = fileName
    }

    File getDataStorage() {
        return dataStorage
    }

    Participant getAuthor() {
        return author
    }

    void setAuthor(Participant author) {
        this.author = author
    }
}
