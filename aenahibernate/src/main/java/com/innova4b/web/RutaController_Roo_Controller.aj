// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b.web;

import com.innova4b.Aeropuerto;
import com.innova4b.Avion;
import com.innova4b.Compania;
import com.innova4b.Ruta;
import com.innova4b.RutaPK;
import com.innova4b.web.RutaController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect RutaController_Roo_Controller {
    
    private ConversionService RutaController.conversionService;
    
    @Autowired
    public RutaController.new(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String RutaController.create(@Valid Ruta ruta, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, ruta);
            return "rutas/create";
        }
        uiModel.asMap().clear();
        ruta.persist();
        return "redirect:/rutas/" + encodeUrlPathSegment(conversionService.convert(ruta.getId(), String.class), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String RutaController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Ruta());
        return "rutas/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String RutaController.show(@PathVariable("id") RutaPK id, Model uiModel) {
        uiModel.addAttribute("ruta", Ruta.findRuta(id));
        uiModel.addAttribute("itemId", conversionService.convert(id, String.class));
        return "rutas/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String RutaController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("rutas", Ruta.findRutaEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Ruta.countRutas() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("rutas", Ruta.findAllRutas(sortFieldName, sortOrder));
        }
        return "rutas/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String RutaController.update(@Valid Ruta ruta, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, ruta);
            return "rutas/update";
        }
        uiModel.asMap().clear();
        ruta.merge();
        return "redirect:/rutas/" + encodeUrlPathSegment(conversionService.convert(ruta.getId(), String.class), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String RutaController.updateForm(@PathVariable("id") RutaPK id, Model uiModel) {
        populateEditForm(uiModel, Ruta.findRuta(id));
        return "rutas/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String RutaController.delete(@PathVariable("id") RutaPK id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Ruta ruta = Ruta.findRuta(id);
        ruta.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/rutas";
    }
    
    void RutaController.populateEditForm(Model uiModel, Ruta ruta) {
        uiModel.addAttribute("ruta", ruta);
        uiModel.addAttribute("aeropuertoes", Aeropuerto.findAllAeropuertoes());
        uiModel.addAttribute("avions", Avion.findAllAvions());
        uiModel.addAttribute("companias", Compania.findAllCompanias());
    }
    
    String RutaController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
