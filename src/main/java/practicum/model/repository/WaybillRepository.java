package practicum.model.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import practicum.model.entity.Status;
import practicum.model.entity.Waybill;

import java.util.Collection;
import java.util.List;

@Repository
public interface WaybillRepository extends JpaRepository<Waybill, Long> {


    @Modifying
    @Query(nativeQuery = true, value = "update item as i set status = ?1 from waybill as w where i.id = w.item_Id")
    void buyItemsInWaybill(String bought);

    List<Waybill> findAll(Specification<Waybill> generateSearchSpecifications, Pageable page);
}