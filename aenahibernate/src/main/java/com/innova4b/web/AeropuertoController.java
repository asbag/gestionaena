package com.innova4b.web;
import com.innova4b.Aeropuerto;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/aeropuertoes")
@Controller
@RooWebScaffold(path = "aeropuertoes", formBackingObject = Aeropuerto.class)
public class AeropuertoController {
}
