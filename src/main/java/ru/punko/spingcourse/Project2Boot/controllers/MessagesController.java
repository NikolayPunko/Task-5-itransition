package ru.punko.spingcourse.Project2Boot.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.punko.spingcourse.Project2Boot.models.Message;
import ru.punko.spingcourse.Project2Boot.models.User;
import ru.punko.spingcourse.Project2Boot.services.MessagesService;
import ru.punko.spingcourse.Project2Boot.services.UsersService;


@Controller
@RequestMapping("/messages")
public class MessagesController {

    private final UsersService usersService;
    private final MessagesService messagesService;

    public MessagesController(UsersService usersService, MessagesService messagesService) {
        this.usersService = usersService;
        this.messagesService = messagesService;
    }

    @GetMapping("/send")
    public String send(Model model, @ModelAttribute("message") Message message, @CookieValue(value = "log") String log) {
        model.addAttribute("people", usersService.findAll());
        model.addAttribute("new_message", messagesService.findByRecipientNewMessage(log));
        model.addAttribute("log", log);
        return "messages/send";
    }


    @GetMapping("/chats")
    public String pageChats(Model model, @ModelAttribute("chats") Message message, @CookieValue(value = "log") String log) {
        model.addAttribute("chats", messagesService.findByRecipient(log));
        model.addAttribute("new_message", messagesService.findByRecipientNewMessage(log));
        model.addAttribute("log", log);
        return "messages/chats";
    }

    @GetMapping()
    public String start(@ModelAttribute("user") User user) {
        return "messages/start";
    }

    @PostMapping()
    public String create( @ModelAttribute("user") User user, HttpServletResponse response) {
        setCookie(user.getName(),response);
        if(usersService.getUserByName(user.getName()).isEmpty()){
            usersService.save(user);
        }
        return "redirect:/messages/send";
    }

    @PostMapping("/send")
    public String sendMessage(@ModelAttribute("message") Message message, @CookieValue(value = "log") String log) {
        message.setSender(log);
        messagesService.save(message);
        return "redirect:/messages/send";
    }

    public void setCookie(String name, HttpServletResponse response){
        Cookie cookie = new Cookie("log", name);
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        response.setContentType("text/plain");
    }

}
