package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.DefaultListSelectionModel
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JSplitPane
import javax.swing.JTable
import javax.swing.RowSorter
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import javax.swing.table.TableModel
import java.awt.BorderLayout
import java.awt.Dimension

import static java.util.ResourceBundle.getBundle

class MediaOverviewPanel extends JPanel implements ListSelectionListener {
    MediaOverviewDataModel dataModel
    JButton backToStoryDetailsButton, backToStoryOverViewButton, participantButton, addMediaButton
    JTable overviewTable

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
//            Main.participantsOverviewPanel.dataModel.setStory(Main.activeStory)
//            Main.participantsOverviewPanel.dataModel.setStory(story)
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
    }

    void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            showSelection()
        }
    }

    void showSelection(){
        Picture picture

        int row = overviewTable.getSelectedRow()
        if(row == -1) {
            picture = null
        } else {
            RowSorter<? extends TableModel> rowSorter = overviewTable.getRowSorter()
            int col = overviewTable.getSelectedColumn()
            if (rowSorter) {
                int rowInModel = rowSorter.convertRowIndexToModel(row)
                picture = dataModel.getObject(rowInModel, col)
            } else {
                picture = dataModel.getObject(row, col)
            }
        }
        imagePanel.setPicture(picture)
            // TODO: (add option to) show selected image in new ImageFrame
    }

    void loadData(){
//        Story story = Main.activeStory
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
