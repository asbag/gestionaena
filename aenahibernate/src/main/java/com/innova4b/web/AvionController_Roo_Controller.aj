// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b.web;

import com.innova4b.Avion;
import com.innova4b.Billete;
import com.innova4b.Compania;
import com.innova4b.Estado;
import com.innova4b.Ruta;
import com.innova4b.web.AvionController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect AvionController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String AvionController.create(@Valid Avion avion, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, avion);
            return "avions/create";
        }
        uiModel.asMap().clear();
        avion.persist();
        return "redirect:/avions/" + encodeUrlPathSegment(avion.getIdAvion().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String AvionController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Avion());
        return "avions/create";
    }
    
    @RequestMapping(value = "/{idAvion}", produces = "text/html")
    public String AvionController.show(@PathVariable("idAvion") Integer idAvion, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("avion", Avion.findAvion(idAvion));
        uiModel.addAttribute("itemId", idAvion);
        return "avions/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String AvionController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("avions", Avion.findAvionEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Avion.countAvions() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("avions", Avion.findAllAvions(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "avions/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String AvionController.update(@Valid Avion avion, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, avion);
            return "avions/update";
        }
        uiModel.asMap().clear();
        avion.merge();
        return "redirect:/avions/" + encodeUrlPathSegment(avion.getIdAvion().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{idAvion}", params = "form", produces = "text/html")
    public String AvionController.updateForm(@PathVariable("idAvion") Integer idAvion, Model uiModel) {
        populateEditForm(uiModel, Avion.findAvion(idAvion));
        return "avions/update";
    }
    
    @RequestMapping(value = "/{idAvion}", method = RequestMethod.DELETE, produces = "text/html")
    public String AvionController.delete(@PathVariable("idAvion") Integer idAvion, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Avion avion = Avion.findAvion(idAvion);
        avion.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/avions";
    }
    
    void AvionController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("avion_horasalida_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("avion_horallegada_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("avion_caducidadlicencia_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void AvionController.populateEditForm(Model uiModel, Avion avion) {
        uiModel.addAttribute("avion", avion);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("billetes", Billete.findAllBilletes());
        uiModel.addAttribute("companias", Compania.findAllCompanias());
        uiModel.addAttribute("estadoes", Estado.findAllEstadoes());
        uiModel.addAttribute("rutas", Ruta.findAllRutas());
    }
    
    String AvionController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
