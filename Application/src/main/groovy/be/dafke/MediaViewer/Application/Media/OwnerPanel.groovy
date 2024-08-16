package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JCheckBox
import javax.swing.JComboBox
import javax.swing.JPanel

class OwnerPanel extends JPanel {
    JComboBox ownerComboBox
    JCheckBox setOwner
//    Story story
//
//    Story getStory() {
//        return story
//    }
//
//    void setStory(Story story) {
//        this.story = story
//    }

    OwnerPanel(Story story) {
//        this.story = story
        ownerComboBox = new JComboBox<>()
        story.getPersons().each { ownerComboBox.addItem(it) }
        //
        setOwner = new JCheckBox("Set Owner")
        setOwner.setSelected true
        setOwner.addActionListener { e ->
            if(setOwner.selected){
//                ownerComboBox.enabled = true
            } else {
                ownerComboBox.setSelectedIndex(-1)
//                ownerComboBox.enabled = false
            }
            ownerComboBox.enabled = setOwner.selected
        }
        //
        add setOwner
        add ownerComboBox
    }

//    Person getSelectedItem() {
//        return (Person) ownerComboBox.selectedItem
//    }

    int getSelectedIndex() {
        return ownerComboBox.selectedIndex
    }

    boolean isSetOwnerChecked() {
        return setOwner.selected
    }
}
