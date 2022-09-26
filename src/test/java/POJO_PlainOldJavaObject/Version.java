package POJO_PlainOldJavaObject;

public class Version {

    private String version;
    private double major;
    private double minor;
    private double patch;

    public Version(String version, double major, double minor, double patch) {
        this.version = version;
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public Version() {
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public double getMajor() {
        return major;
    }
    public void setMajor(double major) {
        this.major = major;
    }

    public double getMinor() {
        return minor;
    }
    public void setMinor(double minor) {
        this.minor = minor;
    }

    public double getPatch() {
        return patch;
    }
    public void setPatch(double patch) {
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Version{" +
                "version='" + version + '\'' +
                ", major=" + major +
                ", minor=" + minor +
                ", patch=" + patch +
                '}';
    }
}
