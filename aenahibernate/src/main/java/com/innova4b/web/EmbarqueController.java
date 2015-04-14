package com.innova4b.web;
import com.innova4b.Embarque;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/embarques")
@Controller
@RooWebScaffold(path = "embarques", formBackingObject = Embarque.class)
public class EmbarqueController {
}
