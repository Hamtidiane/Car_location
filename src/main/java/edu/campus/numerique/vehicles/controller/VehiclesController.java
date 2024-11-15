package edu.campus.numerique.vehicles.controller;

import edu.campus.numerique.vehicles.Vehicle;
import edu.campus.numerique.vehicles.service.VehiclesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/vehicles")
@Tag(name = "Vehicles", description = "API pour la gestion des véhicules")
public class VehiclesController {

    private final VehiclesService vehiclesService;

    @Autowired
    public VehiclesController(VehiclesService vehiclesService) {
        this.vehiclesService = vehiclesService;
    }

    @Operation(summary = "Récupérer tous les véhicules", description = "Renvoie la liste de tous les véhicules disponibles")
    @ApiResponse(responseCode = "200", description = "Liste de véhicules récupérée avec succès")
    @GetMapping


    public List<Vehicle> getAllVehicles() {
        return vehiclesService.getAllVehicles();
    }

    @Operation(summary = "Récupérer un véhicule par ID", description = "Renvoie les détails d'un véhicule spécifique par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Véhicule trouvé"),
            @ApiResponse(responseCode = "404", description = "Véhicule non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehiclesService.getVehicleById(id)
                .map(vehicle -> new ResponseEntity<>(vehicle, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/availability")
    public boolean checkVehicleAvailability(
            @PathVariable Long id,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return vehiclesService.isVehicleUnavailable(id, start, end);
    }

    @Operation(summary = "Créer un nouveau véhicule", description = "Ajoute un nouveau véhicule dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Véhicule créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle newVehicle) {
        Vehicle createdVehicle = vehiclesService.createVehicle(newVehicle);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    @Operation(summary = "Mettre à jour un véhicule existant", description = "Modifie les informations d'un véhicule existant par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Véhicule mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Véhicule non trouvé")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle) {
        return vehiclesService.updateVehicle(id, updatedVehicle)
                .map(vehicle -> new ResponseEntity<>(vehicle, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Supprimer un véhicule par ID", description = "Supprime un véhicule existant par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Véhicule supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Véhicule non trouvé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        if (vehiclesService.deleteVehicle(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
