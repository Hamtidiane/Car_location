package edu.campus.numerique.vehicles.service;

import edu.campus.numerique.vehicles.Motorcycle;
import edu.campus.numerique.vehicles.UtilityVehicle;
import edu.campus.numerique.vehicles.Vehicle;
import edu.campus.numerique.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VehiclesServiceImpl implements VehiclesService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehiclesServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public boolean isVehicleUnavailable(Long id, LocalDate start, LocalDate end) {
        // Implémentation de la logique pour vérifier la disponibilité
        return false;  // Exemple, à adapter selon votre logique
    }

    @Override
    public Vehicle createVehicle(Vehicle newVehicle) {
        return vehicleRepository.save(newVehicle);
    }

    @Override
    public Optional<Vehicle> updateVehicle(Long id, Vehicle updatedVehicle) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setRegistration(updatedVehicle.getRegistration());
            vehicle.setBrand(updatedVehicle.getBrand());
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setColor(updatedVehicle.getColor());
            vehicle.setMileage(updatedVehicle.getMileage());
            vehicle.setMileagePrice(updatedVehicle.getMileagePrice());
            vehicle.setBasePrice(updatedVehicle.getBasePrice());

            if(vehicle instanceof Motorcycle && updatedVehicle instanceof Motorcycle) {
                ((Motorcycle) vehicle).setMoteurcm3(((Motorcycle) updatedVehicle).getMoteurcm3());
            } else if(vehicle instanceof UtilityVehicle && updatedVehicle instanceof UtilityVehicle) {
                ((UtilityVehicle) vehicle).setVolumecm3(((UtilityVehicle) updatedVehicle).getVolumecm3());
            }

            return vehicleRepository.save(vehicle);
        });
    }

    @Override
    public boolean deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
