package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.NewStory.NewStoryDialog
import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import be.dafke.MediaViewer.ObjectModel.Media.Media
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Media.Size2D
import be.dafke.MediaViewer.ObjectModel.Media.Story
import com.sun.imageio.plugins.jpeg.JPEGImageReader

import javax.imageio.*;
import javax.imageio.stream.*;
import javax.imageio.metadata.*;

import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class MediaOverviewPanel extends JPanel {
    MediaOverviewDataModel dataModel
    JButton backToStoryDetailsButton, backToStoryOverViewButton, participantButton, addMediaButton
    static JTable overviewTable

    MediaOverviewPanel() {
        setLayout(new BorderLayout())
        dataModel = new MediaOverviewDataModel()
        overviewTable = new JTable(dataModel)
        add new JScrollPane(overviewTable), BorderLayout.CENTER

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
        addMediaButton.addActionListener { e ->
            JFileChooser chooser = new JFileChooser()
            chooser.setMultiSelectionEnabled(true)
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File[] files = chooser.getSelectedFiles()
                files.each { File file ->
                    // TODO: use 'name' to store Catalog

                    // TODO: use 'extension' to store in Media object
//                    System.out.println("name: ${name}")
//                    System.out.println("file: ${file.getName()}")

                    if(file.name.toLowerCase().endsWith('.jpg')) {
                        Picture picture = new Picture()
                        String fileName = file.name - '.jpg'
                        picture.setFileName(fileName)
                        picture.setExtension('jpg')
                        picture.setSubFolderName('jpg')
                        Size2D size2D = IoTools.readAndDisplayMetadata(file)
                        picture.setSize(size2D)

                        Story story = Main.getActiveStory()
                        if(story){
                            List<String> mediaList = story.getMediaList()
                            if(mediaList){
                                mediaList.add(fileName)
                            }


//                            Main.getCatalog().sourceFiles.put(fileName, file)
//                            Main.catalog.mediaFiles.put(fileName, picture)

                        }
//
                        // TODO: show popup to set owner
                    }
                }
                Main.mediaOverviewPanel.dataModel.fireTableDataChanged()
            }
//            Point locationOnScreen = getLocationOnScreen()
//            NewMediaDialog newMediaDialog = new NewMediaDialog()
//            newMediaDialog.setLocation(locationOnScreen)
//            newMediaDialog.visible = true
        }

        JPanel south = new JPanel()
//        south.add backToStoryOverViewButton
        south.add backToStoryDetailsButton
        south.add participantButton
        south.add addMediaButton

        add south, BorderLayout.SOUTH
    }
}
