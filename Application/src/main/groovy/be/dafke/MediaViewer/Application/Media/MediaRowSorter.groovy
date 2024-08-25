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
        return column == PictureOverviewDataModel.ID_COL ||
                column == PictureOverviewDataModel.CREATION_DATE_COL ||
                column == PictureOverviewDataModel.OWNER_COL ||
                column == PictureOverviewDataModel.CHAPTER_COL ||
                column == PictureOverviewDataModel.STARS_COL ||
                column == PictureOverviewDataModel.INDEX_COL
    }
}
