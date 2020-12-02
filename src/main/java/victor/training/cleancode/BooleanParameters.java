package victor.training.cleancode;

import java.util.List;

public class BooleanParameters {
   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      bigUglyMethod(5, 1);
      bigUglyMethod(4, 2);
      bigUglyMethod(3, 3);
      bigUglyMethod(2, 4);
      bigUglyMethod(1, 5);

      // TODO From my use-case #323, I call it too, to do more within:
      bigUglyMethod323(1, 2);


      // TODO From my use-case #457, I call it too, to do more within:
      bigUglyMethod457(1, 2);
//      BigUgly bigUgly = new BigUglyDefault();
      BigUgly bigUgly = new BigUglyDefaultCR457(new BigUglyDefault());
      // la apel, trebuie sa te prinzi pe ce instanta chemi functia; iti ascunde ce chemi de fapt. Asta poate fi BINE sau RAU
      bigUgly.f(1, 2);

   }
   interface BigUgly {
      void f(int a, int b);
   }
   static class BigUglyDefaultCR457 implements BigUgly { // decorator design pattern
      private final BigUgly delegate;
      BigUglyDefaultCR457(BigUgly delegate) {
         this.delegate = delegate;
      }
      @Override
      public void f(int a, int b) {
         System.out.println("Ceva doar pentru cazul CR457 INAINTE");

         delegate.f(a, b);
         System.out.println("Ceva doar pentru cazul CR457 DUPA");
      }
   }
   static class BigUglyDefault implements BigUgly{
      public void f(int a, int b) {
         // logica mare
      }
   }

   static void bigUglyMethod457(int a, int b) {
      System.out.println("Ceva doar pentru cazul CR457");
      bigUglyMethod(a, b);
   }
   static void bigUglyMethod(int a, int b) {
      beforeLogic(a, b);
      afterLogic(b);
   }
   static void bigUglyMethod323(int a, int b) {
      beforeLogic(a, b);
      System.out.println("Ceva doar pentru mine, pe UC 323");
      afterLogic(b);
   }

   private static void beforeLogic(int a, int b) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);
   }

   private static void afterLogic(int b) {
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   public void bossLevel(boolean stuff, boolean fluff, List<Task> tasks) {
      int index = 0; // TODO move closer to usages
      int j = tasks.size();
      System.out.println("Logic1");
      if (stuff) {
         System.out.println("Logic2");
         if (fluff) {
            System.out.println("Logic3");
            for (Task task : tasks) {
               System.out.println("Logic4: Validate " + task);
               task.start();

               // TODO When **I** call this method, I want this to run HERE, too:
               // System.out.println("My Logic: " + task);

               index++;
               System.out.println("Logic5 " + index + " on " + task.isRunning());
            }
            System.out.println("Logic6 " + j);
         } else {
            System.out.println("Logic7 " + tasks);
         }
      }
      System.out.println("Logic7");
   }

}


class Task {
   private boolean running;

   public void start() {
      running = true;
   }

   public boolean isRunning() {
      return running;
   }
}