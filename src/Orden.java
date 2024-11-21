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

