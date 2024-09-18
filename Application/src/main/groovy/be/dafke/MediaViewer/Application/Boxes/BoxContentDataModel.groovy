package be.dafke.MediaViewer.Application.Boxes

import be.dafke.MediaViewer.ObjectModel.Media.Media
import be.dafke.MediaViewer.ObjectModel.Media.MediaBox

import javax.swing.table.DefaultTableModel

import static java.util.ResourceBundle.getBundle
import static java.util.ResourceBundle.getBundle

class BoxContentDataModel extends DefaultTableModel {
    static int TITLE_COL = 0
    static int AUTHOR_COL = 1
    static int NR_OF_COL = 2

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    List<Media> content

    BoxContentDataModel() {
        content = []
        columnClasses.put(TITLE_COL, String.class)
        columnClasses.put(AUTHOR_COL, String.class)
        columnNames.put(TITLE_COL, getBundle("MediaViewer").getString("MEDIA_TITLE"))
        columnNames.put(AUTHOR_COL, getBundle("MediaViewer").getString("MEDIA_AUTHOR"))
    }

    @Override
    int getRowCount() {
        content?.size()?:0
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
        return true
    }

    @Override
    Object getValueAt(int rowIndex, int columnIndex) {

    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {

    }
}
