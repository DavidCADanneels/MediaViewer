package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JComboBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import static java.util.ResourceBundle.getBundle

class ChapterIndexConverterPanel extends JPanel implements ActionListener {

    JComboBox chapterComboBox
    JTextField prefixField, indexField


    ChapterIndexConverterPanel(Story story, Chapter chapter) {

        prefixField = new JTextField(6)
        prefixField.editable = false
        indexField = new JTextField(6)

        chapterComboBox = new JComboBox<>()
        story.getChapters().each { chapterComboBox.addItem(it) }
        if(chapter != null){
            chapterComboBox.setSelectedItem(chapter)
            chapterComboBox.enabled = false
        } else {
            chapterComboBox.enabled = true
            chapterComboBox.addActionListener this
        }
        add new JLabel("${getBundle("MediaViewer").getString("CHAPTER")}:")
        add chapterComboBox
        add new JLabel("${getBundle("MediaViewer").getString("CHAPTER_INDEX")}:")
        add prefixField
        add indexField
    }

    @Override
    void actionPerformed(ActionEvent e) {
        Chapter selectedChapter = (Chapter)chapterComboBox.selectedItem
        if(selectedChapter!=null){
            prefixField.text = selectedChapter.prefix
        }
    }

    Chapter getSelectedChapter() {
        return (Chapter)chapterComboBox.selectedItem
    }

    String getPrefixField() {
        return prefixField.text.trim()
    }

    String getFullIndex(){
        return prefixField.text.trim() + indexField.text.trim()
    }
}