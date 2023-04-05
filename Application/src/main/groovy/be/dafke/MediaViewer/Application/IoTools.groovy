package be.dafke.MediaViewer.Application

import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import com.sun.imageio.plugins.jpeg.JPEGImageReader

import javax.imageio.ImageIO
import javax.imageio.ImageReader
import javax.imageio.stream.ImageInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes
import java.util.concurrent.TimeUnit

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

                    Path path = file.toPath()
                    BasicFileAttributes attributes = null
                    try {
                        attributes = Files.readAttributes(path, BasicFileAttributes.class)
                    } catch (IOException exception) {
                        System.out.println("Exception handled when trying to get file " +
                                "attributes: " + exception.getMessage());
                    }
                    long milliseconds = attributes.creationTime().to(TimeUnit.MILLISECONDS);
                    if((milliseconds > Long.MIN_VALUE) && (milliseconds < Long.MAX_VALUE))
                    {
                        Date creationDate =
                                new Date(attributes.creationTime().to(TimeUnit.MILLISECONDS));

                        System.out.println """\
File ${path.toString()} created on ${creationDate.getDate()}/${creationDate.getMonth() + 1}/${creationDate.getYear() + 1900} \
at ${creationDate.getHours()}:${creationDate.getMinutes()}:${creationDate.getSeconds()}"""
                    }
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
