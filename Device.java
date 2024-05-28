public class Device {
    private String id;
    private String firmware;
    private User user;

    public Device(String id, String firmware) {
        this.id = id;
        this.firmware = firmware;
        this.user = null;
    }

    public String getId() {
        return id;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
