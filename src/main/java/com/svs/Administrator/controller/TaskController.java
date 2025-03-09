package com.svs.Administrator.controller;

import com.svs.Administrator.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    List<Task> taskList = new ArrayList<>();

    @GetMapping("/create")
    public String home(){
        return "create";
    }

    @PostMapping("/create")
    public String create(Task task){
        Long id = taskList.size() + 1L;
        System.out.println("A tarefa " + task.getName() + " foi postada na data " + task.getDate());
        taskList.add(new Task(id, task.getName(), task.getDate()));

        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("tasks", taskList);
        return mv;
    }
}
