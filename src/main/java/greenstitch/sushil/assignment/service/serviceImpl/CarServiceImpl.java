package greenstitch.sushil.assignment.service.serviceImpl;

import greenstitch.sushil.assignment.entity.Car;
import greenstitch.sushil.assignment.entity.ParkingLot;
import greenstitch.sushil.assignment.exception.MessageException;
import greenstitch.sushil.assignment.repository.CarRepository;
import greenstitch.sushil.assignment.repository.ParkingLotRepository;
import greenstitch.sushil.assignment.service.CarService;
import greenstitch.sushil.assignment.service.ParkingLotService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private ParkingLotRepository parkingLotRepository;

    public CarServiceImpl(CarRepository carRepository, ParkingLotRepository parkingLotRepository){
        this.carRepository = carRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public Car createCar(Car car){
//        Optional<ParkingLot> oneRow = parkingLotRepository.findById(1L);
//
//        if(!oneRow.isPresent()){
//            throw new MessageException("Please make a Parking Lot first", "Then you may park a car !!");
//        }
        ParkingLot p = parkingLotRepository.findById(1L).orElseThrow(() -> new MessageException("Please make a Parking Lot first", "Then you may park a car !!"));

        int max_alloted_cars = p.getNumcars();
        //System.out.println(p);

        int available_cars;
        available_cars = (int)carRepository.count();

        if(available_cars == max_alloted_cars){
            throw new MessageException("Sorry", "parking lot is full");
        }

        if(available_cars==0){
            car.setSlot(1);
        }else{
            List<Integer> listslots = carRepository.findAll().stream().map(c -> c.getSlot()).collect(Collectors.toList());
            Collections.sort(listslots);


            for(int i=1; i<=max_alloted_cars; ++i){
                if(listslots.indexOf(i) == -1){
                    car.setSlot(i);
                    break;
                }
            }

        }


        car.setPark(p);
        Car newData = carRepository.save(car);
        return newData;
    }

    @Override
    public List<Car> getAllCar(){

        ParkingLot p = parkingLotRepository.findById(1L).orElseThrow(() -> new MessageException("Please make a Parking Lot first", "Then Park a car and you may get the details!!"));

        List<Car> ls = carRepository.findAll();

        if(ls.isEmpty()){
            throw new MessageException("Parking Lot is empty", "Please park some cars!!");
        }


        return ls;

    }

    @Override
    public List<String> getByColourInfo(String name){
        ParkingLot p = parkingLotRepository.findById(1L).orElseThrow(() -> new MessageException("Please make a Parking Lot first", "Then Park a car and you may get the details!!"));
        List<Car> cars = carRepository.findByColour(name);

        if(cars.isEmpty()){
            throw new MessageException("Given Colour is not available", "Please Enter a valid colour");
        }
        List<String> registraionNumbers = cars.stream().map(c -> c.getRegnum()).collect(Collectors.toList());

        return registraionNumbers;
    }

    @Override
    public void deletecar(int num){
        ParkingLot par = parkingLotRepository.findById(1L).orElseThrow(() -> new MessageException("Please make a Parking Lot first", "Then Park a car and you may get the details!!"));
        Car slotcar = carRepository.findBySlot(num);

        if(slotcar == null){
            throw new MessageException("Parked car slot num is not avaiable", "Please enter another car details");
        }
//        Car slotnumcar = slotcar.get();

        carRepository.delete(slotcar);
    }
}
