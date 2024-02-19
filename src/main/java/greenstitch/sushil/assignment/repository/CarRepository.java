package greenstitch.sushil.assignment.repository;

import greenstitch.sushil.assignment.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByColour(String name);
    public Car findBySlot(int num);

}
