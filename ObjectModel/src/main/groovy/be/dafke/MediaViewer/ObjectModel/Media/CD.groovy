package be.dafke.MediaViewer.ObjectModel.Media

class CD extends Media {
    String albumTitle
    String artist
    int boxNr

    CD() {
    }

    String getAlbumTitle() {
        return albumTitle
    }

    void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle
    }

    String getArtist() {
        return artist
    }

    void setArtist(String artist) {
        this.artist = artist
    }

    int getBoxNr() {
        return boxNr
    }

    void setBoxNr(int boxNr) {
        this.boxNr = boxNr
    }
}
