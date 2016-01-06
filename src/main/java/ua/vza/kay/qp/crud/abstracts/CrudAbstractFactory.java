package ua.vza.kay.qp.crud.abstracts;

import ua.vza.kay.qp.crud.interfaces.CrudFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import java.util.List;

/**
 * Created by kycenko on 16.06.15.
 */
@Stateless
public abstract class CrudAbstractFactory<T> implements CrudFactory<T> {
    @PersistenceContext(unitName = "QP")
    private EntityManager em;

    private String tableObject;

    public CrudAbstractFactory() {}

    public CrudAbstractFactory(String tableObject) {
        this.tableObject = tableObject;
    }

    public T create(T obj) {
        if (obj == null)
            throw new BadRequestException();
        return em.merge(obj);
    }

    public void update(T obj) {
        create(obj);
    }

    public void delete(int id) {
        em.remove(getById(id));
    }

    public T getById(int id) {
        T clas = null;
        try {
            clas = (T) Class.forName("ua.vza.kay.qp.entity."+tableObject).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) em.find(clas.getClass(), id);
    }

    public List<T> getByLike(String name) {
        Query query = em.createQuery("SELECT p FROM " + tableObject + " p WHERE UPPER(p.name) LIKE :param ORDER BY p.name");
        query.setParameter("param", name.toUpperCase() + "%");
        query.setMaxResults(20);
        List<T> resListLikeName = query.getResultList();
        return resListLikeName;
    }

    public List<T> getAll() {
        TypedQuery<T> query = (TypedQuery<T>) em.createNamedQuery(tableObject + ".getAll", Object.class);
        List<T> resList = query.getResultList();
        return resList;
    }

    public void refrash(int id) {
        T clas = null;
        try {
            clas = (T) (Class.forName("ua.vza.kay.qp.entity."+tableObject).newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        T obj = (T) (em.find(clas.getClass(), id));
        em.refresh(obj);
    }

}
