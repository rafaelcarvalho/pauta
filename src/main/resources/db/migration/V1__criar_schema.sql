create table pauta (id uuid not null, assunto varchar(255) not null, status varchar(20) not null, primary key (id));
create table pauta_item (id uuid not null, texto text not null, pauta_id uuid not null, primary key (id));
create table sessao_de_votacao (id uuid not null, duracao_em_minutos timestamp, pauta_id uuid, primary key (id));
create table voto (id uuid not null, usuario uuid, voto char(3), sessao_de_votacao_id uuid, primary key (id));
alter table if exists pauta_item add constraint fk01_pauta_item_pauta foreign key (pauta_id) references pauta;
alter table if exists sessao_de_votacao add constraint fk01_sessao_de_votacao_pauta foreign key (pauta_id) references pauta;
alter table if exists voto add constraint f01_voto_sessao_votacao foreign key (sessao_de_votacao_id) references sessao_de_votacao;