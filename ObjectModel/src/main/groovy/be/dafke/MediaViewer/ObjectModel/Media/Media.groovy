package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Stories.Chapter

abstract class Media {
    String subFolderName
    String fileName
    String extension
    Date creationDate
    Integer owner
    String indexNumber
    // FIXME: Use Chapter iso String
//    Chapter chapter
    String chapter

    String getChapter() {
        return chapter
    }

    void setChapter(String chapter) {
        this.chapter = chapter
        if(subFolderName == null) {
            subFolderName = ''
        }
        if(indexNumber == null){
            indexNumber = chapter
        }
    }
}
