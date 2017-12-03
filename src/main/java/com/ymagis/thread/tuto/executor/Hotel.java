/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ymagis.thread.tuto.executor;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * Executor Interface permet au travers des interface Runnable et Callable permet de controller l'odre dans lequel sont executé les
 * threads. Il faudra pour cela implementer la methode void execute(Runnable)
 */
public class Hotel implements Executor {
    /*une objet ou une variable final n'est instancié qu'une fois à la déclaration ou au plutard dans le constructeur mais attention
     quand c'est un objet car on peut appeler les methodes de l'objet (ex set, add etc ...) et changer l'objet sans l'instancier à nouveau */
    final Queue<Runnable> custQueue = new ArrayDeque<>();
    @Override
    public void execute(Runnable r) {
        // pour empêcher à plusieurs theard d'accéder à  mon objet partagé au même moment
        synchronized(custQueue){
            custQueue.add(r); // rajout des runnable qui vont être exécuter via des thread
        }
        /* execute va forcer l'exécution des thread de la method processEarliestOrder() d'être lancé dans l'ordre dans lequel ils
        sont créés dans l'ArrayList qui est notre objet partagé. ce qui n'est pas le cas quand c'est le scheduler qui le fait*/
        processEarliestOrder();
    }
    
    // récupère mon objet Runnable et lance un thread
    private void processEarliestOrder(){
         synchronized(custQueue){
            Runnable task= custQueue.poll(); 
            new Thread(task).start();
        }
    }
    
    public static void main(String[] args) {
        /* les threads seront executer dans l'ordre dans lequel ils sont appelé or quand c'est le scheduler, 
        c'est pas parce que le thread start en 1er qu'il s'exécute en 1er*/
        
        Hotel hotel = new Hotel();
        hotel.execute(new Order("Pierre"));
        hotel.execute(new Order("Paul"));
        hotel.execute(new Order("Jacques"));
        
    }
}

 