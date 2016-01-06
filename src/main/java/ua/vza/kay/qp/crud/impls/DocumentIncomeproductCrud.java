package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.DocumentIncomeproduct;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by kycenko on 01.07.15.
 */
@Stateless
public class DocumentIncomeproductCrud extends CrudAbstractFactory<DocumentIncomeproduct> {
    public static final String TABLEOBJECT = "DocumentIncomeproductCrud";

    public DocumentIncomeproductCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public DocumentIncomeproduct create(DocumentIncomeproduct obj) {
        return super.create(obj);
    }

    @Override
    public void update(DocumentIncomeproduct obj) {
        super.update(obj);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public DocumentIncomeproduct getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<DocumentIncomeproduct> getByLike(String name) {
        return super.getByLike(name);
    }

    @Override
    public List<DocumentIncomeproduct> getAll() {
        return super.getAll();
    }

}
