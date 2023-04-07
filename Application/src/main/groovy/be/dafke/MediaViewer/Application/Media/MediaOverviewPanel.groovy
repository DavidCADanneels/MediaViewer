package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.DefaultCellEditor
import javax.swing.DefaultListSelectionModel
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JFileChooser
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JSplitPane
import javax.swing.JTable
import javax.swing.ListSelectionModel
import javax.swing.RowSorter
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import javax.swing.table.TableColumn
import javax.swing.table.TableModel
import java.awt.BorderLayout
import java.awt.Dimension

import static java.util.ResourceBundle.getBundle

class MediaOverviewPanel extends JPanel implements ListSelectionListener {
    MediaOverviewDataModel dataModel
    JButton backToStoryDetailsButton, backToStoryOverViewButton, participantButton, addMediaButton
    JTable overviewTable
    boolean singleSelection = true

    ImagePanel imagePanel
    Story story

    MediaOverviewPanel() {
        setLayout(new BorderLayout())
        dataModel = new MediaOverviewDataModel()
        overviewTable = new JTable(dataModel)
        overviewTable.setPreferredScrollableViewportSize(new Dimension(500, 200))
//        overviewTable.setAutoCreateRowSorter(true)
        overviewTable.setRowSorter(new MediaRowSorter(dataModel))
        DefaultListSelectionModel selection = new DefaultListSelectionModel()
        selection.addListSelectionListener(this)
        overviewTable.setSelectionModel(selection)
        if(singleSelection) {
            overviewTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        } else {
            overviewTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)
        }

        JScrollPane overviewScrol = new JScrollPane(overviewTable)

        imagePanel = new ImagePanel()

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT)
        splitPane.add overviewScrol, JSplitPane.TOP
        splitPane.add imagePanel, JSplitPane.BOTTOM
        add splitPane, BorderLayout.CENTER

        backToStoryOverViewButton = new JButton("${getBundle("MediaViewer").getString("BACK_TO_MAIN")}")
        backToStoryOverViewButton.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_OVERVIEW)
        }

        backToStoryDetailsButton = new JButton("${getBundle("MediaViewer").getString("BACK_TO_DETAILS")}")
        backToStoryDetailsButton.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_DETAILS)
        }

        participantButton = new JButton(getBundle("MediaViewer").getString("SHOW_PARTICIPANTS_FOR_STORY"))
        participantButton.addActionListener { e ->
            Main.switchView(Main.VIEW_PARTICIPANTS_FOR_STORY)
        }

        addMediaButton = new JButton(getBundle("MediaViewer").getString("ADD_MEDIA_BUTTON"))
        addMediaButton.addActionListener { e -> loadData() }

        JPanel south = new JPanel()
        south.add backToStoryOverViewButton
        south.add backToStoryDetailsButton
        south.add participantButton
        south.add addMediaButton

        add south, BorderLayout.SOUTH
    }

    void setStory(Story story) {
        this.story = story
        dataModel.setStory(story)
        imagePanel.setStory(story)
        JComboBox<Participant> comboBox = new JComboBox<>()
        story.getParticipants().each { comboBox.addItem(it) }
        TableColumn column = overviewTable.getColumnModel().getColumn(dataModel.OWNER_COL)
        column.setCellEditor(new DefaultCellEditor(comboBox))
    }

    void valueChanged(ListSelectionEvent e) {
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
        imagePanel.setPicture(picture)
            // TODO: (add option to) show selected image in new ImageFrame
    }

    void showMultipleSelection(){
        List<Picture> pictures = getAllSelectedPictures()
        imagePanel.setPictures(pictures)
            // TODO: (add option to) show selected image in new ImageFrame
    }

    void loadData(){
        File startFolder = Main.getSubFolder(story)

        JFileChooser chooser = new JFileChooser(startFolder)
        chooser.setMultiSelectionEnabled(true)
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles()
            files.each { File file ->
                if(file.name.toLowerCase().endsWith('.jpg')) {
                    Picture picture = new Picture()
                    String fileName = file.name - '.jpg'
                    picture.setFileName(fileName)
                    picture.setExtension('jpg')
                    picture.setSubFolderName('jpg')
                    // TODO: no need to reassign picture to picture ?
                    picture = IoTools.readAndDisplayMetadata(file, picture)

                    List<Picture> pictures = story.getPictures()
                    pictures.add(picture)

                    // TODO: show popup to set owner
                }
            }
            Main.mediaOverviewPanel.dataModel.fireTableDataChanged()
        }
        // TODO: add support to read Movies, Text, etc. (not only pictures)
//            Point locationOnScreen = getLocationOnScreen()
//            NewMediaDialog newMediaDialog = new NewMediaDialog()
//            newMediaDialog.setLocation(locationOnScreen)
//            newMediaDialog.visible = true

    }
}
