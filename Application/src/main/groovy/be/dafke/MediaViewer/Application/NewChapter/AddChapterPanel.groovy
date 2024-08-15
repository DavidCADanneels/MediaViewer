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
import java.awt.event.FocusEvent
import java.awt.event.FocusListener

import static java.util.ResourceBundle.getBundle

class AddChapterPanel extends JPanel implements FocusListener {
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
        indexField.addFocusListener(this)
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
//                HashMap<String, Chapter> mainChapters = story.getChapters()
//                mainChapters.put(index, chapter)
//                TODO: refresh Main.chaptersOverviewPanel.chapterTablePanel.dataModel
                Main.chaptersOverviewPanel.chapterTablePanel.dataModel.fireTableDataChanged()
                indexField.text = ''
                titleField.text = ''
            }
        }
    }

    @Override
    void focusGained(FocusEvent e) {

    }

    @Override
    void focusLost(FocusEvent e) {
        // TODO: use DocumentListener iso FocusListener
        // when content of field is changed, search for best matching parent chapter + link automatically

        String index = indexField.text
        System.out.println "index: ${index}"
        if(Main.activeStory == null){
            System.out.println "STORY NULL"
        } else {
            System.out.println "Story: ${Main.activeStory.title}"
        }
        List<Chapter> list = Main.activeStory.getChapters()
        System.out.println "${list.size()} chapters"

        Chapter parent = list.find { Chapter chapter -> chapter.prefix == index }
        System.out.println "parentChapter:${parent}"

        while(parent == null && index.length()>2){
            index = index.substring(0,index.length()-2)
            System.out.println "new index: ${index}"
            parent = list.find { Chapter chapter -> chapter.prefix == index }
        }
        if(parent){
            System.out.println("Found parent: ${parent.title} with index ${index}")
            subChapterOf.selected = true
            parentChapterField.text = Main.getLongTitle(parent)
        } else {
            subChapterOf.selected = false
            parentChapterField.text = ""
        }
    }
}
