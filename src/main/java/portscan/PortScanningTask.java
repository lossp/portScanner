package portscan;

import java.io.IOException;
import java.net.Socket;

public class PortScanningTask {
    private static final int READY = 0;
    private static final int RUNNING = 1;
    private static final int FINISHED = 2;

    private int status;
    private int taskId;
    private int port;
    private String ipAddress;

    /**
     * The constructor method, which requires a taskId, a port and an ip address
     * @param taskId the task index
     * @param port the port needs to scan
     * @param ipAddress the address needs to scan
     */
    public PortScanningTask(int taskId, int port, String ipAddress) {
        this.taskId = taskId;
        this.port = port;
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return this.port;
    }

    /**
     * set the status, 0 for ready, 1 for running, 2 for finished
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * initiate the scanning task, see if the ip`s port is open
     * it needs no params, just to check this class`s port and ip address
     * returns true if the port is open
     */
    public boolean execute() {
        setStatus(PortScanningTask.RUNNING);
//        System.out.println("线程id为" + Thread.currentThread().getName() + " 任务ID为" + this.taskId);
        try {
            boolean isOpen = isConnected(this.port);
            Thread.sleep(500);
            return isOpen;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setStatus(PortScanningTask.FINISHED);
        return false;
    }

    /**
     * get the class`s task id
     * @return this class`s task id
     */
    public int getTaskId() {
        return this.taskId;
    }

    /**
     * This method is decorated as private method. it`s only used within this class, to see if the port is open.
     * @param port the certain port
     */
    private boolean isConnected(int port) {
        try {
            Socket socket = new Socket(this.ipAddress, port);
            socket.close();
//            System.out.println("此时端口: " + port + "状态为打开");
            return true;
        } catch (IOException e) {
//            System.out.println("此时端口: " + port + "状态为关闭");
            return false;
        }

    }
}
