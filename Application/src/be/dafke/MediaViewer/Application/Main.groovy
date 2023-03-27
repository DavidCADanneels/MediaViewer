package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.Application.ChapterView.ChapterOverviewPanel
import be.dafke.MediaViewer.Application.StoryView.StoryOverviewPanel
import be.dafke.MediaViewer.ObjectModel.Chapter
import be.dafke.MediaViewer.ObjectModel.Story

import javax.swing.JFrame
import javax.swing.JMenuBar
import javax.swing.JPanel
import javax.swing.WindowConstants
import java.awt.BorderLayout
import java.awt.CardLayout

import static java.util.ResourceBundle.getBundle

class Main {
    static JPanel center
    static StoryOverviewPanel storyPanel
    static CardLayout cardLayoutCenter
    static JFrame frame

    static final String STORIES = 'STORIES'
    static final String STOReeY_OVERVIEW = 'STORY_OVERVIEW'
    static final String STORY_DETAIL = 'Story'

    static ArrayList<Story> stories = []

    static void addStory(Story story){
        stories.add(story)
        storyPanel.dataModel.fireTableDataChanged()
    }

    static void main(String[] args) {


        frame = new JFrame()

        // MenuBar:
        // JMenuBar menuBar = createMenuBar()
        // frame.setMenuBar(menuBar)

        // Content Panel contains only a 'center' panel -> FullScreen
        // Center Panel in CardLayout to switch between input, display, etc.
        cardLayoutCenter = new CardLayout()
        center = new JPanel(cardLayoutCenter)
        JPanel contentPanel = new JPanel(new BorderLayout())
        contentPanel.add center, BorderLayout.CENTER
        frame.setContentPane(contentPanel)

        // Cards:
        createCardPanels()

        frame.pack()
        frame.setVisible(true)
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

        switchView(STORIES)
    }

    static void createCardPanels(){
        // 1. New Story
        storyPanel = createStoryOverviewPanel()
        center.add storyPanel, STORIES
        // 2. View Stories
        // ...
    }

    static StoryOverviewPanel createStoryOverviewPanel(){
        StoryOverviewPanel panel = new StoryOverviewPanel()
        panel
    }

    static ChapterOverviewPanel createChapterOverviewPanel(Story story){
        ChapterOverviewPanel overviewPanel = new ChapterOverviewPanel(story)
    }

    static ChapterOverviewPanel createChapterOverviewPanel(Chapter chapter){
        ChapterOverviewPanel overviewPanel = new ChapterOverviewPanel(chapter)
    }

    static JMenuBar createMenuBar(){
        JMenuBar bar = new JMenuBar()
        bar
    }

    static switchView(String view){
        cardLayoutCenter.show(center, view)
        String title = getBundle("MediaViewer").getString(view)
        frame.title = title
    }
}