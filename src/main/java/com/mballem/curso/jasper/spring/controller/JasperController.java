package com.mballem.curso.jasper.spring.controller;


import com.mballem.curso.jasper.spring.Service.JasperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class JasperController {

    @Autowired
    JasperService jasperService;


    @GetMapping("/relatorio/pdf/jr1")
    public void exibirRelatorio01(@RequestParam("code") String code,
                                  @RequestParam("acao") String acao,
                                  HttpServletResponse response) throws IOException {

        byte[] bytes = jasperService.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        if(acao.equals("v")) {
            response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");
        } else {
            response.setHeader("Content-disposition", "attachment; filename=relatorio.pdf");
        }

        response.getOutputStream().write(bytes);
    }

}
