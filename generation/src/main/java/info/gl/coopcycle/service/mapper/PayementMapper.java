package info.gl.coopcycle.service.mapper;

import info.gl.coopcycle.domain.Payement;
import info.gl.coopcycle.service.dto.PayementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Payement} and its DTO {@link PayementDTO}.
 */
@Mapper(componentModel = "spring", uses = { PanierMapper.class })
public interface PayementMapper extends EntityMapper<PayementDTO, Payement> {
    @Mapping(target = "panier", source = "panier", qualifiedByName = "id")
    PayementDTO toDto(Payement s);
}
