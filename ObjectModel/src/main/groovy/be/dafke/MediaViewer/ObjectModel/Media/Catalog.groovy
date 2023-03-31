package be.dafke.MediaViewer.ObjectModel.Media

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

//@JacksonXmlRootElement(localName = "catalog")
//@JsonSerialize(using = CatalogSerializer.class)
class Catalog {

    HashMap<String,Media> mediaFiles
    HashMap<String,File> sourceFiles

    Catalog() {
    }

    HashMap<String, Media> getMediaFiles() {
        return mediaFiles
    }

    void setMediaFiles(HashMap<String, Media> mediaFiles) {
        this.mediaFiles = mediaFiles
    }

    HashMap<String, File> getSourceFiles() {
        return sourceFiles
    }

    void setSourceFiles(HashMap<String, File> sourceFiles) {
        this.sourceFiles = sourceFiles
    }
}
