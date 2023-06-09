CREATE TABLE IF NOT EXISTS CREDITANALYSIS(
        id uuid NOT NULL,
        client_id uuid NOT NULL,
        approved boolean,
        approved_limit decimal(10,2),
        withdraw decimal(10,2),
        annual_interest decimal(10,2),
        requested_amount decimal(10,2),
        date Timestamp,
        PRIMARY KEY (id)
);