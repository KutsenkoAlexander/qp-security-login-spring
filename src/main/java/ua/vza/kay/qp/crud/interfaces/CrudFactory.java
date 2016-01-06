package ua.vza.kay.qp.crud.interfaces;

import java.util.List;

/**
 * Created by kycenko on 16.06.15.
 */
public interface CrudFactory<T> {
    //Создает новую запись
    T create(T obj);

    //Обновляет запись выбраного объекта
    void update(T obj);

    //Удаляет запись по id
    void delete(int id);

    //Возвращает объект записи по id
    T getById(int id);

    //Возвращает объект записи по совпадениям
    List<T> getByLike(String name);

    //Возвращает список объектов
    List<T> getAll();
}
