package practicum.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import practicum.model.entity.Item;
import practicum.model.entity.Status;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    @Modifying
    @Query("update Item i set i.status = ?1 where i.status = ?2")
    void setItemStatus(Status deleted, Status bought);

    @Modifying
    @Query("update Item i set i.status = ?1 where i.status = ?2 and i.id = ?3")
    void setItemStatusById(Status deleted, Status bought, Long id);

    Page<Item> findAll(Specification<Item> spec, Pageable page);

}
