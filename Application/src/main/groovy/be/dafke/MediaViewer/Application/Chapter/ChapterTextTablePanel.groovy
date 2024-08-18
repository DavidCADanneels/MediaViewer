package be.dafke.MediaViewer.Application.Chapter


import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.DefaultListSelectionModel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.RowSorter
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import javax.swing.table.TableModel
import java.awt.Dimension

class ChapterTextTablePanel extends JScrollPane implements ListSelectionListener {
    ChaptersOverviewDataModel dataModel
    JTable overviewTable
    ChaptersTextOverviewPanel chaptersOverviewPanel

    ChapterTextTablePanel(ChaptersTextOverviewPanel chaptersOverviewPanel) {
        this.chaptersOverviewPanel = chaptersOverviewPanel
        dataModel = new ChaptersOverviewDataModel()
        overviewTable = new JTable(dataModel)
        overviewTable.setPreferredScrollableViewportSize(new Dimension(500, 200))
        overviewTable.setAutoCreateRowSorter(true)
        setViewportView(overviewTable)
        DefaultListSelectionModel selection = new DefaultListSelectionModel()
        selection.addListSelectionListener(this)
        overviewTable.setSelectionModel(selection)
    }

    void setStory(Story story) {
        dataModel.setStory(story)
    }

    Chapter getSingleSelectedChapter() {
        int row = overviewTable.getSelectedRow()
        if (row == -1) return null
        RowSorter<? extends TableModel> rowSorter = overviewTable.getRowSorter()
        if (rowSorter) {
            int rowInModel = rowSorter.convertRowIndexToModel(row)
            return dataModel.getObject(rowInModel)
        } else {
            return dataModel.getObject(row)
        }
    }

    void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Chapter chapter = getSingleSelectedChapter()
            chaptersOverviewPanel.setChapter(chapter)
        }
    }
}
