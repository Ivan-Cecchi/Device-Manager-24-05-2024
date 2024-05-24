package device.manager.controllers;

import device.manager.exceptions.BadRequestException;
import device.manager.dtos.DeviceDTO;
import device.manager.dtos.NewDeviceDTO;
import device.manager.entities.Device;
import device.manager.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device addDevice(@RequestBody @Validated NewDeviceDTO device, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid data", validation.getAllErrors());
        }
        return deviceService.addDevice(device);
    }


    @GetMapping
    public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return deviceService.getDevices(pageable);
    }


    @GetMapping("{id}")
    public Device getDevice(@PathVariable UUID id) {
        return deviceService.getDevice(id);
    }

    @PutMapping("{id}")
    public Device updateDevice(@PathVariable UUID id, @RequestBody @Validated DeviceDTO device, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid data", validation.getAllErrors());
        }
        return deviceService.updateDevice(id, device);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable UUID id) {
        deviceService.deleteDevice(id);
    }


    @PatchMapping("{id}/employee/{employeeId}")
    public Device assignDeviceToEmployee(@PathVariable UUID id, @PathVariable UUID employeeId) {
        return deviceService.assignDeviceToEmployee(id, employeeId);
    }
}
