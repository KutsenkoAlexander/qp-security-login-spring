package ua.vza.kay.qp.crud.impls;

import ua.vza.kay.qp.crud.abstracts.CrudAbstractFactory;
import ua.vza.kay.qp.entity.Product;
import ua.vza.kay.qp.utils.SetLimitSQL;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kycenko on 16.06.15.
 */
@Stateless
public class ProductCrud extends CrudAbstractFactory<Product> {
    public static final String TABLEOBJECT = "Product";
    @PersistenceContext(unitName = "QP")
    private EntityManager em;

    public ProductCrud() {
        super(TABLEOBJECT);
    }

    @Override
    public Product create(Product obj) {
        return super.create(obj);
    }

    @Override
    public void update(Product obj) {
        super.update(obj);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public Product getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return super.getAll();
    }

    @Override
    public void refrash(int id) {
        super.refrash(id);
    }

    public List<Product> getAllLimit(int pageNumber) {
        Query query = em.createQuery("SELECT q FROM Product q ORDER BY q.id DESC");
        return (List<Product>) SetLimitSQL.doLimitSQL(query, pageNumber);
    }

    public void refrasheProduct(int productId) {
        Product product = em.find(Product.class, productId);
        em.refresh(product);
    }

    //Search parametrs
    public List<Product> getByNameId(int id){
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.nameId = :param");
        query.setParameter("param", id);
        query.setMaxResults(20);
        List<Product> resListLikeName = query.getResultList();
        return resListLikeName;
    }

}
