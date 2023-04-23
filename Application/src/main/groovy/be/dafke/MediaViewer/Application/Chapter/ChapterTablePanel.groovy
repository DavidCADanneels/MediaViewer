package be.dafke.MediaViewer.Application.Chapter

import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.Dimension

class ChapterTablePanel extends JScrollPane {
    ChaptersOverviewDataModel dataModel
    JTable overviewTable

    ChapterTablePanel() {
        overviewTable = new JTable(dataModel)
        overviewTable.setPreferredScrollableViewportSize(new Dimension(500, 200))
        overviewTable.setAutoCreateRowSorter(true)
        setViewportView(overviewTable)
    }
}
