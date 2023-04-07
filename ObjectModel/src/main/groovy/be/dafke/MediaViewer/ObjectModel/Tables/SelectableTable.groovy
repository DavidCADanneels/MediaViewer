package be.dafke.MediaViewer.ObjectModel.Tables

import javax.swing.*
import javax.swing.table.TableModel

class SelectableTable<T> extends JTable{
    protected SelectableTableModel<T> model

    SelectableTable(SelectableTableModel<T> model) {
        super(model)
        this.model = model
//        setAutoCreateRowSorter(true)
//        setRowSorter(null)
    }

    ArrayList<T> getSelectedObjects() {
        int[] selectedRows = getSelectedRows()
        int col = getSelectedColumn()
        RowSorter<? extends TableModel> rowSorter = getRowSorter()
        ArrayList<T> businessObjectArrayList = new ArrayList<>()
        for(int selectedRow : selectedRows) {
            T businessObject
            if(rowSorter) {
                int rowInModel = rowSorter.convertRowIndexToModel(selectedRow)
                businessObject = model.getObject(rowInModel, col)
            } else {
                businessObject = model.getObject(selectedRow, col)
            }
            if(businessObject) {
                businessObjectArrayList.add(businessObject)
            }
        }
        businessObjectArrayList
    }

    T getSelectedObject() {
        int row = getSelectedRow()
        if(row == -1) return null
        RowSorter<? extends TableModel> rowSorter = getRowSorter()
        int col = getSelectedColumn()
        if(rowSorter) {
            int rowInModel = rowSorter.convertRowIndexToModel(row)
            return model.getObject(rowInModel, col)
        } else {
            return model.getObject(row,col)
        }
    }
}