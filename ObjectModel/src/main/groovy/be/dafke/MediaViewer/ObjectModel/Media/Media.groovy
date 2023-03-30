package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant

abstract class Media {
    File dataStorage
    Participant author
    Date creationTime
//    String index

    Media(File dataStorage, Participant author, Date creationTime) {
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

    Date getCreationTime() {
        return creationTime
    }

    void setCreationTime(Date creationTime) {
        this.creationTime = creationTime
    }
}
