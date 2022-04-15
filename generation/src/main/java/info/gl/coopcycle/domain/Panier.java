package info.gl.coopcycle.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Panier.
 */
@Entity
@Table(name = "panier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "prixtotal", nullable = false)
    private Integer prixtotal;

    @OneToMany(mappedBy = "panier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "panier", "restaurant" }, allowSetters = true)
    private Set<Produit> produits = new HashSet<>();

    @JsonIgnoreProperties(value = { "panier", "utilisateur" }, allowSetters = true)
    @OneToOne(mappedBy = "panier")
    private Course course;

    @JsonIgnoreProperties(value = { "panier" }, allowSetters = true)
    @OneToOne(mappedBy = "panier")
    private Payement payement;

    @ManyToOne
    @JsonIgnoreProperties(value = { "courses", "paniers", "cooperatives", "roles" }, allowSetters = true)
    private Utilisateur utilisateur;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Panier id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrixtotal() {
        return this.prixtotal;
    }

    public Panier prixtotal(Integer prixtotal) {
        this.setPrixtotal(prixtotal);
        return this;
    }

    public void setPrixtotal(Integer prixtotal) {
        this.prixtotal = prixtotal;
    }

    public Set<Produit> getProduits() {
        return this.produits;
    }

    public void setProduits(Set<Produit> produits) {
        if (this.produits != null) {
            this.produits.forEach(i -> i.setPanier(null));
        }
        if (produits != null) {
            produits.forEach(i -> i.setPanier(this));
        }
        this.produits = produits;
    }

    public Panier produits(Set<Produit> produits) {
        this.setProduits(produits);
        return this;
    }

    public Panier addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setPanier(this);
        return this;
    }

    public Panier removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setPanier(null);
        return this;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        if (this.course != null) {
            this.course.setPanier(null);
        }
        if (course != null) {
            course.setPanier(this);
        }
        this.course = course;
    }

    public Panier course(Course course) {
        this.setCourse(course);
        return this;
    }

    public Payement getPayement() {
        return this.payement;
    }

    public void setPayement(Payement payement) {
        if (this.payement != null) {
            this.payement.setPanier(null);
        }
        if (payement != null) {
            payement.setPanier(this);
        }
        this.payement = payement;
    }

    public Panier payement(Payement payement) {
        this.setPayement(payement);
        return this;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Panier utilisateur(Utilisateur utilisateur) {
        this.setUtilisateur(utilisateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Panier)) {
            return false;
        }
        return id != null && id.equals(((Panier) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Panier{" +
            "id=" + getId() +
            ", prixtotal=" + getPrixtotal() +
            "}";
    }
}
