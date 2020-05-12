CREATE TABLE public.price (
	id bigserial NOT NULL,
	outletid int4 NOT NULL,
	productid int4 NULL,
	salesprice numeric NULL,
	lastupdate int8 NULL,
	currency varchar(10) NULL,
	oldprice numeric NULL,
	CONSTRAINT price_pkey PRIMARY KEY (id),
	CONSTRAINT unique_product_per_store UNIQUE (outletid, productid)
);