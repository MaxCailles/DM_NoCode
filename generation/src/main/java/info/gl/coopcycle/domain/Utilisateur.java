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
 * A Utilisateur.
 */
@Entity
@Table(name = "utilisateur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "pseudo", nullable = false)
    private String pseudo;

    @NotNull
    @Pattern(regexp = "[a-z]*/@/[a-z]*")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "numero_telephone", nullable = false)
    private String numeroTelephone;

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "panier", "utilisateur" }, allowSetters = true)
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produits", "course", "payement", "utilisateur" }, allowSetters = true)
    private Set<Panier> paniers = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "restaurants", "utilisateur" }, allowSetters = true)
    private Set<Cooperative> cooperatives = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "utilisateur" }, allowSetters = true)
    private Set<Role> roles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Utilisateur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public Utilisateur pseudo(String pseudo) {
        this.setPseudo(pseudo);
        return this;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return this.email;
    }

    public Utilisateur email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelephone() {
        return this.numeroTelephone;
    }

    public Utilisateur numeroTelephone(String numeroTelephone) {
        this.setNumeroTelephone(numeroTelephone);
        return this;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        if (this.courses != null) {
            this.courses.forEach(i -> i.setUtilisateur(null));
        }
        if (courses != null) {
            courses.forEach(i -> i.setUtilisateur(this));
        }
        this.courses = courses;
    }

    public Utilisateur courses(Set<Course> courses) {
        this.setCourses(courses);
        return this;
    }

    public Utilisateur addCourse(Course course) {
        this.courses.add(course);
        course.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeCourse(Course course) {
        this.courses.remove(course);
        course.setUtilisateur(null);
        return this;
    }

    public Set<Panier> getPaniers() {
        return this.paniers;
    }

    public void setPaniers(Set<Panier> paniers) {
        if (this.paniers != null) {
            this.paniers.forEach(i -> i.setUtilisateur(null));
        }
        if (paniers != null) {
            paniers.forEach(i -> i.setUtilisateur(this));
        }
        this.paniers = paniers;
    }

    public Utilisateur paniers(Set<Panier> paniers) {
        this.setPaniers(paniers);
        return this;
    }

    public Utilisateur addPanier(Panier panier) {
        this.paniers.add(panier);
        panier.setUtilisateur(this);
        return this;
    }

    public Utilisateur removePanier(Panier panier) {
        this.paniers.remove(panier);
        panier.setUtilisateur(null);
        return this;
    }

    public Set<Cooperative> getCooperatives() {
        return this.cooperatives;
    }

    public void setCooperatives(Set<Cooperative> cooperatives) {
        if (this.cooperatives != null) {
            this.cooperatives.forEach(i -> i.setUtilisateur(null));
        }
        if (cooperatives != null) {
            cooperatives.forEach(i -> i.setUtilisateur(this));
        }
        this.cooperatives = cooperatives;
    }

    public Utilisateur cooperatives(Set<Cooperative> cooperatives) {
        this.setCooperatives(cooperatives);
        return this;
    }

    public Utilisateur addCooperative(Cooperative cooperative) {
        this.cooperatives.add(cooperative);
        cooperative.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeCooperative(Cooperative cooperative) {
        this.cooperatives.remove(cooperative);
        cooperative.setUtilisateur(null);
        return this;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        if (this.roles != null) {
            this.roles.forEach(i -> i.setUtilisateur(null));
        }
        if (roles != null) {
            roles.forEach(i -> i.setUtilisateur(this));
        }
        this.roles = roles;
    }

    public Utilisateur roles(Set<Role> roles) {
        this.setRoles(roles);
        return this;
    }

    public Utilisateur addRole(Role role) {
        this.roles.add(role);
        role.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeRole(Role role) {
        this.roles.remove(role);
        role.setUtilisateur(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Utilisateur)) {
            return false;
        }
        return id != null && id.equals(((Utilisateur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Utilisateur{" +
            "id=" + getId() +
            ", pseudo='" + getPseudo() + "'" +
            ", email='" + getEmail() + "'" +
            ", numeroTelephone='" + getNumeroTelephone() + "'" +
            "}";
    }
}
