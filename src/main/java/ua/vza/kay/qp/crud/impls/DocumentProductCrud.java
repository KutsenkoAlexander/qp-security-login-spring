package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.DocumentProduct;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by kycenko on 04.09.15.
 */
@Stateless
public class DocumentProductCrud extends CrudAbstractFactory<DocumentProduct> {
    public static final String TABLEOBJECT = "DocumentIncomeproductCrud";

    public DocumentProductCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public DocumentProduct create(DocumentProduct obj) {
        return super.create(obj);
    }

    @Override
    public void update(DocumentProduct obj) {
        super.update(obj);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public DocumentProduct getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<DocumentProduct> getByLike(String name) {
        return super.getByLike(name);
    }

    @Override
    public List<DocumentProduct> getAll() {
        return super.getAll();
    }

    @Override
    public void refrash(int id) {
        super.refrash(id);
    }
}
