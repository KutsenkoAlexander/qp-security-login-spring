package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.SprStandartName;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by kycenko on 23.06.15.
 */
@Stateless
public class SprStandartNameCrud extends CrudAbstractFactory<SprStandartName> {
    public static final String TABLEOBJECT = "SprStandartName";

    public SprStandartNameCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public List<SprStandartName> getAll() {
        return super.getAll();
    }

    @Override
    public SprStandartName create(SprStandartName obj) {
        return super.create(obj);
    }

    @Override
    public void update(SprStandartName person) {
        super.update(person);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public SprStandartName getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<SprStandartName> getByLike(String name) {
        return super.getByLike(name);
    }
}
