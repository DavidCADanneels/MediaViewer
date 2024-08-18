package be.dafke.MediaViewer.Application.Chapter


import be.dafke.MediaViewer.Application.Media.PictureOverviewDataModel
import be.dafke.MediaViewer.Application.Media.MediaRowSorter
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.Dimension

class ChapterPicturesPanel extends JScrollPane {
    PictureOverviewDataModel dataModel
    JTable overviewTable
    boolean singleSelection

    ChapterPicturesPanel() {
        dataModel = new PictureOverviewDataModel()
        overviewTable = new JTable(dataModel)
        overviewTable.setPreferredScrollableViewportSize(new Dimension(500, 200))

//        overviewTable.setAutoCreateRowSorter(true)
        overviewTable.setRowSorter(new MediaRowSorter(dataModel))
//        DefaultListSelectionModel selection = new DefaultListSelectionModel()
//        selection.addListSelectionListener(this)
//        overviewTable.setSelectionModel(selection)

        setSingleSelection(true)
        setViewportView(overviewTable)
    }

    void setStory(Story story){
        dataModel.setStory(story)
    }

    void setChapter(Chapter chapter){
        dataModel.setChapter(chapter)
    }
}
