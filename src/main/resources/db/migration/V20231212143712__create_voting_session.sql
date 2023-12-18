CREATE TABLE IF NOT EXISTS voting_session (
                                              id INT PRIMARY KEY,
                                              topics_id INT NOT NULL,
                                              topics_title VARCHAR(255) NOT NULL,
                                              start_date TIMESTAMP NOT NULL,
                                              end_date TIMESTAMP NOT NULL DEFAULT '2024-01-01 00:00:00',

                                              FOREIGN KEY (topics_id) REFERENCES topics(id),
                                              FOREIGN KEY (topics_title) REFERENCES topics(title)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;