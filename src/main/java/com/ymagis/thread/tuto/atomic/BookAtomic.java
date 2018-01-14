package com.ymagis.thread.tuto.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * les fonction xxxAndGet ou getAndXxx() rendent les opération atomique et évite
 * une incohérence entre thread. Method incrementAndGet() returns the updated
 * value but method AtomicInteger ’s getAndIncrement() returns the previous
 * value. Les différents types de classes atomic possible: AtomicBoolean ,
 * AtomicLong , AtomicIntegerArray , AtomicLongArray , and Atomic- Reference<V>
 * . AtomicLong
 */
public class BookAtomic {

    String title;
    AtomicInteger copiesSold = new AtomicInteger(0);

    BookAtomic(String title) {
        this.title = title;
    }

    public void newSale() {
        copiesSold.incrementAndGet();
    }

    public void returnBook() {
        copiesSold.decrementAndGet();
    }
}
