package ua.vza.kay.qp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kycenko on 15.09.15.
 */
@Entity
@Table(name = "spr_measure", schema = "", catalog = "qualityofproducts")
@NamedQuery(name = "SprMeasure.getAll", query = "SELECT d FROM SprMeasure d ORDER BY d.name")
public class SprMeasure implements Serializable {
    private int measureId;
    private String name;
    private Collection<ProductIncome> productIncomesByMeasureId;

    public SprMeasure() {}

    @Id
    @Column(name = "measure_id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getMeasureId() {
        return measureId;
    }

    public void setMeasureId(int measureId) {
        this.measureId = measureId;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SprMeasure that = (SprMeasure) o;

        if (measureId != that.measureId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = measureId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sprMeasureByMeasureId", fetch = FetchType.LAZY)
    public Collection<ProductIncome> getProductIncomesByMeasureId() {
        return productIncomesByMeasureId;
    }

    public void setProductIncomesByMeasureId(Collection<ProductIncome> productIncomesByMeasureId) {
        this.productIncomesByMeasureId = productIncomesByMeasureId;
    }
}
