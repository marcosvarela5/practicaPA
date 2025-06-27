package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InscriptionDao extends CrudRepository<Inscription, Long> {
    // Inscripción en una prueba.
    // búsqueda por id ya implementada en la interfaz

    //[FUNC-4] Entrega del dorsal. y [FUNC-6] Puntuación de una prueba.
    // modificación de un atributo, no hay que llamar a Dao. Se hace solo al cambiar la clase y hacer el commit.

    // [FUNC-5] Visualización del histórico de inscripciones.
    Slice<Inscription> findByUserIdOrderByDateTimeDesc(Long userId, Pageable pageable);

    Optional<Inscription> findByUserIdAndTrialId(Long userId, Long trialId);
}