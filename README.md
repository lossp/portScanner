# portScanner
Personal little demo of ports scanning

The main entrance of this little demo is PortScanningController;

This project is based on Maven structure.

There are two models provided

> ​	1.Scan the ports of a certain ip, a max port number is required
>
> ​	2.Scan the ip with a certian port provided. More accurate



There are some tips you might learn.

> Java 8 lambda
>
> Java factory model (Use interface)
>
> Java Multiple Threads programming
>
> Java Socket Programming



How to run it ? Just execute it.

If you have any better idea, feel free to modify it.

PS: I replace the Thread from Runnable into Callable. Only in that way can we get the execution result from every thread.
Runnable interface defined the run() method, which returns void. While the Callable`s call() method returning a generics type. In that way, we can do anything about the data we gather from each thread, which is more convenient.
But we have to add some more feature to the thread, for example, what will we do if one thread is down during the working? In the next phase, CompletionService will solve this problem