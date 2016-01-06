package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.SprStandartType;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by kycenko on 22.06.15.
 */
@Stateless
public class SprStandartTypeCrud extends CrudAbstractFactory<SprStandartType> {
    public static final String TABLEOBJECT = "SprStandartType";

    public SprStandartTypeCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public SprStandartType create(SprStandartType obj) {
        return super.create(obj);
    }

    @Override
    public void update(SprStandartType person) {
        super.update(person);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<SprStandartType> getByLike(String name) {
        return super.getByLike(name);
    }

    @Override
    public List<SprStandartType> getAll() {
        return super.getAll();
    }
}
