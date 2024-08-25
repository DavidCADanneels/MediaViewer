package be.dafke.MediaViewer.Application.DateUtils

import javax.swing.BoxLayout
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.WindowConstants
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class DateConverterPanel extends JPanel {
    JTextField year, month, day, index, readible, hour, minutes, seconds, milliSinceCounting

    DateConverterPanel() {


        year = new JTextField(4)
        month = new JTextField(2)
        day = new JTextField(2)
        year.addActionListener { e -> calculate1()}
        month.addActionListener { e -> calculate1()}
        day.addActionListener { e -> calculate1()}
        //
        JPanel datePanel = new JPanel()
        datePanel.add new JLabel('year (yyyy):')
        datePanel.add year
        datePanel.add new JLabel('month (MM):')
        datePanel.add month
        datePanel.add new JLabel('day (dd):')
        datePanel.add day

        index = new JTextField(10)
        index.addActionListener { e -> calculate2()}
        //
        JPanel indexPanel = new JPanel()
        indexPanel.add new JLabel('Index (yyyyMMdd[hhmmss]):')
        indexPanel.add index

        readible = new JTextField(10)
        //
        JPanel readiblePanel =  new JPanel()
        readiblePanel.add new JLabel('readible:')
        readiblePanel.add readible

        hour = new JTextField(2)
        minutes = new JTextField(2)
        seconds = new JTextField(2)
        hour.addActionListener { e -> calculate1()}
        minutes.addActionListener { e -> calculate1()}
        seconds.addActionListener { e -> calculate1()}
        //
        JPanel timePanel = new JPanel()
        timePanel.add new JLabel('hour (hh):')
        timePanel.add hour
        timePanel.add new JLabel('minutes (mm):')
        timePanel.add minutes
        timePanel.add new JLabel('seconds (ss):')
        timePanel.add seconds

        milliSinceCounting = new JTextField(10)
        milliSinceCounting.addActionListener { e -> calculate3()}
        //
        JPanel milliPanel = new JPanel()
        milliPanel.add new JLabel('milliseconds since 01/01/1970:')
        milliPanel.add milliSinceCounting

        setLayout new BoxLayout(this, BoxLayout.Y_AXIS)
        add datePanel
        add timePanel
        add indexPanel
        add readiblePanel
        add milliPanel
    }

    // Use fields 'year,day,month,hour,minutes,seconds' to calculate the rest, i.e. 'ms since 1970, index'
    void calculate1(){
        int yearInt = Integer.parseInt(year.text.trim())
        int monthInt = Integer.parseInt(month.text.trim())
        int dayInt = Integer.parseInt(day.text.trim())
        int hourInt = Integer.parseInt(hour.text.trim())
        int minutesInt = Integer.parseInt(minutes.text.trim())
        int secondsInt = Integer.parseInt(seconds.text.trim())

        Calendar calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, yearInt)
        calendar.set(Calendar.MONTH, monthInt-1)
        calendar.set(Calendar.DAY_OF_MONTH, dayInt)
        calendar.set(Calendar.HOUR_OF_DAY, hourInt)
        calendar.set(Calendar.MINUTE, minutesInt)
        calendar.set(Calendar.SECOND, secondsInt)
        calendar.set(Calendar.MILLISECOND, 0)

        index.text = "${yearInt}${monthInt<10?'0':''}${monthInt}${dayInt<10?'0':''}${dayInt}${hourInt<10?'0':''}${hourInt}${minutesInt<10?'0':''}${minutesInt}${secondsInt<10?'0':''}${secondsInt}"
        readible.text = "${yearInt}-${monthInt<10?'0':''}${monthInt}-${dayInt<10?'0':''}${dayInt} ${hourInt<10?'0':''}${hourInt}:${minutesInt<10?'0':''}${minutesInt}:${secondsInt<10?'0':''}${secondsInt}"
        milliSinceCounting.text = calendar.getTimeInMillis()
    }

    // use 'index' to calculate the rest
    void calculate2(){
        // either chop of yyyy/MM/dd/hh/mm/ss
        // or use a 'template'
    }

    // use 'ms since 1970' to calculate the rest
    void calculate3(){
        String milliSecondsString = milliSinceCounting.text.trim()
        if(milliSecondsString!=''){
            try {
                long milliSeconds = Long.parseLong(milliSecondsString)
                Date date = new Date(milliSeconds)
                Calendar calendar = Calendar.getInstance()
                calendar.setTime(date)
                year.text = calendar.get(Calendar.YEAR)
                month.text = calendar.get(Calendar.MONTH) + 1
                day.text = calendar.get(Calendar.DAY_OF_MONTH)
                hour.text = calendar.get(Calendar.HOUR_OF_DAY)
                minutes.text = calendar.get(Calendar.MINUTE)
                seconds.text = calendar.get(Calendar.SECOND)
            } catch (NumberFormatException nfe){

            }
        }
    }

    static void main(String [] args) {
        JFrame frame = new JFrame("Converter")
        JPanel content = new DateConverterPanel()
        frame.setContentPane(content)
        frame.pack()
        frame.visible = true
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    }
}
