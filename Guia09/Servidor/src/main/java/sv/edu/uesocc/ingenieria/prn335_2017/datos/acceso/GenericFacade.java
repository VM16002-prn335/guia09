/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso;

import java.util.List;

/**
 *
 * @author bryan
 * @param <T>
 */
public interface GenericFacade<T> {
    
    void create(T t);
    
    T crear(T t);

    void edit(T t);
    
    T editar(T t);

    void remove(T t);
    
    T remover(T t);

    T find(Object id);

    List<T> findAll();

    List<T> findRange(int inicio, int tamanio);

    int count();
    
    
}
