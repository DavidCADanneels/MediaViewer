package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import com.sun.imageio.plugins.jpeg.JPEGImageReader

import javax.imageio.ImageIO
import javax.imageio.ImageReader
import javax.imageio.stream.ImageInputStream

class IoTools {
    static void writeObject(Object object, File file){
        XmlMapper xmlMapper = new XmlMapper()
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
        String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object)
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

    static Picture readAndDisplayMetadata(File file, Picture picture) {
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
                    picture.setWidth(width)
                    picture.setHeigth(height)

//                    IIOMetadata metadata = reader.getImageMetadata(0)
//                    String[] names = metadata.getMetadataFormatNames()

                    return picture
                } else {
                    return null
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
