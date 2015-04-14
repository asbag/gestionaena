package com.innova4b.web;
import com.innova4b.Compania;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/companias")
@Controller
@RooWebScaffold(path = "companias", formBackingObject = Compania.class)
public class CompaniaController {
}
