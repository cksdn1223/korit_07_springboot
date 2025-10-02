package com.example.todolist.controller;

import com.example.todolist.dto.AppUserRecord;
import com.example.todolist.dto.TodoRequestRecord;
import com.example.todolist.dto.TodoUpdateRecord;
import com.example.todolist.service.AppUserService;
import com.example.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoRequestRecord> find(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/todos/save")
    public ResponseEntity<TodoRequestRecord> addTodo(@RequestBody TodoRequestRecord content, @AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(todoService.addTodo(content.content(), userDetails.getUsername()),HttpStatus.OK);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }

    @PatchMapping("/todos/content/{id}")
    public ResponseEntity<TodoUpdateRecord> updateTodoContent(@PathVariable Long id, @RequestBody TodoUpdateRecord content){
        return todoService.updateTodoContent(id,content.content())
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/todos/{id}")
    public ResponseEntity<TodoRequestRecord> updateTodoStatus(@PathVariable Long id) {
        return todoService.updateTodoStatus(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/todos/completed")
    public ResponseEntity<AppUserRecord> clearCompletedTodos(@AuthenticationPrincipal UserDetails userDetails) {
        return todoService.clearCompletedTodos(userDetails);
    }
}
