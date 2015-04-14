package com.innova4b.web;
import com.innova4b.Billete;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/billetes")
@Controller
@RooWebScaffold(path = "billetes", formBackingObject = Billete.class)
public class BilleteController {
}
