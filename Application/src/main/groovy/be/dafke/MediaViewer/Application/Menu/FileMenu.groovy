package be.dafke.MediaViewer.Application.Menu

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.NewStory.NewStoryDialog
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.*
import java.awt.Point

import static java.util.ResourceBundle.getBundle

class FileMenu extends JMenu  {
    JMenuItem loadStory, saveStory, newStory

    FileMenu() {
        super(getBundle("MediaViewer").getString("FILE_MENU"))

        loadStory = new JMenuItem(getBundle("MediaViewer").getString("LOAD_STORY_BUTTON"))
        loadStory.addActionListener { e ->
            JFileChooser chooser = new JFileChooser()
            chooser.setMultiSelectionEnabled(false)
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile()
                Story story = IoTools.readStory(file)

                if (story) {
                    Main.addStory(story, file)
                    Main.activeStory = story
//                    Main.participantsOverviewPanel.dataModel.setStory(story)
//                    Main.participantsOverviewPanel.dataModel.fireTableDataChanged()
//                    Main.mediaOverviewPanel.dataModel.setStory(story)
//                    Main.mediaOverviewPanel.dataModel.fireTableDataChanged()
                    Main.switchView(Main.VIEW_STORY_DETAILS)
                }
            }
        }

        saveStory = new JMenuItem(getBundle("MediaViewer").getString("SAVE_STORY_BUTTON"))
        saveStory.addActionListener { e ->
            Story story = Main.activeStory
            if(story!=null) {
                // TODO: disable button if no activeStory
                saveStory(story)
//                saveCatalog()
            }
        }

        newStory = new JMenuItem(getBundle("MediaViewer").getString("NEW_STORY_BUTTON"))
        newStory.addActionListener { e ->
            showDialog()
        }
        add newStory
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
