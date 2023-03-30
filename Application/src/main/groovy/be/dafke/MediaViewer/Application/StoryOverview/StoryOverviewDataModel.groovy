package be.dafke.MediaViewer.Application.StoryOverview

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.table.DefaultTableModel

import static java.util.ResourceBundle.getBundle

class StoryOverviewDataModel extends DefaultTableModel {

    static int NAME_COL = 0
    static int DESC_COL = 1
    static int NR_OF_COL = 2

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    StoryOverviewDataModel() {
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
            } else null
        } else {
            null
        }
    }

    @Override
    void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // no editable fields for now
    }
}
