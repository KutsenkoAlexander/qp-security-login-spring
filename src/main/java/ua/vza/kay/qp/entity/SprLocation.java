package ua.vza.kay.qp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kycenko on 15.09.15.
 */
@Entity
@Table(name = "spr_location", schema = "", catalog = "qualityofproducts")
@NamedQuery(name = "SprLocation.getAll", query = "SELECT d FROM SprLocation d ORDER BY d.name")
public class SprLocation implements Serializable {
    private int locationId;
    private String name;
    private Collection<ProductIncome> productIncomesByLocationId;

    public SprLocation() {}

    @Id
    @Column(name = "location_id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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

        SprLocation that = (SprLocation) o;

        if (locationId != that.locationId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = locationId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sprLocationByLocationId", fetch = FetchType.LAZY)
    public Collection<ProductIncome> getProductIncomesByLocationId() {
        return productIncomesByLocationId;
    }

    public void setProductIncomesByLocationId(Collection<ProductIncome> productIncomesByLocationId) {
        this.productIncomesByLocationId = productIncomesByLocationId;
    }
}
