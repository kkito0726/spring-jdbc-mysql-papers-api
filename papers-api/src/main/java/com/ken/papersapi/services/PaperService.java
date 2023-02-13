package com.ken.papersapi.services;

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
    final String query = "SELECT * from paper";
    List<Paper> papers = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(Paper.class)
    );

    return papers;
  }

  // public Paper findByPaperId(UUID paperId) {
  //   final String query = "SELECT * from paper WHERE paper_id=" + paperId;
  //   Paper paper = jdbcTemplate.query(
  //     query,
  //     new BeanPropertyRowMapper<>(Paper.class).getMappedClass()
  //   );
  // }

  public Paper save(Paper paper) {
    onCreatedPaper(paper);
    SqlParameterSource param = new BeanPropertySqlParameterSource(paper);
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
      .withTableName("paper")
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
