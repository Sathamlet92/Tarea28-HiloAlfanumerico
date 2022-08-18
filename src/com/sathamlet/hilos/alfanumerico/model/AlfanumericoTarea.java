package com.sathamlet.hilos.alfanumerico.model;

import java.util.concurrent.CountDownLatch;

public class AlfanumericoTarea implements Runnable{
    private Tipo tipo;
    private CountDownLatch lock;

    public AlfanumericoTarea(Tipo tipo) {
        this.tipo = tipo;
    }

    public AlfanumericoTarea(Tipo tipo, CountDownLatch lock) {
        this.lock = lock;
        this.tipo = tipo;
    }

    private void auxImprim(Tipo tipo, CountDownLatch lock){
        char leLast = 'Z';
        char leFirst = 'A';
        int i = 1;
        int aux = 10;

        if(tipo == Tipo.LETRA) {
            i = leFirst;
            aux = leLast;
        }
        for ( ; i <= aux ; i++, leFirst++) {
            try {
                if(tipo == Tipo.LETRA)
                    System.out.print(leFirst + ", ");

                else System.out.print(i + ", ");
                Thread.sleep(500);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.countDown();
        System.out.println(lock.getCount());
    }
    private void auxImprim(Tipo tipo){
        char leLast = 'Z';
        char leFirst = 'A';
        int i = 1;
        int aux = 10;

        if(tipo == Tipo.LETRA) {
            i = leFirst;
            aux = leLast;
        }
        for ( ; i <= aux ; i++, leFirst++) {
            try {
                if(tipo == Tipo.LETRA)
                    System.out.print(leFirst + ", ");

                else System.out.print(i + ", ");

                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        if(this.lock != null) this.auxImprim(this.tipo, this.lock);
        else this.auxImprim(this.tipo);
    }
}
