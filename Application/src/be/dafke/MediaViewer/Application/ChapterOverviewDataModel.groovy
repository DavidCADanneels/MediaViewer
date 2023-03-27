package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.ObjectModel.Story

import javax.swing.table.AbstractTableModel

import static java.util.ResourceBundle.getBundle

class ChapterOverviewDataModel extends AbstractTableModel {

    static int NAME_COL = 0
    static int DESC_COL = 1
    static int NR_OF_COL = 2

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    ChapterOverviewDataModel() {
        columnClasses.put(NAME_COL, String.class)
        columnClasses.put(DESC_COL, String.class)
        columnNames.put(NAME_COL, getBundle("MediaViewer").getString("NAME"))
        columnNames.put(DESC_COL, getBundle("MediaViewer").getString("DESCR"))
    }

    @Override
    int getRowCount() {
        Main.stories.size()
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
    }

    @Override
    Object getValueAt(int rowIndex, int columnIndex) {
        Story story = Main.stories.get(rowIndex)
        if(story != null) {
            if (columnIndex == NAME_COL) {
                story.getTitle()
            } else if (columnIndex == DESC_COL) {
                story.getShortDescription()
            }
        } else {
//            System.err.println("Stories is still null")
            null
        }
        null
    }

    @Override
    void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // no editable fields for now
    }

//    remaining methods in interface TableModel:
//    @Override
//    void addTableModelListener(TableModelListener l) {
//
//    }
//
//    @Override
//    void removeTableModelListener(TableModelListener l) {
//
//    }
}
