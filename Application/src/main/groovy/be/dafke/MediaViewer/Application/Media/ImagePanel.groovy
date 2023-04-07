package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JScrollPane
import javax.swing.JTextField
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
    File imageFile
    JScrollPane scrollPane
    ImageDetailPanel imageDetailPanel
    ImageShowOptionsPanel imageShowOptionsPanel
    Story story

    ImagePanel() {
        setLayout new BorderLayout()
        label = new JLabel()
        add label, BorderLayout.CENTER
        JPanel east = new JPanel(new BorderLayout())
        east.add createOptionPanel(), BorderLayout.NORTH
        // east.add new ImageDetailsPanel(). BorderLayout.CENTER
        add east, BorderLayout.EAST
    }

    void setStory(Story story) {
        this.story = story
    }

    void setFullSize(boolean fullSize) {
        this.fullSize = fullSize
        showPicture()
    }

    void showPicture(){
        if(fullSize){
            remove(label)
            scrollPane = new JScrollPane(label)
            add scrollPane, BorderLayout.CENTER
        } else {
            remove(scrollPane)
            add label, BorderLayout.CENTER
        }
        revalidate()
        repaint()
    }

    JPanel createOptionPanel(){
        imageShowOptionsPanel = new ImageShowOptionsPanel(this)
        imageDetailPanel = new ImageDetailPanel()

        JPanel panel = new JPanel()
        panel.setLayout new BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add imageShowOptionsPanel
        panel.add imageDetailPanel
        panel
    }

    void setPictures(List<Picture> picture){
        // TODO: show all pictures in FlowLayout or GridLayout
    }

    void setPicture(Picture picture){
        this.picture = picture
        try {
            if(picture) {
                imageDetailPanel.setPicture(picture)
                File startFolder = Main.getSubFolder(story)
                String subFolderName = picture.getSubFolderName()
                if (subFolderName) {
                    startFolder = new File(startFolder, subFolderName)
                }
                String fileName = "${picture.getFileName()}.${picture.getExtension()}"
                imageFile = new File(startFolder, fileName)
//            System.out.println("file: ${imageFile.getAbsolutePath()}")

                bufferedImage = ImageIO.read(imageFile)
            }
        } catch (IOException ex) {
            // handle exception...
        } finally {
            repaint()
        }
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