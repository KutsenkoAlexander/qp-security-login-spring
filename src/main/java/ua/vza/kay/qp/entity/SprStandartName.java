package ua.vza.kay.qp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kycenko on 15.09.15.
 */
@Entity
@Table(name = "spr_standart_name", schema = "", catalog = "qualityofproducts")
@NamedQuery(name = "SprStandartName.getAll", query = "SELECT d FROM SprStandartName d ORDER BY d.name")
public class SprStandartName implements Serializable {
    private int sprStdNameId;
    private String name;
    private int stdIdType;
    private Collection<Product> productsBySprStdNameId;
    private Collection<Product> productsBySprStdNameId_0;
    private SprStandartType sprStandartTypeByStdIdType;

    public SprStandartName() {}

    @Id
    @Column(name = "spr_std_name_id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getSprStdNameId() {
        return sprStdNameId;
    }

    public void setSprStdNameId(int sprStdNameId) {
        this.sprStdNameId = sprStdNameId;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "std_id_type", nullable = false, insertable = true, updatable = true)
    public int getStdIdType() {
        return stdIdType;
    }

    public void setStdIdType(int stdIdType) {
        this.stdIdType = stdIdType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SprStandartName that = (SprStandartName) o;

        if (sprStdNameId != that.sprStdNameId) return false;
        if (stdIdType != that.stdIdType) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sprStdNameId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + stdIdType;
        return result;
    }

    @OneToMany(mappedBy = "sprStandartNameByStdNameId")
    public Collection<Product> getProductsBySprStdNameId() {
        return productsBySprStdNameId;
    }

    public void setProductsBySprStdNameId(Collection<Product> productsBySprStdNameId) {
        this.productsBySprStdNameId = productsBySprStdNameId;
    }

    @OneToMany(mappedBy = "sprStandartNameByStdTypeId")
    public Collection<Product> getProductsBySprStdNameId_0() {
        return productsBySprStdNameId_0;
    }

    public void setProductsBySprStdNameId_0(Collection<Product> productsBySprStdNameId_0) {
        this.productsBySprStdNameId_0 = productsBySprStdNameId_0;
    }

    @ManyToOne
    @JoinColumn(name = "std_id_type", referencedColumnName = "spr_std_type_id", nullable = false, insertable = false, updatable = false)
    public SprStandartType getSprStandartTypeByStdIdType() {
        return sprStandartTypeByStdIdType;
    }

    public void setSprStandartTypeByStdIdType(SprStandartType sprStandartTypeByStdIdType) {
        this.sprStandartTypeByStdIdType = sprStandartTypeByStdIdType;
    }
}
