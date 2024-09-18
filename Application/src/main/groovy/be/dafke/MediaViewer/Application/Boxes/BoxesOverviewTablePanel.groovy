package be.dafke.MediaViewer.Application.Boxes

import javax.swing.DefaultListSelectionModel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import java.awt.Dimension

class BoxesOverviewTablePanel extends JScrollPane implements ListSelectionListener {

    JTable overviewTable
    BoxesOverviewDataModel dataModel

    BoxesOverviewTablePanel() {
        dataModel = new BoxesOverviewDataModel()
        overviewTable = new JTable(dataModel)
        overviewTable.setPreferredScrollableViewportSize(new Dimension(500, 200))
        overviewTable.setAutoCreateRowSorter(true)
        setViewportView(overviewTable)
        DefaultListSelectionModel selection = new DefaultListSelectionModel()
        selection.addListSelectionListener(this)
        overviewTable.setSelectionModel(selection)
    }

    @Override
    void valueChanged(ListSelectionEvent e) {

    }
}
