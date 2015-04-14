package com.innova4b.web;
import com.innova4b.Pasajero;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pasajeroes")
@Controller
@RooWebScaffold(path = "pasajeroes", formBackingObject = Pasajero.class)
public class PasajeroController {
}
