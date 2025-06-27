package es.udc.paproject.backend.model.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface TrialTypeDao extends ListPagingAndSortingRepository<TrialType, Long>, CrudRepository<TrialType, Long> {

}
