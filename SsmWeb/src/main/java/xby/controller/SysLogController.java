package xby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xby.domain.SysLog;
import xby.service.ISysLogService;

import java.util.List;
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;
    @RequestMapping("/findAll")
    public ModelAndView findAll()
    {
        ModelAndView mv = new ModelAndView();
        List<SysLog> logs = sysLogService.findAll();
        mv.addObject("sysLogs",logs);
        mv.setViewName("syslog-list");
        return mv;
    }
}
