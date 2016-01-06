package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.SprConsumer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kycenko on 24.06.15.
 */
@Stateless
public class SprConsumerCrud extends CrudAbstractFactory<SprConsumer> {
    public static final String TABLEOBJECT = "SprConsumer";
    @PersistenceContext(unitName = "QP")
    private EntityManager em;

    public SprConsumerCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public SprConsumer create(SprConsumer obj) {
        return super.create(obj);
    }

    @Override
    public void update(SprConsumer person) {
        super.update(person);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public SprConsumer getById(int id) {
        return super.getById(id);
    }

    public SprConsumer getByName(String name) {
        Query query = em.createQuery("SELECT p FROM SprConsumer p WHERE UPPER(p.name) = :param");
        query.setParameter("param", name.toUpperCase());
        SprConsumer resName = (SprConsumer) query.getSingleResult();
        return resName;
    }

    @Override
    public List<SprConsumer> getByLike(String name) {
        return super.getByLike(name);
    }

    @Override
    public List<SprConsumer> getAll() {
        return super.getAll();
    }
}
