package portscan;

import java.util.List;

public class PortScanningThread implements Runnable {

    private List<PortScanningTask> tasks = null;
    private int threadId;

    /**
     * to bind the task list to the certain thread
     * @param tasks task list
     */
    public void setTasks(List<PortScanningTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * to bind the thread with certain id
     * @param threadId thread id
     */
    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    /**
     * start each one of the port scan task
     */
    @Override
    public void run() {
        for (PortScanningTask task : tasks) {
            task.execute();
        }
    }
}