package be.dafke.MediaViewer.ObjectModel.Interactive

class Participant {
    String firstName, lastName

    Participant() {
        firstName = ''
        lastName = ''
    }

    @Override
    String toString(){
        return firstName
    }

    String getFirstName() {
        return firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() {
        return lastName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }
}
