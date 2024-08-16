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
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.JTextField
import java.awt.BorderLayout
import java.awt.CardLayout

class AddTextPanel extends JPanel {
    Story story
    Chapter chapter = null

    JTextField indexField
    JButton saveAction

    ChapterIndexConverterPanel indexPanel
    MediaBrowsePanel mediaBrowsePanel
    OwnerPanel ownerPanel

    AddTextPanel(Story story, Chapter chapter) {
        this.story = story
        this.chapter = chapter

        indexPanel = new ChapterIndexConverterPanel(story, chapter)
        mediaBrowsePanel = new MediaBrowsePanel()

        saveAction = new JButton("Add to Story")
        saveAction.addActionListener { e -> saveAction() }
        //
        ownerPanel = new OwnerPanel(story)
        ownerPanel.add saveAction

        JTextField titleField = new JTextField(20)
        JPanel titlePanel = new JPanel()
        titlePanel.add new JLabel('Title:')
        titlePanel.add titleField

        JPanel north = new JPanel()
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS))
        north.add indexPanel
        north.add ownerPanel
        north.add titlePanel

        JScrollPane textAreaPanel = createTextAreaPanel()

        setLayout(new BorderLayout())
        add north, BorderLayout.NORTH
        add textAreaPanel, BorderLayout.CENTER


        JPanel panel = new JPanel()
        panel.setLayout new BorderLayout()


        add textAreaPanel, BorderLayout.CENTER
        // TODO: add JTextArea in Center
        // TODO: add saveToFile Button in South

    }

    void saveAction() {
        // TODO save Text from JTextArea
    }

    JScrollPane createTextAreaPanel() {
        JTextArea textArea = new JTextArea(10,40)
//        textArea.setRows(25)
//        textArea.setColumns(25)
        textArea.setWrapStyleWord(true)

        JScrollPane scrollPane = new JScrollPane(textArea)


        return scrollPane
    }
}
