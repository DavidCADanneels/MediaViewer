package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.ObjectModel.Media.Story
import com.fasterxml.jackson.dataformat.xml.XmlMapper

class IoTools {
    static void writeObject(Object object, File file){
        XmlMapper xmlMapper = new XmlMapper()
        String xml = xmlMapper.writeValueAsString(object)
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

    static Story readStory(File file){
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
