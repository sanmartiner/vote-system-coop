CREATE TABLE IF NOT EXISTS voting_session (
                                              id INT PRIMARY KEY,
                                              topics_id INT NOT NULL,
                                              start_date TIMESTAMP NOT NULL,
                                              end_date TIMESTAMP NOT NULL DEFAULT '2024-01-01 00:00:00',

                                              FOREIGN KEY (topic_id) REFERENCES topic(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;