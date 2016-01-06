package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.SprMagazine;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Aleksandr on 21.06.15.
 */
@Stateless
public class SprMagazineCrud extends CrudAbstractFactory<SprMagazine> {
    public static final String TABLEOBJECT = "SprMagazine";

    public SprMagazineCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public SprMagazine create(SprMagazine obj) {
        return super.create(obj);
    }

    @Override
    public void update(SprMagazine person) {
        super.update(person);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public List<SprMagazine> getByLike(String name) {
        return super.getByLike(name);
    }

    @Override
    public List<SprMagazine> getAll() {
        return super.getAll();
    }
}
