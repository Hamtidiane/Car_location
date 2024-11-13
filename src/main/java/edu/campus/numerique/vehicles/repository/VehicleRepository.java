package edu.campus.numerique.vehicles.repository;

import edu.campus.numerique.vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface VehicleRepository permettant d'accéder aux données des objets {@link Vehicle}.
 * <p>
 * Cette interface étend {@link JpaRepository} de Spring Data JPA, ce qui fournit des méthodes
 * CRUD de base (Create, Read, Update, Delete) ainsi que des fonctionnalités avancées pour
 * gérer les entités de type {@link Vehicle}.
 * <p>
 * En utilisant {@link JpaRepository}, Spring Data JPA génère automatiquement les implémentations
 * des méthodes de gestion de données, facilitant ainsi l'accès aux données sans implémenter
 * explicitement les méthodes.
 * </p>
 *
 * <p>
 * <strong>Note :</strong> L'annotation {@link Repository} est une spécialisation de {@link org.springframework.stereotype.Component}.
 * Elle indique que cette interface est un composant Spring qui peut être injecté dans d'autres
 * composants et services.
 * </p>
 *
 * @see Vehicle
 * @see JpaRepository
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
