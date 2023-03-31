package be.dafke.MediaViewer.ObjectModel.Media

class Catalog {

    static HashMap<String,Media> mediaFiles
    static HashMap<String,File> sourceFiles

    Catalog() {
        mediaFiles = new HashMap<String,Media>()
        sourceFiles = new HashMap<String,File>()
    }

    static HashMap<String, Media> getMediaFiles() {
        return mediaFiles
    }

    void setMediaFiles(HashMap<String, Media> mediaFiles) {
        this.mediaFiles = mediaFiles
    }

    static HashMap<String, File> getSourceFiles() {
        return sourceFiles
    }

    void setSourceFiles(HashMap<String, File> sourceFiles) {
        this.sourceFiles = sourceFiles
    }
}
