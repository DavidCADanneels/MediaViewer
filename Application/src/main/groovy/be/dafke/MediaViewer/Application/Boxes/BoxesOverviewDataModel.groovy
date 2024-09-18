package be.dafke.MediaViewer.Application.Boxes

import be.dafke.MediaViewer.ObjectModel.Media.MediaBox

import javax.swing.table.DefaultTableModel

import static java.util.ResourceBundle.getBundle

class BoxesOverviewDataModel extends DefaultTableModel {
    static int NR_COL = 0
    static int NAME_COL = 1
    static int NR_OF_COL = 2

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    List<MediaBox> mediaBoxes

    BoxesOverviewDataModel() {
        mediaBoxes = []
        columnClasses.put(NR_COL, Integer.class)
        columnClasses.put(NAME_COL, String.class)
        columnNames.put(NR_COL, getBundle("MediaViewer").getString("BOX_NUMBER"))
        columnNames.put(NAME_COL, getBundle("MediaViewer").getString("BOX_NAME"))
    }

    @Override
    int getRowCount() {
        mediaBoxes?.size()?:0
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
        return columnIndex == NAME_COL
    }

    @Override
    Object getValueAt(int rowIndex, int columnIndex) {
        MediaBox box = mediaBoxes.get(rowIndex)
        if(columnIndex == NR_COL){
            return box.nr
        } else if (columnIndex == NAME_COL){
            return box.name
        }
    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {
        MediaBox box = mediaBoxes.get(rowIndex)
        if(columnIndex == NAME_COL){
            box.name = (String)value
        }
    }
}
