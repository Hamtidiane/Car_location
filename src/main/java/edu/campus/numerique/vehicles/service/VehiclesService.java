package edu.campus.numerique.vehicles.service;

import edu.campus.numerique.vehicles.Vehicle;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehiclesService {
   List<Vehicle> getAllVehicles();
   Optional<Vehicle> getVehicleById(Long id);
   boolean isVehicleUnavailable(Long id, LocalDate start, LocalDate end);
   Vehicle createVehicle(Vehicle newVehicle);
   Optional<Vehicle> updateVehicle(Long id, Vehicle updatedVehicle);
   boolean deleteVehicle(Long id);
}
