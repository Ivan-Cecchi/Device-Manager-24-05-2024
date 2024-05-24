package device.manager.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.NONE)
    private UUID id;
    private String name;
    private String type;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private DeviceStatus status;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Device(String name, String type, String description, DeviceStatus status) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.type = type;
    }

    public Device(String name, String type, String description, DeviceStatus status, Employee employee) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.status = status;
        this.employee = employee;
    }
}
