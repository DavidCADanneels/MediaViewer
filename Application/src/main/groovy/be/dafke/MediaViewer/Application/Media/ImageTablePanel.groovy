package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.People.Person
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.DefaultCellEditor
import javax.swing.DefaultListSelectionModel
import javax.swing.JComboBox
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.ListSelectionModel
import javax.swing.RowSorter
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import javax.swing.table.TableColumn
import javax.swing.table.TableModel
import java.awt.Dimension

class ImageTablePanel extends JScrollPane implements ListSelectionListener {
    PictureOverviewDataModel dataModel
    JTable overviewTable
    boolean singleSelection
    boolean showSelection
    MediaOverviewPanel mediaOverviewPanel
//    Story story

    ImageTablePanel(MediaOverviewPanel mediaOverviewPanel) {
        this.mediaOverviewPanel = mediaOverviewPanel
        dataModel = new PictureOverviewDataModel()
        overviewTable = new JTable(dataModel)
        overviewTable.setPreferredScrollableViewportSize(new Dimension(500, 200))

//        overviewTable.setAutoCreateRowSorter(true)
        overviewTable.setRowSorter(new MediaRowSorter(dataModel))
        DefaultListSelectionModel selection = new DefaultListSelectionModel()
        selection.addListSelectionListener(this)
        overviewTable.setSelectionModel(selection)

        setSingleSelection(true)
        setShowSelection(true)
        setViewportView(overviewTable)
    }

    void setStory(Story story) {
//        this.story = story
        dataModel.setStory(story)
        JComboBox<Person> comboBox = new JComboBox<>()
        story.getPersons().each { comboBox.addItem(it) }
        TableColumn column = overviewTable.getColumnModel().getColumn(dataModel.OWNER_COL)
        column.setCellEditor(new DefaultCellEditor(comboBox))
    }

    void setShowSelection(boolean showSelection) {
        this.showSelection = showSelection
    }

    void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection
        if(singleSelection) {
            overviewTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        } else {
            overviewTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)
        }
    }

    void valueChanged(ListSelectionEvent e) {
        // FIXME: add 'showSelection &&' condition
//        if (showSelection && !e.getValueIsAdjusting()) {
        if (!e.getValueIsAdjusting()) {
            if(singleSelection) {
                showSingleSelection()
            } else {
                showMultipleSelection()
            }
        }
    }

    Picture getSingleSelectedPicture() {
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

    List<Picture> getAllSelectedPictures(){
        int[] selectedRows = overviewTable.getSelectedRows()
        RowSorter<? extends TableModel> rowSorter = overviewTable.getRowSorter()
        ArrayList<Picture> pictures = new ArrayList<>()
        for(int selectedRow : selectedRows) {
            Picture picture
            if(rowSorter){
                int rowInModel = rowSorter.convertRowIndexToModel(selectedRow)
                picture = dataModel.getObject(rowInModel)
            } else {
                picture = dataModel.getObject(selectedRow)
            }
            if(picture){
                pictures.add(picture)
            }
        }
        return pictures
    }

    void showSingleSelection(){
        Picture picture = getSingleSelectedPicture()
        mediaOverviewPanel.setPicture(picture)
    }

    void showMultipleSelection(){
        List<Picture> pictures = getAllSelectedPictures()
        mediaOverviewPanel.setPictures(pictures)
    }
}
