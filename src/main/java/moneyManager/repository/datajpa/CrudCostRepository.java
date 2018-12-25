package moneyManager.repository.datajpa;

import moneyManager.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudCostRepository extends JpaRepository<Cost, Integer> {
}
