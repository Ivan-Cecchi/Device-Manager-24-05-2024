package device.manager.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DeviceDTO(
        @NotBlank(message = "Device name (name) is required")
        @Size(min = 3, max = 50, message = "Device name (name) must be between 3 and 20 characters")
        String name,
        @NotBlank(message = "Device type (type) is required")
        String type,
        @NotBlank(message = "Device description (description) is required")
        String description,
        @NotBlank(message = "Device status (status) is required")
        @Pattern(regexp = "AVILABLE|ASSIGNED|MAINTENANCE|DISPOSED", message = "Device status (status) must be one of the following: AVILABLE, ASSIGNED, MAINTENANCE, DISPOSED")
        // Ho cercato di rendere dinamico il pattern ma non ho trovato una soluzione.
        //Ho provato con String.join("|", Arrays.stream(DeviceStatus.values()).map(Enum::name).toArray(String[]::new)) ma dice che il parametro deve essere una costante
        String status,
        @Nullable
        UUID employeeId
) {
}
