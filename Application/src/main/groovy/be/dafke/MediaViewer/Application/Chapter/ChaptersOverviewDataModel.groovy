package be.dafke.MediaViewer.Application.Chapter

import javax.swing.table.DefaultTableModel

class ChaptersOverviewDataModel extends DefaultTableModel {
    static int NAME_COL = 0
    static int DESC_COL = 1
    static int NR_OF_COL = 2

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]


}
