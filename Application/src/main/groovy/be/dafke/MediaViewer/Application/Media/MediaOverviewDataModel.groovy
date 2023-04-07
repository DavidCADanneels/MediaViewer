package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Tables.SelectableTableModel


import static java.util.ResourceBundle.getBundle

class MediaOverviewDataModel extends SelectableTableModel<Picture> {
    static int ID_COL = 0
    static int CREATION_DATE_COL = 1
    static int FILE_NAME_COL = 2
    static int SIZE_COL = 3
//    static int AUTHOR_COL = 1
//    static int FULL_PATH_COL = 2
    static int EXTENSION_COL = 4
    static int FOLDER_NAME_COL = 5
    static int NR_OF_COL = 4

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    List<Picture> pictures = []

    MediaOverviewDataModel() {
        columnClasses.put(ID_COL, String.class)
        columnClasses.put(FOLDER_NAME_COL, String.class)
        columnClasses.put(EXTENSION_COL, String.class)
        columnClasses.put(FILE_NAME_COL, String.class)
        columnClasses.put(SIZE_COL, String.class)
        columnClasses.put(CREATION_DATE_COL, Date.class)
//        columnClasses.put(AUTHOR_COL, Participant.class)
//        columnClasses.put(FULL_PATH_COL, File.class)

        columnNames.put(ID_COL, getBundle("MediaViewer").getString("ID"))
        columnNames.put(FOLDER_NAME_COL, getBundle("MediaViewer").getString("FOLDER_NAME"))
        columnNames.put(EXTENSION_COL, getBundle("MediaViewer").getString("EXTENSION"))
        columnNames.put(FILE_NAME_COL, getBundle("MediaViewer").getString("FILE_NAME"))
        columnNames.put(SIZE_COL, getBundle("MediaViewer").getString("IMAGE_SIZE"))
        columnNames.put(CREATION_DATE_COL, getBundle("MediaViewer").getString("CREATION_DATE"))
//        columnNames.put(AUTHOR_COL, getBundle("MediaViewer").getString("AUTHOR"))
//        columnNames.put(FULL_PATH_COL, getBundle("MediaViewer").getString("FULL_PATH"))
    }

    void setPictures(List<Picture> pictures) {
        this.pictures = pictures
    }

    Picture getObject(int row, int col) {
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
        return false
//        return columnIndex == AUTHOR_COL
    }

    @Override
    Object getValueAt(int rowIndex, int columnIndex) {
        Picture picture = getObject(rowIndex, columnIndex)
        if (picture) {
            if (columnIndex == ID_COL) {
                return picture.getFileName()
            } else if (columnIndex == FOLDER_NAME_COL) {
                return picture.getSubFolderName()
            } else if (columnIndex == EXTENSION_COL) {
                return picture.getExtension()
            } else if (columnIndex == CREATION_DATE_COL) {
                return picture.getCreationDate()
            } else if (columnIndex == FILE_NAME_COL) {
                return "${picture.getFileName()}.${picture.getExtension()}"
            } else if (columnIndex == SIZE_COL) {
                return "${picture.getWidth()} x ${picture.getHeigth()}"
            } else {
                null
            }
            null
        }
        null
    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {

    }
}
