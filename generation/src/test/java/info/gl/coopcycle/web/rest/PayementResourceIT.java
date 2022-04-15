package info.gl.coopcycle.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import info.gl.coopcycle.IntegrationTest;
import info.gl.coopcycle.domain.Payement;
import info.gl.coopcycle.repository.PayementRepository;
import info.gl.coopcycle.service.dto.PayementDTO;
import info.gl.coopcycle.service.mapper.PayementMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PayementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PayementResourceIT {

    private static final String ENTITY_API_URL = "/api/payements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PayementRepository payementRepository;

    @Autowired
    private PayementMapper payementMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPayementMockMvc;

    private Payement payement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Payement createEntity(EntityManager em) {
        Payement payement = new Payement();
        return payement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Payement createUpdatedEntity(EntityManager em) {
        Payement payement = new Payement();
        return payement;
    }

    @BeforeEach
    public void initTest() {
        payement = createEntity(em);
    }

    @Test
    @Transactional
    void createPayement() throws Exception {
        int databaseSizeBeforeCreate = payementRepository.findAll().size();
        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);
        restPayementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payementDTO)))
            .andExpect(status().isCreated());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeCreate + 1);
        Payement testPayement = payementList.get(payementList.size() - 1);
    }

    @Test
    @Transactional
    void createPayementWithExistingId() throws Exception {
        // Create the Payement with an existing ID
        payement.setId(1L);
        PayementDTO payementDTO = payementMapper.toDto(payement);

        int databaseSizeBeforeCreate = payementRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPayements() throws Exception {
        // Initialize the database
        payementRepository.saveAndFlush(payement);

        // Get all the payementList
        restPayementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payement.getId().intValue())));
    }

    @Test
    @Transactional
    void getPayement() throws Exception {
        // Initialize the database
        payementRepository.saveAndFlush(payement);

        // Get the payement
        restPayementMockMvc
            .perform(get(ENTITY_API_URL_ID, payement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(payement.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingPayement() throws Exception {
        // Get the payement
        restPayementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPayement() throws Exception {
        // Initialize the database
        payementRepository.saveAndFlush(payement);

        int databaseSizeBeforeUpdate = payementRepository.findAll().size();

        // Update the payement
        Payement updatedPayement = payementRepository.findById(payement.getId()).get();
        // Disconnect from session so that the updates on updatedPayement are not directly saved in db
        em.detach(updatedPayement);
        PayementDTO payementDTO = payementMapper.toDto(updatedPayement);

        restPayementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, payementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payementDTO))
            )
            .andExpect(status().isOk());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeUpdate);
        Payement testPayement = payementList.get(payementList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingPayement() throws Exception {
        int databaseSizeBeforeUpdate = payementRepository.findAll().size();
        payement.setId(count.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, payementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPayement() throws Exception {
        int databaseSizeBeforeUpdate = payementRepository.findAll().size();
        payement.setId(count.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPayement() throws Exception {
        int databaseSizeBeforeUpdate = payementRepository.findAll().size();
        payement.setId(count.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payementDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePayementWithPatch() throws Exception {
        // Initialize the database
        payementRepository.saveAndFlush(payement);

        int databaseSizeBeforeUpdate = payementRepository.findAll().size();

        // Update the payement using partial update
        Payement partialUpdatedPayement = new Payement();
        partialUpdatedPayement.setId(payement.getId());

        restPayementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayement))
            )
            .andExpect(status().isOk());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeUpdate);
        Payement testPayement = payementList.get(payementList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdatePayementWithPatch() throws Exception {
        // Initialize the database
        payementRepository.saveAndFlush(payement);

        int databaseSizeBeforeUpdate = payementRepository.findAll().size();

        // Update the payement using partial update
        Payement partialUpdatedPayement = new Payement();
        partialUpdatedPayement.setId(payement.getId());

        restPayementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayement))
            )
            .andExpect(status().isOk());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeUpdate);
        Payement testPayement = payementList.get(payementList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingPayement() throws Exception {
        int databaseSizeBeforeUpdate = payementRepository.findAll().size();
        payement.setId(count.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, payementDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPayement() throws Exception {
        int databaseSizeBeforeUpdate = payementRepository.findAll().size();
        payement.setId(count.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPayement() throws Exception {
        int databaseSizeBeforeUpdate = payementRepository.findAll().size();
        payement.setId(count.incrementAndGet());

        // Create the Payement
        PayementDTO payementDTO = payementMapper.toDto(payement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayementMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(payementDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Payement in the database
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePayement() throws Exception {
        // Initialize the database
        payementRepository.saveAndFlush(payement);

        int databaseSizeBeforeDelete = payementRepository.findAll().size();

        // Delete the payement
        restPayementMockMvc
            .perform(delete(ENTITY_API_URL_ID, payement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Payement> payementList = payementRepository.findAll();
        assertThat(payementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
