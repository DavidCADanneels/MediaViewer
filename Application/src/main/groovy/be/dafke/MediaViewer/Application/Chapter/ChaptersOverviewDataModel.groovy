package be.dafke.MediaViewer.Application.Chapter

import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.table.DefaultTableModel

import static java.util.ResourceBundle.getBundle

class ChaptersOverviewDataModel extends DefaultTableModel {
    static int INDEX_COL = 0
    static int TITLE_COL = 1
    static int NR_OF_COL = 2

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    Story story
    List<Chapter> chapters

    ChaptersOverviewDataModel() {
        chapters = []
        columnClasses.put(INDEX_COL, String.class)
        columnClasses.put(TITLE_COL, String.class)
        columnNames.put(INDEX_COL, getBundle("MediaViewer").getString("CHAPTER_INDEX"))
        columnNames.put(TITLE_COL, getBundle("MediaViewer").getString("CHAPTER_TITLE"))

    }

    void setStory(Story story) {
        this.story = story
        chapters = story.getChapters()
        fireTableDataChanged()
    }

    @Override
    int getRowCount() {
        chapters?.size()?:0
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
        Chapter chapter = chapters.get(rowIndex)
        if(chapter != null) {
            if (columnIndex == INDEX_COL) {
                chapter.getPrefix()
            } else if (columnIndex == TITLE_COL) {
                chapter.getTitle()
            } else null
        } else null
    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {
        Chapter chapter = chapters.get(rowIndex)
        if(chapter != null) {
            if(columnIndex == INDEX_COL){
                String index = (String) value
                chapter.setPrefix(index)
            } else if(columnIndex == TITLE_COL){
                String title = (String) value
                chapter.setTitle(title)
            }
        }
    }
}
