package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.SprMeasure;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by kycenko on 24.06.15.
 */
@Stateless
public class SprMeasureCrud extends CrudAbstractFactory<SprMeasure> {
    public static final String TABLEOBJECT = "SprMeasure";

    public SprMeasureCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public SprMeasure create(SprMeasure obj) {
        return super.create(obj);
    }

    @Override
    public void update(SprMeasure person) {
        super.update(person);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public SprMeasure getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<SprMeasure> getByLike(String name) {
        return super.getByLike(name);
    }

    @Override
    public List<SprMeasure> getAll() {
        return super.getAll();
    }
}
