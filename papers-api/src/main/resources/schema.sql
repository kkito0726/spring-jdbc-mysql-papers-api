CREATE TABLE IF NOT EXISTS paper (
  no INTEGER NOT NULL AUTO_INCREMENT,
  paper_id VARCHAR(256),
  title VARCHAR(256),
  comment VARCHAR(256),
  created_at DATETIME,
  deleted_at DATETIME,
  PRIMARY KEY (no)
)