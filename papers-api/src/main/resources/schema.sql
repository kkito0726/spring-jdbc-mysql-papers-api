-- CREATE TABLE IF NOT EXISTS paper (
--   no INTEGER NOT NULL AUTO_INCREMENT,
--   paper_id VARCHAR(256),
--   title VARCHAR(256),
--   comment VARCHAR(256),
--   created_at DATETIME,
--   deleted_at DATETIME,
--   PRIMARY KEY (no)
-- )

CREATE TABLE IF NOT EXISTS user (
  no INTEGER NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(256),
  name VARCHAR(256),
  email VARCHAR(64),
  password VARCHAR(64),
  created_at DATETIME,
  deleted_at DATETIME,
  PRIMARY KEY (no)
)