package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Text
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JLabel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.JTextField
import java.awt.BorderLayout
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent

class AddTextPanel extends JPanel {
    Story story
    Chapter chapter = null

    JTextField titleField
    JButton saveAction
    JTextArea textArea

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
        saveAction.enabled = false
        //
        ownerPanel = new OwnerPanel(story)
        ownerPanel.add saveAction

        titleField = new JTextField(20)
        JPanel titlePanel = new JPanel()
        titleField.addFocusListener(new FocusAdapter() {
            @Override
            void focusLost(FocusEvent e) {
                checkEnableConditions()
            }
        })

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

    void checkEnableConditions(){
        saveAction.enabled = indexPanel.fullIndex != '' && titleField.text.trim() != ''
    }

    void saveAction() {
        // TODO save Text from JTextArea
        String index = indexPanel.fullIndex
        String title = titleField.text.trim()
//        if(index == null || title == '')
        String extension = 'txt'
        String fileName = "${index}-${title}.${extension}"
        System.out.println("fileName=${fileName}")

        chapter = Main.getLowestParentChapter(story, index)
        File chapterFolder = Main.getChapterFolder(story, index)
        System.out.println("chapterFolder=${chapterFolder}")

        File textFile = new File(chapterFolder, fileName)

        System.out.println("textFile=${textFile.path}")

        String text = textArea.text.trim()
        if(text != '') {
            textFile.write text
        }

        Text newText = new Text()
        newText.content = text
        newText.title = title

        story.textFragments.add(newText)

        // TODO: save content of textArea to file
//        textArea.text

    }

    JScrollPane createTextAreaPanel() {
        textArea = new JTextArea(10,40)
//        textArea.setRows(25)
//        textArea.setColumns(25)
        textArea.setWrapStyleWord(true)

        JScrollPane scrollPane = new JScrollPane(textArea)


        return scrollPane
    }
}
