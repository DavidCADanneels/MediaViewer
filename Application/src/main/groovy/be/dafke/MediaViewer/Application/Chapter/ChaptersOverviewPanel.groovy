package be.dafke.MediaViewer.Application.Chapter

import javax.swing.JPanel
import java.awt.BorderLayout

class ChaptersOverviewPanel extends JPanel {

    ChapterTablePanel chapterTablePanel

    ChaptersOverviewPanel() {
        setLayout(new BorderLayout())

        chapterTablePanel = new ChapterTablePanel()

        add chapterTablePanel, BorderLayout.CENTER

    }

//    void setStory(Story story) {
//        this.story = story
//        dataModel.setStory(story)
//    }
}
