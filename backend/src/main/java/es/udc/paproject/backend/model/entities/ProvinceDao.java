package es.udc.paproject.backend.model.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ProvinceDao extends ListPagingAndSortingRepository<Province, Long>, CrudRepository<Province, Long> {
}
