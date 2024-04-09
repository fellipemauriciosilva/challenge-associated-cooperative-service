create table meeting (
    id bigserial not null,
    name varchar(255) not null,
    status varchar(255) check (status in ('OPEN','CLOSED')),
    primary key (id)
);

create table member (
    id bigserial not null,
    cpf varchar(255) not null,
    name varchar(255) not null,
    status varchar(255) check (status in ('ABLE_TO_VOTE','UNABLE_TO_VOTE')),
    primary key (id)
);

create table member_vote (
    member_id bigint not null,
    vote_id bigint not null
);

create table ruling (
    id bigserial not null,
    description varchar(255) not null,
    end_time timestamp(6),
    start_time timestamp(6),
    status varchar(255) check (status in ('STARTED','FINISHED')),
    meeting_id bigint not null,
    primary key (id)
);

create table ruling_vote (
    ruling_id bigint not null,
    vote_id bigint not null
);

create table vote (
    id bigserial not null,
    vote_value varchar(255) not null check (vote_value in ('YES','NO')),
    primary key (id)
);

alter table if exists member
drop constraint if exists UK_member;

alter table if exists member
add constraint UK_member unique (cpf);

alter table if exists member_vote
    add constraint FKmember_vote_vote_id
    foreign key (vote_id)
    references vote;

alter table if exists member_vote
    add constraint FKmember_vote_member_id
    foreign key (member_id)
    references member;

alter table if exists ruling
    add constraint FKruling_meeting_id
    foreign key (meeting_id)
    references meeting;

alter table if exists ruling_vote
    add constraint FKruling_vote_vote_id
    foreign key (vote_id)
    references vote;

alter table if exists ruling_vote
    add constraint FKruling_vote_ruling_id
    foreign key (ruling_id)
    references ruling;
