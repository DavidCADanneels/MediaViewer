package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class MediaBrowsePanel extends JPanel {
    Story story
    Chapter chapter = null
    File[] files

    JButton browseForFiles
    JTextField filePathField

    MediaBrowsePanel() {
        filePathField = new JTextField(50)
        browseForFiles = new JButton("Browse ...")
        browseForFiles.addActionListener { e -> fileChoosenAction() }
        //
        if(chapter != null){
            String path = "... TODO: calculate 'C:\\Users\\david\\Pictures\\Laos 2024\\02\\01' if index is '0201'"
            // TODO: browseForFiles setDefault/StartPath to the above ...
            // filePathField.text = path
        }
        //
        add new JLabel("File/Folder:")
        add filePathField
        add browseForFiles
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

    File[] getFiles() {
        return files
    }
}
