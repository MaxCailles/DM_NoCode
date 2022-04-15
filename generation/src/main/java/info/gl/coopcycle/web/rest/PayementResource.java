package info.gl.coopcycle.web.rest;

import info.gl.coopcycle.repository.PayementRepository;
import info.gl.coopcycle.service.PayementService;
import info.gl.coopcycle.service.dto.PayementDTO;
import info.gl.coopcycle.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link info.gl.coopcycle.domain.Payement}.
 */
@RestController
@RequestMapping("/api")
public class PayementResource {

    private final Logger log = LoggerFactory.getLogger(PayementResource.class);

    private static final String ENTITY_NAME = "payement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayementService payementService;

    private final PayementRepository payementRepository;

    public PayementResource(PayementService payementService, PayementRepository payementRepository) {
        this.payementService = payementService;
        this.payementRepository = payementRepository;
    }

    /**
     * {@code POST  /payements} : Create a new payement.
     *
     * @param payementDTO the payementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payementDTO, or with status {@code 400 (Bad Request)} if the payement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payements")
    public ResponseEntity<PayementDTO> createPayement(@RequestBody PayementDTO payementDTO) throws URISyntaxException {
        log.debug("REST request to save Payement : {}", payementDTO);
        if (payementDTO.getId() != null) {
            throw new BadRequestAlertException("A new payement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayementDTO result = payementService.save(payementDTO);
        return ResponseEntity
            .created(new URI("/api/payements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payements/:id} : Updates an existing payement.
     *
     * @param id the id of the payementDTO to save.
     * @param payementDTO the payementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payementDTO,
     * or with status {@code 400 (Bad Request)} if the payementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payements/{id}")
    public ResponseEntity<PayementDTO> updatePayement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PayementDTO payementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Payement : {}, {}", id, payementDTO);
        if (payementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PayementDTO result = payementService.save(payementDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, payementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /payements/:id} : Partial updates given fields of an existing payement, field will ignore if it is null
     *
     * @param id the id of the payementDTO to save.
     * @param payementDTO the payementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payementDTO,
     * or with status {@code 400 (Bad Request)} if the payementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the payementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the payementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/payements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PayementDTO> partialUpdatePayement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PayementDTO payementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Payement partially : {}, {}", id, payementDTO);
        if (payementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PayementDTO> result = payementService.partialUpdate(payementDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, payementDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /payements} : get all the payements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payements in body.
     */
    @GetMapping("/payements")
    public List<PayementDTO> getAllPayements() {
        log.debug("REST request to get all Payements");
        return payementService.findAll();
    }

    /**
     * {@code GET  /payements/:id} : get the "id" payement.
     *
     * @param id the id of the payementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payements/{id}")
    public ResponseEntity<PayementDTO> getPayement(@PathVariable Long id) {
        log.debug("REST request to get Payement : {}", id);
        Optional<PayementDTO> payementDTO = payementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payementDTO);
    }

    /**
     * {@code DELETE  /payements/:id} : delete the "id" payement.
     *
     * @param id the id of the payementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payements/{id}")
    public ResponseEntity<Void> deletePayement(@PathVariable Long id) {
        log.debug("REST request to delete Payement : {}", id);
        payementService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
