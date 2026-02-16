package com.example.taskmanager.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Task.Status;
import com.example.taskmanager.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task dto, Authentication auth) {
        dto.setOwnerUsername(auth.getName());
        Task created = service.createTask(dto);
        return ResponseEntity.created(URI.create("/api/tasks/" + created.getId())).body(created);
    }

    @GetMapping
    public List<Task> listTasks(Authentication auth) {
        return service.getTasksForUser(auth.getName());
    }

    @PutMapping("/{id}")
    public Task updateStatus(@PathVariable Long id, @RequestParam Status status, Authentication auth) {
        return service.updateStatus(id, status, auth.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id, Authentication auth) {
        service.deleteTask(id, auth.getName());
        return ResponseEntity.noContent().build();
    }
}
