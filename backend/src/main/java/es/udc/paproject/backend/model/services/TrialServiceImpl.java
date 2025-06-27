package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.InscriptionDao;
import es.udc.paproject.backend.model.entities.Trial;
import es.udc.paproject.backend.model.entities.TrialDao;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class TrialServiceImpl implements TrialService {

    @Autowired
    private TrialDao trialDao;

    @Autowired
    private InscriptionDao inscriptionDao;

    @Override
    public Block<Trial> findTrials(Long trialTypeId, Long provinceId, LocalDate startDate,
                                   LocalDate endDate, int page, int size) {
        // atStartOfDay() transforms LocalDate to LocalDateTime
        LocalDateTime startDateWithHours = (startDate == null)? null : startDate.atStartOfDay();
        LocalDateTime endDateWithHours = (endDate == null)? null : endDate.atStartOfDay();

        Slice<Trial> slice = trialDao.findTrials
                (trialTypeId, provinceId, startDateWithHours, endDateWithHours, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());
    }

    // [FUNC-2: Visualización de la información detallada de una prueba deportiva]
    @Override
    public Trial findTrialDetailsById(Long id) throws InstanceNotFoundException {
        Optional<Trial> trial = trialDao.findById(id);

        if (trial.isEmpty()) {
            throw new InstanceNotFoundException("trial", id);
        }

        return trial.get();
    }
}
