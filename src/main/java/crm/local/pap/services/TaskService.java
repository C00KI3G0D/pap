package crm.local.pap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.local.pap.models.Task;
import crm.local.pap.repositories.TasksRepository;

@Service
public class TaskService {

    @Autowired
    private TasksRepository tasksRepository;

    public List<Task> getTasks() {
        return tasksRepository.findAll();
    }

}
