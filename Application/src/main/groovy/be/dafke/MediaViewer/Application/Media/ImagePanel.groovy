package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.ImageIcon
import javax.swing.JLabel
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.JPanel

class ImagePanel extends JPanel{

    JLabel label

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

            BufferedImage image = ImageIO.read(fileToLoad)
            ImageIcon imageIcon = new ImageIcon(image)
            label.setBounds(0,0,picture.getWidth(),picture.getHeigth())
            label.setIcon(imageIcon)
        } catch (IOException ex) {
            // handle exception...
        }
    }
}