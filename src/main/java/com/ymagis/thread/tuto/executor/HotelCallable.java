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
public class HotelCallable implements Executor {

    @Override
    public void execute(Runnable r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}

 