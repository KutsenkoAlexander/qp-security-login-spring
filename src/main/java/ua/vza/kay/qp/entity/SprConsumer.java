package ua.vza.kay.qp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by kycenko on 15.09.15.
 */
@Entity
@Table(name = "spr_consumer", schema = "", catalog = "qualityofproducts")
@NamedQuery(name = "SprConsumer.getAll", query = "SELECT d FROM SprConsumer d ORDER BY d.name")
public class SprConsumer implements Serializable {
    private int consumerId;
    private String name;
    private Collection<Product> productsByConsumerId;

    @Id
    @Column(name = "consumer_id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
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

        SprConsumer that = (SprConsumer) o;

        if (consumerId != that.consumerId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = consumerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sprConsumerByConsumerId", fetch = FetchType.LAZY)
    public Collection<Product> getProductsByConsumerId() {
        return productsByConsumerId;
    }

    public void setProductsByConsumerId(Collection<Product> productsByConsumerId) {
        this.productsByConsumerId = productsByConsumerId;
    }
}
