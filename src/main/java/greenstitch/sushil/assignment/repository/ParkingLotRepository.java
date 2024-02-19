package greenstitch.sushil.assignment.repository;

import greenstitch.sushil.assignment.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

}
