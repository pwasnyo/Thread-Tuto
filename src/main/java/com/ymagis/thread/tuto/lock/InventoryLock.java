package com.ymagis.thread.tuto.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author pwasnyo
 */
public class InventoryLock {

    int inStock;
    String name;
    Lock lock = new ReentrantLock(); // Objet Lock pour servira à éviter que le thread qui utilisera cet objet ne sois dans un deadlock

    public InventoryLock(String name) {
        this.name = name;
    }

    // methodes succeptible de produire des incohérence si plusieurs thread y accèdent sans contrôle
    public void stockIn(int qty) {
        inStock += qty;
    }

    public void stockOut(int qty) {
        inStock -= qty;
    }
}
