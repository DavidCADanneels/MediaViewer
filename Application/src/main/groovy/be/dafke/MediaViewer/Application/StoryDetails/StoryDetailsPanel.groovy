package be.dafke.MediaViewer.Application.StoryDetails

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.JTextField
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class StoryDetailsPanel extends JPanel {
    JTextField nameField, descrField
    JTextArea longText
    JButton saveChanges

    StoryDetailsPanel() {
        setLayout(new BorderLayout())

        nameField = new JTextField(20)
        descrField = new JTextField(30)
        nameField.editable = false
        saveChanges = new JButton(getBundle("MediaViewer").getString("SAVE_STORY_BUTTON"))
        saveChanges.addActionListener({ e -> save()})

        JPanel line1 = new JPanel()
        line1.add new JLabel("${getBundle("MediaViewer").getString("NAME")}:")
        line1.add nameField
        line1.add saveChanges

        JPanel line2 = new JPanel()
        line2.add new JLabel("${getBundle("MediaViewer").getString("DESCR")}:")
        line2.add descrField

        JPanel north = new JPanel()
        north.setLayout(new BoxLayout(north,BoxLayout.Y_AXIS))
        north.add line1
        north.add line2

        longText = new JTextArea(20, 50)
        JScrollPane scrollPane = new JScrollPane(longText)
        JPanel center = new JPanel()
        center.add scrollPane

        add north, BorderLayout.NORTH
        add center, BorderLayout.CENTER
        add new StoryButtonsPanel(), BorderLayout.SOUTH
    }

    void setStory() {
        Story story = Main.activeStory
        if(story) {
            nameField.setText(story.getTitle())
            descrField.setText(story.getShortDescription())
            longText.setText(story.getIntroText())
        }
    }

    void save(){
        Story story = Main.activeStory
        String title = nameField.getText().trim()
        String descr = descrField.text.trim()
        String intro = longText.text.trim()
        story.setTitle(title)
        story.setShortDescription(descr)
        story.setIntroText(intro)
        File file = Main.storyMap.get(story)
        if(file != null) {
            IoTools.writeObject(story, file)
        }
    }
}
