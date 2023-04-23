package be.dafke.MediaViewer.Application.Chapter

import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.Dimension

class ChapterTablePanel extends JScrollPane {
    ChaptersOverviewDataModel dataModel
    JTable overviewTable

    ChapterTablePanel() {
        dataModel = new ChaptersOverviewDataModel()
        overviewTable = new JTable(dataModel)
        overviewTable.setPreferredScrollableViewportSize(new Dimension(500, 200))
        overviewTable.setAutoCreateRowSorter(true)
        setViewportView(overviewTable)
    }

    void setStory(Story story) {
        dataModel.setStory(story)
    }
}
