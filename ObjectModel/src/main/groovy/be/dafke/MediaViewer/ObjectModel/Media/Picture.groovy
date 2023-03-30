package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant

class Picture extends Media {
    Size2D size

    Picture(File dataStorage, Participant author, Date creationTime, Size2D size) {
        super(dataStorage, author, creationTime)
        this.size = size
    }

    Size2D getSize() {
        return size
    }

    void setSize(Size2D size) {
        this.size = size
    }
}
