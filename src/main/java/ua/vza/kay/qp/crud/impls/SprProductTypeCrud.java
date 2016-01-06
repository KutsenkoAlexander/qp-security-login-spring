package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.SprProductType;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by kycenko on 23.06.15.
 */
@Stateless
public class SprProductTypeCrud extends CrudAbstractFactory<SprProductType> {
    public static final String TABLEOBJECT = "SprProductType";

    public SprProductTypeCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public SprProductType create(SprProductType obj) {
        return super.create(obj);
    }

    @Override
    public void update(SprProductType person) {
        super.update(person);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public SprProductType getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<SprProductType> getByLike(String name) {
        return super.getByLike(name);
    }

    @Override
    public List<SprProductType> getAll() {
        return super.getAll();
    }
}
