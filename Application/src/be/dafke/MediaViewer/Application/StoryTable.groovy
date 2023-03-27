package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.Application.StoryView.StoryOverviewDataModel

import javax.swing.JTable

class StoryTable extends JTable {
    StoryTable(StoryOverviewDataModel dataModel) {
        super(dataModel)
    }
}
