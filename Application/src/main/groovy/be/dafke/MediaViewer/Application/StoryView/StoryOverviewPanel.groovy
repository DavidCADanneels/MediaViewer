package be.dafke.MediaViewer.Application.StoryView

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Chapter
import be.dafke.MediaViewer.ObjectModel.IoTools
import be.dafke.MediaViewer.ObjectModel.Story
import com.fasterxml.jackson.dataformat.xml.XmlMapper

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.JTextField
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class StoryOverviewPanel extends JPanel {
    JTextField nameField, descriptionField
    JButton createButton, chaptersButton, participantsButton
    static JTable overviewTable
    static StoryOverviewDataModel dataModel

    StoryOverviewPanel() {
        setLayout(new BorderLayout())
        add createTopPanel(), BorderLayout.NORTH
        add createCenterPanel(), BorderLayout.CENTER
        add createBottomPanel(), BorderLayout.SOUTH
    }

    JPanel createTopPanel(){
        nameField = new JTextField(20)
        descriptionField = new JTextField(30)
        createButton = new JButton("Create")
        createButton.addActionListener { e ->
            String storyName = nameField.text.trim()
            String description = descriptionField.text.trim()
            if (storyName) {
                Story story = new Story()
                story.setTitle(storyName)
                story.setShortDescription(description)
                Chapter root = new Chapter()
                root.setTitle(storyName)
                root.setPrefix("00")
                Main.addStory(story)
                nameField.text = ''
                descriptionField.text = ''
            }
        }

        JPanel line1 = new JPanel()
        line1.add new JLabel("${getBundle("MediaViewer").getString("NAME")}:")
        line1.add nameField
        line1.add createButton

        JPanel line2 = new JPanel()
        line2.add new JLabel("${getBundle("MediaViewer").getString("DESCR")}:")
        line2.add descriptionField

        JPanel panel = new JPanel()
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS))
        panel.add line1
        panel.add line2

        panel
    }

    JPanel createBottomPanel(){
        JPanel panel = new JPanel()
        chaptersButton = new JButton(getBundle("MediaViewer").getString("SHOW_CHAPTERS_FOR_STORY"))
        chaptersButton.addActionListener { e ->
            Story story = getSelectedItem()
            if(story) {
                // TODO: disable button if nothing is selected
                showChapters(story)
            }
        }
        panel.add chaptersButton

        participantsButton = new JButton(getBundle("MediaViewer").getString("SHOW_PARTICPANTS_FOR_STORY"))
        participantsButton.addActionListener { e ->
            Story story = getSelectedItem()
            if(story) {
                // TODO: disable button if nothing is selected
                showParticipants(story)
            }
        }
        panel.add participantsButton
        panel
    }

    JScrollPane createCenterPanel(){
        dataModel = new StoryOverviewDataModel()
//        overviewTable = new StoryTable(dataModel)
//        overviewTable = new IndexTable(dataModel)
//        overviewTable = new ChapterTable(dataModel)
        overviewTable = new JTable(dataModel)

        new JScrollPane(overviewTable)
    }

//    static void refresh(){
//        dataModel.fireTableDataChanged()
//    }

    Story getSelectedItem(){
        int row = overviewTable.getSelectedRow()
        if(row == -1){
            // TODO: disable button of none is selected
            return null
        } else {
            Main.stories.get(row)
        }
    }

    void showChapters(Story story){
        if(story){
            Main.switchView(Main.CHAPTERS)
//            Main.chapterPanel.setChapter(story.getRootChapter())
//            Main.chapterPanel.setStory(story)
        } else {
            System.err.println("Story is 'null'")
        }
    }

    void showParticipants(Story story){

    }
}
