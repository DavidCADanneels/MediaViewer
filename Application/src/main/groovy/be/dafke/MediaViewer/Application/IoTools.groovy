package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.ObjectModel.Media.Size2D
import be.dafke.MediaViewer.ObjectModel.Media.Story
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.sun.imageio.plugins.jpeg.JPEGImageReader
import org.w3c.dom.NamedNodeMap

import javax.imageio.ImageIO
import javax.imageio.ImageReader
import javax.imageio.metadata.IIOMetadata
import javax.imageio.stream.ImageInputStream

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

    static Size2D readAndDisplayMetadata(File file) {
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

            if (readers.hasNext()) {
                // pick the first available ImageReader
                ImageReader reader = readers.next()

                // attach source to the reader
                reader.setInput(iis, true);

                if(reader instanceof JPEGImageReader){
                    JPEGImageReader jpegImageReader = (JPEGImageReader)reader
                    int index = 0
                    int width = jpegImageReader.getWidth(index)
                    int height = jpegImageReader.getHeight(index)
                    Size2D size2D = new Size2D(width, height)

//                    IIOMetadata metadata = reader.getImageMetadata(0)
//                    String[] names = metadata.getMetadataFormatNames()

                    return size2D
                } else {
                    return null
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
