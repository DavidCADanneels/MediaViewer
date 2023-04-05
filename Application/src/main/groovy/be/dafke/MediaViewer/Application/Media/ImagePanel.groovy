package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.ImageIcon
import javax.swing.JLabel
import java.awt.Dimension
import java.awt.Image
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.JPanel

class ImagePanel extends JPanel{

    JLabel label
    boolean fullSize = false

    ImagePanel() {
        label = new JLabel()
        add label
    }

    void setPicture(Picture picture){
        try {
            Story story = Main.activeStory
            File startFolder = Main.getSubFolder(story)
            String subFolderName = picture.getSubFolderName()
            if(subFolderName){
                startFolder = new File(startFolder, subFolderName)
            }
            String fileName = "${picture.getFileName()}.${picture.getExtension()}"
            File fileToLoad = new File(startFolder, fileName)
            System.out.println("file: ${fileToLoad.getAbsolutePath()}")


            BufferedImage bufferedImage = ImageIO.read(fileToLoad)

            ImageIcon imageIcon
            if(fullSize){
                imageIcon = new ImageIcon(bufferedImage)
            } else {
                Dimension available = getSize()
                Dimension oldDimension = new Dimension(picture.getWidth(), picture.getHeigth())
                Dimension newDimension = rescale(oldDimension, available)
                int availableWidth = available.getWidth()
                int availableHeight = available.getHeight()
                System.out.println("Available: ${availableWidth} x ${availableHeight}")

                Image image = bufferedImage.getScaledInstance(newDimension.getWidth().intValue(),newDimension.getHeight().intValue(),Image.SCALE_SMOOTH)
                imageIcon = new ImageIcon(image)
            }
            label.setIcon(imageIcon)
        } catch (IOException ex) {
            // handle exception...
        }
    }

    Dimension rescale(Dimension pictureSize, Dimension available){
        Double oldWidth = pictureSize.getWidth()
        Double oldHeigth = pictureSize.getHeight()
        Double scaleWidth = oldWidth / available.getWidth()
        Double scaleHeigth = oldHeigth / available.getHeight()
        Double scale = (scaleWidth > scaleHeigth)?scaleWidth:scaleHeigth
        Double newWidth = oldWidth / scale
        Double newHeigth = oldHeigth / scale
        return new Dimension(newWidth.intValue(),newHeigth.intValue())
    }
}