package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.Application.Media.MediaOverviewPanel
import be.dafke.MediaViewer.Application.Menu.MediaMenuBar
import be.dafke.MediaViewer.Application.ParticipantsOverview.ParticipantsOverviewPanel
import be.dafke.MediaViewer.Application.StoryDetails.StoryDetailsPanel
import be.dafke.MediaViewer.Application.StoryOverview.StoryOverviewPanel
import be.dafke.MediaViewer.ObjectModel.Media.Catalog
import be.dafke.MediaViewer.ObjectModel.Media.Media
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
    static MediaOverviewPanel mediaOverviewPanel
    static ParticipantsOverviewPanel participantsOverviewPanel
    static CardLayout cardLayoutCenter
    static JFrame frame

    static final String VIEW_STORY_OVERVIEW = 'STORY_OVERVIEW_TITLE'
    static final String VIEW_STORY_DETAILS = 'STORY_DETAILS_TITLE'
    static final String VIEW_CHAPTERS_FOR_STORY = 'CHAPTERS'
    static final String VIEW_MEDIA_FOR_STORY = 'MEDIA'
    static final String VIEW_PARTICIPANTS_FOR_STORY = 'PARTICIPANTS'

    static HashMap<String, Story> stories
    // TODO: replace 'storyLocations' by 'storyPaths' + 'Story.fileName()' (e.g. "CassablancaVibes\/.metadata\/story.xml")
    // TODO: NOTE: "CassablancaVibes\/.metadata\/story.xml" == "${index=name} + '.metadata' + 'story.xml
    static HashMap<String, String> storyPaths
    static HashMap<String, File> storyLocations
    static Story activeStory
    static HashMap<String, Media> mediaFiles
    static HashMap<String,File> sourceFiles
//    static Catalog catalog
    static HashMap<String, Media> getMediaFiles() {
        return mediaFiles
    }

    static void setMediaFiles(HashMap<String, Media> mediaFiles) {
        Main.mediaFiles = mediaFiles
    }

    static HashMap<String, File> getSourceFiles() {
        return sourceFiles
    }

    static void setSourceFiles(HashMap<String, File> sourceFiles) {
        Main.sourceFiles = sourceFiles
    }

    static void addStory(String rootPath, Story story, File dataFile){
        String title = story.getTitle()
        storyPaths.put(title, rootPath)
        stories.put(title, story)
        storyLocations.put(title, dataFile)
        storyOverviewPanel.dataModel.fireTableDataChanged()
    }

//    static Catalog getCatalog() {
//        return catalog
//    }

//    static void setCatalog(Catalog catalog) {
//        Main.catalog = catalog
//    }

    static Story getActiveStory() {
        return activeStory
    }

    static void setActiveStory(Story activeStory) {
        this.activeStory = activeStory
    }

    static void main(String[] args) {
        stories = [:]
        storyPaths = [:]
        sourceFiles = [:]
        mediaFiles = [:]
        storyLocations = [:]
        activeStory = null
//        catalog = Catalog.getInstance()

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
        frame.visible = true
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

        switchView(VIEW_STORY_OVERVIEW)
    }

    static void createCardPanels(){
        // 1. Story Overview
        storyOverviewPanel = new StoryOverviewPanel()
        center.add storyOverviewPanel, VIEW_STORY_OVERVIEW
        // 2. Story Details
        storyDetailsPanel = new StoryDetailsPanel()
        center.add storyDetailsPanel, VIEW_STORY_DETAILS

//        def chapterPanel = null
//        center.add chapterPanel, CHAPTERS_FOR_STORY

        mediaOverviewPanel = new MediaOverviewPanel()
        center.add mediaOverviewPanel, VIEW_MEDIA_FOR_STORY

        participantsOverviewPanel = new ParticipantsOverviewPanel()
        center.add participantsOverviewPanel, VIEW_PARTICIPANTS_FOR_STORY
    }

    static void setStory(Story story){
        storyDetailsPanel.setSt
    }

    static JMenuBar createMenuBar(){
        JMenuBar bar = new MediaMenuBar()
        bar
    }

    static switchView(String view){
        cardLayoutCenter.show(center, view)
        String title = getBundle("MediaViewer").getString(view)
        frame.title = title
//        frame.revalidate()
    }
}