package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Trial;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

import java.time.LocalDate;

public interface TrialService {

    Block<Trial> findTrials(Long trialTypeId, Long provinceId, LocalDate startDate,
                            LocalDate endDate, int page, int size);

    Trial findTrialDetailsById(Long id) throws InstanceNotFoundException;
}
