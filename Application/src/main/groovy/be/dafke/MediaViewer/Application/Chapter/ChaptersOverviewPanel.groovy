package be.dafke.MediaViewer.Application.Chapter

import be.dafke.MediaViewer.Application.NewChapter.NewChapterDialog
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JButton
import javax.swing.JPanel
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class ChaptersOverviewPanel extends JPanel {
    JButton addChapterButton
    ChapterTablePanel chapterTablePanel

    ChaptersOverviewPanel() {
        setLayout(new BorderLayout())

        chapterTablePanel = new ChapterTablePanel()

        add chapterTablePanel, BorderLayout.CENTER

        addChapterButton = new JButton(getBundle("MediaViewer").getString("ADD_CHAPTER_BUTTON"))
        addChapterButton.addActionListener { e ->
            NewChapterDialog newChapterDialog = new NewChapterDialog()
            newChapterDialog.setLocation(getLocationOnScreen())
            newChapterDialog.visible = true
        }

        JPanel south = new JPanel()

        south.add addChapterButton

        add south, BorderLayout.SOUTH
    }

    void setStory(Story story) {
        chapterTablePanel.setStory(story)
    }
}
