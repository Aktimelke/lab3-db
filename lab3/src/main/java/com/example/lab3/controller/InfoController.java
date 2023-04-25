package com.example.lab3.controller;

import com.example.lab3.Connect;
import com.example.lab3.model.Info;
import com.example.lab3.service.InfoService;
import jakarta.jws.WebParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class InfoController {

    private final InfoService infoService;

    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<Info> info = infoService.findAll();
        model.addAttribute("info", info);
        return "Info-list";
    }

    @GetMapping("/info-create")
    public String createForm(Info info) {
        return "info-create";
    }

    @PostMapping("/info-create")
    public String createInfo(Info info) {
        infoService.saveInfo(info);
        time(info.getId());
        return "redirect:/";
    }

    @GetMapping("info-delete/{id}")
    public String deleteInfo(@PathVariable("id") Long id) {
        infoService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("info-update/{id}")
    public String infoUpdateForm(@PathVariable("id") Long id, Model model) {
        Info info = infoService.findById(id);
        model.addAttribute("info", info);
        return "info-update";
    }

    @PostMapping("/info-update")
    public String infoUpdate(Info info) {
        infoService.saveInfo(info);
        time(info.getId());
        return "redirect:/";
    }

    private void time(Long id) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a dd-MM-yyyy");
        String timeNow = sdf.format(new Date());
        Connection connection = null;
        Statement statement = null;
        Connect c = new Connect();
        connection = c.get_Connection();
        try {
            String query = String.format("UPDATE info SET addingdate ='%s' WHERE id ='%d'", timeNow, id);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}