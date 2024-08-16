package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter

import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import java.awt.event.FocusEvent
import java.awt.event.FocusListener

import static java.util.ResourceBundle.getBundle

class IndexChapterConverterPanel extends JPanel implements FocusListener {
    //    TODO: use 'JComboBox' with available chapters, iso (non-editable) JTextField parentChapterField
    JTextField indexField, parentChapterField
    JCheckBox subChapterOf
    Chapter parentChapter

    IndexChapterConverterPanel(Chapter chapter) {
        indexField = new JTextField(6)
        indexField.addFocusListener(this)

        add new JLabel("${getBundle("MediaViewer").getString("CHAPTER_INDEX")}:")
        add indexField

        subChapterOf = new JCheckBox("Sub Chapter of:")
        subChapterOf.selected = false
        subChapterOf.enabled = false
        add subChapterOf

        parentChapterField = new JTextField(20)
        parentChapterField.enabled = false
        add parentChapterField

        if(chapter != null) {
            indexField.text = chapter.prefix
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

        parentChapter = list.find { Chapter chapter -> chapter.prefix == index }
        System.out.println "parentChapter:${parentChapter}"

        while(parentChapter == null && index.length()>2){
            index = index.substring(0,index.length()-2)
            System.out.println "new index: ${index}"
            parentChapter = list.find { Chapter chapter -> chapter.prefix == index }
        }
        if(parentChapter){
            System.out.println("Found parentChapter: ${parentChapter.title} with index ${index}")
            subChapterOf.selected = true
            parentChapterField.text = Main.getLongTitle(parentChapter)
        } else {
            subChapterOf.selected = false
            parentChapterField.text = ""
        }
    }

    Chapter getParentChapter() {
        return parentChapter
    }

    String getIndex() {
        return indexField.text
    }

    void setIndex(String index) {
        indexField.text = index
    }
}
