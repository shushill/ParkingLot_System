package greenstitch.sushil.assignment.service.serviceImpl;

import greenstitch.sushil.assignment.entity.ParkingLot;
import greenstitch.sushil.assignment.exception.MessageException;
import greenstitch.sushil.assignment.repository.ParkingLotRepository;
import greenstitch.sushil.assignment.service.ParkingLotService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    ParkingLotRepository parkingLotRepository;

    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository){
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingLot createParkingLot(ParkingLot parkingLot){
        Optional<ParkingLot> oneRow = parkingLotRepository.findById(1L);

        if(oneRow.isPresent()){
            throw new MessageException("Already made", "No need to make another ParkingLot");
        }


        ParkingLot newData = parkingLotRepository.save(parkingLot);
        return newData;
    }

}
