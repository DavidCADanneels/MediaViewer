package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.Application.Chapter.ChaptersOverviewPanel
import be.dafke.MediaViewer.Application.Media.MediaOverviewPanel
import be.dafke.MediaViewer.Application.Media.NewMediaDialog
import be.dafke.MediaViewer.Application.Menu.MediaMenuBar
import be.dafke.MediaViewer.Application.ParticipantsOverview.ParticipantsOverviewPanel
import be.dafke.MediaViewer.Application.StoryDetails.StoryButtonsPanel
import be.dafke.MediaViewer.Application.StoryDetails.StoryDetailsPanel
import be.dafke.MediaViewer.Application.StoryOverview.StoryOverviewPanel
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JFrame
import javax.swing.JMenuBar
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.WindowConstants
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.Component

import static java.util.ResourceBundle.getBundle

class Main {
    static JPanel center
    static StoryButtonsPanel storyButtonsPanel
    static StoryOverviewPanel storyOverviewPanel
    static StoryDetailsPanel storyDetailsPanel
    static MediaOverviewPanel mediaOverviewPanel
    static ParticipantsOverviewPanel participantsOverviewPanel
    static ChaptersOverviewPanel chaptersOverviewPanel
    static CardLayout cardLayoutCenter
    static JFrame frame

    static final String VIEW_STORY_OVERVIEW = 'STORY_OVERVIEW_TITLE'
    static final String VIEW_STORY_DETAILS = 'STORY_DETAILS_TITLE'
    static final String VIEW_CHAPTERS_FOR_STORY = 'CHAPTERS'
    static final String VIEW_MEDIA_FOR_STORY = 'MEDIA'
    static final String VIEW_PARTICIPANTS_FOR_STORY = 'PARTICIPANTS'

    static HashMap<Story, File> storyMap
    static Story activeStory

    static void addStory(Story story, File dataFile){
        storyMap.put(story,dataFile)
        storyOverviewPanel.dataModel.fireTableDataChanged()
    }

    static Story getActiveStory() {
        return activeStory
    }

    static void setActiveStory(Story story) {
        activeStory = story
        storyButtonsPanel.setStory(story)
        participantsOverviewPanel.setStory(story)
        chaptersOverviewPanel.setStory(story)
        mediaOverviewPanel.setStory(story)
        storyDetailsPanel.setStory(story)
    }

    static int selectParticipant(Story story, Component component){
        Object [] participants = story.getParticipants().toArray()
        return JOptionPane.showOptionDialog(component, "Select Owner", "Assign Owner",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                participants, null)
    }

    static void setOwners(List<Picture> pictures, Integer owner){
        pictures.each {it.setOwner(owner) }
    }

    static void setOwner(Picture picture, Integer owner){
        picture.setOwner(owner)
    }

    static File getSubFolder(Story story){
        File storyFile = Main.storyMap.get(story)
        storyFile?.getParentFile()?.getParentFile()
    }

    static void main(String[] args) {
        storyMap = [:]
        activeStory = null

        frame = new JFrame()

        // MenuBar:
         JMenuBar menuBar = createMenuBar()
         frame.setJMenuBar(menuBar)

        storyButtonsPanel = new StoryButtonsPanel()
//        storyButtonsPanel.disableAllButtons()

        // Content Panel contains only a 'center' panel -> FullScreen
        // Center Panel in CardLayout to switch between input, display, etc.
        cardLayoutCenter = new CardLayout()
        center = new JPanel(cardLayoutCenter)
        JPanel contentPanel = new JPanel(new BorderLayout())
        contentPanel.add center, BorderLayout.CENTER
        contentPanel.add storyButtonsPanel, BorderLayout.SOUTH
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

        chaptersOverviewPanel = new ChaptersOverviewPanel()
        center.add chaptersOverviewPanel, VIEW_CHAPTERS_FOR_STORY

        mediaOverviewPanel = new MediaOverviewPanel()
        center.add mediaOverviewPanel, VIEW_MEDIA_FOR_STORY

        participantsOverviewPanel = new ParticipantsOverviewPanel()
        center.add participantsOverviewPanel, VIEW_PARTICIPANTS_FOR_STORY
    }

    static JMenuBar createMenuBar(){
        JMenuBar bar = new MediaMenuBar()
        bar
    }

//    static addMedia(Story story){
//
//    }

    static switchView(String view){
        storyButtonsPanel.enableAllButtons()
        if (view == VIEW_STORY_OVERVIEW) {
            storyButtonsPanel.disableAllButtons()
        } else if(view == VIEW_STORY_DETAILS){
            storyButtonsPanel.backToStoryDetailsButton.enabled = false
        } else if(view == VIEW_CHAPTERS_FOR_STORY){
            storyButtonsPanel.chaptersButton.enabled = false
        } else if (view == VIEW_MEDIA_FOR_STORY) {
            storyButtonsPanel.mediaButton.enabled = false
            storyButtonsPanel.addMediaButton.enabled = true
        } else if (view == VIEW_PARTICIPANTS_FOR_STORY){
            storyButtonsPanel.participantButton.enabled = false
        }
        cardLayoutCenter.show(center, view)
        String title = getBundle("MediaViewer").getString(view)
        frame.title = title
    }
}