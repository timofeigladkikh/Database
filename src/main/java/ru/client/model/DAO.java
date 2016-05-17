package ru.client.model;

import java.util.List;

public interface DAO<T, K> {

    void create(T entity);

    T getById(K id);

    List<T> getAll();

    boolean delete(K id);

    boolean update(T entity);

}