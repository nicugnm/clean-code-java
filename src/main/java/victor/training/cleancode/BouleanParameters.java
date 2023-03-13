package victor.training.cleancode;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

class SomeController {
  SomeService someService;

  @GetMapping("blue/{storeId}")
  public void blueEndpoint(@PathVariable int storeId, @RequestBody Task task) {
    someService.blueMethod(storeId, task);
  }

  @GetMapping("red/{storeId}")
  public void redEndpoint(@PathVariable int storeId, @RequestBody Task task) {
    someService.redMethod(storeId, task);
  }
}

class SomeService {
  public void blueMethod(int id, Task task) {
    BouleanParameters.bigUglyMethod(id, task, false);
  }

  public void greenMethod(int id, Task task) {
    BouleanParameters.bigUglyMethod(id, task, false);
  }

  public void yellowMethod(int id, Task task) {
    BouleanParameters.bigUglyMethod(id, task, false);
  }

  public void redMethod(int id, Task task) {
    BouleanParameters.bigUglyMethod(id, task, false);
  }
}


class MyService {
  public void useCase323(int id, Task task) {
    // TODO The shared called method must execute logic specific for my use-case #323
    BouleanParameters.bigUglyMethod(id, task, true);
  }
}

public class BouleanParameters {

  // Note: this method might be called from multiple places in the codebase ...
  static void bigUglyMethod(int storeId, Task task, boolean nlUser) {
    System.out.println("Cow Logic 1 " + task + " and " + storeId);
    System.out.println("Cow Logic 2 " + task);
    System.out.println("Cow Logic 3 " + task);

    if (nlUser) {
      System.out.println("Logic just for NL User : " + task);
    }

    System.out.println("Donkey Logic 1 " + storeId);
    System.out.println("Donkey Logic 2 " + storeId);
    System.out.println("Donkey Logic 3 " + storeId);
  }


  // ============== "BOSS" LEVEL: Deeply nested functions are a lot harder to break down =================

  // Lord gave us tests! 👌 TODO run tests
  public void bossLevel(boolean fluff, List<Task> tasks, boolean cr323) {
    int index = 0; // TODO move closer to usages in a safe way
    int j = tasks.size();
    System.out.println("Logic1");
    List<Integer> taskIds = new ArrayList<>();
    if (fluff) {
      System.out.println("Logic3");
      for (Task task : tasks) {
        System.out.println("Validate " + task);
        task.setStarted(true);


        taskIds.add(task.getId());

        if (cr323) { // TODO remove the boolean
          System.out.println("My Logic: " + task);
        }

        index++;
        System.out.println("Audit task #" + index + ": " + task);
      }
      System.out.println("Logic6 " + j);
      System.out.println("Task Ids: " + taskIds);
    } else {
      System.out.println("Logic7 on fluff=false " + tasks);
    }
    System.out.println("Logic8");
  }

}


@Data
class Task {
  private final int id;
  private boolean started;
}