package be.dafke.MediaViewer.Application.Chapter

import be.dafke.MediaViewer.Application.Media.NewMediaDialog
import be.dafke.MediaViewer.Application.Media.NewTextDialog
import be.dafke.MediaViewer.Application.NewChapter.NewChapterDialog
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JSplitPane
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class ChaptersOverviewPanel extends JPanel {
    JButton addChapterButton, addMediaToChapterButton, addTextToChapterButton
    ChapterTablePanel chapterTablePanel
    ChapterPanel chapterPanel
    Story story
    Chapter selectedChapter

    ChaptersOverviewPanel() {
        setLayout(new BorderLayout())

        chapterTablePanel = new ChapterTablePanel(this)
        chapterPanel = new ChapterPanel()

        JPanel north = new JPanel()
        north.setLayout(new BorderLayout())
        north.add chapterTablePanel, BorderLayout.CENTER
        north.add addChapterButtons(), BorderLayout.EAST

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT)
        splitPane.add north, JSplitPane.TOP
        splitPane.add chapterPanel, JSplitPane.BOTTOM

        add splitPane, BorderLayout.CENTER
    }

    JPanel addChapterButtons(){

        addChapterButton = new JButton(getBundle("MediaViewer").getString("ADD_CHAPTER_BUTTON"))
        addChapterButton.addActionListener { e -> createNewChapter() }

        addMediaToChapterButton = new JButton(getBundle("MediaViewer").getString("ADD_MEDIA_TO_CHAPTER_BUTTON"))
        addMediaToChapterButton.addActionListener{e ->
            addMediaToChapter()
        }

        addTextToChapterButton = new JButton(getBundle("MediaViewer").getString("ADD_TEXT_TO_CHAPTER_BUTTON"))
        addTextToChapterButton.addActionListener{e ->
            addTextToChapter()
        }

        JPanel panel = new JPanel()
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))
        panel.add addChapterButton
        panel.add addMediaToChapterButton
        panel.add addTextToChapterButton
        return panel
    }

    void addMediaToChapter(){
        NewMediaDialog newMediaDialog = new NewMediaDialog(story, selectedChapter)
        newMediaDialog.setLocation(getLocationOnScreen())
        newMediaDialog.visible = true
    }

    void addTextToChapter(){
        NewTextDialog newTextDialog = new NewTextDialog(story, selectedChapter)
        newTextDialog.setLocation(getLocationOnScreen())
        newTextDialog.visible = true
    }

    void createNewChapter(){
        NewChapterDialog newChapterDialog = new NewChapterDialog()
        newChapterDialog.setLocation(getLocationOnScreen())
        newChapterDialog.visible = true
    }

    void setChapter(Chapter chapter){
        selectedChapter = chapter
        chapterPanel.setChapter(chapter)
//        chapterTablePanel
    }

    void setStory(Story story) {
        this.story = story
        chapterPanel.setStory(story)
        chapterTablePanel.setStory(story)
    }
}
