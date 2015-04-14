package com.innova4b.web;
import com.innova4b.Ruta;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rutas")
@Controller
@RooWebScaffold(path = "rutas", formBackingObject = Ruta.class)
public class RutaController {
}
