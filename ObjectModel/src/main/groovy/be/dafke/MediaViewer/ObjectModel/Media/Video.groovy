package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant

import java.nio.file.attribute.FileTime

class Video extends Media {
    long length

    /**
     *
     * @param dataStorage
     * @param author
     * @param creationTime
     * @param length duriation of video in seconds
     */
    Video(File dataStorage, Participant author, FileTime creationTime, long length) {
        super(dataStorage, author, creationTime)
        this.length = length
    }

    long getLength() {
        return length
    }

    void setLength(long length) {
        this.length = length
    }
}
