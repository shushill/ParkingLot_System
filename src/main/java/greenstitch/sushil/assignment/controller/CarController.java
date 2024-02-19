package greenstitch.sushil.assignment.controller;

import greenstitch.sushil.assignment.entity.Car;
import greenstitch.sushil.assignment.service.CarService;
import greenstitch.sushil.assignment.service.ParkingLotService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(
        name = "Park a car with its details: ",
        description = " The system should provide me with the ability to find out: \n" +
                "1.  Registration numbers of all cars of a particular colour. \n" +
                "2.  Slot number in which a car with a given registration number is parked.\n" +
                "3.  Slot numbers of all slots where a car of a particular colour is parked. \n"
)
@Validated
@RequestMapping("/api/v1")
@RestController
public class CarController {
    private CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    @PostMapping("/park/newcar")
    public ResponseEntity<String> parkNewCar(@Valid @RequestBody Car car){
        Car newD = carService.createCar(car);
        Integer num = newD.getSlot();
        String str = num.toString();
        String message = String.format("Allocated slot number: %s", str);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/park/status")
    public ResponseEntity<String> getAllCarInfo(){
        List<Car> listcar = carService.getAllCar();
        String message = "Slot No.\tRegistration Number\t\tColour\n";
        String[] str1;
        str1 = listcar.stream()
                .map(c -> String.format("\t%s\t\t%s\t\t\t%s\n", c.getSlot()+"", c.getRegnum(), c.getColour()))
                .toArray(String[]::new);

        for(String s : str1){
            message +=s;
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/park/{colour}/detail")
    public ResponseEntity<String> getAllCarInfo(@PathVariable (name = "colour") String colour){
        List<String> listcars = carService.getByColourInfo(colour);
        String message="";
        String[] str1;
        str1 = listcars.stream()
                .map(c -> String.format("\t%s\n",c))
                .toArray(String[]::new);

        for(String s : str1){
            message +=s;
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @DeleteMapping("/park/leave/{slotnum}")
    public ResponseEntity<String> getAllCarInfo(@PathVariable (name = "slotnum") int num){
         carService.deletecar(num);
        Integer number = num;
        String str = number.toString();
         String message = String.format("Slot number %s is free.", str);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
