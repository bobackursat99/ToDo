package com.Todo.ToDo_v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    // Get all ToDos
    @GetMapping
    public ResponseEntity<List<ToDo>> getAllToDos() {
        List<ToDo> todos = toDoService.getAllToDos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    // Get a ToDo by ID
    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable Long id) {
        Optional<ToDo> todo = toDoService.getToDoById(id);
        return todo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new ToDo
    @PostMapping
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo todo) {
        ToDo createdToDo = toDoService.createOrUpdateToDo(todo);
        return new ResponseEntity<>(createdToDo, HttpStatus.CREATED);
    }

    // Update an existing ToDo
    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable Long id, @RequestBody ToDo todo) {
        ToDo updatedToDo = toDoService.createOrUpdateToDo(todo);
        return new ResponseEntity<>(updatedToDo, HttpStatus.OK);
    }

    // Delete a ToDo by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
