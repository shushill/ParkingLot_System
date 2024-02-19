package greenstitch.sushil.assignment.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Car")
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long carId;

    @NotBlank
    private String colour;

    @Pattern(regexp = "^[A-Z]{2}-\\d{2}-[A-Z]{2}-\\d{4}$", message = "Invalid registration number format")
    private String regnum;

    private int slot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_id")
    @JsonBackReference
    private ParkingLot park;
}
