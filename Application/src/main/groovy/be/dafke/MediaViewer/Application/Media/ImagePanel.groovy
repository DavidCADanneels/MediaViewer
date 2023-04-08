package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JScrollPane
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.JPanel

class ImagePanel extends JPanel{

    JLabel label
    boolean fullSize = false
    BufferedImage bufferedImage
    ImageIcon imageIcon
    Picture picture
    JScrollPane scrollPane
    Story story
    ImageShowOptionsPanel imageShowOptionsPanel

    ImagePanel(ImageShowOptionsPanel imageShowOptionsPanel) {
        this.imageShowOptionsPanel = imageShowOptionsPanel
        setLayout new BorderLayout()
        label = new JLabel()
        add label, BorderLayout.CENTER
        add imageShowOptionsPanel, BorderLayout.NORTH
    }

    void setStory(Story story) {
        this.story = story
    }

    void setFullSize(boolean fullSize) {
        this.fullSize = fullSize
        removeAll()
        add imageShowOptionsPanel, BorderLayout.NORTH
        if(fullSize){
            scrollPane = new JScrollPane(label)
            add scrollPane, BorderLayout.CENTER
        } else {
            add label, BorderLayout.CENTER
        }
        revalidate()
        repaint()
    }

    void setPictures(List<Picture> pictures){
        // TODO: show all pictures in FlowLayout or GridLayout
    }

    BufferedImage readImage(Picture picture){
        try {
            File startFolder = Main.getSubFolder(story)
            String subFolderName = picture.getSubFolderName()
            if (subFolderName) {
                startFolder = new File(startFolder, subFolderName)
            }
            String fileName = "${picture.getFileName()}.${picture.getExtension()}"
            File imageFile = new File(startFolder, fileName)
            return ImageIO.read(imageFile)
        } catch (IOException ex) {
            // handle exception...
            return null
        }
    }

    void setPicture(Picture picture){
        this.picture = picture
        bufferedImage = readImage(picture)
        repaint()
    }

    Dimension rescale(Dimension pictureSize, Dimension available){
//        int availableWidth = available.getWidth()
//        int availableHeight = available.getHeight()
//        System.out.println("Available: ${availableWidth} x ${availableHeight}")
        Double oldWidth = pictureSize.getWidth()
        Double oldHeigth = pictureSize.getHeight()
        Double scaleWidth = oldWidth / available.getWidth()
        Double scaleHeigth = oldHeigth / available.getHeight()
        Double scale = (scaleWidth > scaleHeigth)?scaleWidth:scaleHeigth
        Double newWidth = oldWidth / scale
        Double newHeigth = oldHeigth / scale
        return new Dimension(newWidth.intValue(),newHeigth.intValue())
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g)
        if (picture) {
            if (fullSize) {
                imageIcon = new ImageIcon(bufferedImage)
            } else {
                Dimension available = getSize()
                Dimension oldDimension = new Dimension(picture.getWidth(), picture.getHeigth())
                Dimension newDimension = rescale(oldDimension, available)

                Image image = bufferedImage.getScaledInstance(newDimension.getWidth().intValue(), newDimension.getHeight().intValue(), Image.SCALE_SMOOTH)
                imageIcon = new ImageIcon(image)
            }
            label.setIcon(imageIcon)
        }
    }
}