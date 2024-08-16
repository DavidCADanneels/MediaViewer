package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Media
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Media.Text
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.ButtonGroup
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComboBox
import javax.swing.JFileChooser
import javax.swing.JPanel
import javax.swing.JLabel
import javax.swing.JRadioButton
import javax.swing.JTextArea
import javax.swing.JTextField
import java.awt.BorderLayout
import java.awt.CardLayout

class AddMediaPanel extends JPanel {
    Story story
    Chapter chapter = null

    JTextField indexField
    JButton saveAction

    ChapterIndexConverterPanel indexPanel
    MediaBrowsePanel mediaBrowsePanel
    OwnerPanel ownerPanel

//    CardLayout cardLayoutCenter
//    JPanel center

    JRadioButton files, text

    final String VIEW_TEXT = 'TEXT'
    final String VIEW_FILES = 'FILES'

    AddMediaPanel(Story story, Chapter chapter) {
        this.story = story
        this.chapter = chapter

        indexPanel = new ChapterIndexConverterPanel(story, chapter)
        mediaBrowsePanel = new MediaBrowsePanel()

        saveAction = new JButton("Add to Story")
        saveAction.addActionListener { e -> saveAction() }
        //
        files = new JRadioButton("Select file(s)")
        text = new JRadioButton("Enter text and save to file")
        files.addActionListener { e-> showFilesInput() }
        text.addActionListener { e-> showTextInput() }
        files.selected = true
        ButtonGroup group = new ButtonGroup()
        group.add(files)
        group.add(text)
        //
        ownerPanel = new OwnerPanel(story)
        ownerPanel.add files
        ownerPanel.add text
        ownerPanel.add saveAction

        JPanel north = new JPanel()
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS))
        north.add indexPanel
        north.add ownerPanel


//        cardLayoutCenter = new CardLayout()
//        center = new JPanel(cardLayoutCenter)
//        center = new JPanel()
//        center.setLayout
//        center.add mediaBrowsePanel//, VIEW_FILES
//        center.add createTextAreaInputPanel(), VIEW_TEXT
        //

        setLayout(new BorderLayout())
        add north, BorderLayout.NORTH
        add mediaBrowsePanel, BorderLayout.CENTER
//        add center, BorderLayout.CENTER
    }

    JPanel createTextAreaInputPanel() {
        JPanel panel = new JPanel()
        panel.setLayout new BorderLayout()

        JTextField titleField = new JTextField(20)
        JPanel north = new JPanel()
        north.add new JLabel('Title:')
        north.add titleField
        add north, BorderLayout.NORTH

        add new JTextArea(10,40), BorderLayout.CENTER
        // TODO: add JTextArea in Center
        // TODO: add saveToFile Button in South

        return panel
    }

    void saveAction() {
        // TODO: use other switch, e.g. radioButton Files/Text
        if(mediaBrowsePanel.files){
            saveFiles()
        } else {
            // TODO save Text from JTextArea
        }
    }

    void saveFiles() {
        mediaBrowsePanel.files.each { File file ->
            int index = file.name.lastIndexOf('.')
            String extension = file.name.substring(index + 1)
            System.out.println("extension: $extension")
            String fileName = file.name - ".${extension}"
            System.out.println("fileName: $fileName")

            Media media
            if (['jpg', 'jpeg', 'heic'].contains(extension.toLowerCase())) {
                media = new Picture()
                IoTools.readAndDisplayMetadata(file, media)
//            } else if (['txt', 'adoc', 'md'].contains(extension.toLowerCase())) {
//                media = new Text()
                // ... get Prefix, Title and TextContent
            }
            if(media != null) {
                media.setFileName(fileName)
                media.setExtension(extension)
                if (chapter == null) {
                    String chapterIndex = indexPanel.prefixField
                    if (chapterIndex == -1) {
                        chapter = null
                    } else {
                        chapter = story.getChapters().get(chapterIndex)
                    }
                }
                if (ownerPanel.setOwnerChecked) {
                    media.setOwner(ownerPanel.getSelectedIndex())
                }
                if (chapter != null) {
                    media.setChapter(chapter.getPrefix())
                } else {

                }
                media.setIndexNumber(indexPanel.prefixField.trim())
                story.getPictures().add(media)
            }
        }

        // TODO: add support to read Movies, Text, etc. (not only pictures)
//            Point locationOnScreen = getLocationOnScreen()
//            NewMediaDialog newMediaDialog = new NewMediaDialog()
//            newMediaDialog.setLocation(locationOnScreen)
//            newMediaDialog.visible = true
    }

    void showFilesInput() {
//        cardLayoutCenter.show(center, VIEW_FILES)
    }

    void showTextInput() {
//        cardLayoutCenter.show(center, VIEW_TEXT)
    }
}
