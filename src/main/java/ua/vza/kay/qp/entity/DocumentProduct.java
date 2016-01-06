package ua.vza.kay.qp.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kycenko on 15.09.15.
 */
@Entity
@Table(name = "document_product", schema = "", catalog = "qualityofproducts")
@NamedQuery(name = "DocumentProduct.getAll", query = "SELECT d FROM DocumentProduct d ORDER BY d.name")
public class DocumentProduct implements Serializable {
    private int idDocProduct;
    private int productId;
    private String name;
    private String link;
    private Product productByProductId;

    public DocumentProduct() {}

    @Id
    @Column(name = "id_doc_product", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getIdDocProduct() {
        return idDocProduct;
    }

    public void setIdDocProduct(int idDocProduct) {
        this.idDocProduct = idDocProduct;
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
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "link", nullable = false, insertable = true, updatable = true, length = 45)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentProduct that = (DocumentProduct) o;

        if (idDocProduct != that.idDocProduct) return false;
        if (productId != that.productId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDocProduct;
        result = 31 * result + productId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }
}
