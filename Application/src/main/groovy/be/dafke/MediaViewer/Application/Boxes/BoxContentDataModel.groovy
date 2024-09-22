package be.dafke.MediaViewer.Application.Boxes

import be.dafke.MediaViewer.ObjectModel.Media.Book
import be.dafke.MediaViewer.ObjectModel.Media.CD
import be.dafke.MediaViewer.ObjectModel.Media.DVD
import be.dafke.MediaViewer.ObjectModel.Media.Media
import be.dafke.MediaViewer.ObjectModel.Media.MediaBox
import be.dafke.MediaViewer.ObjectModel.Media.VHS

import javax.swing.table.DefaultTableModel

import static java.util.ResourceBundle.getBundle

class BoxContentDataModel extends DefaultTableModel {
    static int TITLE_COL = 0
    static int AUTHOR_COL = 1
    static int TYPE_COL = 2
    static int NR_OF_COL = 3

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    List<Media> content

    BoxContentDataModel() {
        content = []

        columnClasses.put(TITLE_COL, String.class)
        columnClasses.put(AUTHOR_COL, String.class)
        columnClasses.put(TYPE_COL, String.class)
        columnNames.put(TITLE_COL, getBundle("MediaViewer").getString("MEDIA_TITLE"))
        columnNames.put(AUTHOR_COL, getBundle("MediaViewer").getString("MEDIA_AUTHOR"))
        columnNames.put(TYPE_COL, getBundle("MediaViewer").getString("MEDIA_TYPE"))
    }

    void setMediaBox(MediaBox mediaBox){
        if(mediaBox) {
            content = mediaBox.books + mediaBox.cds + mediaBox.dvds + mediaBox.vhss
        } else {
            content = []
        }
        fireTableDataChanged()
    }

    Media getObject(int row){
        return content.get(row)
    }

    @Override
    int getRowCount() {
        content?.size()?:0
    }

    @Override
    int getColumnCount() {
        return NR_OF_COL
    }

    @Override
    String getColumnName(int columnIndex) {
        columnNames.get(columnIndex)
    }

    @Override
    Class<?> getColumnClass(int columnIndex) {
        columnClasses.get(columnIndex)
    }

    @Override
    boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != TYPE_COL
    }

    @Override
    Object getValueAt(int rowIndex, int columnIndex) {
        Media media = content.get(rowIndex)
        if(media instanceof Book){
            Book book = (Book)media
            if(columnIndex == TITLE_COL){
                return book.title
            } else if(columnIndex == AUTHOR_COL){
                return book.author
            } else if (columnIndex == TYPE_COL){
                return "BOOK"
            }
        } else if (media instanceof CD){
            CD cd = (CD)media
            if(columnIndex == TITLE_COL){
                return cd.albumTitle
            } else if (columnIndex == AUTHOR_COL){
                return cd.artist
            } else if (columnIndex == TYPE_COL){
                return "CD"
            }
        } else if (media instanceof DVD){
            DVD dvd = (DVD)media
            if(columnIndex == TITLE_COL){
                return dvd.title
            } else if (columnIndex == AUTHOR_COL) {
                return dvd.author
            } else if (columnIndex == TYPE_COL){
                return "DVD"
            }
        } else if (media instanceof VHS){
            VHS vhs = (VHS)media
            if(columnIndex == TITLE_COL){
                return vhs.title
            } else if (columnIndex == AUTHOR_COL) {
                return vhs.author
            } else if (columnIndex == TYPE_COL){
                return "VHS"
            }
        }
        return null
    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {
        Media media = content.get(rowIndex)
        if(media instanceof Book){
            Book book = (Book)media
            if(columnIndex == TITLE_COL){
                book.title = value
            } else if(columnIndex == AUTHOR_COL){
                book.author = value
            }
        } else if(media instanceof CD){
            CD cd = (CD)media
            if(columnIndex == TITLE_COL){
                cd.albumTitle = value
            } else if(columnIndex == AUTHOR_COL){
                cd.artist = value
            }
        } else if (media instanceof DVD){
            DVD dvd = (DVD)media
            if(columnIndex == TITLE_COL) {
                dvd.title = value
            } else if(columnIndex == AUTHOR_COL){
                dvd.author = value
            }
        }
    }
}
