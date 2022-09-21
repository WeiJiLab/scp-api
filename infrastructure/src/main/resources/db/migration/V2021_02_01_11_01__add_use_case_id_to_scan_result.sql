ALTER TABLE `scan_result`
    ADD COLUMN `use_case_id` INT NOT NULL AFTER `updated_at`;
