package portscan;

public class PortScanningFactory {
    /**
     * A factory method to generates certain object
     * @param <T>
     */
    public interface getPortScanningThread<T> {
        T newInstance() throws Exception;
    }
}
