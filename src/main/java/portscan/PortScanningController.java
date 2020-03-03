package portscan;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PortScanningController {
    public static void main(String[] args) {
        try {
            System.out.println("Please Enter the ip address");
            InputStream is = System.in;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ipAddress = br.readLine();
            if (!IpAssertion.ipCheck(ipAddress)) throw new IllegalArgumentException("ip地址不合法，请重新输入");
            System.out.println("请输入最大端口号: ");
            InputStream is1 = System.in;
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
            int initialPortNumber = br1.read();
            List<PortScanningTask> portTasks = new ArrayList<>();
            for (int i = 0; i < initialPortNumber; i++) {
                portTasks.add(new PortScanningTask(i, i, ipAddress));
            }
            int threadCount = 10;
            List[] taskList = Distributor.distributeTask(portTasks, threadCount);

            PortScanningFactory.getPortScanningThread<PortScanningThread> factory = () -> new PortScanningThread();
            for (int i = 0; i < threadCount; i++) {
                //初始化线程
                PortScanningThread portScanningThread = factory.newInstance();
                portScanningThread.setTasks(taskList[i]);
                portScanningThread.setThreadId(i);
                Thread thread = new Thread(portScanningThread, "thread = " + i);
                thread.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
