package xby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xby.domain.Product;
import xby.service.IProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @RequestMapping("/findAll")
    public ModelAndView testFindAll(ModelAndView mv)
    {
        List<Product> products = productService.findAll();

        mv.addObject("productList",products);
        mv.setViewName("product-list1");
        return mv;
    }
    @RequestMapping("/save")
    public String testSave(Product product)
    {
        System.out.println(product.toString());
        productService.save(product);
        return "redirect:findAll";
    }
}
