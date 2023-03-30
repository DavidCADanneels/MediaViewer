package be.dafke.MediaViewer.Application.Menu

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Story
import com.fasterxml.jackson.dataformat.xml.XmlMapper

import javax.swing.*

import static java.util.ResourceBundle.getBundle

class FileMenu extends JMenu  {
    JMenuItem loadStory

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
