package portscan;

public class PortScanningFactory {
    public interface getPortScanningThread<T> {
        T newInstance() throws Exception;
    }
}
