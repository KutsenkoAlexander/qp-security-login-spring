package ua.vza.kay.qp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kycenko on 15.09.15.
 */
@Entity
@Table(name = "spr_magazine", schema = "", catalog = "qualityofproducts")
@NamedQuery(name = "SprMagazine.getAll", query = "SELECT d FROM SprMagazine d ORDER BY d.name")
public class SprMagazine implements Serializable {
    private int magazineId;
    private String name;
    private Collection<ProductIncome> productIncomesByMagazineId;

    public SprMagazine() {}

    @Id
    @Column(name = "magazine_id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(int magazineId) {
        this.magazineId = magazineId;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 25)
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

        SprMagazine that = (SprMagazine) o;

        if (magazineId != that.magazineId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = magazineId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sprMagazineByMagazineId", fetch = FetchType.LAZY)
    public Collection<ProductIncome> getProductIncomesByMagazineId() {
        return productIncomesByMagazineId;
    }

    public void setProductIncomesByMagazineId(Collection<ProductIncome> productIncomesByMagazineId) {
        this.productIncomesByMagazineId = productIncomesByMagazineId;
    }
}
