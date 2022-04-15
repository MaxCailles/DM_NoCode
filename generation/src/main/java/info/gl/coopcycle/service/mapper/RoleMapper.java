package info.gl.coopcycle.service.mapper;

import info.gl.coopcycle.domain.Role;
import info.gl.coopcycle.service.dto.RoleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Role} and its DTO {@link RoleDTO}.
 */
@Mapper(componentModel = "spring", uses = { UtilisateurMapper.class })
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
    @Mapping(target = "utilisateur", source = "utilisateur", qualifiedByName = "id")
    RoleDTO toDto(Role s);
}
