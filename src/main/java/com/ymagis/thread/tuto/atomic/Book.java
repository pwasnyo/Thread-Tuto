package com.ymagis.thread.tuto.atomic;

/**
 une variable volatile (type primitifs) parmet à la variable d'être vu par tous les autres threads, de ne pas être optimisée
 * dans sa gestion par le programme. Le machine lorsqu'elle utilise une variable, pour otimiser la gestion de celle-ci, elle la
 * charge en mémoire, la lit ou écrit ensuite la remet dans un registre ce qui n'est pas une opération atomic et peut entrainer
 * des erreurs sur les threads qui essayent d'y accéder alors que la variable est dans un état transitoire.
 * Le code ci-dessous si il était appelé par 2 threads qui accéderait aux fonctions newSale et reurnBook on pourrait avoir
 * des incohérence sur la variable copiesSold. On peut comme solution mettre les 2 fonction en synchronized pour accéder
 * à la variable copiesSold mais ça peut entrainer des deadlock du coup il faut rendre la variable atomic (concept meilleur que la volatilité)
 * voir classe BookAtomic
 */
public class Book {

    String title;
    int copiesSold = 0;

    Book(String title) {
        this.title = title;
    }

    public void newSale() {
        ++copiesSold;
    }

    public void returnBook() {
        --copiesSold;
    }
}
