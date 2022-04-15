package info.gl.coopcycle.service.mapper;

import info.gl.coopcycle.domain.Cooperative;
import info.gl.coopcycle.service.dto.CooperativeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cooperative} and its DTO {@link CooperativeDTO}.
 */
@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class })
public interface CooperativeMapper extends EntityMapper<CooperativeDTO, Cooperative> {
    @Mapping(target = "utilisateur", source = "utilisateur", qualifiedByName = "id")
    CooperativeDTO toDto(Cooperative s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CooperativeDTO toDtoId(Cooperative cooperative);
}
