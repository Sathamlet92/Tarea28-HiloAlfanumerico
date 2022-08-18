package com.sathamlet.hilos.alfanumerico;

import com.sathamlet.hilos.alfanumerico.model.AlfanumericoTarea;
import com.sathamlet.hilos.alfanumerico.model.Tipo;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(3);

        //Tipos para scheduled Threads
        Runnable tipoS1 = new AlfanumericoTarea(Tipo.NUMERO, lock);
        Runnable tipoS2 = new AlfanumericoTarea(Tipo.LETRA, lock);

        //Tipos para threads sin agendar
        Runnable tipo1 = new AlfanumericoTarea(Tipo.LETRA);
        Runnable tipo2 = new AlfanumericoTarea(Tipo.NUMERO);


        /*Ejemplo con Clase Thread*/
        /*
        new Thread(tipo1).start();
        new Thread(tipo2).start();
         */

        /*Ejemplo con Execute Framework #1*/
        /*
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Future<?> resul1 = ex.submit(tipo1);
        Future<?> resul2 = ex.submit(tipo2);
        while(!resul1.isDone() || !resul2.isDone()){
            System.out.println(String.format("Tipo 1: %s", resul1.isDone()? "Finalizado" : "En proceso"));
            System.out.println(String.format("Tipo 2: %s", resul2.isDone()? "Finalizado" : "En proceso"));
            TimeUnit.SECONDS.sleep(2);
        }
        ex.shutdown();
        */

        /*Ejemplo con Execute Framework #2*/
        /*
        ScheduledExecutorService ex = Executors.newScheduledThreadPool(2);
        Future<?> future1 = ex.schedule(tipo1, 300, TimeUnit.MILLISECONDS);
        Future<?> future2 = ex.schedule(tipo2, 300, TimeUnit.MILLISECONDS);
        ex.shutdown();
        */


        /*Ejemplo con Execute Framework #3*/
        ScheduledExecutorService ex = Executors.newScheduledThreadPool(2);
        Future<?> future1 = ex.scheduleAtFixedRate(tipoS1, 500, 900 , TimeUnit.MILLISECONDS);
        //Future<?> future2 = ex.scheduleAtFixedRate(tipoS2, 15000, 3000 , TimeUnit.MILLISECONDS);
        lock.await();
        ex.shutdown();







    }
}
