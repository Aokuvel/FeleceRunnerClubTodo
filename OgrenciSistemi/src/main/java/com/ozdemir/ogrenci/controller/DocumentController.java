package com.ozdemir.ogrenci.controller;

import com.ozdemir.ogrenci.dao.UserDAO;
import com.ozdemir.ogrenci.dto.DocumentDto;
import com.ozdemir.ogrenci.entity.OrderEntity;
import com.ozdemir.ogrenci.entity.DocumentEntity;
import com.ozdemir.ogrenci.dao.DocumentDAO;
import com.ozdemir.ogrenci.repositories.OrderRepository;
import com.ozdemir.ogrenci.repositories.DocumentRepository;
import com.ozdemir.ogrenci.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/")
public class DocumentController {

    @Autowired
    private DocumentDAO documentDAO;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRepository userRepository;


    //@ResponseBody
    @Secured({"ADMIN"})
    @RequestMapping("/admin/document/add")
    public String addDocument(Model model)
    {
        DocumentEntity documentEntity=new DocumentEntity();
        model.addAttribute("document",documentEntity);
        return "/admin/document-form";
    }

    //@ResponseBody
    @Secured({"ADMIN"})
    @PostMapping("/admin/document/save")
    public String saveDocument(@ModelAttribute("document") DocumentEntity document)
    {
        documentRepository.save(document);
        return "redirect:/admin/home";
    }

    //@ResponseBody
    @Secured({"ADMIN"})
    @RequestMapping(value = "admin/orders/makeReady/{id}", method = RequestMethod.GET)
    public String makeReady(@PathVariable int id) {
        //@PathVariable PathVariable anotasyonu url de bulunan değişkenleri ilgili metodlara aktararak ilgili metodun işlemi yapmasını sağlamaktayız.
        // Bu değişkenler bir ya da birden fazla olabilmektedir.
        //örnek makeReady/1 makeReady/2 makeReady/3
        OrderEntity orderEntity = orderRepository.findByID(id);
        //UserEntity userEntity=userRepository.findByName()
        Date date = new Date();
        orderEntity.setUpdateTime(date);
        orderEntity.setStatus("ready to send");
        orderRepository.save(orderEntity);
        return "redirect:/admin/orderss";
    }

    //@ResponseBody
    @Secured({"ADMIN"})
    @RequestMapping( value = "admin/orders/deleteOrder/{id}", method = RequestMethod.GET)
    public ModelAndView deleteOrder(@PathVariable("id") int id){
        ModelAndView mv = new ModelAndView("redirect:/admin/orderss");
        orderRepository.deleteById(id);
        return mv;
    }

    //@ResponseBody
    @Secured({"ADMIN"})
    @RequestMapping("admin/orderss")
    public String showOrders(Model model, @AuthenticationPrincipal UserDetails currentUser){
        //@AuthenticationPrincipal ile user‘ın o an sisteme giriş yapmış kullanıcı olduğunu belirtiyor
        // ve bu user‘ı ana sayfada kullanacağımız model olarak döndürüyoruz.

        //şuan giren userı al
        userDAO.findUserByEmail(currentUser.getUsername());

        model.addAttribute("orders", orderRepository.findAll());
        //şuan girmiş olan user ı modele ekliyor ve onun siparişlerini gösteriyor
        model.addAttribute("documents",userDAO.findUserByEmail(currentUser.getUsername()).getOrderEntities());
        return "/admin/orderss";

    }

    //@ResponseBody
    @Secured({"ADMIN","USER"})
    @RequestMapping("/user/document/list")
    public String listDocument(Model model){
        List<DocumentDto> documentList =  documentDAO.findAll();

        model.addAttribute("document", documentRepository.findAll());
        return "/user/document-list";
    }

    //@ResponseBody
    @Secured({"ADMIN"})
    @RequestMapping("/admin/document/list")
    public String showDocument(Model model)
    {
        model.addAttribute("dokumanlar",documentDAO.findAll());
        return "/admin/document-list";
    }
}
