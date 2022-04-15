package info.gl.coopcycle.service;

import info.gl.coopcycle.domain.Panier;
import info.gl.coopcycle.repository.PanierRepository;
import info.gl.coopcycle.service.dto.PanierDTO;
import info.gl.coopcycle.service.mapper.PanierMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Panier}.
 */
@Service
@Transactional
public class PanierService {

    private final Logger log = LoggerFactory.getLogger(PanierService.class);

    private final PanierRepository panierRepository;

    private final PanierMapper panierMapper;

    public PanierService(PanierRepository panierRepository, PanierMapper panierMapper) {
        this.panierRepository = panierRepository;
        this.panierMapper = panierMapper;
    }

    /**
     * Save a panier.
     *
     * @param panierDTO the entity to save.
     * @return the persisted entity.
     */
    public PanierDTO save(PanierDTO panierDTO) {
        log.debug("Request to save Panier : {}", panierDTO);
        Panier panier = panierMapper.toEntity(panierDTO);
        panier = panierRepository.save(panier);
        return panierMapper.toDto(panier);
    }

    /**
     * Partially update a panier.
     *
     * @param panierDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PanierDTO> partialUpdate(PanierDTO panierDTO) {
        log.debug("Request to partially update Panier : {}", panierDTO);

        return panierRepository
            .findById(panierDTO.getId())
            .map(existingPanier -> {
                panierMapper.partialUpdate(existingPanier, panierDTO);

                return existingPanier;
            })
            .map(panierRepository::save)
            .map(panierMapper::toDto);
    }

    /**
     * Get all the paniers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PanierDTO> findAll() {
        log.debug("Request to get all Paniers");
        return panierRepository.findAll().stream().map(panierMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the paniers where Course is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PanierDTO> findAllWhereCourseIsNull() {
        log.debug("Request to get all paniers where Course is null");
        return StreamSupport
            .stream(panierRepository.findAll().spliterator(), false)
            .filter(panier -> panier.getCourse() == null)
            .map(panierMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the paniers where Payement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PanierDTO> findAllWherePayementIsNull() {
        log.debug("Request to get all paniers where Payement is null");
        return StreamSupport
            .stream(panierRepository.findAll().spliterator(), false)
            .filter(panier -> panier.getPayement() == null)
            .map(panierMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one panier by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PanierDTO> findOne(Long id) {
        log.debug("Request to get Panier : {}", id);
        return panierRepository.findById(id).map(panierMapper::toDto);
    }

    /**
     * Delete the panier by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Panier : {}", id);
        panierRepository.deleteById(id);
    }
}
