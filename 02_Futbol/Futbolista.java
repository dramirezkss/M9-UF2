import java.util.Random;

public class Futbolista extends Thread {
    private static final int NUM_JUGADORS = 11;
    private static final int NUM_TIRADES = 20;
    private static final float PROBABILITAT = 0.5f;

    private int ngols = 0;
    private int ntirades = 0;

    public Futbolista(String name) {
        super(name);
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (random.nextFloat() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public int getNgols() {
        return ngols;
    }

    public int getNtirades() {
        return ntirades;
    }
    
    public static void main(String[] args) {
        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];
        String[] noms = {"Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé"};
    
        System.out.println("Inici dels xuts --------------------");
            
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(noms[i]);
            jugadors[i].start();
        }
    
        for (int i = 0; i < NUM_JUGADORS; i++) {
            try {
                jugadors[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
        System.out.println("Fi dels xuts -----------------------");
        System.out.println("--- Estadístiques ------");
    
        for (int i = 0; i < NUM_JUGADORS; i++) {
            System.out.println(jugadors[i].getName() +  " -> " + jugadors[i].getNgols() + " gols");
        }
    }
}