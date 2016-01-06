package ua.vza.kay.qp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kycenko on 15.09.15.
 */
@Entity
@Table(name = "spr_standart_type", schema = "", catalog = "qualityofproducts")
@NamedQuery(name = "SprStandartType.getAll", query = "SELECT d FROM SprStandartType d ORDER BY d.name")
public class SprStandartType implements Serializable {
    private int sprStdTypeId;
    private String name;
    private Collection<SprStandartName> sprStandartNamesBySprStdTypeId;

    public SprStandartType() {}

    @Id
    @Column(name = "spr_std_type_id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getSprStdTypeId() {
        return sprStdTypeId;
    }

    public void setSprStdTypeId(int sprStdTypeId) {
        this.sprStdTypeId = sprStdTypeId;
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

        SprStandartType that = (SprStandartType) o;

        if (sprStdTypeId != that.sprStdTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sprStdTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sprStandartTypeByStdIdType", fetch = FetchType.LAZY)
    public Collection<SprStandartName> getSprStandartNamesBySprStdTypeId() {
        return sprStandartNamesBySprStdTypeId;
    }

    public void setSprStandartNamesBySprStdTypeId(Collection<SprStandartName> sprStandartNamesBySprStdTypeId) {
        this.sprStandartNamesBySprStdTypeId = sprStandartNamesBySprStdTypeId;
    }
}
