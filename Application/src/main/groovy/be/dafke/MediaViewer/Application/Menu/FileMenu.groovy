package be.dafke.MediaViewer.Application.Menu

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Story
import com.fasterxml.jackson.dataformat.xml.XmlMapper

import javax.swing.*

import static java.util.ResourceBundle.getBundle

class FileMenu extends JMenu  {
    JMenuItem loadStory, saveStory, newStory

    FileMenu() {
        super(getBundle("MediaViewer").getString("FILE_MENU"))

        loadStory = new JMenuItem(getBundle("MediaViewer").getString("LOAD_STORY_FROM_XML"))
        loadStory.addActionListener { e ->
            Story story = loadStory()
            if(story){
                Main.addStory(story)
                Main.setActiveStory(story)
            }
        }
        add loadStory

        saveStory = new JMenuItem(getBundle("MediaViewer").getString("SAVE_STORY_TO_XML"))
        saveStory.addActionListener { e ->
            Story story = Main.activeStory
            if(story!=null) {
                // TODO: disable button if no activeStory
                saveStory(story)
            }
        }
        add saveStory

        newStory = new JMenuItem(getBundle("MediaViewer").getString("NEW_STORY"))
        newStory.addActionListener { e ->
            // TODO: open new Frame  with StoryOverviewPanel.topPanel to create new Story
        }
        add newStory
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
            XmlMapper xmlMapper = new XmlMapper()
            String xml = xmlMapper.writeValueAsString(story)
            try {
                Writer writer = new FileWriter(file)
                writer.write xml
                writer.flush()
                writer.close()
            } catch (IOException ex) {
                System.err.println(ex)
//                Logger.getLogger(Accounts.class.name).log(Level.SEVERE, null, ex)
            }
        }
    }

    Story loadStory(){
        JFileChooser chooser = new JFileChooser()
        chooser.setMultiSelectionEnabled(false)
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile()
            readStory(file)
        }
        else null
    }

    Story readStory(File file){
        XmlMapper xmlMapper = new XmlMapper()
        try {
            def xml
            xmlMapper.readValue(file, Story.class)
        } catch (Exception ex) {
            System.err.println(ex)
//                Logger.getLogger(Accounts.class.name).log(Level.SEVERE, null, ex)
        }
    }
}
