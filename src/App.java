import java.util.Random;

public class App {
    public static void main(String[] args) throws InterruptedException {
        int[] temperaturas = new int[3650];

        Random aleatorio = new Random();

        for (int i = 0; i < temperaturas.length; i++) {
            temperaturas[i] = aleatorio.nextInt(-30, 51);
        }

        int numHilos = Runtime.getRuntime().availableProcessors();
        int trozo = temperaturas.length / numHilos; // en mi caso = 228 con algo...

        Orden[] misHilos = new Orden[numHilos];
        for (int i = 0; i < numHilos - 1; i++) {
            misHilos[i] = new Orden(temperaturas, trozo * i, trozo * i + trozo);
            misHilos[i].start();
            Thread.sleep(1);
            System.out.println(misHilos[i].getMaxTemp());
            
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

class Orden extends Thread {

    private int[] temperaturas;
    private int inicio;
    private int fin;
    private int maxTemp;

    public Orden(int[] temperaturas, int inicio, int fin) {
        this.temperaturas = temperaturas;
        this.inicio = inicio;
        this.fin = fin;
        this.maxTemp = -31;
    }

    public int maxTemperatura() {
        int maxTemp = -31;
        for (int i = inicio; i < fin; i++) {
            if (temperaturas[i] > maxTemp) {
                maxTemp = temperaturas[i];
            }
        }
        return maxTemp;
    }

    public void run() {
        maxTemp = maxTemperatura();
    }

    public int[] getTemperaturas() {
        return temperaturas;
    }

    public void setTemperaturas(int[] temperaturas) {
        this.temperaturas = temperaturas;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

}
