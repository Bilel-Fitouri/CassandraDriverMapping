CREATE TABLE site (
	site_id text,
	bp text,
	offer_family ascii,
	pce text,
	pdl text,
	PRIMARY KEY (site_id)
);

CREATE TABLE gas_index (
	offer_family ascii,
	bp text,
	pce text,
	pdl text,
	ts timestamp,
	nature text,
	generation_ts timestamp,
	is_before_change boolean,
	PRIMARY KEY ((offer_family,bp,pce),ts,nature,generation_ts,is_before_change)
);

CREATE TABLE sensor_index (
	offer_family ascii,
	infos frozen<map<ascii, text>>,
	ts timestamp,
	nature text,
	generation_ts timestamp,
	is_before_change boolean,
	PRIMARY KEY ((offer_family,infos),ts)
);