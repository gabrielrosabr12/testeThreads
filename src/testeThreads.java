class MinhaThread extends Thread {
    @Override
    public void run() {
        // O código a ser executado pela thread vai aqui
        System.out.println("Minha thread está executando!");
    }
}

public class testeThreads {
    public static void main(String[] args) {
        MinhaThread thread1 = new MinhaThread();
        thread1.start(); // Inicia a execução da thread
        System.out.println("Thread principal finalizada.");
    }
}