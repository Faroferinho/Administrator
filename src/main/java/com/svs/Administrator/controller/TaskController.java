package com.svs.Administrator.controller;

import com.svs.Administrator.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {

    List<Task> taskList = new ArrayList<>();

    @GetMapping("/create")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("create");
        mv.addObject("task", new Task());
        return mv;
    }

    @PostMapping("/create")
    public String create(Task task){

        if(task.getId() != null){
            Task taskToFind = taskList.stream().filter(taskItem -> task.getId().equals(taskItem.getId())).findFirst().get();
            taskList.set(taskList.indexOf(taskToFind), task);
        }else{
            Long id = taskList.size() + 1L;
            System.out.println("A tarefa " + task.getName() + " foi postada na data " + task.getDate());
            taskList.add(new Task(id, task.getName(), task.getDate()));
        }

        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("tasks", taskList);
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("create");

        Task taskFind = taskList.stream().filter(task -> id.equals(task.getId())).findFirst().get();
        mv.addObject("task", taskFind);
        return mv;
    }
}
