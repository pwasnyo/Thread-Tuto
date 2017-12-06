package com.ymagis.thread.tuto.lock.lockInterruptibly;

/**
 *
 * Les méthodes lockInterruptibly() et tryLock(long time, TimeUnit unit)
 * permettent d'intérompre l'exécution d'un thread, si le thread est en attente
 * du lock ou a déjà exécuter son run() sauf qu'avec tryLock(long time, TimeUnit
 * unit) on peut fixer pendant combien de temps le thread doit essayé d'avoir le
 * lock si il ne l'obtient pas au bout de ce temps il laisse tombé. C'est un peu
 * comme si on allait chez le médécin et qu'on se dise si je ne suis pas reçu au
 * bout de 1h je part c'est le tryLock(long time, TimeUnit unit) ou pendant que
 * j'attend on m'appelle pour me demander d'aller faire quelque chose de plus
 * important donc je dois interrompre mon attente et partir pour une autre tâche
 * lockInterruptibly()
 */
public class Employee extends Thread {

    Bus bus;
    String name;

    Employee(String name, Bus bus) {
        this.bus = bus;
        this.name = name;
    }

    public void run() {
        try {
            /* on essaye d'avoir le lock sur l'objet bus si il est disponible on utilise lockInterruptibly() pour
            prévenir que le thread peut être interrompu plutard si il est en attente du lock ou qu'il a fini son run*/
            bus.lock.lockInterruptibly();
            try {
                /* si le lock a été acquis on execute la methode qui dois être accédé par un seul thread en même temps
                et on libère le lock dans le finally*/
                bus.boardBus(name);

            } finally {
                bus.lock.unlock();

            }
            // InterruptedException au cas où on interompt le thread parce qu'on dois faire une autre tache plus importante
        } catch (InterruptedException e) {
            System.out.println(name + ": Interrupted!!");
            Thread.currentThread().interrupt();
        }

    }

    public static void main(String[] args) {
        Bus bus = new Bus();
        Employee e1 = new Employee("Paul", bus);
        e1.start();
        /* J'essaye d'interrompre le thread e1 parce que j'ai peut-être une tâche autre que je veux effectuer
        mais l'interruption n'aura lieu que si e1 n'a pas encore acquis le lock ou a déjà exécuté son run*/
        e1.interrupt();
        Employee e2 = new Employee("Shreya", bus);
        e2.start();
        /* Ne pas oublier que le thread e2 peut être lancé avant le e1 (si je veux controler l'ordre des threads
        il faut utiliser l'interface Executor) ce qui fait qu'on pourrait avoir les sortie suivante:
        1-(e2 s'est lancé avant e1 qui était en attente du lock donc a été interrompu)
        Shreya: boarded
        Paul: Interrupted!!
        
        2- (e1 s'est lancé mais n'avais pas encore le lock quand on a demandé à l'interrompre)
        Paul: Interrupted!!
        Shreya: boarded
        
        3-(e1 a pris le lock donc ne pouvait pas être interrompu avant que son run ai été exécuté)
        Paul: boarded
        Shreya: boarded
        
        4- (e2 à commencé et fini en 1er ensuite e1 a été lancé et a pu avoir le lock et exécuter son run)
        Shreya: boarded
        Paul: boarded
         */
    }
}
