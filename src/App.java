import java.util.Random;

public class App {
    private static final int MAXNUMTEMP = 3650;
    private static final int MINTEMP = -30;
    private static final int MAXTEMP = 50;

    public static void main(String[] args) throws InterruptedException {
        int[] temperaturas = new int[MAXNUMTEMP];

        Random aleatorio = new Random();

        for (int i = 0; i < temperaturas.length; i++) {
            temperaturas[i] = aleatorio.nextInt(MINTEMP, MAXTEMP+1);
        }

        int numHilos = Runtime.getRuntime().availableProcessors();
        int trozo = temperaturas.length / numHilos; // en mi caso = 228 con algo...

        Orden[] misHilos = new Orden[numHilos];
        for (int i = 0; i < numHilos - 1; i++) {
            misHilos[i] = new Orden(temperaturas, trozo * i, trozo * i + trozo);
            misHilos[i].start();
        }
        misHilos[numHilos - 1] = new Orden(temperaturas, trozo * (numHilos - 1), temperaturas.length);
        misHilos[numHilos - 1].start();
        
        for (int i = 0; i < numHilos; i++) {
            misHilos[i].join();         
        }
        

        int maxTemDefinitiva = -31;
        for (int i = 0; i < misHilos.length; i++) {
            if (misHilos[i].getMaxTemp() > maxTemDefinitiva) {
                maxTemDefinitiva = misHilos[i].getMaxTemp();
            }
        }
        System.out.println("la temperatura máxima total es de: "+ maxTemDefinitiva);

    }
}

