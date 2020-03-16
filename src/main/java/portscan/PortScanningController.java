package portscan;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PortScanningController {
    public static void main(String[] args) {
        try {
            System.out.println("Please choose the model");
            System.out.println("1. Scan all the ports within the range of a certain ip");
            System.out.println("2. Scan the certain ip and its certain port");
            InputStream initialInput = System.in;
            BufferedReader initialInputBufferReader = new BufferedReader(new InputStreamReader(initialInput));
            int model = Integer.parseInt(initialInputBufferReader.readLine());
            if (model == 1) {
                System.out.println("Please Enter the ip address");
                InputStream is = System.in;
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String ipAddress = br.readLine();
                if (!IpAssertion.ipCheck(ipAddress)) throw new IllegalArgumentException("ip地址不合法，请重新输入");
                System.out.println("请输入最大端口号: ");
                InputStream is1 = System.in;
                BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
                // defines the port list, for collecting all the result from each thread and combine them together
                List<Integer> listAll = new ArrayList<>();

                int initialPortNumber = Integer.parseInt(br1.readLine());
                List<PortScanningTask> portTasks = new ArrayList<>();
                for (int i = 0; i < initialPortNumber; i++) {
                    portTasks.add(new PortScanningTask(i, i, ipAddress));
                }
                int threadCount = 10;
                List[] taskList = Distributor.distributeTask(portTasks, threadCount);

                PortScanningFactory.getPortScanningThread<PortScanningThread> factory = () -> new PortScanningThread();

                // 定义线程池 defines the ExecutorService
                ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
                System.out.println("扫描中，请耐心等待...");
                for (int i = 0; i < taskList.length; i++) {
                    //初始化线程
                    PortScanningThread portScanningThread = factory.newInstance();
                    portScanningThread.setTasks(taskList[i]);
                    portScanningThread.setThreadId(i);
                    Future future = executorService.submit(portScanningThread);
                    List<Integer> result = (List<Integer>) future.get();
                    listAll.addAll(result);
                }
                System.out.println("扫描完毕，结果为: " + listAll);
            } else if (model == 2) {
                System.out.println("Please enter the ip");
                BufferedReader ipBufferReader = new BufferedReader(new InputStreamReader(System.in));
                String ipAddress = ipBufferReader.readLine();
                System.out.println("Please enter the port");
                BufferedReader portBufferReader = new BufferedReader(new InputStreamReader(System.in));
                int port = Integer.parseInt(portBufferReader.readLine());
                PortScanningFactory.getPortScanningThread<PortScanningThread> factory = () -> new PortScanningThread();

                // initialize the executor service
                ExecutorService executorService = Executors.newFixedThreadPool(1);



                PortScanningThread portScanningThread = factory.newInstance();
                List<PortScanningTask> portTasks = new ArrayList<>();
                portTasks.add(new PortScanningTask(1, port, ipAddress));
                portScanningThread.setTasks(portTasks);
                portScanningThread.setThreadId(1);
                Future future = executorService.submit(portScanningThread);
                System.out.println(future.get());
                List<Integer> resultList =(List<Integer>) future.get();
                if (!resultList.isEmpty()) System.out.println("端口 " + port + " 处于打开状态");
                else System.out.println("端口 " + port + " 处于关闭状态");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
