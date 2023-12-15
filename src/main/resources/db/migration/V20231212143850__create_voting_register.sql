CREATE TABLE IF NOT EXISTS vote (
                                    id SERIAL PRIMARY KEY,
                                    voting_session_id INT NOT NULL,
                                    associate_id INT NOT NULL,
                                    vote VARCHAR(3) NOT NULL,

                                    FOREIGN KEY (voting_session_id) REFERENCES voting_session(id),
                                    FOREIGN KEY (associate_id) REFERENCES associate(id)
);
