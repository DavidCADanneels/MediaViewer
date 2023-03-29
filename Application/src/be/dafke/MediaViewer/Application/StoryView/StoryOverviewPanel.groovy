package be.dafke.MediaViewer.Application.StoryView

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Chapter
import be.dafke.MediaViewer.ObjectModel.Story

import com.fasterxml.jackson.dataformat.xml.XmlMapper

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.JTextField
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import static java.util.ResourceBundle.getBundle

class StoryOverviewPanel extends JPanel implements ActionListener{
    JTextField nameField, descriptionField
    JButton createButton, openButton
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
        createButton.addActionListener this

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
        openButton = new JButton("Open")
        openButton.addActionListener({ e ->
            Story story = getSelectedItem()
            openStory(story)
        } )
        panel.add openButton
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

    static Story getSelectedItem(){
        int row = overviewTable.getSelectedRow()
        if(row == -1){
            // TODO: disable button of none is selected
            return null
        } else {
            Main.stories.get(row)
        }
    }

    static void openStory(Story story){
        if(story){
            Main.switchView(Main.CHAPTERS)
//            Main.chapterPanel.setChapter(story.getRootChapter())
//            Main.chapterPanel.setStory(story)
        } else {
            System.err.println("Story is 'null'")
        }
    }

    @Override
    void actionPerformed(ActionEvent e) {
        Object source = e.getSource()
        if (source == createButton) {
            String storyName = nameField.text.trim()
            String description = descriptionField.text.trim()
            if (storyName) {
//                Story story = new Story(storyName, description, "")
                Story story = new Story()
                story.setTitle(storyName)
                story.setShortDescription(description)
//                Chapter root = new Chapter(story, story.getTitle(), '00')
                Chapter root = new Chapter()
//                root.setStory(story)
                root.setTitle(storyName)
                root.setPrefix("00")
//                story.setRootChapter(root)
                Main.addStory(story)
                nameField.text = ''
                descriptionField.text = ''

                XmlMapper xmlMapper = new XmlMapper()
                String xml = xmlMapper.writeValueAsString(story)
                System.out.println(xml)
            }
        }
    }
}
