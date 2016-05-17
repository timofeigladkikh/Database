package ru.client.view.dialogs;

public interface DialogHandler<T> {
    void handle(T entity);
}
