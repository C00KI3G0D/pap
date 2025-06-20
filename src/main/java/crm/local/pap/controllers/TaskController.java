package crm.local.pap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.local.pap.services.TaskService;
import crm.local.pap.dtos.TaskDisplayDTO;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping({"" , "/"})
    public ResponseEntity<List<TaskDisplayDTO>> getTasks() {
        return new ResponseEntity<>(this.taskService.getTasksForDisplay(), HttpStatus.OK);
    }
}