package device.manager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewDeviceDTO(
        @NotBlank(message = "Device name (name) is required")
        @Size(min = 3, max = 50, message = "Device name (name) must be between 3 and 20 characters")
        String name,
        @NotBlank(message = "Device type (type) is required")
        String type,
        @NotBlank(message = "Device description (description) is required")
        String description
) {
}
