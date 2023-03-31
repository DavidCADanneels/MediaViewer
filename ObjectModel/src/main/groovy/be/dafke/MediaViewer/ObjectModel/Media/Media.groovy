package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant

import java.nio.file.attribute.FileTime

abstract class Media {
    File dataStorage
    Participant author
    FileTime creationTime
//    String index

    Media(File dataStorage, Participant author, FileTime creationTime) {
        this.dataStorage = dataStorage
        this.author = author
        this.creationTime = creationTime
    }

    File getDataStorage() {
        return dataStorage
    }

    void setDataStorage(File dataStorage) {
        this.dataStorage = dataStorage
    }

    Participant getAuthor() {
        return author
    }

    void setAuthor(Participant author) {
        this.author = author
    }

    FileTime getCreationTime() {
        return creationTime
    }

    void setCreationTime(FileTime creationTime) {
        this.creationTime = creationTime
    }
}
