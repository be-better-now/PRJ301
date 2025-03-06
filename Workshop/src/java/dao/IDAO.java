/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author huudu
 */
public interface IDAO<T> {
    List<T> getAll();
    T getById(int id);
    boolean insert(T obj);
    boolean update(T obj);
    boolean delete(int id);
}

