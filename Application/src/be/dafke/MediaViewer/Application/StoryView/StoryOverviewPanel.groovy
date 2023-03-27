package be.dafke.MediaViewer.Application.StoryView

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.StoryView.StoryOverviewDataModel
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
    JButton createButton
    static JTable overviewTable
    static StoryOverviewDataModel dataModel

    StoryOverviewPanel() {
        setLayout(new BorderLayout())
        add createTopPanel(), BorderLayout.NORTH
        add createCenterPanel(), BorderLayout.CENTER
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

    @Override
    void actionPerformed(ActionEvent e) {
        String storyName = nameField.text.trim()
        String description = descriptionField.text.trim()
        if(storyName) {
            Story story = new Story(storyName, description)
            Main.addStory(story)
            nameField.text = ''
            descriptionField.text = ''

            XmlMapper xmlMapper = new XmlMapper()
            String xml = xmlMapper.writeValueAsString(story)
            System.out.println(xml)
        }
    }
}