-- Drop COMPANY table if it exists
DROP TABLE IF EXISTS company CASCADE;

-- Drop REPORT table if it exists
DROP TABLE IF EXISTS report CASCADE;

-- SQL code for COMPANY table
CREATE TABLE company (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         registration_number VARCHAR(255) NOT NULL,
                         address VARCHAR(255),
                         created_at TIMESTAMP
);

-- SQL code for REPORT table
CREATE TABLE report (
                        id UUID PRIMARY KEY,
                        company_id UUID NOT NULL,
                        report_date TIMESTAMP,
                        total_revenue DECIMAL,
                        net_profit DECIMAL,
                        CONSTRAINT fk_company
                            FOREIGN KEY(company_id)
                                REFERENCES company(id)
);

