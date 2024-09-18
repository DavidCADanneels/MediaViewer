package be.dafke.MediaViewer.Application.Boxes

import javax.swing.DefaultListSelectionModel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import java.awt.Dimension

class BoxContentTablePanel extends JScrollPane implements ListSelectionListener {
    BoxContentDataModel boxContentDataModel
    JTable overviewTable

    BoxContentTablePanel() {
        boxContentDataModel = new BoxContentDataModel()
        overviewTable = new JTable(boxContentDataModel)
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
