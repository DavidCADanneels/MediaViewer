package be.dafke.MediaViewer.Application.Media

import javax.swing.table.TableModel
import javax.swing.table.TableRowSorter

class MediaRowSorter extends TableRowSorter {
    MediaRowSorter(TableModel tableModel) {
        super(tableModel)
    }

//    Comparator<?> getComparator(int column) {
//        if(isSortable(column)){
//            super.getComparator(column)
//        }
//    }

    boolean isSortable(int column){
        return column == MediaOverviewDataModel.ID_COL ||
                column == MediaOverviewDataModel.CREATION_DATE_COL ||
                column == MediaOverviewDataModel.OWNER_COL
    }
}
