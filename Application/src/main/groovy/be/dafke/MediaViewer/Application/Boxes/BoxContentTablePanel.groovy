package be.dafke.MediaViewer.Application.Boxes

import be.dafke.MediaViewer.ObjectModel.Media.Media

import javax.swing.DefaultListSelectionModel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.RowSorter
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import javax.swing.table.TableModel
import java.awt.Dimension

class BoxContentTablePanel extends JScrollPane implements ListSelectionListener {
    BoxContentDataModel boxContentDataModel
    JTable overviewTable
    Media selectedMedia

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

    Media getSelectedMedia() {
        return selectedMedia
    }

    Media getSingleSelectedMedia(){
        int row = overviewTable.getSelectedRow()
        if (row == -1) return null
        RowSorter<? extends TableModel> rowSorter = overviewTable.getRowSorter()
        if (rowSorter) {
            int rowInModel = rowSorter.convertRowIndexToModel(row)
            return boxContentDataModel.getObject(rowInModel)
        } else {
            return boxContentDataModel.getObject(row)
        }
    }

    @Override
    void valueChanged(ListSelectionEvent e) {
        selectedMedia = getSingleSelectedMedia()
    }
}
