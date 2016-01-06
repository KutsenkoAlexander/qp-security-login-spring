package ua.vza.kay.qp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kycenko on 15.09.15.
 */
@Entity
@Table(name = "product_income", schema = "", catalog = "qualityofproducts")
@NamedQueries({
        @NamedQuery(name = "ProductIncome.getAll", query = "SELECT d FROM ProductIncome d ORDER BY d.datePresenter"),
        @NamedQuery(name = "ProductIncome.getByProductId", query = "SELECT d FROM ProductIncome d WHERE d.productId = :param"),
        @NamedQuery(name = "ProductIncome.getCountNewProduct", query = "SELECT COUNT(d) FROM ProductIncome d WHERE d.magazineId = :param1 AND d.control = :param2"),
        @NamedQuery(name = "ProductIncome.getNotControlProduct", query = "SELECT d FROM ProductIncome d WHERE d.control = :param")
})public class ProductIncome implements Serializable {
    private int idproductIncome;
    private int productId;
    private int magazineId;
    private int countProduct;
    private int measureId;
    private String fioPresenter;
    private long datePresenter;
    private String fioLastModify;
    private long dateLastModify;
    private String fioControler;
    private int locationId;
    private String arrangements;
    private String note;
    private Integer control;
    private Collection<DocumentIncomeproduct> documentIncomeproductsByIdproductIncome;
    private SprLocation sprLocationByLocationId;
    private SprMagazine sprMagazineByMagazineId;
    private SprMeasure sprMeasureByMeasureId;
    private Product productByProductId;

    public ProductIncome() {}

    @Id
    @Column(name = "idproduct_income", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdproductIncome() {
        return idproductIncome;
    }

    public void setIdproductIncome(int idproductIncome) {
        this.idproductIncome = idproductIncome;
    }

    @Basic
    @Column(name = "product_id", nullable = false, insertable = true, updatable = true)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "magazine_id", nullable = false, insertable = true, updatable = true)
    public int getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(int magazineId) {
        this.magazineId = magazineId;
    }

    @Basic
    @Column(name = "count_product", nullable = false, insertable = true, updatable = true)
    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    @Basic
    @Column(name = "measure_id", nullable = false, insertable = true, updatable = true)
    public int getMeasureId() {
        return measureId;
    }

    public void setMeasureId(int measureId) {
        this.measureId = measureId;
    }

    @Basic
    @Column(name = "fio_presenter", nullable = false, insertable = true, updatable = true, length = 45)
    public String getFioPresenter() {
        return fioPresenter;
    }

    public void setFioPresenter(String fioPresenter) {
        this.fioPresenter = fioPresenter;
    }

    @Basic
    @Column(name = "date_presenter", nullable = false, insertable = true, updatable = true)
    public long getDatePresenter() {
        return datePresenter;
    }

    public void setDatePresenter(long datePresenter) {
        this.datePresenter = datePresenter;
    }

    @Basic
    @Column(name = "fio_last_modify", nullable = false, insertable = true, updatable = true, length = 45)
    public String getFioLastModify() {
        return fioLastModify;
    }

    public void setFioLastModify(String fioLastModify) {
        this.fioLastModify = fioLastModify;
    }

    @Basic
    @Column(name = "date_last_modify", nullable = false, insertable = true, updatable = true)
    public long getDateLastModify() {
        return dateLastModify;
    }

    public void setDateLastModify(long dateLastModify) {
        this.dateLastModify = dateLastModify;
    }

    @Basic
    @Column(name = "fio_controler", nullable = true, insertable = true, updatable = true, length = 45)
    public String getFioControler() {
        return fioControler;
    }

    public void setFioControler(String fioControler) {
        this.fioControler = fioControler;
    }

    @Basic
    @Column(name = "location_id", nullable = false, insertable = true, updatable = true)
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "arrangements", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getArrangements() {
        return arrangements;
    }

    public void setArrangements(String arrangements) {
        this.arrangements = arrangements;
    }

    @Basic
    @Column(name = "note", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "control", nullable = true, insertable = true, updatable = true)
    public Integer getControl() {
        return control;
    }

    public void setControl(Integer control) {
        this.control = control;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductIncome that = (ProductIncome) o;

        if (idproductIncome != that.idproductIncome) return false;
        if (productId != that.productId) return false;
        if (magazineId != that.magazineId) return false;
        if (countProduct != that.countProduct) return false;
        if (measureId != that.measureId) return false;
        if (datePresenter != that.datePresenter) return false;
        if (dateLastModify != that.dateLastModify) return false;
        if (locationId != that.locationId) return false;
        if (fioPresenter != null ? !fioPresenter.equals(that.fioPresenter) : that.fioPresenter != null) return false;
        if (fioLastModify != null ? !fioLastModify.equals(that.fioLastModify) : that.fioLastModify != null)
            return false;
        if (fioControler != null ? !fioControler.equals(that.fioControler) : that.fioControler != null) return false;
        if (arrangements != null ? !arrangements.equals(that.arrangements) : that.arrangements != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (control != null ? !control.equals(that.control) : that.control != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idproductIncome;
        result = 31 * result + productId;
        result = 31 * result + magazineId;
        result = 31 * result + countProduct;
        result = 31 * result + measureId;
        result = 31 * result + (fioPresenter != null ? fioPresenter.hashCode() : 0);
        result = 31 * result + (int) (datePresenter ^ (datePresenter >>> 32));
        result = 31 * result + (fioLastModify != null ? fioLastModify.hashCode() : 0);
        result = 31 * result + (int) (dateLastModify ^ (dateLastModify >>> 32));
        result = 31 * result + (fioControler != null ? fioControler.hashCode() : 0);
        result = 31 * result + locationId;
        result = 31 * result + (arrangements != null ? arrangements.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (control != null ? control.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productIncomeByIncomeId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Collection<DocumentIncomeproduct> getDocumentIncomeproductsByIdproductIncome() {
        return documentIncomeproductsByIdproductIncome;
    }

    public void setDocumentIncomeproductsByIdproductIncome(Collection<DocumentIncomeproduct> documentIncomeproductsByIdproductIncome) {
        this.documentIncomeproductsByIdproductIncome = documentIncomeproductsByIdproductIncome;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false, insertable = false, updatable = false)
    public SprLocation getSprLocationByLocationId() {
        return sprLocationByLocationId;
    }

    public void setSprLocationByLocationId(SprLocation sprLocationByLocationId) {
        this.sprLocationByLocationId = sprLocationByLocationId;
    }

    @ManyToOne
    @JoinColumn(name = "magazine_id", referencedColumnName = "magazine_id", nullable = false, insertable = false, updatable = false)
    public SprMagazine getSprMagazineByMagazineId() {
        return sprMagazineByMagazineId;
    }

    public void setSprMagazineByMagazineId(SprMagazine sprMagazineByMagazineId) {
        this.sprMagazineByMagazineId = sprMagazineByMagazineId;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "measure_id", referencedColumnName = "measure_id", nullable = false, insertable = false, updatable = false)
    public SprMeasure getSprMeasureByMeasureId() {
        return sprMeasureByMeasureId;
    }

    public void setSprMeasureByMeasureId(SprMeasure sprMeasureByMeasureId) {
        this.sprMeasureByMeasureId = sprMeasureByMeasureId;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }
}
