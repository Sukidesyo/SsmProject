package xby.controller;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xby.domain.Order;
import xby.service.IOrderService;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    //不分页
//    @RequestMapping("/findAll")
//    public ModelAndView findAll()
//    {
//        ModelAndView mv=new ModelAndView();
//
//        List<Order> orders = orderService.findAll();
//
//        mv.addObject("ordersList",orders);
//
//        mv.setViewName("orders-list");
//        return mv;
//    }

    @RequestMapping("/findAll")
    public ModelAndView findAllPageInfo(@RequestParam(name = "page",required = true,defaultValue = "1")int page,@RequestParam(name="size",required = true,defaultValue = "4")int size)
    {
        ModelAndView mv=new ModelAndView();

        List<Order> orders = orderService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);

        mv.setViewName("orders-page-list");
        return mv;
    }
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(value = "id",required =true)String id)
    {

        ModelAndView mv=new ModelAndView();

        Order order = orderService.findById(id);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }
}
