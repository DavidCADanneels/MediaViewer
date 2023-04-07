package be.dafke.MediaViewer.ObjectModel.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
//import com.fasterxml.jackson.dataformat.xml.XmlMapper

abstract class Media {
    String subFolderName
    String fileName
    String extension
    Date creationDate
    // TODO: add Custom @XmlMapper output for 'author' field: use Participant.getId()
//    Participant author

    Media() {
    }

//    String getAbsolutePath(File projectRoot){
//        File file = getFile(projectRoot)
//        if(file){
//            return file.name
//        } else null
//    }

//    String getFile(File projectRoot){
//        String fullFileName = fileName
//        if(extension != null){
//            fullFileName = "${fullFileName}.${extension}"
//        }
//
//        File folder = projectRoot
//        if(subFolderName != null){
//            folder = new File(projectRoot, subFolderName)
//        }
//        new File(folder, fullFileName)
//    }

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

    Date getCreationDate() {
        return creationDate
    }

    void setCreationDate(Date creationDate) {
        this.creationDate = creationDate
    }

//    Participant getAuthor() {
//        return author
//    }
//
//    void setAuthor(Participant author) {
//        this.author = author
//    }
}
