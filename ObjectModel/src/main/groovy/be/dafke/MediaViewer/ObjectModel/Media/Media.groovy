package be.dafke.MediaViewer.ObjectModel.Media

abstract class Media {
    String subFolderName
    String fileName
    String extension
    Date creationDate
    Integer owner
    String indexNumber
    String chapter

    String getChapter() {
        return chapter
    }
}
