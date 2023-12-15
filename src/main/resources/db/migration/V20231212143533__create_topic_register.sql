CREATE TABLE IF NOT EXISTS topics (
                                      id INT PRIMARY KEY,
                                      title VARCHAR(255) NOT NULL,
                                      description TEXT,
                                      voting_date TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;