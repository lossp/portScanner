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

    // ip与端口号
    public PortScanningTask(int taskId, int port, String ipAddress) {
        this.taskId = taskId;
        this.port = port;
        this.ipAddress = ipAddress;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void execute() {
        setStatus(PortScanningTask.RUNNING);
        System.out.println("线程id为" + Thread.currentThread().getName() + " 任务ID为" + this.taskId);
        try {
            isConnected(this.port);
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setStatus(PortScanningTask.FINISHED);
    }

    public int getTaskId() {
        return this.taskId;
    }

    private void isConnected(int port) {
        try {
            Socket socket = new Socket(this.ipAddress, port);
            socket.close();
            System.out.println("此时端口: " + port + "状态为打开");
        } catch (IOException e) {
            System.out.println("此时端口: " + port + "状态为关闭");
        }

    }
}
