package ua.vza.kay.qp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kycenko on 15.09.15.
 */
@Entity
@Table(name = "spr_product_type", schema = "", catalog = "qualityofproducts")
@NamedQuery(name = "SprProductType.getAll", query = "SELECT d FROM SprProductType d ORDER BY d.name")
public class SprProductType implements Serializable {
    private int sprTypeId;
    private String name;
    private Collection<Product> productsBySprTypeId;

    public SprProductType() {}

    @Id
    @Column(name = "spr_type_id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getSprTypeId() {
        return sprTypeId;
    }

    public void setSprTypeId(int sprTypeId) {
        this.sprTypeId = sprTypeId;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 45)
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

        SprProductType that = (SprProductType) o;

        if (sprTypeId != that.sprTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sprTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sprProductTypeByTypeId", fetch = FetchType.LAZY)
    public Collection<Product> getProductsBySprTypeId() {
        return productsBySprTypeId;
    }

    public void setProductsBySprTypeId(Collection<Product> productsBySprTypeId) {
        this.productsBySprTypeId = productsBySprTypeId;
    }
}
