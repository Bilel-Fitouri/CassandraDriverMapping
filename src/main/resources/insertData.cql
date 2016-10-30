INSERT INTO site (site_id, bp ,offer_family, pce, pdl) VALUES ('site1', 'bp1', 'offer1', 'pce1', 'pdl1');
INSERT INTO site (site_id, bp ,offer_family, pce, pdl) VALUES ('site2', 'bp2', 'offer2', 'pce2', 'pdl2');


INSERT INTO gas_index (offer_family, bp, pce, pdl, ts, nature, generation_ts, is_before_change)
VALUES ('offer1', 'bp1', 'pce1', 'pdl1', 1477835647000, 'nature1', 1477835601000, false);

INSERT INTO gas_index (offer_family, bp, pce, pdl, ts, nature, generation_ts, is_before_change)
VALUES ('offer1', 'bp1', 'pce1', 'pdl1', 1377835647000, 'nature1', 1477835601000, false);

INSERT INTO gas_index (offer_family, bp, pce, pdl, ts, nature, generation_ts, is_before_change)
VALUES ('offer1', 'bp1', 'pce1', 'pdl1', 1277835647000, 'nature1', 1477835601000, false);

INSERT INTO gas_index (offer_family, bp, pce, pdl, ts, nature, generation_ts, is_before_change)
VALUES ('offer1', 'bp1', 'pce1', 'pdl1', 1177835647000, 'nature1', 1477835601000, false);


INSERT INTO sensor_index (offer_family, infos, ts, nature, generation_ts, is_before_change)
VALUES ('offer1', {'name' : 'fitouri', 'prenom' : 'bilel'}, 1477835647000, 'nature1', 1477835601000, false);

INSERT INTO sensor_index (offer_family, infos, ts, nature, generation_ts, is_before_change)
VALUES ('offer1', {'name' : 'fitouri', 'prenom' : 'bilel'}, 1472835647000, 'nature1', 1477835601000, false);

INSERT INTO sensor_index (offer_family, infos, ts, nature, generation_ts, is_before_change)
VALUES ('offer1', {'name' : 'dupont', 'prenom' : 'martin'}, 1477835697000, 'nature1', 1477835601000, false);


