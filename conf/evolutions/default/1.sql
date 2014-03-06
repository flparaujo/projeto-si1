# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table disciplina (
  id                        bigint not null,
  nome                      varchar(255) not null,
  creditos                  integer,
  dificuldade               integer,
  periodo_original          integer,
  constraint uq_disciplina_nome unique (nome),
  constraint pk_disciplina primary key (id))
;

create table periodo (
  id                        bigint not null,
  numero                    integer,
  constraint pk_periodo primary key (id))
;

create table plano_de_curso (
  id                        bigint not null,
  constraint pk_plano_de_curso primary key (id))
;


create table disciplina_requisito (
  fk_disciplina                  bigint not null,
  fk_requisito                   bigint not null,
  constraint pk_disciplina_requisito primary key (fk_disciplina, fk_requisito))
;

create table periodo_disciplina (
  fk_periodo                     bigint not null,
  fk_disciplina                  bigint not null,
  constraint pk_periodo_disciplina primary key (fk_periodo, fk_disciplina))
;

create table plano_periodo (
  fk_plano                       bigint not null,
  fk_periodo                     bigint not null,
  constraint pk_plano_periodo primary key (fk_plano, fk_periodo))
;
create sequence disciplina_seq;

create sequence periodo_seq;

create sequence plano_de_curso_seq;




alter table disciplina_requisito add constraint fk_disciplina_requisito_disci_01 foreign key (fk_disciplina) references disciplina (id) on delete restrict on update restrict;

alter table disciplina_requisito add constraint fk_disciplina_requisito_disci_02 foreign key (fk_requisito) references disciplina (id) on delete restrict on update restrict;

alter table periodo_disciplina add constraint fk_periodo_disciplina_periodo_01 foreign key (fk_periodo) references periodo (id) on delete restrict on update restrict;

alter table periodo_disciplina add constraint fk_periodo_disciplina_discipl_02 foreign key (fk_disciplina) references disciplina (id) on delete restrict on update restrict;

alter table plano_periodo add constraint fk_plano_periodo_plano_de_cur_01 foreign key (fk_plano) references plano_de_curso (id) on delete restrict on update restrict;

alter table plano_periodo add constraint fk_plano_periodo_periodo_02 foreign key (fk_periodo) references periodo (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists disciplina;

drop table if exists disciplina_requisito;

drop table if exists periodo;

drop table if exists periodo_disciplina;

drop table if exists plano_de_curso;

drop table if exists plano_periodo;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists disciplina_seq;

drop sequence if exists periodo_seq;

drop sequence if exists plano_de_curso_seq;

