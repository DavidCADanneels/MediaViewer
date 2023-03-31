package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant

abstract class Media {
    String subFolderName
    String fileName
    String extension
    Participant author

    Media() {
    }

    String getAbsolutePath(File projectRoot){
        File file = getFile(projectRoot)
        if(file){
            return file.name
        } else null
    }

    String getFile(File projectRoot){
        String fullFileName = fileName
        if(extension != null){
            fullFileName = "${fullFileName}.${extension}"
        }

        File folder = projectRoot
        if(subFolderName != null){
            folder = new File(projectRoot, subFolderName)
        }
        new File(folder, fullFileName)
    }

    String getSubFolderName() {
        return subFolderName
    }

    void setSubFolderName(String subFolderName) {
        this.subFolderName = subFolderName
    }

    String getFileName() {
        return fileName
    }

    void setFileName(String fileName) {
        this.fileName = fileName
    }

    Participant getAuthor() {
        return author
    }

    void setAuthor(Participant author) {
        this.author = author
    }
}
