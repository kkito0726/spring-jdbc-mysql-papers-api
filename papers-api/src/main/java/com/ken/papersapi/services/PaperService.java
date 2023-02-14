package com.ken.papersapi.services;

import com.ken.papersapi.dtos.UpdatePaperDto;
import com.ken.papersapi.models.Paper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class PaperService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Paper> findAll() {
    final String query = "SELECT * from papers WHERE deleted_at is NULL";
    List<Paper> papers = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(Paper.class)
    );

    return papers;
  }

  public Paper findByPaperId(UUID paperId) {
    final String query = "SELECT * from papers WHERE paper_id=?";
    List<Paper> papers = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(Paper.class),
      paperId.toString()
    );

    return papers.get(0);
  }

  public List<Paper> findByUserId(UUID userId) {
    final String query = "SELECT * from papers WHERE user_id=?";
    List<Paper> papers = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(Paper.class),
      userId.toString()
    );

    return papers;
  }

  public void updateByPaperId(UpdatePaperDto paper, UUID paperId) {
    final String query =
      "UPDATE papers SET title=?, comment=? WHERE paper_id=?";
    jdbcTemplate.update(
      query,
      paper.getTitle(),
      paper.getComment(),
      paperId.toString()
    );
  }

  public void delete(UUID paperId) {
    final String query = "UPDATE papers SET deleted_at=? WHERE paper_id=?";
    jdbcTemplate.update(query, LocalDateTime.now(), paperId.toString());
  }

  public Paper save(Paper paper) {
    onCreatedPaper(paper);
    SqlParameterSource param = new BeanPropertySqlParameterSource(paper);
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
      .withTableName("papers")
      .usingGeneratedKeyColumns("id");

    Number key = insert.executeAndReturnKey(param);
    paper.setNo(key.hashCode());

    return paper;
  }

  public void onCreatedPaper(Paper paper) {
    paper.setPaperId(UUID.randomUUID());
    paper.setCreatedAt(LocalDateTime.now());
  }
}
