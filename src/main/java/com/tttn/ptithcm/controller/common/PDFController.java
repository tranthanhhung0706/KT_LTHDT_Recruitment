package com.tttn.ptithcm.controller.common;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PDFController {


  @GetMapping("/view-pdf")
  public ResponseEntity<byte[]> viewPDF(@RequestParam(name="filename",required=true)String filename) throws IOException {
      Resource resource = new ClassPathResource("upload/" + filename);
      Path path = Paths.get(resource.getURI());
      byte[] pdfData = Files.readAllBytes(path);

      return ResponseEntity
              .ok()
              .contentType(MediaType.APPLICATION_PDF)
              .body(pdfData);
  }
}
