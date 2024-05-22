package com.aldo.thymeleaf.layout.thymeleafLayout.Controllers;

import com.aldo.thymeleaf.layout.thymeleafLayout.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CreateController {

    List<Task> task = new ArrayList<>();

    @GetMapping("/create")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("create");
        mv.addObject("task", new Task());
        return mv;
    }

    @PostMapping("/create")
    public String create(Task task){
        if(task.getId() != null){
            Task findTask = this.task.stream()
                    .filter(taskItem -> task.getId().equals(taskItem.getId())).findFirst().get();
            this.task.set((this.task.indexOf(findTask)), task);
        }else{
            Long id = this.task.size() + 1L;
            this.task.add(new Task(id, task.getName(), task.getDate()));
        }
        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("tasks", task);
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("create");
        Task findTask = task.stream().filter(task1 -> id.equals(task1.getId())).findFirst().get();
        mv.addObject("task", findTask);
        return mv;
    }

    @GetMapping("/pdf")
    public String gerarPdf(){
        return "pdf";
    }
}
