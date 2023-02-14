CREATE TABLE IF NOT EXISTS papers (
  no INTEGER NOT NULL AUTO_INCREMENT,
  paper_id VARCHAR(64),
  title VARCHAR(256) NOT NULL,
  comment VARCHAR(256),
  user_id VARCHAR(64) NOT NULL,
  created_at DATETIME NOT NULL,
  deleted_at DATETIME,
  PRIMARY KEY (no)
)

-- CREATE TABLE IF NOT EXISTS users (
--   no INTEGER NOT NULL AUTO_INCREMENT,
--   user_id VARCHAR(256) NOT NULL,
--   name VARCHAR(256) NOT NULL,
--   email VARCHAR(64) NOT NULL,
--   password VARCHAR(64) NOT NULL,
--   created_at DATETIME NOT NULL,
--   deleted_at DATETIME,
--   PRIMARY KEY (no)
-- )