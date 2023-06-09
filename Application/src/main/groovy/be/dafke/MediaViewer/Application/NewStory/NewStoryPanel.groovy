package be.dafke.MediaViewer.Application.NewStory

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

import static java.util.ResourceBundle.getBundle

class NewStoryPanel extends JPanel {
    JTextField nameField, descriptionField
    JButton createButton

    NewStoryPanel() {
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
                Main.addStory(story, null)
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

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS))
        add line1
        add line2
    }
}
