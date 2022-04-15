package info.gl.coopcycle.service.mapper;

import info.gl.coopcycle.domain.Produit;
import info.gl.coopcycle.service.dto.ProduitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = { PanierMapper.class, RestaurantMapper.class })
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {
    @Mapping(target = "panier", source = "panier", qualifiedByName = "id")
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "id")
    ProduitDTO toDto(Produit s);
}
