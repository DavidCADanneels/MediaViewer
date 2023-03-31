package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import be.dafke.MediaViewer.ObjectModel.Media.Media
import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.table.DefaultTableModel

import static java.util.ResourceBundle.getBundle

class MediaOverviewDataModel extends DefaultTableModel {
    static int FILE_NAME_COL = 0
    static int AUTHOR_COL = 1
    static int FULL_PATH_COL = 2
    static int CREATION_DATE_COL = 2
    static int NR_OF_COL = 3

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    Story story

    MediaOverviewDataModel() {
        columnClasses.put(FILE_NAME_COL, String.class)
        columnClasses.put(AUTHOR_COL, Participant.class)
        columnClasses.put(CREATION_DATE_COL, Date.class)
        columnClasses.put(FULL_PATH_COL, File.class)
        columnNames.put(FILE_NAME_COL, getBundle("MediaViewer").getString("FILE_NAME"))
        columnNames.put(AUTHOR_COL, getBundle("MediaViewer").getString("AUTHOR"))
        columnNames.put(CREATION_DATE_COL, getBundle("MediaViewer").getString("CREATION_DATE"))
        columnNames.put(FULL_PATH_COL, getBundle("MediaViewer").getString("FULL_PATH"))
    }

    void setStory(Story story) {
        this.story = story
        fireTableDataChanged()
    }

    @Override
    int getRowCount() {
        story?.mediaMap?.size()?:0
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
        return false
//        return columnIndex == AUTHOR_COL
    }

    @Override
    Object getValueAt(int rowIndex, int columnIndex) {
        HashMap<String, Media> mediaMap = story?.mediaMap
        if(mediaMap) {
            Media media = mediaMap.get(rowIndex)
            if (media != null) {
                String fileName = media.getFileName()
                if (columnIndex == FILE_NAME_COL) {
                    fileName
                } else if (columnIndex == FULL_PATH_COL) {
                    String pathPrefix = Main.storyPaths.get(fileName)
//                    media.getDataStorage()
                    // TODO: append 'pathPrefix' with 'fileName' (+ infix?)
                    pathPrefix
                } else if (columnIndex == AUTHOR_COL) {
                    media.getAuthor()
                } else if (columnIndex == CREATION_DATE_COL) {
                    null
//                media.getCreationTime()
                }
            }
        }
        null
    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {

    }
}
