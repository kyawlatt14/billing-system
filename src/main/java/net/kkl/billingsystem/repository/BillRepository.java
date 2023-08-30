package net.kkl.billingsystem.repository;

import net.kkl.billingsystem.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
    Bill findByName(String name);

    Bill findByNameIgnoreCaseLike(String name);
}
