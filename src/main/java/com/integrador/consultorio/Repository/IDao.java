package com.integrador.consultorio.Repository;

import java.util.List;

public interface IDao<T> {

     T guardar(T t);

    void eliminar(long id);

    T buscar(long id);

     List<T> listarTodos();

    void actualizar(T t);


}
