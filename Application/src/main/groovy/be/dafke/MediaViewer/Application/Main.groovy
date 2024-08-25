package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.Application.Chapter.ChaptersPicturesOverviewPanel
import be.dafke.MediaViewer.Application.Chapter.ChaptersTextOverviewPanel
import be.dafke.MediaViewer.Application.Media.MediaOverviewPanel
import be.dafke.MediaViewer.Application.Menu.MediaMenuBar
import be.dafke.MediaViewer.Application.ParticipantsOverview.ParticipantsOverviewPanel
import be.dafke.MediaViewer.Application.StoryDetails.StoryButtonsPanel
import be.dafke.MediaViewer.Application.StoryDetails.StoryDetailsPanel
import be.dafke.MediaViewer.Application.StoryOverview.StoryOverviewPanel
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
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
    static ChaptersPicturesOverviewPanel chaptersPicturesOverviewPanel
    static ChaptersTextOverviewPanel chaptersTextOverviewPanel
    static CardLayout cardLayoutCenter
    static JFrame frame

    static final String VIEW_STORY_OVERVIEW = 'STORY_OVERVIEW_TITLE'
    static final String VIEW_STORY_DETAILS = 'STORY_DETAILS_TITLE'
    static final String VIEW_MEDIA_PER_CHAPTER = 'MEDIA_CHAPTERS'
    static final String VIEW_TEXT_PER_CHAPTER = 'TEXT_CHAPTERS'
    static final String VIEW_ALL_PICTURES_FOR_STORY = 'ALL_MEDIA'
    static final String VIEW_PARTICIPANTS_FOR_STORY = 'PARTICIPANTS'

    static HashMap<Story, File> storyMap
    // TODO: use allStories.allStories iso static Main.storyMap
//    static Stories allStories

    static File storiesFile
    static Story activeStory
    static HashMap<String, Chapter> chapterMap

//    static HashMap<Story, File> getStoryMap() {
//        return storyMap
//    }
//
//    static void setStoryMap(HashMap<Story, File> storyMap) {
//        Main.storyMap = storyMap
//    }
//    static Stories getAllStories() {
//        return allStories
//    }
//
//    static void setAllStories(Stories allStories) {
//        Main.allStories = allStories
//    }

    static File getStoriesFile() {
        return storiesFile
    }

    static void setStoriesFile(File storiesFile) {
        Main.storiesFile = storiesFile
    }

    static void addStory(Story story, File dataFile){
        storyMap.put(story,dataFile)
        storyOverviewPanel.dataModel.fireTableDataChanged()
    }

    static Story getActiveStory() {
        return activeStory
    }

    static String getLongTitle(Chapter chapter){
        if(chapter == null){
            return null
        }
        String result = chapter.title
        // TODO: if we use String parentChapterIndex,
        // we store a HashMap<String, Chapters> for the activeStory

        String index = chapter.parentChapter
        Chapter parentChapter = chapterMap.get(index)
        while(parentChapter != null){
            result = "${parentChapter.title} | ${result}"
            index = parentChapter.parentChapter
            parentChapter = chapterMap.get(index)
        }
        return result
    }

    static HashMap<String, Chapter> getChapterMap() {
        return chapterMap
    }

    static void setActiveStory(Story story) {
        chapterMap = [:]
        story.chapters.each { Chapter chapter ->
            chapterMap.put(chapter.prefix, chapter)
        }
        
        activeStory = story
        storyButtonsPanel.setStory(story)
        participantsOverviewPanel.setStory(story)
        chaptersPicturesOverviewPanel.setStory(story)
        chaptersTextOverviewPanel.setStory(story)
        mediaOverviewPanel.setStory(story)
        storyDetailsPanel.setStory(story)
    }

    static int selectParticipant(Story story, Component component){
        Object [] participants = story.getPersons().toArray()
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
        contentPanel.add storyButtonsPanel, BorderLayout.NORTH
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

        chaptersPicturesOverviewPanel = new ChaptersPicturesOverviewPanel()
        center.add chaptersPicturesOverviewPanel, VIEW_MEDIA_PER_CHAPTER

        chaptersTextOverviewPanel = new ChaptersTextOverviewPanel()
        center.add chaptersTextOverviewPanel, VIEW_TEXT_PER_CHAPTER

        mediaOverviewPanel = new MediaOverviewPanel()
        center.add mediaOverviewPanel, VIEW_ALL_PICTURES_FOR_STORY

        participantsOverviewPanel = new ParticipantsOverviewPanel()
        center.add participantsOverviewPanel, VIEW_PARTICIPANTS_FOR_STORY
    }

    static JMenuBar createMenuBar(){
        JMenuBar bar = new MediaMenuBar()
        bar
    }

    static String getNettoPrefix(Chapter chapter) {
        if(chapter.parentChapter == null) {
            return chapter.prefix
        } else {
            return chapter.prefix - chapter.parentChapter
        }
    }

    static File getChapterFolder(Story story, String index){
        File projectFile = storyMap.get(story)
        File metadataFolder = projectFile.parentFile
        File projectFolder = metadataFolder.parentFile

        System.out.println "chapter.prefix=${index}"

        Chapter lowestParent = getLowestParentChapter(story, index)
        System.out.println "lowestParent.prefix=${lowestParent.prefix}"

        Stack<String> indexStack = new Stack<>()

        Chapter pivot = lowestParent
        while(pivot != null){
            indexStack.push(getNettoPrefix(pivot))
            pivot = getLowestParentChapter(story, pivot.parentChapter)
        }
        File resultFolder = projectFolder

        String pit
        while(!indexStack.empty()){
            pit = indexStack.pop()
            resultFolder = new File(resultFolder, pit)
        }
        return resultFolder
    }

    static Chapter getLowestParentChapter(Story story, String index){
        List<Chapter> chapters = story.getChapters()
        System.out.println "${chapters.size()} chapters"
        Chapter parentChapter = chapters.find { Chapter chapter -> chapter.prefix == index }
        System.out.println "parentChapter:${parentChapter}"

        while(parentChapter == null && index.length()>2){
            index = index.substring(0,index.length()-2)
            System.out.println "new index: ${index}"
            parentChapter = chapters.find { Chapter chapter -> chapter.prefix == index }
        }
        if(parentChapter) {
            System.out.println("Found parentChapter: ${parentChapter.title} with index ${index}")
        }
        return parentChapter
    }

    static switchView(String view){
        storyButtonsPanel.enableAllButtons()
        if (view == VIEW_STORY_OVERVIEW) {
            storyButtonsPanel.disableAllButtons()
        } else if(view == VIEW_STORY_DETAILS){
            storyButtonsPanel.backToStoryDetailsButton.enabled = false
        } else if(view == VIEW_MEDIA_PER_CHAPTER){ // TODO: add 'else if view == VIEW_TEXT_PER_CHAPTER ???
            storyButtonsPanel.picturesPerChapterButton.enabled = false
        } else if (view == VIEW_ALL_PICTURES_FOR_STORY) {
            storyButtonsPanel.mediaButton.enabled = false
        } else if (view == VIEW_PARTICIPANTS_FOR_STORY){
            storyButtonsPanel.participantButton.enabled = false
        }
        cardLayoutCenter.show(center, view)
        String title = getBundle("MediaViewer").getString(view)
        frame.title = title
    }
}