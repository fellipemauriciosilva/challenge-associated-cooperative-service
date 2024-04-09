INSERT INTO public.meeting
(id, name, status)
VALUES(1, 'General Assembly Residential Villa Colina', 'OPEN');

INSERT INTO public.meeting
(id, name, status)
VALUES(2, 'General Assembly Residential Island of Malta', 'OPEN');

INSERT INTO public.member
(id, cpf, name, status)
VALUES(1, '75129556003', 'Felipe Silva', 'ABLE_TO_VOTE');

INSERT INTO public.member
(id, cpf, name, status)
VALUES(2, '78469158007', 'Paulo Henrique', 'ABLE_TO_VOTE');

INSERT INTO public.member
(id, cpf, name, status)
VALUES(3, '91175264008', 'Maria Luiza', 'ABLE_TO_VOTE');

INSERT INTO public.member
(id, cpf, name, status)
VALUES(4, '69517993005', 'Pedro Henri', 'UNABLE_TO_VOTE');

INSERT INTO public.member
(id, cpf, name, status)
VALUES(5, '70674453026', 'Nelzira Silva', 'ABLE_TO_VOTE');

INSERT INTO public.ruling
(id, description, end_time, start_time, status, meeting_id)
VALUES(1, 'Should the Condo be responsible for building a gym?', '2025-04-08T00:12', '2024-04-08T00:12', 'STARTED', 1);

INSERT INTO public.ruling
(id, description, end_time, start_time, status, meeting_id)
VALUES(2, 'Can Condo residents walk their pets without a leash?', '2025-04-08T00:12', '2024-04-08T00:12', 'STARTED', 2);

INSERT INTO public.vote
(id, vote_value)
VALUES(1, 'YES');

INSERT INTO public.ruling_vote
(ruling_id, vote_id)
VALUES(1, 1);

INSERT INTO public.member_vote
(member_id, vote_id)
VALUES(2, 1);

INSERT INTO public.vote
(id, vote_value)
VALUES(2, 'YES');

INSERT INTO public.ruling_vote
(ruling_id, vote_id)
VALUES(2, 2);

INSERT INTO public.member_vote
(member_id, vote_id)
VALUES(5, 2);

INSERT INTO public.vote
(id, vote_value)
VALUES(3, 'YES');

INSERT INTO public.ruling_vote
(ruling_id, vote_id)
VALUES(1, 3);

INSERT INTO public.member_vote
(member_id, vote_id)
VALUES(3, 3);
