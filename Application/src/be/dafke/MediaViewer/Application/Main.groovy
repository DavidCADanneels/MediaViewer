package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.ObjectModel.Story

import javax.swing.JFrame
import javax.swing.JMenuBar
import javax.swing.JPanel
import javax.swing.WindowConstants
import java.awt.BorderLayout
import java.awt.CardLayout

class Main {
    static JPanel center
    static StoryPanel storyPanel
    static CardLayout cardLayoutCenter
    static JFrame frame

    static final String NEW_STORY = 'Create new Story'
    static final String STORY_OVERVIEW = 'Stories'
    static final String STORY_DETAIL = 'Story'

    static List<Story> stories = []

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

        switchView(NEW_STORY)
    }

    static void createCardPanels(){
        // 1. New Story
        storyPanel = createStoryPanel()
        center.add storyPanel, NEW_STORY
        // 2. View Stories
        // ...
    }

    static StoryPanel createStoryPanel(){
        StoryPanel panel = new StoryPanel()
        panel
    }

    static JMenuBar createMenuBar(){
        JMenuBar bar = new JMenuBar()
        bar
    }

    static switchView(String view){
        cardLayoutCenter.show(center, view)
        frame.title = view
    }
}