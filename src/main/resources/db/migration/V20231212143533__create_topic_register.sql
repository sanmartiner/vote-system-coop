CREATE TABLE IF NOT EXISTS topics (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(255) NOT NULL,
                                      description TEXT,
                                      voting_date TIMESTAMP NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
CREATE INDEX idx_topics_id ON topics(id);
CREATE INDEX idx_topics_title ON topics(title);