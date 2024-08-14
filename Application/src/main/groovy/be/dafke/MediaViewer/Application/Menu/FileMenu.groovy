package be.dafke.MediaViewer.Application.Menu

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.NewStory.NewStoryDialog
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.*
import java.awt.Point
import java.awt.event.KeyEvent

import static java.util.ResourceBundle.getBundle

class FileMenu extends JMenu  {
    JMenuItem loadStory, saveStory, newStory

    FileMenu() {
        super(getBundle("MediaViewer").getString("FILE_MENU"))

        loadStory = new JMenuItem(getBundle("MediaViewer").getString("LOAD_STORY_BUTTON"))
        loadStory.addActionListener { e ->
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
        loadStory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK))

        saveStory = new JMenuItem(getBundle("MediaViewer").getString("SAVE_STORY_BUTTON"))
        saveStory.addActionListener { e ->
            Story story = Main.activeStory
            if(story!=null) {
                // TODO: disable button if no activeStory
                saveStory(story)
            }
        }
        saveStory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK))

        newStory = new JMenuItem(getBundle("MediaViewer").getString("NEW_STORY_BUTTON"))
        newStory.addActionListener{ e ->
            showDialog()
        }
        newStory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK))

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
