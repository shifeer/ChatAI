CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE public.chat (
    id uuid primary key,
    created_at timestamp not null,
    title varchar(255) not null
);

CREATE TABLE public.message (
    id uuid primary key,
    created_at timestamp not null,
    payload text not null,
    chat_id uuid,
    foreign key (chat_id) references public.chat(id)
);

alter table message add column chat_role varchar(255);