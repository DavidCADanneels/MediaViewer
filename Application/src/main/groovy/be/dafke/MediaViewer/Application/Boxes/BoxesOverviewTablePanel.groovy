package be.dafke.MediaViewer.Application.Boxes

import be.dafke.MediaViewer.ObjectModel.Media.MediaBox

import javax.swing.DefaultListSelectionModel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.RowSorter
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import javax.swing.table.TableModel
import java.awt.Dimension

class BoxesOverviewTablePanel extends JScrollPane implements ListSelectionListener {

    JTable overviewTable
    BoxesOverviewDataModel dataModel
    BoxContentDataModel contentDataModel

    BoxesOverviewTablePanel(BoxContentTablePanel boxContentTablePanel) {
        contentDataModel = boxContentTablePanel.boxContentDataModel
        dataModel = new BoxesOverviewDataModel()
        overviewTable = new JTable(dataModel)
        overviewTable.setPreferredScrollableViewportSize(new Dimension(500, 200))
        overviewTable.setAutoCreateRowSorter(true)
        setViewportView(overviewTable)
        DefaultListSelectionModel selection = new DefaultListSelectionModel()
        selection.addListSelectionListener(this)
        overviewTable.setSelectionModel(selection)
    }

    MediaBox getSingleSelectedMediaBox() {
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

    @Override
    void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            MediaBox mediaBox = getSingleSelectedMediaBox()
            contentDataModel.setMediaBox(mediaBox)
        }
    }
}
