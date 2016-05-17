package ru.client;

public interface Observable {
    void addListener(Observer observer);

    void removeListener(Observer observer);
}
