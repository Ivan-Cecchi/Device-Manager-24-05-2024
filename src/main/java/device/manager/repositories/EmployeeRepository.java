package device.manager.repositories;

import device.manager.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsernameAndEmail(String username, String email);
}
