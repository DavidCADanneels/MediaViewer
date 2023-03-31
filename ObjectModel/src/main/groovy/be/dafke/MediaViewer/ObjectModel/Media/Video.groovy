package be.dafke.MediaViewer.ObjectModel.Media

class Video extends Media {
    long length

    Video() {
    }

    long getLength() {
        return length
    }

    void setLength(long length) {
        this.length = length
    }
}
