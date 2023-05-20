package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Media
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.table.AbstractTableModel
import java.util.function.Predicate

import static java.util.ResourceBundle.getBundle

class MediaOverviewDataModel extends AbstractTableModel {
    static int ID_COL = 0
    static int CREATION_DATE_COL = 1
    static int CREATION_TIME_COL = 2
    static int SIZE_COL = 3
    static int OWNER_COL = 4
    static int INDEX_COL = 5
    static int NR_OF_COL = 6
    static int FILE_NAME_COL = 6
    static int EXTENSION_COL = 7
    static int FOLDER_NAME_COL = 8
//    static int FULL_PATH_COL = 9

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    List<Picture> pictures = []
    Story story

    MediaOverviewDataModel() {
        columnClasses.put(ID_COL, String.class)
        columnClasses.put(FOLDER_NAME_COL, String.class)
        columnClasses.put(EXTENSION_COL, String.class)
        columnClasses.put(FILE_NAME_COL, String.class)
        columnClasses.put(INDEX_COL, String.class)
        columnClasses.put(SIZE_COL, String.class)
        columnClasses.put(CREATION_DATE_COL, Date.class)
        columnClasses.put(CREATION_TIME_COL, String.class)
        columnClasses.put(OWNER_COL, Participant.class)
//        columnClasses.put(FULL_PATH_COL, File.class)

        columnNames.put(ID_COL, getBundle("MediaViewer").getString("ID"))
        columnNames.put(FOLDER_NAME_COL, getBundle("MediaViewer").getString("FOLDER_NAME"))
        columnNames.put(EXTENSION_COL, getBundle("MediaViewer").getString("EXTENSION"))
        columnNames.put(FILE_NAME_COL, getBundle("MediaViewer").getString("FILE_NAME"))
        columnNames.put(INDEX_COL, getBundle("MediaViewer").getString("MEDIA_INDEX"))
        columnNames.put(SIZE_COL, getBundle("MediaViewer").getString("IMAGE_SIZE"))
        columnNames.put(CREATION_DATE_COL, getBundle("MediaViewer").getString("CREATION_DATE"))
        columnNames.put(CREATION_TIME_COL, getBundle("MediaViewer").getString("CREATION_TIME"))
        columnNames.put(OWNER_COL, getBundle("MediaViewer").getString("OWNER"))
//        columnNames.put(FULL_PATH_COL, getBundle("MediaViewer").getString("FULL_PATH"))
    }

    void setChapter(Chapter chapter) {
        String prefix = chapter.getPrefix()
        if(prefix) {
            pictures = []
            List<Picture> allPictures = story.getPictures()
            allPictures.each { Picture picture ->
                String myChapter = picture.getChapter()
                if(myChapter != null && myChapter == prefix){
                    pictures.add picture
                }
            }
        } else  {
            pictures = story.getPictures()
        }
        fireTableDataChanged()
    }

    void setStory(Story story) {
        this.story = story
        pictures = story.getPictures()
        fireTableDataChanged()
    }

//    void setPictures(List<Picture> pictures) {
//        this.pictures = pictures
//    }

    Picture getObject(int row) {
        return pictures.get(row)
    }

    @Override
    int getRowCount() {
        pictures.size()
    }

    @Override
    int getColumnCount() {
        return NR_OF_COL
    }

    @Override
    String getColumnName(int columnIndex) {
        columnNames.get(columnIndex)
    }

    @Override
    Class<?> getColumnClass(int columnIndex) {
        columnClasses.get(columnIndex)
    }

    @Override
    boolean isCellEditable(int rowIndex, int columnIndex) {
//        return false
        return columnIndex == INDEX_COL
//        return columnIndex == OWNER_COL
    }

    @Override
    Object getValueAt(int rowIndex, int columnIndex) {
        Picture picture = getObject(rowIndex)
        if (picture) {
            if (columnIndex == ID_COL) {
                return picture.getFileName()
            } else if (columnIndex == FOLDER_NAME_COL) {
                return picture.getSubFolderName()
            } else if (columnIndex == EXTENSION_COL) {
                return picture.getExtension()
            } else if (columnIndex == INDEX_COL) {
                return picture.getIndexNumber()
            } else if (columnIndex == OWNER_COL) {
                Integer id = picture.getOwner()
                if(id != null && id != -1) {
                    return story.getParticipants().get(id)
                } else return null
            } else if (columnIndex == CREATION_DATE_COL) {
                return picture.getCreationDate()
            } else if (columnIndex == CREATION_TIME_COL) {
                Date date = picture.getCreationDate()
                if(date == null) return null
                Calendar calendar = Calendar.getInstance()
                calendar.setTime(date)
                int hours = calendar.get(Calendar.HOUR_OF_DAY)
                int minutes = calendar.get(Calendar.MINUTE)
                int seconds = calendar.get(Calendar.SECOND)
                return "${hours}:${minutes<10?'0':''}${minutes}:${seconds<10?'0':''}${seconds}"
//                return "${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}"
            } else if (columnIndex == FILE_NAME_COL) {
                return "${picture.getFileName()}.${picture.getExtension()}"
            } else if (columnIndex == SIZE_COL) {
                return "${picture.getWidth()} x ${picture.getHeight()}"
            } else {
                null
            }
            null
        }
        null
    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {
        Picture picture = getObject(rowIndex)
        if (columnIndex == INDEX_COL) {
            String indexNumber = (String) value
            picture.setIndexNumber(indexNumber)
        } else if (columnIndex == OWNER_COL) {
            List<Participant> list = story.getParticipants()
            Participant participant = (Participant) value
            Integer id = list.indexOf(participant)
            if (id != -1) {
                System.out.println("Participant: ${participant} has ID: ${id}")
                picture.setOwner(id)
            }
        }
    }
}
