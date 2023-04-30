
create table public.programs
(
    id          bigserial
        primary key,
    description varchar(255),
    title       varchar(255)
);

create table public.users
(
    id    bigserial
        primary key,
    email varchar(255),
    name  varchar(255)
);


create table public.subscribes
(
    user_id    bigint not null
        constraint uk_qdvc35ufcwb7arru254xvbte4
            unique
        constraint fk6hen8xwilkn7nbablqbqs6nkg
            references public.users,
    program_id bigint not null
        constraint uk_62f9abyckulc4od3763m4a1oo
            unique
        constraint fk28dxvuesexn33em3sdtnvxsac
            references public.programs
);
