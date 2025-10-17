import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ContadorComLock {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void incrementar() {
        lock.lock(); // Adquire o lock
        try {
            count++;
            System.out.println(Thread.currentThread().getName() + ": " + count);
        } finally {
            lock.unlock(); // Garante que o lock seja liberado, mesmo se ocorrer uma exceção
        }
    }

    public int getCount() {
        return count;
    }
}

public class ExemploReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        ContadorComLock contador = new ContadorComLock();

        Runnable tarefa = () -> {
            for (int i = 0; i < 5; i++) {
                contador.incrementar();
            }
        };

        Thread t1 = new Thread(tarefa, "Thread-Lock-1");
        Thread t2 = new Thread(tarefa, "Thread-Lock-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor final do contador com Lock: " + contador.getCount());
    }
}