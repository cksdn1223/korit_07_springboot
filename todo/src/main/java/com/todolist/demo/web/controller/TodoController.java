package com.todolist.demo.web.controller;

import com.todolist.demo.domain.Todo;
import com.todolist.demo.dto.TodoRequestDto;
import com.todolist.demo.dto.TodoRequestRecord;
import com.todolist.demo.web.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(todoService.getTodos());
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody TodoRequestRecord todoRequestRecord) {
        return ResponseEntity.ok(todoService.createTodo(todoRequestRecord));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, TodoRequestDto updateDto) {
        return ResponseEntity.ok(todoService.updateTodoContent(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> toggleTodoStatus(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.toggleTodoStatus(id));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCompletedTodos() {
        todoService.clearCompletedTodos();
        return ResponseEntity.noContent().build();
    }
}
