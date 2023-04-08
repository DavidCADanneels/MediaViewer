package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JScrollPane
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.Image
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.JPanel

class ImagePanel extends JPanel{

    JLabel label
    boolean fullSize = false
    boolean singleSelection = true
    Picture picture
    List<Picture> pictures
    JScrollPane scrollPane
    Story story
    ImageShowOptionsPanel imageShowOptionsPanel

    ImagePanel(ImageShowOptionsPanel imageShowOptionsPanel) {
        this.imageShowOptionsPanel = imageShowOptionsPanel
        setLayout new BorderLayout()
        label = new JLabel()
        scrollPane = new JScrollPane(label)
        add scrollPane, BorderLayout.CENTER
        add imageShowOptionsPanel, BorderLayout.NORTH
    }

    void setStory(Story story) {
        this.story = story
    }

    void setFullSize(boolean fullSize) {
        this.fullSize = fullSize
        if(singleSelection){
            if(picture) {
                setPicture(picture)
            }
        } else {
            setPictures(pictures)
        }
        revalidate()
        repaint()
    }

    void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection
        if(singleSelection){
            setFullSize(false)
        } else {
            pictures = []
            if(picture) {
                pictures.add(picture)
            }
            setPictures(pictures)
        }
    }

    void setPictures(List<Picture> pictures){
        picture = pictures.size()==0?null:pictures.get(0)
        this.pictures = pictures
        remove scrollPane
        JPanel panel = new JPanel()
        int nrOfPictures = pictures.size()
        if(nrOfPictures > 0) {
            Double totalNr = (Double) nrOfPictures
            Double squaredNumber = Math.sqrt(totalNr)
            System.out.println("nr: ${totalNr} -> ${squaredNumber}")
            Dimension available = scrollPane.getSize()
            Double widthPerPicture = available.getWidth() / squaredNumber
            Double heightPerPicture = available.getHeight() / squaredNumber
            System.out.println("available: ${available.getWidth()} x ${available.getHeight()} -> ${widthPerPicture} x ${heightPerPicture} -> ${widthPerPicture.intValue()} x ${heightPerPicture.intValue()}")
            Dimension dimPerPicture = new Dimension(widthPerPicture.intValue(), heightPerPicture.intValue())

            panel.setLayout(new GridLayout(squaredNumber.intValue(), 0))

            pictures.each { Picture picture ->
                BufferedImage bufferedImage = readImage(picture)
                Dimension oldDimension = new Dimension(picture.getWidth(), picture.getHeight())
                System.out.println("oldDimension: ${oldDimension.getWidth()} x ${oldDimension.getHeight()}")
                Dimension newDimension = rescale(oldDimension, dimPerPicture)
                System.out.println("oldDimension: ${newDimension.getWidth()} x ${newDimension.getHeight()}")

                Image image = bufferedImage.getScaledInstance(newDimension.getWidth().intValue(), newDimension.getHeight().intValue(), Image.SCALE_SMOOTH)
                ImageIcon imageIcon = new ImageIcon(image)
                panel.add new JLabel(imageIcon)
            }
        }
        scrollPane = new JScrollPane(panel)
        add scrollPane, BorderLayout.CENTER
        revalidate()
        repaint()
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
        pictures = []
        pictures.add(picture)
        this.picture = picture
        BufferedImage bufferedImage = readImage(picture)
        ImageIcon imageIcon
        if (fullSize) {
            imageIcon = new ImageIcon(bufferedImage)
        } else {
            Dimension available = scrollPane.getSize()
            Dimension oldDimension = new Dimension(picture.getWidth(), picture.getHeight())
            Dimension newDimension = rescale(oldDimension, available)

            Image image = bufferedImage.getScaledInstance(newDimension.getWidth().intValue(), newDimension.getHeight().intValue(), Image.SCALE_SMOOTH)
            imageIcon = new ImageIcon(image)
        }
        label.setIcon(imageIcon)
        repaint()
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