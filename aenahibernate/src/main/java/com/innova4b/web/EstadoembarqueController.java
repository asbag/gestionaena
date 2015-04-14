package com.innova4b.web;
import com.innova4b.Estadoembarque;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/estadoembarques")
@Controller
@RooWebScaffold(path = "estadoembarques", formBackingObject = Estadoembarque.class)
public class EstadoembarqueController {
}
