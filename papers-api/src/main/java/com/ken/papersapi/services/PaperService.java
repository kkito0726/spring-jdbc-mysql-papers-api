package com.ken.papersapi.services;

import com.ken.papersapi.dtos.PaperDto;
import com.ken.papersapi.dtos.UpdatePaperDto;
import com.ken.papersapi.mappers.PaperMapper;
import com.ken.papersapi.models.Like;
import com.ken.papersapi.models.Paper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaperService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final PaperMapper _paperMapper;

  public List<PaperDto> findAll() {
    final String query = "SELECT * from papers WHERE deleted_at is NULL";
    List<Paper> papers = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(Paper.class)
    );
    List<PaperDto> paperDtos = papers
      .stream()
      .map(paperItem -> _paperMapper.toDto(paperItem))
      .toList();

    return paperDtos;
  }

  public PaperDto findByPaperId(UUID paperId) {
    final String query = "SELECT * from papers WHERE paper_id=?";
    List<Paper> papers = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(Paper.class),
      paperId.toString()
    );

    return _paperMapper.toDto(papers.get(0));
  }

  public List<PaperDto> findByUserId(UUID userId) {
    final String query = "SELECT * from papers WHERE user_id=?";
    List<Paper> papers = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(Paper.class),
      userId.toString()
    );
    List<PaperDto> paperDtos = papers
      .stream()
      .map(paperItem -> _paperMapper.toDto(paperItem))
      .toList();

    return paperDtos;
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

  public void like(Like like) {
    SqlParameterSource pram = new BeanPropertySqlParameterSource(like);
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
      .withTableName("likes");
    insert.execute(pram);
  }

  public List<Like> getLikesByPaperId(UUID paperId) {
    final String query = "SELECT * FROM likes WHERE paper_id=?";
    List<Like> likes = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(),
      paperId.toString()
    );

    return likes;
  }

  public List<Like> getLikesByUserId(UUID userId) {
    final String query = "SELECT * FROM likes WHERE paper_id=?";
    List<Like> likes = jdbcTemplate.query(
      query,
      new BeanPropertyRowMapper<>(),
      userId.toString()
    );

    return likes;
  }

  public PaperDto save(Paper paper) {
    onCreatedPaper(paper);
    SqlParameterSource param = new BeanPropertySqlParameterSource(paper);
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
      .withTableName("papers")
      .usingGeneratedKeyColumns("id");

    Number key = insert.executeAndReturnKey(param);
    paper.setNo(key.hashCode());

    return _paperMapper.toDto(paper);
  }

  public void onCreatedPaper(Paper paper) {
    paper.setPaperId(UUID.randomUUID());
    paper.setCreatedAt(LocalDateTime.now());
  }
}
