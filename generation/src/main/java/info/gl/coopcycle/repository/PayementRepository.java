package info.gl.coopcycle.repository;

import info.gl.coopcycle.domain.Payement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Payement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayementRepository extends JpaRepository<Payement, Long> {}
