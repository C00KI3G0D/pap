package crm.local.pap.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.local.pap.dtos.TaskDTO;
import crm.local.pap.dtos.TaskDisplayDTO;
import crm.local.pap.models.Task;
import crm.local.pap.services.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<TaskDisplayDTO>> getTasks() {
        return new ResponseEntity<>(this.taskService.getTasksForDisplay(), HttpStatus.OK);
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO, Principal principal) { 

        Task createdTask = taskService.createTask(taskDTO, principal.getName()); 
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
}