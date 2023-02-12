package com.ken.papersapi.controllers;

import com.ken.papersapi.models.Paper;
import com.ken.papersapi.services.PaperService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PaperController.BASE_URL)
@RequiredArgsConstructor
public class PaperController {

  private static final String BASE_URL = "/api/v1/papers";
  private final PaperService paperService;

  @GetMapping("")
  public ResponseEntity<List<Paper>> getPapers() {
    List<Paper> papers = paperService.findAll();

    return ResponseEntity.ok(papers);
  }

  @PostMapping("")
  public ResponseEntity<Paper> createPaper(@RequestBody Paper paper) {
    Paper newPaper = paperService.save(paper);

    return ResponseEntity.ok(newPaper);
  }
}
