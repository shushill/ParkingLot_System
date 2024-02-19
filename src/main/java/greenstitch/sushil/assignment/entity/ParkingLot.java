package greenstitch.sushil.assignment.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="parkingLot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long parkingLotId;

    @Min(value = 1)
    @NotNull
    private int numcars;



    @OneToMany(mappedBy = "park", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference()
    private List<Car> cardetail;


}
