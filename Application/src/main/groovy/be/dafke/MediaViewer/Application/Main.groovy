package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.Application.Menu.MediaMenuBar
import be.dafke.MediaViewer.Application.StoryOverview.StoryDetailsPanel
import be.dafke.MediaViewer.Application.StoryOverview.StoryOverviewPanel
import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.JFrame
import javax.swing.JMenuBar
import javax.swing.JPanel
import javax.swing.WindowConstants
import java.awt.BorderLayout
import java.awt.CardLayout

import static java.util.ResourceBundle.getBundle

class Main {
    static JPanel center
    static StoryOverviewPanel storyOverviewPanel
    static StoryDetailsPanel storyDetailsPanel
    static CardLayout cardLayoutCenter
    static JFrame frame

    static final String STORY_OVERVIEW = 'STORY_OVERVIEW_TITLE'
    static final String STORY_DETAILS = 'STORY_DETAILS_TITLE'

    static ArrayList<Story> stories = []
    static Story activeStory

    static void addStory(Story story){
        stories.add(story)
        storyOverviewPanel.dataModel.fireTableDataChanged()
    }

    static void main(String[] args) {


        frame = new JFrame()

        // MenuBar:
         JMenuBar menuBar = createMenuBar()
         frame.setJMenuBar(menuBar)

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

        switchView(STORY_OVERVIEW)
    }

    static void createCardPanels(){
        // 1. Story Overview
        storyOverviewPanel = new StoryOverviewPanel()
        center.add storyOverviewPanel, STORY_OVERVIEW
        // 2. Story Details
        storyDetailsPanel = new StoryDetailsPanel()
        center.add storyDetailsPanel, STORY_DETAILS
    }

    static JMenuBar createMenuBar(){
        JMenuBar bar = new MediaMenuBar()
        bar
    }

    static switchView(String view){
        cardLayoutCenter.show(center, view)
        String title = getBundle("MediaViewer").getString(view)
        frame.title = title
    }
}