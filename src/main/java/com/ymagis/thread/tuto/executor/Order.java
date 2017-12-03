/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ymagis.thread.tuto.executor;

/**
 *
 * @author pwasnyo
 */
public class Order implements Runnable {

    String name;

    public Order(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(" my name is " + name);
    }

}
