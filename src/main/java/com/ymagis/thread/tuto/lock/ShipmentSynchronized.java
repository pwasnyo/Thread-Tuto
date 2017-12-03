package com.ymagis.thread.tuto.lock;

/**
  * Cet Exemple utilise synchronized sur 2 objets partagé ce qui peut produire
 * un deadlock et nous allons voir comment être sûr d'éviter des deadlock avec
 * l'interface Lock
 */
public class ShipmentSynchronized extends Thread{
    InventorySynchronized lock1, lock2;
    int qty;

    public ShipmentSynchronized(InventorySynchronized lock1, InventorySynchronized lock2, int qty) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.qty = qty;
    }
    
    public void run(){
        synchronized(lock1){ // lock du 1er objet
            synchronized(lock2){// lock du 2ème objet
                lock2.stockOut(qty);
                lock1.stockIn(qty);
                System.out.println(lock1.inStock+ ":" + lock2.inStock);
            }// libération de lock2
        }// libération de lock1
    }
    
    public static void main(String[] args) {
        InventorySynchronized lock1 = new InventorySynchronized("Paris");
        lock1.inStock = 50;
        InventorySynchronized lock2 = new InventorySynchronized("Marseille");
        lock2.inStock = 100;
        ShipmentSynchronized s1 = new ShipmentSynchronized(lock1, lock2, 1);
        ShipmentSynchronized s2 = new ShipmentSynchronized(lock1, lock2, 10);
        // les 2 thread partage lock1 et lock2 ce qui fait qu'il peut avoir des dealock
        s1.start();
        s2.start();
        
    }
}
