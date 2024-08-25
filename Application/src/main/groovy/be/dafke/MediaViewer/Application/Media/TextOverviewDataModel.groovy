package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Media.Text
import be.dafke.MediaViewer.ObjectModel.People.Person
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.table.AbstractTableModel

import static java.util.ResourceBundle.getBundle

class TextOverviewDataModel extends AbstractTableModel {
    static int ID_COL = 0
    static int CREATION_DATE_COL = 1
    static int CREATION_TIME_COL = 2
    static int SIZE_COL = 3
    static int OWNER_COL = 4
    static int INDEX_COL = 5
    static int CHAPTER_COL = 6
    static int NR_OF_COL = 7
    static int FILE_NAME_COL = 7
    static int EXTENSION_COL = 8
//    static int FULL_PATH_COL = 9

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    List<Text> textFragments = []
    Story story

    TextOverviewDataModel() {
        columnClasses.put(ID_COL, String.class)
//        columnClasses.put(FOLDER_NAME_COL, String.class)
        columnClasses.put(EXTENSION_COL, String.class)
        columnClasses.put(FILE_NAME_COL, String.class)
        columnClasses.put(INDEX_COL, String.class)
        columnClasses.put(CHAPTER_COL, String.class)
        columnClasses.put(SIZE_COL, String.class)
        columnClasses.put(CREATION_DATE_COL, Date.class)
        columnClasses.put(CREATION_TIME_COL, String.class)
        columnClasses.put(OWNER_COL, Person.class)
//        columnClasses.put(FULL_PATH_COL, File.class)

        columnNames.put(ID_COL, getBundle("MediaViewer").getString("ID"))
//        columnNames.put(FOLDER_NAME_COL, getBundle("MediaViewer").getString("FOLDER_NAME"))
        columnNames.put(EXTENSION_COL, getBundle("MediaViewer").getString("EXTENSION"))
        columnNames.put(FILE_NAME_COL, getBundle("MediaViewer").getString("FILE_NAME"))
        columnNames.put(INDEX_COL, getBundle("MediaViewer").getString("MEDIA_INDEX"))
        columnNames.put(SIZE_COL, getBundle("MediaViewer").getString("TEXT_SIZE"))
        columnNames.put(CHAPTER_COL, getBundle("MediaViewer").getString("CHAPTER"))
        columnNames.put(CREATION_DATE_COL, getBundle("MediaViewer").getString("CREATION_DATE"))
        columnNames.put(CREATION_TIME_COL, getBundle("MediaViewer").getString("CREATION_TIME"))
        columnNames.put(OWNER_COL, getBundle("MediaViewer").getString("OWNER"))
//        columnNames.put(FULL_PATH_COL, getBundle("MediaViewer").getString("FULL_PATH"))
    }

    void setChapter(Chapter chapter) {
        if(chapter != null) {
            String prefix = chapter.getPrefix()
            textFragments = []
            if (prefix) {
                List<Text> allFragments = story.getTextFragments()
                allFragments.each { Text text ->
                    String myChapter = text.getChapter()
                    if (myChapter != null && myChapter == prefix) {
                        textFragments.add text
                    }
                }
            }
        }
        fireTableDataChanged()
    }

    void setStory(Story story) {
        this.story = story
        textFragments = story.getTextFragments()
        fireTableDataChanged()
    }

//    void setPictures(List<Picture> pictures) {
//        this.pictures = pictures
//    }

    Text getObject(int row) {
        return textFragments.get(row)
    }

    @Override
    int getRowCount() {
        textFragments.size()
    }

    @Override
    int getColumnCount() {
        return NR_OF_COL
    }

    @Override
    String getColumnName(int columnIndex) {
        columnNames.get(columnIndex)
    }

    @Override
    Class<?> getColumnClass(int columnIndex) {
        columnClasses.get(columnIndex)
    }

    @Override
    boolean isCellEditable(int rowIndex, int columnIndex) {
//        return false
        return columnIndex == INDEX_COL
//        return columnIndex == OWNER_COL
    }

    @Override
    Object getValueAt(int rowIndex, int columnIndex) {
        Text text = getObject(rowIndex)
        if (text) {
            if (columnIndex == ID_COL) {
                return text.getFileName()
//            } else if (columnIndex == FOLDER_NAME_COL) {
//                return text.getSubFolderName()
            } else if (columnIndex == EXTENSION_COL) {
                return text.getExtension()
            } else if (columnIndex == INDEX_COL) {
                return text.getIndexNumber()
            } else if (columnIndex == CHAPTER_COL) {
                // TODO text.getChapter -> int -> Chapter.toString()
                return text.getChapter()
            } else if (columnIndex == OWNER_COL) {
                Integer id = text.getOwner()
                if(id != null && id != -1) {
                    return story.getPersons().get(id)
                } else return null
            } else if (columnIndex == CREATION_DATE_COL) {
                return text.getCreationDate()
            } else if (columnIndex == CREATION_TIME_COL) {
                Date date = text.getCreationDate()
                if(date == null) return null
                Calendar calendar = Calendar.getInstance()
                calendar.setTime(date)
                int hours = calendar.get(Calendar.HOUR_OF_DAY)
                int minutes = calendar.get(Calendar.MINUTE)
                int seconds = calendar.get(Calendar.SECOND)
                return "${hours}:${minutes<10?'0':''}${minutes}:${seconds<10?'0':''}${seconds}"
//                return "${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}"
            } else if (columnIndex == FILE_NAME_COL) {
                return "${text.getFileName()}.${text.getExtension()}"
            } else if (columnIndex == SIZE_COL) {
                return ""
            } else {
                null
            }
            null
        }
        null
    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {
        Text text = getObject(rowIndex)
        if (columnIndex == INDEX_COL) {
            String indexNumber = (String) value
            text.setIndexNumber(indexNumber)
//        } else if (columnIndex == CHAPTER_COL) {
        } else if (columnIndex == OWNER_COL) {
            List<Person> list = story.getPersons()
            Person participant = (Person) value
            Integer id = list.indexOf(participant)
            if (id != -1) {
                System.out.println("Participant: ${participant} has ID: ${id}")
                text.setOwner(id)
            }
        }
    }
}
