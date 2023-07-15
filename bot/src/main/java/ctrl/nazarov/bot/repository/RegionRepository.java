package ctrl.nazarov.bot.repository;

import ctrl.nazarov.bot.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Stack;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
    Stack<RegionEntity> findByVisible(boolean visible);

}
