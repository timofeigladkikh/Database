package ru.client.model.tasks;

/**
 * Created by User on 5/15/2016.
 */
public class Task8 {
    private String surname;
    private String name;
    private String brigadeName;

    public Task8(String surname, String name, String brigadeName) {
        this.surname = surname;
        this.name = name;
        this.brigadeName = brigadeName;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getBrigadeName() {
        return brigadeName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrigadeName(String brigadeName) {
        this.brigadeName = brigadeName;
    }

    @Override
    public String toString() {
        return "Task8{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", brigadeName='" + brigadeName + '\'' +
                '}';
    }
}
