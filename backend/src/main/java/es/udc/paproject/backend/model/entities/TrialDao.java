package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface TrialDao extends CrudRepository<Trial, Long> {

    @Query("SELECT t FROM Trial t WHERE (?1 IS NULL OR t.trialType.id = ?1) AND (?2 IS NULL OR t.province.id = ?2) " +
            "AND (?3 IS NULL OR t.dateTime > ?3) AND (?4 IS NULL OR t.dateTime < ?4) ORDER BY t.dateTime desc")
    Slice<Trial> findTrials(Long trialTypeId, Long provinceId, LocalDateTime startDate, LocalDateTime endDate,
            Pageable pageable);

}