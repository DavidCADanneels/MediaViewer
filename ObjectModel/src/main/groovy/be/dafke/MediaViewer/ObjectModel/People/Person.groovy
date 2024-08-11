package be.dafke.MediaViewer.ObjectModel.People

class Person {
    String firstName, lastName

    Person() {
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
