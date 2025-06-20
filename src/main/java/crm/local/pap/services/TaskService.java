package crm.local.pap.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.local.pap.models.Task;
import crm.local.pap.repositories.TasksRepository;
import crm.local.pap.dtos.TaskDisplayDTO;

@Service

public class TaskService {

    @Autowired
    private TasksRepository tasksRepository;

    public List<TaskDisplayDTO> getTasksForDisplay() {
        return tasksRepository.findAll().stream().map(task -> {

            TaskDisplayDTO dto = new TaskDisplayDTO();
            dto.setTaskId(task.getTaskid().toString());
            dto.setTopic(task.getTopic());
            dto.setState(task.getState());

            if (task.getResponsible() != null) {
                dto.setClientName(task.getResponsible().getFirstName());
                dto.setResponsibleName(task.getResponsible().getFirstName() + " " + task.getResponsible().getLastName());
            } else {
                dto.setClientName("N/A");
                dto.setResponsibleName("N/A");
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return tasksRepository.findAll();
    }
}