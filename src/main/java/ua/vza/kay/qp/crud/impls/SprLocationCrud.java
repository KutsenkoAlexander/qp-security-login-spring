package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.SprLocation;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by kycenko on 30.06.15.
 */
@Stateless
public class SprLocationCrud extends CrudAbstractFactory<SprLocation> {
    public static final String TABLEOBJECT = "SprLocation";

    public SprLocationCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public SprLocation create(SprLocation obj) {
        return super.create(obj);
    }

    @Override
    public void update(SprLocation obj) {
        super.update(obj);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public SprLocation getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<SprLocation> getByLike(String name) {
        return super.getByLike(name);
    }

    @Override
    public List<SprLocation> getAll() {
        return super.getAll();
    }
}
