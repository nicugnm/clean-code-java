package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the variation required for CR323 without adding a boolean parameter
 */
public class BooleanParameters {

   public static void main(String[] args) {
      // The big method is called from various foreign places in the codebase
      bigUglyMethod(new Task(5), 1, false);
      bigUglyMethod(new Task(4), 2, false);
      bigUglyMethod(new Task(3), 3, false);
      bigUglyMethod(new Task(2), 4, false);
      bigUglyMethod(new Task(1), 5, false);

      // TODO From my use-case #323, I call it too, to do more within:
      Task task = new Task(1);
      bigUglyMethod(task, 2, true);

   }

   static void bigUglyMethod(Task task, int b, boolean cr323) {
      System.out.println("Complex Logic 1 " + task + " and " + b);
      System.out.println("Complex Logic 2 " + task);
      System.out.println("Complex Logic 3 " + task);

      if (cr323) {
         System.out.println("Logic just for CR323 : " + task);
      }

      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
      System.out.println("More Complex Logic " + b);
   }


   // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

   // Lord gave us tests!
   public void bossLevel(boolean fluff2, List<Task> tasks, boolean cr323) {
      int index = 0; // TODO move closer to usages
      int taskCount = tasks.size();
      System.out.println("Logic1");
      List<Integer> taskIds = new ArrayList<>();
      System.out.println("Logic2");
      if (fluff2) {
         System.out.println("Logic3");
         for (Task task : tasks) {
            System.out.println("Logic4: Validate " + task);
            task.setRunning();

            taskIds.add(task.getId());

            if (cr323) { // TODO remove the boolean
               System.out.println("My Logic: " + task);
            }

            index++;
            System.out.println("Logic5 index=" + index + " on running=" + task.isRunning());
         }
         System.out.println("Logic6 " + taskCount);
         System.out.println("Task Ids: " + taskIds);
      } else {
         System.out.println("Logic7 " + tasks);
      }
      System.out.println("Logic7");
   }

}


class Task {
   private final int id;
   private boolean running;

   Task(int id) {
      this.id = id;
   }

   public void setRunning() {
      running = true;
   }

   public boolean isRunning() {
      return running;
   }

   public int getId() {
      return id;
   }

   @Override
   public String toString() {
      return "Task{" + "id=" + id + ", running=" + running + '}';
   }
}