package portscan;

import java.util.ArrayList;
import java.util.List;


/**
 * @descrption This class is for tasks distribution
 */
public class Distributor {

    /**
     *
     * @param tasks task list
     * @param threadCount number
     * @return perWorkingThreadTasks a list contains each thread`s task list
     */
    public static List[] distributeTask (List tasks, int threadCount) {
        int minTaskNumber = tasks.size() / threadCount;
        int remainingNumber = tasks.size() % threadCount;
        int actualWorkingNumber = minTaskNumber > 0 ? threadCount : remainingNumber;
        List[] perWorkingThreadTasks = new List[actualWorkingNumber];
        int taskIndex = 0;
        int remainIndces = remainingNumber;
        for (int i = 0; i < perWorkingThreadTasks.length; i++) {
            perWorkingThreadTasks[i] = new ArrayList();
            if (minTaskNumber > 0) {
                for (int j = taskIndex; j < taskIndex + minTaskNumber; j++) {
                    perWorkingThreadTasks[i].add(tasks.get(j));
                }
            }
            taskIndex += minTaskNumber;
            if (remainIndces > 0) {
                perWorkingThreadTasks[i].add(tasks.get(taskIndex++));
                remainIndces--;
            }

        }
        return perWorkingThreadTasks;
    }
}
