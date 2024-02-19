package greenstitch.sushil.assignment.controller;


import greenstitch.sushil.assignment.entity.ParkingLot;
import greenstitch.sushil.assignment.service.ParkingLotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Parking Lot System",
        description = " The system should provide me the functionality: \n" +
                "1.  Should be able to create a parking lot for 'n' number of cars. \n" +
                "2. Please Note only one parking lot will be created for this project. "
)
@RestController
@Validated
@RequestMapping("/api/v1")
public class ParkingController {

    ParkingLotService parkingLotService;

    public ParkingController(ParkingLotService parkingLotService){
        this.parkingLotService = parkingLotService;
    }

//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello, World!";
//    }

    @PostMapping("/create/parkinglot")
    public ResponseEntity<String> makeNewParkingLot(@Valid @RequestBody ParkingLot parkingLot){
        ParkingLot newdata = parkingLotService.createParkingLot(parkingLot);
        Integer num = newdata.getNumcars();
        String str = num.toString();
        String message = String.format("Created a parking lot with %s slots.", str);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


}