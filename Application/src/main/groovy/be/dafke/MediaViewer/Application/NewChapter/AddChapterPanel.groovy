package be.dafke.MediaViewer.Application.NewChapter

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import java.awt.GridLayout

import static java.util.ResourceBundle.getBundle

class AddChapterPanel extends JPanel {
    JTextField indexField, titleField, parentChapterField
    //    TODO: use 'dropdownlist' with available chapters, iso (non-editable) JTextField parentChapterField
    JButton createButton
    JCheckBox subChapterOf

    // TODO: select chapter with prefix, while editing prefix field
    Story story

    AddChapterPanel() {
        indexField = new JTextField(6)
        titleField = new JTextField(30)
        createButton = new JButton("Create")
        createButton.addActionListener({ e -> showDialog() })
        JPanel line1 = new JPanel()
        line1.add new JLabel("${getBundle("MediaViewer").getString("CHAPTER_INDEX")}:")
        line1.add indexField

        subChapterOf = new JCheckBox("Sub Chapter of:")
        subChapterOf.selected = false
        subChapterOf.enabled = false
        line1.add subChapterOf

        parentChapterField = new JTextField(20)
        parentChapterField.enabled = false
        line1.add parentChapterField

        JPanel line2 = new JPanel()
        line2.add new JLabel("${getBundle("MediaViewer").getString("CHAPTER_TITLE")}:")
        line2.add titleField
        line2.add createButton

        setLayout(new GridLayout(0,1))
        add line1
        add line2
    }

    void showDialog(){
        String index = indexField.text.trim()
        String title = titleField.text.trim()
        if(index && title){
            Chapter chapter = new Chapter()
            chapter.setPrefix(index)
            chapter.setTitle(title)
            Story story = Main.activeStory
            if (story!=null && story.getChapters()!=null){
                List<Chapter> chapters = story.getChapters()
                chapters.add(chapter)
//                TODO: refresh Main.chaptersOverviewPanel.chapterTablePanel.dataModel
                Main.chaptersOverviewPanel.chapterTablePanel.dataModel.fireTableDataChanged()
                indexField.text = ''
                titleField.text = ''
            }
        }
    }
}
