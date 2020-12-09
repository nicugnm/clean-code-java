package victor.training.cleancode;

import java.util.List;

public class BouleanParameters {
   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      bigUglyMethod(1, 5, false);
      bigUglyMethod(2, 4, false);
      bigUglyMethod(3, 3, false);
      bigUglyMethod(4, 2, false);
      bigUglyMethod(5, 1, false);

      // TODO From my use-case #323, I call it too, to do more within:
      bigUglyMethod(2, 1, true);

   }

   static void bigUglyMethod(int b, int a, boolean cr323) {
      System.out.println("Complex Logic 1 " + a + " and " + b);
      System.out.println("Complex Logic 2 " + a);
      System.out.println("Complex Logic 3 " + a);

      // functia asta e chema din zeci de locuri
      // dar, tre s-o chem si eu.
      // cand EU o chem, vreau ca ea AICI sa faca in plus :

      if (cr323) {
         System.out.println("Doar pentru CR323 " + a);
      }


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