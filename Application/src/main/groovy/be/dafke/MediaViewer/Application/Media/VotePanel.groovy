package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Picture

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTextField
import java.awt.Dimension
import java.awt.event.KeyEvent

class VotePanel extends JPanel {
    JButton delete, zeroStar, oneStar, twoStar, threeStar

    Picture currentPicture
    int currentIndex
    ImagePanel view
    List<Picture> pictures

    JTextField currentStars

    VotePanel(ImagePanel imagePanel, List<Picture> pictures) {
        view = imagePanel
        this.pictures = pictures

        delete = new JButton("-1")
        zeroStar = new JButton("0")
        oneStar = new JButton("1")
        twoStar = new JButton("2")
        threeStar = new JButton("3")

        currentStars = new JTextField(2)
        currentStars.enabled = false

//        delete.getInputMap().put KeyStroke.getKeyStroke(KeyEvent.VK_0,KeyEvent.CTRL_DOWN_MASK), "delete"
//        delete.getActionMap().put "delete", deletePicture()
        // Ctrl+0 : set stars = -1 (do not include in any series)
        // Cttl+1 : set stars = 1 (set or add? later)
        // Cttl+2 : set stars = 2
        // Cttl+3 : set stars = 3

        delete.addActionListener { e -> deletePicture() }
        zeroStar.addActionListener { e -> showNext() }
        oneStar.addActionListener { e -> setVote(1) }
        twoStar.addActionListener { e -> setVote(2) }
        threeStar.addActionListener { e -> setVote(3) }

        delete.setMnemonic(KeyEvent.VK_DELETE)
        zeroStar.setMnemonic(KeyEvent.VK_0)
        oneStar.setMnemonic(KeyEvent.VK_1)
        twoStar.setMnemonic(KeyEvent.VK_2)
        threeStar.setMnemonic(KeyEvent.VK_3)

        add currentStars
        add threeStar
        add twoStar
        add oneStar
        add zeroStar
        add delete

        setCurrentPicture(pictures.get(0))
    }

    void setCurrentPicture(Picture currentPicture) {
        this.currentPicture = currentPicture
        view.scrollPane.size = new Dimension(1200,800)
        view.setPicture(currentPicture)
        currentStars.text = currentPicture.stars
    }

    void setVote(Integer nr){
        currentPicture.stars = nr
        showNext()
    }

    void addVote(Integer nr){
        currentPicture.stars += nr
        showNext()
    }

    void deletePicture(){
        currentPicture.stars = -1
        showNext()
    }

    void showNext(){
        currentIndex++
        if(currentIndex>=pictures.size()) {
            currentIndex = 0
        }
        currentPicture = pictures.get(currentIndex)
        setCurrentPicture(currentPicture)
//            view.setPicture(currentPicture)
//            currentStars.text = currentPicture.stars
    }
}
