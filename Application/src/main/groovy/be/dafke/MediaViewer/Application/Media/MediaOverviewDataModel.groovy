package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import be.dafke.MediaViewer.ObjectModel.Media.Media

import javax.swing.table.DefaultTableModel

import static java.util.ResourceBundle.getBundle

class MediaOverviewDataModel extends DefaultTableModel {
    static int FILE_NAME_COL = 0
    static int AUTHOR_COL = 1
    static int CREATION_DATE_COL = 2
    static int FULL_PATH_COL = 3
    static int NR_OF_COL = 4

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    List<Media> mediaList

    MediaOverviewDataModel() {
        mediaList = []
        columnClasses.put(FILE_NAME_COL, String.class)
        columnClasses.put(AUTHOR_COL, Participant.class)
        columnClasses.put(CREATION_DATE_COL, Date.class)
        columnClasses.put(FULL_PATH_COL, File.class)
        columnNames.put(FILE_NAME_COL, getBundle("MediaViewer").getString("FIRST_NAME"))
        columnNames.put(AUTHOR_COL, getBundle("MediaViewer").getString("AUTHOR"))
        columnNames.put(CREATION_DATE_COL, getBundle("MediaViewer").getString("CREATION_DATE"))
        columnNames.put(FULL_PATH_COL, getBundle("MediaViewer").getString("FULL_PATH"))
    }

    void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList
        fireTableDataChanged()
    }

    @Override
    int getRowCount() {
        mediaList?.size()?:0
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
        Media media = mediaList.get(rowIndex)
        if(media != null) {
            if (columnIndex == FULL_PATH_COL) {
                media.getDataStorage()
            } else if (columnIndex == FILE_NAME_COL) {
                File file = media.getDataStorage()
                file.getName()
            } else if (columnIndex == CREATION_DATE_COL) {
                media.getCreationTime()
            } else if (columnIndex == AUTHOR_COL) {
                media.getAuthor()
            } else null
        } else {
            null
        }
    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {

    }
}
