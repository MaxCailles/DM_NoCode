package info.gl.coopcycle.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link info.gl.coopcycle.domain.Payement} entity.
 */
public class PayementDTO implements Serializable {

    private Long id;

    private PanierDTO panier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PanierDTO getPanier() {
        return panier;
    }

    public void setPanier(PanierDTO panier) {
        this.panier = panier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PayementDTO)) {
            return false;
        }

        PayementDTO payementDTO = (PayementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, payementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PayementDTO{" +
            "id=" + getId() +
            ", panier=" + getPanier() +
            "}";
    }
}
