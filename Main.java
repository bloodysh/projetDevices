import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Device> devices = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public boolean declareDevice(String deviceId, String firmware) {
        devices.add(new Device(deviceId, firmware));
        System.out.println("Dispositif " + deviceId + " declaré: " + firmware);
        return true;
    }

    public boolean associateDevice(String deviceId, String userId) {
        Device device = findDeviceById(deviceId);
        User user = findUserById(userId);

        if (device == null) {
            System.out.println("Dispositif " + deviceId + " n'existe pas.");
            return false;
        }
        
        if (user == null) {
            user = new User(userId);
            users.add(user);
        }
        device.setUser(user);
        System.out.println("Dispositif " + deviceId + " associé avec " + userId);
        return true;
    }

    public boolean dissociateDevice(String userId, String deviceId) {
        Device device = findDeviceById(deviceId);
        if (device == null || device.getUser() == null || !device.getUser().getId().equals(userId)) {
            System.out.println("Dispositif " + deviceId + " n'est pas associé à: " + userId);
            return false;
        }
        device.setUser(null);
        System.out.println("Dispositif " + deviceId + " dissocié de: " + userId);
        return true;
    }

    public boolean updateFirmware(String deviceId, String newFirmware) {
        Device device = findDeviceById(deviceId);
        if (device == null) {
            System.out.println("Dispositif " + deviceId + " n'existe pas.");
            return false;
        }
        device.setFirmware(newFirmware);
        System.out.println("Dispositif " + deviceId + " mis à jour: " + newFirmware);
        return true;
    }

    public void listDevices() {
        for (Device device : devices) {
            System.out.println("Dispositif id: " + device.getId() + ", Firmware: " + device.getFirmware());
        }
    }

    private Device findDeviceById(String deviceId) {
        return devices.stream().filter(d -> d.getId().equals(deviceId)).findFirst().orElse(null);
    }

    private User findUserById(String userId) {
        return users.stream().filter(u -> u.getId().equals(userId)).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        Main manager = new Main();

        // Test cases
        manager.declareDevice("SLB-01", "FW-01"); // Expected: OK
        manager.declareDevice("SLB-01", "FW-01"); // Expected: KO
        manager.declareDevice("SLB-02", "FW-01"); // Expected: OK

        manager.associateDevice("SLB-01", "User-01"); // Expected: OK
        manager.associateDevice("SLB-01", "User-02"); // Expected: KO
        manager.associateDevice("SLB-02", "User-02"); // Expected: OK

        manager.dissociateDevice("User-01", "SLB-01"); // Expected: OK
        manager.dissociateDevice("User-03", "SLB-03"); // Expected: KO

        manager.updateFirmware("SLB-01", "FW-02"); // Expected: OK
        manager.updateFirmware("SLB-02", "FW-02"); // Expected: OK

        
        manager.listDevices();
    }
}
