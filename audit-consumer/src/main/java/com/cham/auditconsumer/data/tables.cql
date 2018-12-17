DROP TABLE IF EXISTS event_source_keyspace.event_source;

CREATE TABLE event_source_keyspace.event_source (
  id text PRIMARY KEY,
  eventId text,
  eventSource text,
  eventDateTime text,
  eventData text
) WITH comment='event source data'
  AND COMPACT STORAGE
  AND read_repair_chance = 1.0;
