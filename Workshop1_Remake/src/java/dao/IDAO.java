/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author huudu
 */

import java.util.List;

public interface IDAO<T, K> {
    boolean create(T entity);
    List<T> readAll();
    T readById(K id);
    boolean update(T entity);
    boolean delete(K id);
    List<T> search(String searchTerm);
    public List<T> searchByName(String searchTerm);
}
