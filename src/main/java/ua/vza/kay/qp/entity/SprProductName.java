package ua.vza.kay.qp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kycenko on 15.09.15.
 */
@Entity
@Table(name = "spr_product_name", schema = "", catalog = "qualityofproducts")
@NamedQuery(name = "SprProductName.getAll", query = "SELECT d FROM SprProductName d ORDER BY d.name")
public class SprProductName implements Serializable {
    private int sprNameId;
    private String name;
    private Collection<Product> productsBySprNameId;

    public SprProductName() {}

    @Id
    @Column(name = "spr_name_id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getSprNameId() {
        return sprNameId;
    }

    public void setSprNameId(int sprNameId) {
        this.sprNameId = sprNameId;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 65535)
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

        SprProductName that = (SprProductName) o;

        if (sprNameId != that.sprNameId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sprNameId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sprProductNameByNameId", fetch = FetchType.LAZY)
    public Collection<Product> getProductsBySprNameId() {
        return productsBySprNameId;
    }

    public void setProductsBySprNameId(Collection<Product> productsBySprNameId) {
        this.productsBySprNameId = productsBySprNameId;
    }
}
