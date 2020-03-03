package portscan;

import java.util.List;

public class PortScanningThread implements Runnable {

    private List<PortScanningTask> tasks = null;
    private int threadId;


    public void setTasks(List<PortScanningTask> tasks) {
        this.tasks = tasks;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        for (PortScanningTask task : tasks) {
            task.execute();
        }
    }
}