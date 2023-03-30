package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant

class Text extends Media {
    int length

    /**
     *
     * @param dataStorage
     * @param author
     * @param creationTime
     * @param length number of characters
     */
    Text(File dataStorage, Participant author, Date creationTime, int length) {
        super(dataStorage, author, creationTime)
        this.length = length
    }

    int getLength() {
        return length
    }

    void setLength(int length) {
        this.length = length
    }
}
