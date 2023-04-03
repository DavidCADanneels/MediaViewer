package be.dafke.MediaViewer.Application.StoryOverview

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.table.DefaultTableModel

import static java.util.ResourceBundle.getBundle

class StoryOverviewDataModel extends DefaultTableModel {

    static int NAME_COL = 0
    static int DESC_COL = 1
    static int FILE_COL = 2
    static int NR_OF_COL = 3

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    StoryOverviewDataModel() {
        columnClasses.put(NAME_COL, String.class)
        columnClasses.put(DESC_COL, String.class)
        columnClasses.put(FILE_COL, File.class)
        columnNames.put(NAME_COL, getBundle("MediaViewer").getString("NAME"))
        columnNames.put(DESC_COL, getBundle("MediaViewer").getString("DESCR"))
        columnNames.put(FILE_COL, getBundle("MediaViewer").getString("FILE"))
    }

    @Override
    int getRowCount() {
        Main.storyMap.size()
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
        if (columnIndex == FILE_COL){
            return Main.storyMap.values().getAt(rowIndex)
        } else {
            Story story = Main.storyMap.keySet().getAt(rowIndex)
            if (story != null) {
                if (columnIndex == NAME_COL) {
                    return story.getTitle()
                } else if (columnIndex == DESC_COL) {
                    return story.getShortDescription()
                }
            }
        }
        return null
    }

    @Override
    void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // no editable fields for now
    }
}
