class BufferCompartilhado {
    private int data = -1;
    private boolean hasData = false;

    public synchronized void put(int value) throws InterruptedException {
        while (hasData) { // Espera se o buffer estiver cheio
            wait();
        }
        data = value;
        hasData = true;
        System.out.println(Thread.currentThread().getName() + " produziu: " + data);
        notifyAll(); // Notifica threads esperando que há dados
    }

    public synchronized int get() throws InterruptedException {
        while (!hasData) { // Espera se o buffer estiver vazio
            wait();
        }
        int retrievedData = data;
        hasData = false;
        System.out.println(Thread.currentThread().getName() + " consumiu: " + retrievedData);
        notifyAll(); // Notifica threads esperando que o buffer está vazio
        return retrievedData;
    }
}

class Produtor implements Runnable {
    private BufferCompartilhado buffer;

    public Produtor(BufferCompartilhado buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                buffer.put(i);
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumidor implements Runnable {
    private BufferCompartilhado buffer;

    public Consumidor(BufferCompartilhado buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                buffer.get();
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ExemploProdutorConsumidor {
    public static void main(String[] args) {
        BufferCompartilhado buffer = new BufferCompartilhado();

        Thread produtorThread = new Thread(new Produtor(buffer), "Produtor");
        Thread consumidorThread = new Thread(new Consumidor(buffer), "Consumidor");

        produtorThread.start();
        consumidorThread.start();
    }
}