package be.dafke.MediaViewer.Application.NewChapter

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.Media.IndexChapterConverterPanel
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import java.awt.GridLayout

import static java.util.ResourceBundle.getBundle

class AddChapterPanel extends JPanel {
    JTextField titleField
    IndexChapterConverterPanel indexPanel
    JButton createButton

    AddChapterPanel() {
        setLayout(new GridLayout(0,1))
        indexPanel = new IndexChapterConverterPanel(null)
        add indexPanel
        add createLine2()
    }

    JPanel createLine2(){
        titleField = new JTextField(30)
        createButton = new JButton("Create")
        createButton.addActionListener({ e -> showDialog() })

        JPanel line2 = new JPanel()
        line2.add new JLabel("${getBundle("MediaViewer").getString("CHAPTER_TITLE")}:")
        line2.add titleField
        line2.add createButton
        return line2
    }

    void showDialog(){
        String index = indexPanel.index.trim()
        String title = titleField.text.trim()
        if(index && title){
            Chapter chapter = new Chapter()
            chapter.setPrefix(index)
            chapter.setTitle(title)
            Chapter parentChapter = indexPanel.parentChapter
            if(parentChapter!=null) {
                chapter.setParentChapter(parentChapter.prefix)
                parentChapter.getSubChapters().add(chapter.prefix)
            }
            Story story = Main.activeStory
            if (story!=null && story.getChapters()!=null){
                List<Chapter> chapters = story.getChapters()
                chapters.add(chapter)
//                HashMap<String, Chapter> mainChapters = story.getChapters()
//                mainChapters.put(index, chapter)
//                TODO: refresh Main.chaptersOverviewPanel.chapterTablePanel.dataModel
                Main.chaptersOverviewPanel.chapterTablePanel.dataModel.fireTableDataChanged()
                indexPanel.index = ''
                titleField.text = ''
            }
        }
    }
}
