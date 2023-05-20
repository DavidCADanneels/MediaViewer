package be.dafke.MediaViewer.Application.Chapter

import be.dafke.MediaViewer.Application.Media.ImageTablePanel
import be.dafke.MediaViewer.Application.Media.MediaOverviewDataModel
import be.dafke.MediaViewer.Application.Media.MediaRowSorter
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter

import javax.swing.DefaultListSelectionModel
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.Dimension

class ChapterPanel extends JScrollPane {
    MediaOverviewDataModel dataModel
    JTable overviewTable
    boolean singleSelection

    ChapterPanel() {
        dataModel = new MediaOverviewDataModel()
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

    void setChapter(Chapter chapter){
        dataModel.setChapter(chapter)
    }
}
