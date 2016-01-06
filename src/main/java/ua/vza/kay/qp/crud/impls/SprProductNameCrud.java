package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.SprProductName;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by kycenko on 16.06.15.
 */
@Stateless
public class SprProductNameCrud extends CrudAbstractFactory<SprProductName> {
    public static final String TABLEOBJECT = "SprProductName";

    public SprProductNameCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public SprProductName create(SprProductName obj) {
        return super.create(obj);
    }

    @Override
    public void update(SprProductName person) {
        super.update(person);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public SprProductName getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<SprProductName> getByLike(String name) {
        return super.getByLike(name);
    }

    @Override
    public List<SprProductName> getAll() {
        return super.getAll();
    }
}
