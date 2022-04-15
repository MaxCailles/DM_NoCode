package info.gl.coopcycle.service.mapper;

import info.gl.coopcycle.domain.Panier;
import info.gl.coopcycle.service.dto.PanierDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Panier} and its DTO {@link PanierDTO}.
 */
@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class })
public interface PanierMapper extends EntityMapper<PanierDTO, Panier> {
    @Mapping(target = "utilisateur", source = "utilisateur", qualifiedByName = "id")
    PanierDTO toDto(Panier s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PanierDTO toDtoId(Panier panier);
}
