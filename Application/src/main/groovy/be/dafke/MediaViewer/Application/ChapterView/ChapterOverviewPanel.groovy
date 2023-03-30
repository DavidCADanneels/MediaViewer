package be.dafke.MediaViewer.Application.ChapterView

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Chapter
import be.dafke.MediaViewer.ObjectModel.Story

import javax.swing.JPanel

class ChapterOverviewPanel extends JPanel {
//    Story story
    Chapter chapter

//    void setStory(Story mainStory) {
//        this.story = mainStory
//        // TODO: use 'setToolTipText()' in graphical part when hoovering over a Point !!!
////        setToolTipText()
//        setTitle(story)
//    }

    void setChapter(Chapter chapter) {
        this.chapter = chapter
//        story = chapter.story
        Main.frame.title = chapter.toString()
    }

//    ChapterOverviewPanel(Story mainStory) {
//        this.mainStory = mainStory
//    }
//
//    ChapterOverviewPanel(Chapter parentChapter) {
//        this.parentChapter = parentChapter
//        parentChapter.getStory()
//    }
}