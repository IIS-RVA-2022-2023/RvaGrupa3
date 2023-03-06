insert into artikl(id,naziv,proizvodjac)
values(nextval('artikl_seq'),'Chipsy 90g', 'Marbo');

insert into porudzbina(datum)
values(to_date('11.01.2023.', 'dd.mm.yyyy.'));