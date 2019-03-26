package com.zillionfortune.cif.common.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/** 
 * 固定长度队列 
 * 
 */
public class LimitQueue<E> {
    //队列长度  
    private int limit = 20;

    Queue<E>    queue = new LinkedList<E>();

    public LimitQueue(int limit) {
        this.limit = limit;
    }

    /** 
     * 入队 
     * @param e 
     */
    public boolean offer(E e) {
        if (queue.size() >= limit) {
            //如果超出长度,入队时,先出队  
            queue.poll();
        }
        return queue.offer(e);
    }

    /** 
     * 出队 
     * @return 
     */
    public E poll() {
        return queue.poll();
    }

    /** 
     * 获取队列 
     * @return 
     */
    public Queue<E> getQueue() {
        return queue;
    }

    /** 
     * 获取限制大小 
     * @return 
     */
    public int getLimit() {
        return limit;
    }

    public boolean isEmpty() {
        return queue.size() == 0 ? true : false;
    }

    public int size() {
        return queue.size();
    }

    public E remove() {
        return queue.remove();
    }

    public boolean addAll(Collection<? extends E> c) {
        return queue.addAll(c);
    }

    public void clear() {
        queue.clear();
    }

    public boolean contains(Object o) {
        return queue.contains(o);
    }

    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    public Iterator<E> iterator() {
        return queue.iterator();
    }

    public boolean remove(Object o) {
        return queue.remove(o);
    }

    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    public Object[] toArray() {
        return queue.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }

}