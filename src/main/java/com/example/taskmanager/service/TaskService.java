package com.example.taskmanager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Task.Status;
import com.example.taskmanager.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public List<Task> getTasksForUser(String username) {
        return repository.findByOwnerUsername(username);
    }

    public Task updateStatus(Long id, Status status, String username) {
        Task t = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        if (!username.equals(t.getOwnerUsername())) throw new UnauthorizedException("Not owner");
        t.setStatus(status);
        Task saved = repository.save(t);
        log.info("Task {} status updated to {} by {}", id, status, username);
        return saved;
    }

    public void deleteTask(Long id, String username) {
        Task t = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        if (!username.equals(t.getOwnerUsername())) throw new UnauthorizedException("Not owner");
        repository.delete(t);
        log.info("Task {} deleted by {}", id, username);
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String msg) { super(msg); }
    }

    public static class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String msg) { super(msg); }
    }
}
