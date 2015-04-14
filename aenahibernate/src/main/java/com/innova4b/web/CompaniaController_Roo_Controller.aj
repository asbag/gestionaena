// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b.web;

import com.innova4b.Avion;
import com.innova4b.Compania;
import com.innova4b.Ruta;
import com.innova4b.web.CompaniaController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect CompaniaController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String CompaniaController.create(@Valid Compania compania, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, compania);
            return "companias/create";
        }
        uiModel.asMap().clear();
        compania.persist();
        return "redirect:/companias/" + encodeUrlPathSegment(compania.getIdCompania().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String CompaniaController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Compania());
        return "companias/create";
    }
    
    @RequestMapping(value = "/{idCompania}", produces = "text/html")
    public String CompaniaController.show(@PathVariable("idCompania") Integer idCompania, Model uiModel) {
        uiModel.addAttribute("compania", Compania.findCompania(idCompania));
        uiModel.addAttribute("itemId", idCompania);
        return "companias/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String CompaniaController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("companias", Compania.findCompaniaEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Compania.countCompanias() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("companias", Compania.findAllCompanias(sortFieldName, sortOrder));
        }
        return "companias/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String CompaniaController.update(@Valid Compania compania, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, compania);
            return "companias/update";
        }
        uiModel.asMap().clear();
        compania.merge();
        return "redirect:/companias/" + encodeUrlPathSegment(compania.getIdCompania().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{idCompania}", params = "form", produces = "text/html")
    public String CompaniaController.updateForm(@PathVariable("idCompania") Integer idCompania, Model uiModel) {
        populateEditForm(uiModel, Compania.findCompania(idCompania));
        return "companias/update";
    }
    
    @RequestMapping(value = "/{idCompania}", method = RequestMethod.DELETE, produces = "text/html")
    public String CompaniaController.delete(@PathVariable("idCompania") Integer idCompania, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Compania compania = Compania.findCompania(idCompania);
        compania.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/companias";
    }
    
    void CompaniaController.populateEditForm(Model uiModel, Compania compania) {
        uiModel.addAttribute("compania", compania);
        uiModel.addAttribute("avions", Avion.findAllAvions());
        uiModel.addAttribute("rutas", Ruta.findAllRutas());
    }
    
    String CompaniaController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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