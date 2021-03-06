// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.innova4b.web;

import com.innova4b.Aeropuerto;
import com.innova4b.Embarque;
import com.innova4b.EmbarquePK;
import com.innova4b.web.EmbarqueController;
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

privileged aspect EmbarqueController_Roo_Controller {
    
    private ConversionService EmbarqueController.conversionService;
    
    @Autowired
    public EmbarqueController.new(ConversionService conversionService) {
        super();
        this.conversionService = conversionService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String EmbarqueController.create(@Valid Embarque embarque, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, embarque);
            return "embarques/create";
        }
        uiModel.asMap().clear();
        embarque.persist();
        return "redirect:/embarques/" + encodeUrlPathSegment(conversionService.convert(embarque.getId(), String.class), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String EmbarqueController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Embarque());
        return "embarques/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String EmbarqueController.show(@PathVariable("id") EmbarquePK id, Model uiModel) {
        uiModel.addAttribute("embarque", Embarque.findEmbarque(id));
        uiModel.addAttribute("itemId", conversionService.convert(id, String.class));
        return "embarques/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String EmbarqueController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("embarques", Embarque.findEmbarqueEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Embarque.countEmbarques() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("embarques", Embarque.findAllEmbarques(sortFieldName, sortOrder));
        }
        return "embarques/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String EmbarqueController.update(@Valid Embarque embarque, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, embarque);
            return "embarques/update";
        }
        uiModel.asMap().clear();
        embarque.merge();
        return "redirect:/embarques/" + encodeUrlPathSegment(conversionService.convert(embarque.getId(), String.class), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String EmbarqueController.updateForm(@PathVariable("id") EmbarquePK id, Model uiModel) {
        populateEditForm(uiModel, Embarque.findEmbarque(id));
        return "embarques/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String EmbarqueController.delete(@PathVariable("id") EmbarquePK id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Embarque embarque = Embarque.findEmbarque(id);
        embarque.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/embarques";
    }
    
    void EmbarqueController.populateEditForm(Model uiModel, Embarque embarque) {
        uiModel.addAttribute("embarque", embarque);
        uiModel.addAttribute("aeropuertoes", Aeropuerto.findAllAeropuertoes());
    }
    
    String EmbarqueController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
