package greenstitch.sushil.assignment.service;

import greenstitch.sushil.assignment.entity.Car;

import java.util.List;

public interface CarService {
    public  Car createCar(Car car);

     public  List<Car>  getAllCar();

    public List<String> getByColourInfo(String name);

    public void deletecar(int num);
}
