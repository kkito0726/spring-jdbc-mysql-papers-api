package com.ken.papersapi.services;

import com.ken.papersapi.models.Paper;
import java.util.List;
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

  public Paper save(Paper paper) {
    SqlParameterSource param = new BeanPropertySqlParameterSource(paper);
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
      .withTableName("paper")
      .usingGeneratedKeyColumns("id");

    Number key = insert.executeAndReturnKey(param);
    paper.setId(key.longValue());

    return paper;
  }
}
