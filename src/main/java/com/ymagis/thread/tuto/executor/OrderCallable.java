/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ymagis.thread.tuto.executor;

import java.util.concurrent.Callable;

/**
 *
 * ici on va utiliser l'interface Callable au lieu de Runnable comme dans Order,
 * la particularité de Callable c'est qu'on peu choisir de retourner un type ou
 * déclancher une Exception que je veux avoir ce qui est impossible avec
 * Runnable
 * <Void> si je ne veux rien retourné ça aurait pu être un objet défini par moi
 * ex <MyObjet>
 */
public class OrderCallable implements Callable<Void> {

    String name;

    public OrderCallable(String name) {
        this.name = name;
    }

    @Override
//    Void parce que je ne veux rien retourné sinon ça aurai été l'objet que je veux retourner ex MyObjet
    public Void call() throws Exception {
        System.out.println(" my callable name is " + name);
        if(name.equalsIgnoreCase("titi")){
            throw new Exception(name+" is unavailable ");
        }
        return null;
    }

}
