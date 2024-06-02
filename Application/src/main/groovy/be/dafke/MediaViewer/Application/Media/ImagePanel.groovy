package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
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
    // FIXME: tmp workaround to not update the view.
    // This boolean should be else where: when false, this ImagePanel should be hidden
    boolean showSelection = true

    ImagePanel() {
        setLayout new BorderLayout()
        label = new JLabel()
        scrollPane = new JScrollPane(label)
        add scrollPane, BorderLayout.CENTER
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

    // FIXME: no tmp workaround, boolean can stay, but checkbox must move
    void setShowSelection(boolean showSelection) {
        this.showSelection = showSelection
    }

    void setPictures(List<Picture> pictures){
        // FIXME: tmp workaround
        // TODO: remove 'if' and hide ImagePanel if showSelection == false (elsewhere)
        if(showSelection) {
            picture = pictures.size() == 0 ? null : pictures.get(0)
            this.pictures = pictures
            remove scrollPane
            JPanel panel = new JPanel()
            int nrOfPictures = pictures.size()
            if (nrOfPictures > 0) {
                Double totalNr = (Double) nrOfPictures
                Double squaredNumber = Math.sqrt(totalNr)
                System.out.println("nr: ${totalNr} -> ${squaredNumber}")
                Dimension available = getSize()
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
                    System.out.println("newDimension: ${newDimension.getWidth()} x ${newDimension.getHeight()}")

                    if (bufferedImage) {
                        Image image = bufferedImage.getScaledInstance(newDimension.getWidth().intValue(), newDimension.getHeight().intValue(), Image.SCALE_SMOOTH)
                        ImageIcon imageIcon = new ImageIcon(image)
                        panel.add new JLabel(imageIcon)
                    }
                }
            }
            scrollPane = new JScrollPane(panel)
            add scrollPane, BorderLayout.CENTER
            revalidate()
            repaint()
        }
    }

    BufferedImage readImage(Picture picture){
        if(picture == null){
            return null
        }
        try {
            File startFolder = Main.getSubFolder(story)
            System.out.println "startFolder=${startFolder.getPath()}"
            String chapterPrefix = picture.getChapter() // e.g. 0200
            System.out.println "chapterPrefix=${chapterPrefix}"
            if (chapterPrefix) {
                while(chapterPrefix.size()>2){
                    String parent = chapterPrefix.substring(0,2)
                    System.out.println"parent=${parent}"
                    startFolder = new File(startFolder, parent)
                    chapterPrefix -= parent
                    System.out.println"remaining chapterPrefix=${chapterPrefix}"
                }
                startFolder = new File(startFolder, chapterPrefix)
                // FIXME: validate this
//                Chapter chapter = story.getChapter(chapterPrefix)
//                if (chapter) {
//                    Chapter parentChapter = chapter.getParentChapter()
//                    if (parentChapter) {
//                        String parentPrefix = parentChapter.getPrefix()
//                        System.out.println "parentPrefix=${parentPrefix}"
//
//                        startFolder = new File(startFolder, parentPrefix)
//                        chapterPrefix -= parentPrefix
//                        System.out.println "chapterPrefix=${chapterPrefix}"
//                    }
//                    startFolder = new File(startFolder, chapterPrefix)
//                }
            }
            String subFolderName = picture.getSubFolderName()
            if (subFolderName != null) {
                startFolder = new File(startFolder, subFolderName)
            }
            String fileName = "${picture.getFileName()}.${picture.getExtension()}"
            System.out.println "fileName=${fileName}"
            File imageFile = new File(startFolder, fileName)
            System.out.println "imageFile=${imageFile.getPath()}"
            return ImageIO.read(imageFile)
        } catch (IOException ex) {
            ex.printStackTrace()
            return null
        }
    }

    void setPicture(Picture picture){
        // FIXME: tmp. workaround
        if(showSelection && picture) {
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
                if (bufferedImage) {
                    Image image = bufferedImage.getScaledInstance(newDimension.getWidth().intValue(), newDimension.getHeight().intValue(), Image.SCALE_SMOOTH)
                    imageIcon = new ImageIcon(image)
                }
            }
            label.setIcon(imageIcon)
            repaint()
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