ALTER TABLE gallery ADD `version` BIGINT;

CREATE UNIQUE INDEX galley_idx_target_id ON gallery(target_id)
