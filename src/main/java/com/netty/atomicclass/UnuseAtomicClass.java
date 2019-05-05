package com.netty.atomicclass;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class UnuseAtomicClass {
    public static void main(String[] args) {
      Person person = new Person();

//      for(int i = 0 ; i <10 ; i++){
//          Thread thread = new Thread(()->{
//              try {
//                  Thread.sleep(20);
//              }catch(Exception e){
//                  e.printStackTrace();
//              }
//              System.out.println( person.age ++);
//          });
//          thread.start();
//      }

        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(Person.class,"age");
        for(int i = 0 ; i <10 ; i++){
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(20);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(atomicIntegerFieldUpdater.getAndIncrement(person));
            });
            thread.start();
        }

    }
}

class Person  {
    volatile  int age = 1;
}