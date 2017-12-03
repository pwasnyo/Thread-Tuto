package com.ymagis.thread.tuto.lock;

/**
  * Cet Exemple utilise synchronized sur 2 objets partagé ce qui peut produire
 * un deadlock et nous allons voir comment être sûr d'éviter des deadlock avec
 * l'interface Lock
 */
public class ShipmentLock extends Thread{
    InventoryLock lock1, lock2;
    int qty;

    public ShipmentLock(InventoryLock lock1, InventoryLock lock2, int qty) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.qty = qty;
    }
    
    public void run(){
        
        if(lock1.lock.tryLock()){ // ne lock l'objet que ssi il est disponible donc pas exécuter par un autre thread
            System.out.println("Loc1 acquis");
            if(lock2.lock.tryLock()){// lock du 2ème objet ssi il est dispo
                System.out.println("Loc2 acquis");
                lock2.stockOut(qty);
                lock1.stockIn(qty);
                System.out.println(lock1.inStock+ ":" + lock2.inStock);
                //toujours libérer les locks
                lock2.lock.unlock();
                lock1.lock.unlock();
            }else{
                System.out.println("Locking false: "+lock2.name);
            }
        }else{
            System.out.println("Locking false: "+lock1.name);
        }
    }
    
    public static void main(String[] args) {
        InventoryLock lock1 = new InventoryLock("Paris");
        lock1.inStock = 50;
        InventoryLock lock2 = new InventoryLock("Marseille");
        lock2.inStock = 100;
        ShipmentLock s1 = new ShipmentLock(lock1, lock2, 1);
        ShipmentLock s2 = new ShipmentLock(lock1, lock2, 10);
        // les 2 thread partage lock1 et lock2 mais on aura jamais de dealock peut importe comment le scheduler lance les threads
        s1.start();
        s2.start();
        
    }
}
