package be.dafke.MediaViewer.Application.StoryOverview

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
    JButton saveChanges
    JTextArea longText

    StoryDetailsPanel() {
        setLayout(new BorderLayout())

        nameField = new JTextField(20)
        descrField = new JTextField(60)
        nameField.editable = false
        saveChanges = new JButton(getBundle("MediaViewer").getString("SAVE_STORY_BUTTON"))
        saveChanges.addActionListener({ e -> save()})

        JPanel north = new JPanel()
        north.add new JLabel("${getBundle("MediaViewer").getString("NAME")}:")
        north.add nameField
        north.add new JLabel("${getBundle("MediaViewer").getString("DESCR")}:")
        north.add descrField
        north.add saveChanges

        longText = new JTextArea(20, 50)
        JScrollPane scrollPane = new JScrollPane(longText)
        JPanel center = new JPanel()
        center.add scrollPane



        add north, BorderLayout.NORTH
        add center, BorderLayout.CENTER
//        add longText, BorderLayout.CENTER
    }

    void save(){

    }
}
