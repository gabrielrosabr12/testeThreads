class MinhaTarefa implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Minha tarefa Runnable está executando pela: " + i + "ª vez");
            try {
                Thread.sleep(100); // pausa de 100 milissegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ExecucaoRunnable {
    public static void main(String[] args) {
        MinhaTarefa tarefa = new MinhaTarefa();
        Thread thread2 = new Thread(tarefa);
        thread2.start();

        for (int j = 0; j < 10; j++) {
            System.out.println("Thread principal executando: " + j);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
