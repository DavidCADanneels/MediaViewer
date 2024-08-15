package be.dafke.MediaViewer.Application.Menu

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.NewStory.NewStoryDialog
import be.dafke.MediaViewer.ObjectModel.Stories.Stories
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.*
import java.awt.Point
import java.awt.event.KeyEvent

import static java.util.ResourceBundle.getBundle

class FileMenu extends JMenu  {
    JMenuItem loadSingle, loadAll, saveCurrent, saveAll, newStory

    FileMenu() {
        super(getBundle("MediaViewer").getString("FILE_MENU"))

        newStory = new JMenuItem(getBundle("MediaViewer").getString("NEW_STORY_BUTTON"))
        newStory.addActionListener{ e -> showDialog() }
        newStory.setAccelerator KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK)

        loadSingle = new JMenuItem(getBundle("MediaViewer").getString("LOAD_STORY_BUTTON"))
        loadSingle.addActionListener { e -> loadSingleStory() }
        loadSingle.setAccelerator KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK)

        loadAll = new JMenuItem(getBundle("MediaViewer").getString("LOAD_ALL_BUTTON"))
        loadAll.addActionListener { e -> loadAllStories() }
        loadAll.setAccelerator KeyStroke.getKeyStroke(KeyEvent.VK_L,KeyEvent.CTRL_DOWN_MASK)

        saveCurrent = new JMenuItem(getBundle("MediaViewer").getString("SAVE_CURRENT_STORY_BUTTON"))
        saveCurrent.addActionListener { e -> saveCurrentStory() }
        saveCurrent.setAccelerator KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK)

        saveAll = new JMenuItem(getBundle("MediaViewer").getString("SAVE_ALL_STORIES_BUTTON"))
        saveAll.addActionListener { e -> saveAllStories() }
        saveAll.setAccelerator KeyStroke.getKeyStroke(KeyEvent.VK_Q,KeyEvent.CTRL_DOWN_MASK)

        add newStory
        add loadSingle
        add loadAll
        add saveCurrent
        add saveAll
    }

    void saveCurrentStory(){
        Story story = Main.activeStory
        if(story!=null) {
            // TODO: disable button if no activeStory
            saveStory(story)
        }
    }

    void saveAllStories(){
//        Stories stories = new Stories()
        HashMap<Story, File> storyMap = Main.storyMap
        storyMap.each { Story story, File file ->
            IoTools.writeObject(story, file)
//            stories.stories.add(story)
        }
        File dataFile = Main.getStoriesFile()
        if(dataFile == null){
            JFileChooser chooser = new JFileChooser()
            chooser.setMultiSelectionEnabled(false)
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                dataFile = chooser.getSelectedFile()
                IoTools.writeObject(storyMap, dataFile)
            }
        } else {
            IoTools.writeObject(storyMap, dataFile)
        }
    }

    void loadSingleStory(){
        JFileChooser chooser = new JFileChooser()
        chooser.setMultiSelectionEnabled(false)
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile()
            Story story = IoTools.readStory(file)

            if (story) {
                Main.addStory(story, file)
                Main.activeStory = story
                Main.switchView(Main.VIEW_STORY_DETAILS)
            }
        }
    }

    void loadAllStories() {
        JFileChooser chooser = new JFileChooser()
        chooser.setMultiSelectionEnabled(false)
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile()
            Stories stories = IoTools.readStories(file)
            if (stories != null && stories.stories != null) {
                List allStories = stories.stories
                System.out.println("size=${allStories.size()}")
                allStories.each { Story story ->
                    // story only contains the title
                    File currentMetadataFolder = file.getParentFile()
                    File parentFolder = currentMetadataFolder.getParentFile()
                    File projectFolder = new File(parentFolder, story.title)
                    File metaDataFolder = new File(projectFolder, '.metadata')
                    File projectFile = new File(metaDataFolder, 'story.xml')
                    System.out.println "File: ${projectFile}"
                    if (projectFile.exists()) {
                        // enrich story with all details
                        Story fullStory = IoTools.readStory(file)
                        System.out.println "read fullStory"
                        // TODO: use Main.stories.addStory
                        Main.addStory(fullStory, file)
                        // this could also be done when loading the project
                        // for now we need only the title and description (what is displayed in the overview table
                    }
                }
                if (stories.stories.size() > 0) {
                    Main.activeStory = stories.stories[0]
                }
                Main.switchView(Main.VIEW_STORY_OVERVIEW)
            }
        }
    }

    void showDialog(){
        // TODO: open new Frame  with StoryOverviewPanel.topPanel to create new Story
        Point locationOnScreen = getLocationOnScreen()
        NewStoryDialog newStoryDialog = new NewStoryDialog()
        newStoryDialog.setLocation(locationOnScreen)
        newStoryDialog.visible = true
    }

    void saveStory(Story story){
        File file = Main.storyMap.get(story)
        if(file == null){
            JFileChooser chooser = new JFileChooser()
            chooser.setMultiSelectionEnabled(false)
            if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile()
                if(file != null) {
                    Main.storyMap.put(story,file)
                }
            }
        }
        if(file != null){
            IoTools.writeObject(story, file)
        }
    }
}
