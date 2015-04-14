package com.innova4b.web;
import com.innova4b.Avion;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/avions")
@Controller
@RooWebScaffold(path = "avions", formBackingObject = Avion.class)
public class AvionController {
}
