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
            JFileChooser chooser = new JFileChooser()
            chooser.setMultiSelectionEnabled(false)
            if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile()
                Story story = IoTools.readStory(file)

                if (story) {
                    String fullPath = file.getAbsolutePath()
                    System.out.println("fullPath:${fullPath}")

                    if (fullPath.contains('.metadata')) {
                        String[] parts = fullPath.split('.metadata')

                        String prefix = parts[0]
                        prefix = prefix.substring(0, prefix.length() - 1)

//                        String remainingPart = parts[1]
//                        remainingPart = remainingPart.substring(1, remainingPart.length())

//                        story.setRootPath(prefix) // do not store this (in xml metadata), this is platform-specific

                        Main.addStory(prefix, story, file)
//                        Main.addStory(story, file)
                        Main.activeStory = story
                        Main.storyDetailsPanel.setStory()
                        Main.switchView(Main.VIEW_STORY_DETAILS)
                    }
                }
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
        String title = story.getTitle()
        File file = Main.storyLocations.get(title)
        if(file == null){
            JFileChooser chooser = new JFileChooser()
            chooser.setMultiSelectionEnabled(false)
            if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile()
                if(file != null) {
                    Main.storyLocations.put(title,file)
                }
            }
        }
        if(file != null){
            IoTools.writeObject(story, file)
        }
    }
}
