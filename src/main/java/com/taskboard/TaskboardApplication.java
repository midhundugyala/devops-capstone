package com.taskboard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@SpringBootApplication
@RestController
public class TaskboardApplication {
  List<Map<String,String>> tasks = new ArrayList<>();
  public static void main(String[] args){ SpringApplication.run(TaskboardApplication.class,args); }
  @GetMapping("/api/info") public Map<String,String> info(){ Map<String,String> m=new HashMap<>(); m.put("version", System.getenv().getOrDefault("APP_VERSION","v1.0")); m.put("environment", System.getenv().getOrDefault("APP_ENVIRONMENT","dev")); m.put("hostname", System.getenv().getOrDefault("HOSTNAME","local")); return m; }
  @GetMapping("/api/tasks") public List<Map<String,String>> list(){ return tasks; }
  @PostMapping("/api/tasks") public Map<String,String> add(@RequestBody Map<String,String> t){ tasks.add(t); return t; }
  @GetMapping("/chaos/error") public String error(){ throw new RuntimeException("Chaos error"); }
  @GetMapping("/chaos/slow") public String slow(@RequestParam(defaultValue="1000") int ms) throws Exception { Thread.sleep(ms); return "slow ok"; }
  @GetMapping("/chaos/crash") public String crash(){ System.exit(1); return "crash"; }
}
