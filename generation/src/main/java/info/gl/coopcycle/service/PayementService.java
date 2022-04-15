package info.gl.coopcycle.service;

import info.gl.coopcycle.domain.Payement;
import info.gl.coopcycle.repository.PayementRepository;
import info.gl.coopcycle.service.dto.PayementDTO;
import info.gl.coopcycle.service.mapper.PayementMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Payement}.
 */
@Service
@Transactional
public class PayementService {

    private final Logger log = LoggerFactory.getLogger(PayementService.class);

    private final PayementRepository payementRepository;

    private final PayementMapper payementMapper;

    public PayementService(PayementRepository payementRepository, PayementMapper payementMapper) {
        this.payementRepository = payementRepository;
        this.payementMapper = payementMapper;
    }

    /**
     * Save a payement.
     *
     * @param payementDTO the entity to save.
     * @return the persisted entity.
     */
    public PayementDTO save(PayementDTO payementDTO) {
        log.debug("Request to save Payement : {}", payementDTO);
        Payement payement = payementMapper.toEntity(payementDTO);
        payement = payementRepository.save(payement);
        return payementMapper.toDto(payement);
    }

    /**
     * Partially update a payement.
     *
     * @param payementDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PayementDTO> partialUpdate(PayementDTO payementDTO) {
        log.debug("Request to partially update Payement : {}", payementDTO);

        return payementRepository
            .findById(payementDTO.getId())
            .map(existingPayement -> {
                payementMapper.partialUpdate(existingPayement, payementDTO);

                return existingPayement;
            })
            .map(payementRepository::save)
            .map(payementMapper::toDto);
    }

    /**
     * Get all the payements.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PayementDTO> findAll() {
        log.debug("Request to get all Payements");
        return payementRepository.findAll().stream().map(payementMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one payement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PayementDTO> findOne(Long id) {
        log.debug("Request to get Payement : {}", id);
        return payementRepository.findById(id).map(payementMapper::toDto);
    }

    /**
     * Delete the payement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Payement : {}", id);
        payementRepository.deleteById(id);
    }
}
