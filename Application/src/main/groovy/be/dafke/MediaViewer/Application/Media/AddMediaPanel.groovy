package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComboBox
import javax.swing.JFileChooser
import javax.swing.JPanel
import javax.swing.JLabel
import javax.swing.JTextField

class AddMediaPanel extends JPanel {
    Story story
    Chapter chapter = null
    File[] files

    JButton browseForFiles//, browseForFolders
    JTextField filePathField
    JCheckBox setOwner

    JComboBox ownerComboBox

    JCheckBox setChapter
    JComboBox chapterComboBox
    JTextField indexField

    JButton saveAction

    AddMediaPanel(Story story, Chapter chapter) {
        this.story = story
        this.chapter = chapter

        JPanel filePanel = createFilePanel(chapter)
        JPanel ownerPanel = createOwnerPanel()
        JPanel chapterAndIndexPanel = createChapterAndIndexPanel(chapter)

        saveAction = new JButton("Add to Story")
        saveAction.addActionListener { e -> saveAction() }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))
        add filePanel
        add ownerPanel
        add chapterAndIndexPanel
        add saveAction
    }

    JPanel createFilePanel(Chapter chapter){
        filePathField = new JTextField(50)
        browseForFiles = new JButton("Browse ...")
        browseForFiles.addActionListener { e -> fileChoosenAction() }
        //
        JPanel filePanel = new JPanel()
        filePanel.add new JLabel("File/Folder:")
        filePanel.add filePathField
        filePanel.add browseForFiles

        return filePanel
    }

    JPanel createOwnerPanel(){
        ownerComboBox = new JComboBox<>()
        story.getParticipants().each { ownerComboBox.addItem(it) }
        //
        setOwner = new JCheckBox("Set Owner")
        setOwner.setSelected true
        setOwner.addActionListener { e ->
            if(setOwner.selected){
//                ownerComboBox.enabled = true
            } else {
                ownerComboBox.setSelectedIndex(-1)
//                ownerComboBox.enabled = false
            }
            ownerComboBox.enabled = setOwner.selected
        }
        //
        JPanel ownerPanel = new JPanel()
        ownerPanel.add setOwner
        ownerPanel.add ownerComboBox
        return ownerPanel
    }

    JPanel createChapterAndIndexPanel(Chapter chapter){
        JPanel panel = new JPanel()
        panel.add new JLabel("Chapter:")
        indexField = new JTextField(30)

        if(chapter != null) {
            JTextField chapterField = new JTextField(chapter.toString())
            chapterField.editable = false
            panel.add chapterField
            indexField.text = chapter.toString()
        } else {
            setChapter = new JCheckBox("Set Chapter and Index")
            setChapter.setSelected true
            setChapter.addActionListener { e ->
                if (setChapter.selected) {
//                chapterComboBox.enabled = true
//                indexField.enabled = true
                } else {
                    chapterComboBox.setSelectedIndex(-1)
//                chapterComboBox.enabled = false
//                indexField.enabled = true
                }
                chapterComboBox.enabled = setChapter.selected
                indexField.enabled = setChapter.selected
            }
            panel.add setChapter
            //
            chapterComboBox = new JComboBox<>()
            story.getChapters().each { chapterComboBox.addItem(it) }
            panel.add chapterComboBox
        }

        panel.add new JLabel("Index:")
        panel.add indexField
        return panel
    }

    void fileChoosenAction() {
        File startFolder = Main.getSubFolder(story)
        JFileChooser chooser = new JFileChooser(startFolder)
        chooser.setMultiSelectionEnabled(true)
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            files = chooser.getSelectedFiles()
            if (files.size() == 1) {
                filePathField.text = files[0].path
            } else if (files.size() > 1) {
                filePathField.text = files[0].parentFile.path
            } else {
                filePathField.text = ''
            }
        }
    }

    void saveAction() {
        files.each { File file ->
            int index = file.name.lastIndexOf('.')
            String extension = file.name.substring(index + 1)
            System.out.println("extension: $extension")
            if (['jpg', 'jpeg', 'heic'].contains(extension.toLowerCase())) {
                Picture picture = new Picture()
                String fileName = file.name - ".${extension}"
                System.out.println("fileName: $fileName")
                picture.setFileName(fileName)
                picture.setExtension(extension)
                IoTools.readAndDisplayMetadata(file, picture)

                if (chapter == null) {
                    int chapterIndex = chapterComboBox.getSelectedIndex()
                    if (chapterIndex == -1) {
                        chapter = null
                    } else {
                        chapter = story.getChapters().get(chapterIndex)
                    }
                }
                picture.setOwner(ownerComboBox.getSelectedIndex())
                picture.setChapter(chapter.getPrefix())
                picture.setIndexNumber(indexField.getText().trim())
                story.getPictures().add(picture)
            }
        }

        // TODO: add support to read Movies, Text, etc. (not only pictures)
//            Point locationOnScreen = getLocationOnScreen()
//            NewMediaDialog newMediaDialog = new NewMediaDialog()
//            newMediaDialog.setLocation(locationOnScreen)
//            newMediaDialog.visible = true
    }
}
