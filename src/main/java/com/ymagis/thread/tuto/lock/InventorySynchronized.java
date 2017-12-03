
package com.ymagis.thread.tuto.lock;

/**
 *
 * Cet Exemple utilise synchronized sur 2 objets partagé ce qui peut produire
 * un deadlock et nous allons voir comment être sûr d'éviter des deadlock avec
 * l'interface Lock
 */
public class InventorySynchronized {

    int inStock;
    String name;

    public InventorySynchronized(String name) {
        this.name = name;
    }

    public void stockIn(int qty) {
        inStock += qty;
    }

    public void stockOut(int qty) {
        inStock -= qty;
    }

}
