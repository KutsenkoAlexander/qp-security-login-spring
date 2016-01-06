package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.ProductIncome;
import ua.vza.kay.qp.utils.SetLimitSQL;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Aleksandr on 26.06.15.
 */
@Stateless
public class ProductIncomeCrud extends CrudAbstractFactory<ProductIncome> {
    public static final String TABLEOBJECT = "ProductIncome";
    @PersistenceContext(unitName = "QP")
    private EntityManager em;

    public ProductIncomeCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public ProductIncome create(ProductIncome obj) {
        return super.create(obj);
    }

    @Override
    public void update(ProductIncome obj) {
        super.update(obj);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public ProductIncome getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<ProductIncome> getAll() {
        return super.getAll();
    }

    @Override
    public void refrash(int id) {
        super.refrash(id);
    }

    public List<ProductIncome> getByMagazineIdLimit(int pageNumber, int magazineId) {
        Query query = em.createQuery("SELECT p FROM ProductIncome p WHERE p.magazineId = :param1 ORDER BY p.control DESC", ProductIncome.class);
        query.setParameter("param1", magazineId);
        return (List<ProductIncome>) SetLimitSQL.doLimitSQL(query, pageNumber);
    }

    public List<ProductIncome> getByProductId(int productId) {
        Query query = em.createNamedQuery("ProductIncome.getByProductId", ProductIncome.class);
        query.setParameter("param", productId);
        return (List<ProductIncome>) query.getResultList();
    }

    public Object getCountNewProduct(int magazineId){
        Query query = em.createNamedQuery("ProductIncome.getCountNewProduct", ProductIncome.class);
        query.setParameter("param1", magazineId);
        query.setParameter("param2", 999);
        return (Object) query.getSingleResult();
    }

    public List<ProductIncome> getNotControlProduct(int pageNumber) {
        Query query = em.createNamedQuery("ProductIncome.getNotControlProduct", ProductIncome.class);
        query.setParameter("param", 2);
        return (List<ProductIncome>) SetLimitSQL.doLimitSQL(query, pageNumber);
    }
}
