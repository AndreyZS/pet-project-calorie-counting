create table Account
(
    id    uuid primary key,
    login varchar(255) unique not null,
    heft  double precision    not null,
    dob   date                not null
);
create table FoodProduct
(
    id uuid primary key,
    name varchar(255) unique not null
);
create table Compound
(
    id uuid primary key,
    name varchar(255) unique null null,
    amount double precision
);

create table FoodProduct_Compound(
    
    product_id uuid ,
    compound_id uuid,

    FOREIGN KEY(product_id) REFERENCES FoodProduct(id) ON DELETE CASCADE,
    FOREIGN KEY(compound_id) REFERENCES Compound(id) ON DELETE CASCADE,
    PRIMARY KEY(product_id,compound_id)
)
