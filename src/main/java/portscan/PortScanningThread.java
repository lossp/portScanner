package portscan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PortScanningThread implements Callable<List<Integer>> {

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

    public List<Integer> call() {
        final List<Integer> list = new ArrayList<>();
        for (PortScanningTask task:tasks) {
            if (task.execute()) list.add(task.getPort());
        }
        return list;
    }
}