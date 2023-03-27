package be.dafke.MediaViewer.Application

import javax.swing.JTable

class StoryTable extends JTable {
    StoryTable(StoryOverviewDataModel dataModel) {
        super(dataModel)
    }
}
