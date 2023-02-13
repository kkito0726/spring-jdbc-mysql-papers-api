package com.ken.papersapi.controllers;

import com.ken.papersapi.dtos.UpdatePaperDto;
import com.ken.papersapi.models.Paper;
import com.ken.papersapi.services.PaperService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/{paperId}")
  public ResponseEntity<Paper> getPaper(@PathVariable UUID paperId) {
    Paper paper = paperService.findByPaperId(paperId);

    return ResponseEntity.ok(paper);
  }

  @PatchMapping("/{paperId}")
  public ResponseEntity<Object> updatePaper(
    @RequestBody UpdatePaperDto dto,
    @PathVariable UUID paperId
  ) {
    paperService.updateByPaperId(dto, paperId);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{paperId}")
  public ResponseEntity<Object> deletePaper(@PathVariable UUID paperId) {
    paperService.delete(paperId);

    return ResponseEntity.noContent().build();
  }

  @PostMapping("")
  public ResponseEntity<Paper> createPaper(@RequestBody Paper paper) {
    Paper newPaper = paperService.save(paper);

    return ResponseEntity.ok(newPaper);
  }
}
