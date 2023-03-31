package be.dafke.MediaViewer.Application.Menu

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.NewStory.NewStoryDialog
import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.*
import java.awt.Point

import static java.util.ResourceBundle.getBundle

class FileMenu extends JMenu  {
    JMenuItem loadStory, saveStory, newStory

    FileMenu() {
        super(getBundle("MediaViewer").getString("FILE_MENU"))

        loadStory = new JMenuItem(getBundle("MediaViewer").getString("LOAD_STORY_BUTTON"))
        loadStory.addActionListener { e ->
            Story story = loadStory()
            if(story){
                Main.addStory(story)
                Main.activeStory = story
                Main.storyDetailsPanel.setStory()
                Main.switchView(Main.VIEW_STORY_DETAILS)
            }
        }

        saveStory = new JMenuItem(getBundle("MediaViewer").getString("SAVE_STORY_BUTTON"))
        saveStory.addActionListener { e ->
            Story story = Main.activeStory
            if(story!=null) {
                // TODO: disable button if no activeStory
                saveStory(story)
            }
        }

        newStory = new JMenuItem(getBundle("MediaViewer").getString("NEW_STORY_BUTTON"))
        newStory.addActionListener { e ->
            showDialog()
        }
//        add newStory
        add loadStory
        add saveStory
    }

    void showDialog(){
        // TODO: open new Frame  with StoryOverviewPanel.topPanel to create new Story
        Point locationOnScreen = getLocationOnScreen()
        NewStoryDialog newStoryDialog = new NewStoryDialog()
        newStoryDialog.setLocation(locationOnScreen)
        newStoryDialog.visible = true
    }

    void saveStory(Story story){
        File file = story.getDataFile()
        if(file == null){
            JFileChooser chooser = new JFileChooser()
            chooser.setMultiSelectionEnabled(false)
            if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile()
                if(file != null) {
                    story.setDataFile(file)
                }
            }
        }
        if(file != null){
            IoTools.writeObject(story, file)
        }
    }

    Story loadStory(){
        JFileChooser chooser = new JFileChooser()
        chooser.setMultiSelectionEnabled(false)
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile()
            IoTools.readStory(file)
        }
        else null
    }
}
