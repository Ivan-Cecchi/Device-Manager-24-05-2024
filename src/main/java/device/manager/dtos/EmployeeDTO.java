package device.manager.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeDTO(
        @NotBlank(message = "Username (username) is required")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,
        @NotBlank(message = "First name (firstName) is required")
        @Size(min = 3, message = "First name (firstName) must be at least 3 characters")
        String firstName,
        @NotBlank(message = "Last name (lastName) is required")
        @Size(min = 3, message = "Last name (lastName) must be at least 3 characters")
        String lastName,
        @NotBlank(message = "Email is (email) required")
        @Email(message = "Email (email) must be a valid email address")
        String email
) {
}
