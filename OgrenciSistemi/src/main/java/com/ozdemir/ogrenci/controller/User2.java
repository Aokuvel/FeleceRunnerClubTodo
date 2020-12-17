package com.ozdemir.ogrenci.controller;

import com.ozdemir.ogrenci.dao.Imp.UserDAOImp;
import com.ozdemir.ogrenci.dao.DocumentDAO;
import com.ozdemir.ogrenci.dto.DocumentDto;
import com.ozdemir.ogrenci.dto.UserDto;
import com.ozdemir.ogrenci.entity.OrderEntity;
import com.ozdemir.ogrenci.entity.UserEntity;
import com.ozdemir.ogrenci.repositories.OrderRepository;
import com.ozdemir.ogrenci.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/user")
public class User2 {

    @Autowired
    DocumentDAO documentDAO;

    @Autowired
    private UserDAOImp userDao;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    UserEntity userEntity=new UserEntity();

    // @ResponseBody
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        //burası kimlik doğrulama alıyor
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userDao.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + userEntity.getFirstName() + " " + userEntity.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/home");
        return modelAndView;
    }

    //@ResponseBody
    @RequestMapping("/order/list")
    public String orderDocumentList(Model model){
        model.addAttribute("documents", documentDAO.findAll());
        return "/user/order";
    }

    //@ResponseBody
    @RequestMapping(value = "/order/giveOrder", method = RequestMethod.POST)
    public String giveOrder(@RequestParam("orders") int[] documentList, @AuthenticationPrincipal UserDetails currentUser){
        //@AuthenticationPrincipal ile user‘ın o an sisteme giriş yapmış kullanıcı olduğunu belirtiyor.
        List<DocumentDto> documents = new ArrayList<DocumentDto>();
        //hashset in mantığı arrayle aynı ama hashsetde aynı elamanı iki defa ekliyemiyoruz
        //çünkü aynı orderı birden fazla user sipariş edebilir
        Set<OrderEntity> orderEntities = new HashSet<OrderEntity>();
        userEntity = userRepository.findByEmail(currentUser.getUsername());
        Date date = new Date();

        for(int i=0; i<documentList.length; i++){
            OrderEntity orderEntity = new OrderEntity();
            documents.add(documentDAO.findByID(documentList[i]));
            orderEntity.setStatus("preparing");
            orderEntity.setDocumentName(documents.get(i).getDocumentName());
            orderEntity.setUserID(userEntity.getId());
            orderEntity.setStartTime(date);
            orderEntities.add(orderEntity);
        }

        userEntity.setOrderEntity(orderEntities);
        userDao.update(userEntity);
        return "redirect:/user/home";
    }

    //@ResponseBody
    @RequestMapping("/order/status")
    public String orderStatus(Model model, @AuthenticationPrincipal UserDetails currentUser){
        // String name=String.valueOf(userDao.findUserByEmail(currentUser.getUsername()));
        int id = userDao.findUserByEmail(currentUser.getUsername()).getId();
        model.addAttribute("orders", orderRepository.findByUser(id));
        // model.addAttribute("orders",String.valueOf(orderRepository.findByName(name)));
        return "/user/order-status";
    }

    // @ResponseBody
    @RequestMapping( value = "/orders/completeOrder/{id}", method = RequestMethod.GET)
    public ModelAndView completeOrder(@PathVariable("id") int id){
        ModelAndView mv = new ModelAndView("redirect:/user/order/status");//requestmapping yolu
        orderRepository.deleteById(id);
        return mv;
    }

}
