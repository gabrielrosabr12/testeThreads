class Contador {
    private int count = 0;

    public synchronized void incrementar() {
        count++;
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }

    public synchronized int getCount() {
        return count;
    }
}

public class SincronizacaoThreads {
    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador();

        Runnable tarefa = () -> {
            for (int i = 0; i < 5; i++) {
                contador.incrementar();
            }
        };

        Thread t1 = new Thread(tarefa, "Thread-1");
        Thread t2 = new Thread(tarefa, "Thread-2");

        t1.start();
        t2.start();

        t1.join(); // Espera a Thread-1 terminar
        t2.join(); // Espera a Thread-2 terminar

        System.out.println("Valor final do contador: " + contador.getCount());
    }
}