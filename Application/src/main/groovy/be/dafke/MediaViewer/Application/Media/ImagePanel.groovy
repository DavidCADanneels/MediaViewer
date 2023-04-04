package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.JPanel

class ImagePanel extends JPanel{

    private BufferedImage image

    ImagePanel() {
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

            image = ImageIO.read(fileToLoad)
//            BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
//            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//            add(picLabel);
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g)
        g.drawImage(image, 0, 0, this) // see javadoc for more info on the parameters            
    }

}