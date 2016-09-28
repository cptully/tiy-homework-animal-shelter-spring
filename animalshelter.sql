--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animal; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE animal (
    id integer NOT NULL,
    color character varying(255),
    description character varying(255),
    name character varying(255),
    breed_id integer
);


ALTER TABLE animal OWNER TO chris;

--
-- Name: breed; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE breed (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    type_id integer
);


ALTER TABLE breed OWNER TO chris;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: chris
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO chris;

--
-- Name: note; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE note (
    id integer NOT NULL,
    content character varying(255),
    date date,
    animal_id integer
);


ALTER TABLE note OWNER TO chris;

--
-- Name: type; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE type (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE type OWNER TO chris;

--
-- Data for Name: animal; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY animal (id, color, description, name, breed_id) FROM stdin;
36	black & white	happy and loves chasing balls	Shadow	26
1	Goldenrod	\N	Jessica	45
2	Maroon	\N	Roy	44
3	Goldenrod	\N	Randy	24
4	Teal	\N	Randy	46
5	Khaki	\N	Victor	26
6	Blue	\N	Joyce	43
7	Indigo	\N	Steven	41
8	Maroon	\N	Gerald	42
9	Orange	\N	Michelle	44
10	Violet	\N	Henry	45
11	Goldenrod	\N	Jack	47
12	Puce	\N	Nicole	26
13	Green	\N	Judy	45
14	Puce	\N	Samuel	44
15	Crimson	\N	Catherine	43
16	Mauv	\N	Shirley	44
17	Crimson	\N	Donna	46
18	Purple	\N	Amy	46
19	Purple	\N	Laura	41
20	Aquamarine	\N	Joseph	43
21	Red	\N	Alan	24
22	Khaki	\N	Jacqueline	44
23	Pink	\N	Bruce	44
24	Green	\N	Justin	44
25	Pink	\N	Jason	47
26	Red	\N	Janet	47
27	Mauv	\N	Jane	26
28	Red	\N	Mildred	45
29	Puce	\N	Jane	45
30	Mauv	\N	Steven	43
31	Indigo	\N	Patrick	47
32	Pink	\N	Tina	24
33	Yellow	\N	Martin	42
34	Yellow	\N	Adam	24
35	Red	\N	Timothy	46
37	Khaki	\N	Anna	26
38	Goldenrod	\N	Harold	46
39	Purple	\N	Michelle	24
40	Fuscia	\N	Thomas	45
41	Red	\N	Carol	43
42	Purple	\N	Christine	43
43	Mauv	\N	Jessica	46
44	Goldenrod	\N	Charles	26
45	Pink	\N	Kimberly	42
46	Teal	\N	Helen	43
47	Puce	\N	Johnny	26
48	Maroon	\N	Shirley	41
49	Yellow	\N	Alice	42
50	Aquamarine	\N	Howard	45
51	Red	\N	Ann	45
52	Crimson	\N	Ernest	45
53	Yellow	\N	Robin	24
54	Teal	\N	Patricia	41
55	Goldenrod	\N	Todd	26
56	Khaki	\N	Brenda	46
57	Pink	\N	Irene	45
58	Violet	\N	Martin	26
59	Yellow	\N	Jimmy	42
60	Teal	\N	Jane	41
61	Goldenrod	\N	Howard	46
62	Violet	\N	Victor	41
63	Mauv	\N	Joseph	26
64	Green	\N	Raymond	45
65	Pink	\N	Barbara	26
66	Purple	\N	Billy	42
67	Pink	\N	Susan	41
68	Goldenrod	\N	Debra	45
69	Mauv	\N	Lois	46
70	Maroon	\N	Bobby	42
71	Maroon	\N	Harry	42
72	Teal	\N	Janice	43
73	Yellow	\N	Melissa	43
74	Purple	\N	Russell	46
75	Pink	\N	Jeremy	46
76	Goldenrod	\N	Richard	42
77	Puce	\N	Kenneth	43
78	Teal	\N	Doris	26
79	Violet	\N	Donald	47
80	Purple	\N	Melissa	43
81	Puce	\N	Tina	42
82	Goldenrod	\N	Dennis	46
83	Violet	\N	Linda	47
84	Blue	\N	Willie	26
85	Red	\N	Walter	46
86	Goldenrod	\N	Jeffrey	45
87	Fuscia	\N	Joan	43
88	Crimson	\N	Ashley	45
89	Turquoise	\N	Paula	47
90	Crimson	\N	Paul	46
91	Teal	\N	Marie	26
92	Blue	\N	Kenneth	44
93	Crimson	\N	Carolyn	45
94	Puce	\N	Rachel	42
95	Aquamarine	\N	Douglas	46
96	Aquamarine	\N	Janet	42
97	Aquamarine	\N	Brandon	26
98	Pink	\N	Larry	24
99	Turquoise	\N	Kenneth	46
100	Mauv	\N	Sandra	24
101	Fuscia	\N	Rebecca	41
102	Goldenrod	\N	Roy	43
103	Goldenrod	\N	Shirley	47
104	Teal	\N	Arthur	47
105	Indigo	\N	Thomas	46
106	Mauv	\N	Amy	45
107	Turquoise	\N	Robin	44
108	Green	\N	Susan	47
109	Violet	\N	Harold	42
110	Khaki	\N	Teresa	24
111	Green	\N	Chris	41
112	Purple	\N	Jerry	46
113	Aquamarine	\N	Denise	44
114	Puce	\N	Lisa	41
115	Turquoise	\N	Roger	44
116	Khaki	\N	Keith	42
117	Goldenrod	\N	Jose	43
118	Turquoise	\N	Lawrence	45
119	Pink	\N	Amanda	47
120	Yellow	\N	Tina	47
121	Purple	\N	Martin	26
122	Green	\N	Bonnie	43
123	Crimson	\N	Joyce	47
124	Violet	\N	Harry	26
125	Khaki	\N	Frances	41
126	Teal	\N	Rebecca	26
127	Turquoise	\N	Samuel	24
128	Indigo	\N	Jimmy	41
129	Yellow	\N	Stephanie	47
130	Puce	\N	Daniel	45
131	Maroon	\N	Susan	47
132	Fuscia	\N	Sharon	47
133	Maroon	\N	Harry	43
134	Indigo	\N	Sharon	41
135	Violet	\N	Alan	41
136	Orange	\N	Henry	45
137	Violet	\N	Jason	24
138	Turquoise	\N	Andrew	44
139	Violet	\N	James	43
140	Blue	\N	Carolyn	24
141	Green	\N	Katherine	42
142	Indigo	\N	Teresa	47
143	Puce	\N	Alan	44
144	Red	\N	Terry	46
145	Yellow	\N	Peter	43
146	Crimson	\N	Sean	24
147	Red	\N	Sara	46
148	Purple	\N	Lawrence	44
149	Purple	\N	Dennis	47
150	Fuscia	\N	Ruth	46
151	Violet	\N	Andrea	43
152	Indigo	\N	Elizabeth	24
153	Khaki	\N	Paula	42
154	Orange	\N	Peter	45
155	Aquamarine	\N	Edward	44
156	Indigo	\N	Ruby	44
157	Pink	\N	Larry	44
158	Blue	\N	Jeffrey	41
159	Aquamarine	\N	Catherine	45
160	Mauv	\N	Elizabeth	42
161	Goldenrod	\N	Sarah	42
162	Khaki	\N	Gerald	26
163	Aquamarine	\N	Nancy	42
164	Crimson	\N	Peter	43
165	Khaki	\N	Jose	45
166	Red	\N	Mildred	41
167	Blue	\N	Lori	45
168	Maroon	\N	Aaron	43
169	Maroon	\N	Shawn	42
170	Goldenrod	\N	Patricia	26
171	Puce	\N	Lisa	44
172	Crimson	\N	Marie	44
173	Mauv	\N	Norma	43
174	Orange	\N	Jennifer	26
175	Goldenrod	\N	Kathleen	24
176	Red	\N	Michael	45
177	Indigo	\N	Todd	44
178	Goldenrod	\N	Roy	26
179	Turquoise	\N	Martin	45
180	Teal	\N	Steven	24
181	Maroon	\N	Sharon	42
182	Khaki	\N	Robin	43
183	Violet	\N	Rebecca	46
184	Puce	\N	Jeffrey	44
185	Teal	\N	Susan	46
186	Pink	\N	Helen	42
187	Indigo	\N	Wanda	26
188	Puce	\N	Beverly	46
189	Purple	\N	Theresa	46
190	Maroon	\N	Brandon	47
191	Teal	\N	Karen	24
192	Goldenrod	\N	Laura	26
193	Pink	\N	Willie	26
194	Blue	\N	Willie	44
195	Aquamarine	\N	Janet	43
196	Blue	\N	Rachel	42
197	Mauv	\N	Jean	47
198	Goldenrod	\N	Laura	42
199	Mauv	\N	Dennis	47
200	Teal	\N	Paula	44
201	Blue	\N	Mark	43
202	Yellow	\N	Larry	42
203	Khaki	\N	Peter	41
204	Red	\N	Emily	43
205	Teal	\N	Sharon	24
206	Khaki	\N	Kathryn	24
207	Fuscia	\N	Jacqueline	43
208	Maroon	\N	Howard	43
209	Puce	\N	Cynthia	46
210	Indigo	\N	Andrea	42
211	Blue	\N	Linda	46
212	Crimson	\N	Alice	42
213	Mauv	\N	Deborah	44
214	Indigo	\N	Betty	26
215	Crimson	\N	Angela	44
216	Blue	\N	Edward	46
217	Crimson	\N	Mildred	24
218	Red	\N	Andrea	43
219	Purple	\N	Bobby	45
220	Mauv	\N	Susan	42
221	Aquamarine	\N	Rebecca	41
222	Blue	\N	Justin	46
223	Goldenrod	\N	Barbara	44
224	Turquoise	\N	Catherine	41
225	Aquamarine	\N	Frances	24
226	Red	\N	Gloria	42
227	Blue	\N	Barbara	24
228	Indigo	\N	Susan	26
229	Purple	\N	Craig	47
230	Pink	\N	Joshua	43
231	Pink	\N	Cheryl	26
232	Fuscia	\N	Kathryn	24
233	Red	\N	Eric	24
234	Blue	\N	William	43
235	Blue	\N	Ralph	24
236	Yellow	\N	Christine	41
237	Purple	\N	Phillip	26
238	Violet	\N	Diane	44
239	Maroon	\N	Harry	46
240	Mauv	\N	Steven	26
241	Violet	\N	Sara	44
242	Purple	\N	Christina	41
243	Red	\N	Ruby	47
244	Khaki	\N	Thomas	47
245	Turquoise	\N	Mildred	44
246	Blue	\N	Charles	24
247	Blue	\N	Jean	47
248	Orange	\N	Terry	44
249	Purple	\N	Gloria	47
250	Puce	\N	Daniel	41
251	Maroon	\N	Pamela	26
252	Red	\N	Roy	44
253	Aquamarine	\N	Emily	41
254	Puce	\N	Martin	46
255	Maroon	\N	Jessica	24
256	Blue	\N	Janice	24
257	Purple	\N	Barbara	24
258	Orange	\N	Norma	42
259	Crimson	\N	Earl	41
260	Purple	\N	Kimberly	46
261	Crimson	\N	Deborah	46
262	Red	\N	Christina	42
263	Khaki	\N	Johnny	24
264	Maroon	\N	Adam	41
265	Orange	\N	Steven	42
266	Mauv	\N	Fred	45
267	Orange	\N	Raymond	45
268	Maroon	\N	Emily	47
269	Puce	\N	Heather	42
270	Aquamarine	\N	Gloria	43
271	Indigo	\N	Timothy	41
272	Yellow	\N	Brian	43
273	Maroon	\N	Ann	44
274	Yellow	\N	Carlos	42
275	Mauv	\N	Craig	43
276	Khaki	\N	Marie	47
277	Fuscia	\N	Donald	26
278	Violet	\N	Joyce	41
279	Puce	\N	Janet	47
280	Indigo	\N	Brian	41
281	Violet	\N	Andrea	26
282	Puce	\N	Anna	41
283	Maroon	\N	Paul	42
284	Indigo	\N	Gary	24
285	Pink	\N	Shawn	24
286	Turquoise	\N	Michael	47
287	Yellow	\N	Mark	41
288	Khaki	\N	Julie	46
289	Fuscia	\N	Jeffrey	26
290	Blue	\N	Beverly	43
291	Turquoise	\N	Barbara	42
292	Puce	\N	Jesse	46
293	Pink	\N	Anne	24
294	Teal	\N	Jimmy	42
295	Aquamarine	\N	Willie	41
296	Teal	\N	Paula	43
297	Maroon	\N	Sara	26
298	Purple	\N	Brandon	26
299	Violet	\N	Janice	44
300	Teal	\N	Joyce	46
301	Red	\N	Cheryl	41
302	Crimson	\N	Donna	26
303	Red	\N	Todd	46
304	Maroon	\N	Tina	42
305	Crimson	\N	Kathryn	44
306	Goldenrod	\N	Justin	42
307	Orange	\N	David	43
308	Violet	\N	Paul	24
309	Turquoise	\N	Sandra	46
310	Crimson	\N	Anne	46
311	Teal	\N	Peter	43
312	Fuscia	\N	Philip	26
313	Yellow	\N	Louis	46
314	Khaki	\N	Frank	47
315	Teal	\N	Eugene	26
316	Fuscia	\N	Maria	41
317	Puce	\N	Willie	24
318	Yellow	\N	Debra	26
319	Indigo	\N	Joyce	24
320	Indigo	\N	Jean	24
321	Pink	\N	Steven	46
322	Blue	\N	Samuel	46
323	Crimson	\N	Diana	46
324	Aquamarine	\N	Juan	41
325	Green	\N	Joan	44
326	Teal	\N	Raymond	46
327	Pink	\N	Janet	45
328	Green	\N	Mary	42
329	Green	\N	Judy	44
330	Yellow	\N	Juan	45
331	Indigo	\N	Katherine	24
332	Puce	\N	Joseph	46
333	Violet	\N	Jonathan	42
334	Pink	\N	Michael	26
335	Blue	\N	Nicholas	42
336	Goldenrod	\N	Victor	26
337	Teal	\N	Jimmy	41
338	Orange	\N	Howard	46
339	Red	\N	Terry	26
340	Violet	\N	Samuel	24
341	Purple	\N	Helen	24
342	Teal	\N	Donna	42
343	Green	\N	Jacqueline	43
344	Aquamarine	\N	Bruce	24
345	Fuscia	\N	Dorothy	41
346	Khaki	\N	Carol	47
347	Fuscia	\N	Phillip	41
348	Teal	\N	Michelle	46
349	Teal	\N	William	44
350	Indigo	\N	Jonathan	44
351	Crimson	\N	Joe	44
352	Indigo	\N	Albert	41
353	Turquoise	\N	Mark	41
354	Khaki	\N	John	41
355	Maroon	\N	Kathleen	46
356	Purple	\N	Phyllis	24
357	Pink	\N	Tammy	43
358	Green	\N	Chris	26
359	Puce	\N	Diane	41
360	Crimson	\N	Adam	44
361	Goldenrod	\N	Jesse	42
362	Maroon	\N	Jesse	24
363	Purple	\N	Maria	46
364	Maroon	\N	Terry	26
365	Orange	\N	Roy	26
366	Khaki	\N	Matthew	26
367	Goldenrod	\N	Brandon	41
368	Aquamarine	\N	Jane	42
369	Mauv	\N	Alan	44
370	Green	\N	Anna	47
371	Teal	\N	Clarence	26
372	Red	\N	Dennis	41
373	Khaki	\N	Lawrence	45
374	Goldenrod	\N	Joseph	46
375	Crimson	\N	Jennifer	42
376	Indigo	\N	John	41
377	Khaki	\N	Howard	43
378	Blue	\N	Shawn	46
379	Crimson	\N	Jessica	46
380	Green	\N	Gloria	41
381	Red	\N	Helen	45
382	Puce	\N	Nicholas	44
383	Blue	\N	Amanda	24
384	Turquoise	\N	Phillip	24
385	Violet	\N	Catherine	26
386	Mauv	\N	Ronald	45
387	Green	\N	Anna	41
388	Violet	\N	Dorothy	24
389	Pink	\N	Nicole	46
390	Indigo	\N	Kenneth	24
391	Orange	\N	Lisa	47
392	Turquoise	\N	Sandra	43
393	Violet	\N	Nicholas	42
394	Indigo	\N	Matthew	26
395	Red	\N	Craig	47
396	Aquamarine	\N	Howard	42
397	Blue	\N	Laura	44
398	Green	\N	Amanda	46
399	Goldenrod	\N	Gary	24
400	Purple	\N	Louis	26
401	Aquamarine	\N	Doris	44
402	Fuscia	\N	Jessica	45
403	Khaki	\N	Samuel	26
404	Goldenrod	\N	Eugene	41
405	Indigo	\N	Lillian	45
406	Crimson	\N	Paul	46
407	Pink	\N	Billy	47
408	Blue	\N	Debra	44
409	Indigo	\N	Lisa	42
410	Yellow	\N	Sean	42
411	Violet	\N	Paul	46
412	Pink	\N	Howard	42
413	Fuscia	\N	Maria	26
414	Turquoise	\N	Jane	42
415	Turquoise	\N	David	44
416	Teal	\N	Benjamin	47
417	Mauv	\N	Aaron	47
418	Indigo	\N	Walter	41
419	Aquamarine	\N	Julia	45
420	Teal	\N	Edward	47
421	Green	\N	Clarence	42
422	Yellow	\N	Ruby	44
423	Maroon	\N	Janet	44
424	Yellow	\N	Peter	24
425	Teal	\N	Cynthia	45
426	Turquoise	\N	Matthew	43
427	Puce	\N	Mary	46
428	Teal	\N	Heather	47
429	Red	\N	Cynthia	47
430	Pink	\N	William	45
431	Crimson	\N	Andrea	26
432	Teal	\N	Nicholas	44
433	Crimson	\N	Ryan	43
434	Indigo	\N	Richard	43
435	Green	\N	Lois	45
436	Maroon	\N	Kevin	47
437	Khaki	\N	Ralph	45
438	Crimson	\N	Donald	42
439	Aquamarine	\N	Emily	24
440	Pink	\N	Wanda	44
441	Maroon	\N	James	26
442	Puce	\N	Jane	24
443	Puce	\N	Thomas	47
444	Mauv	\N	Shawn	46
445	Green	\N	Carl	42
446	Yellow	\N	Angela	24
447	Red	\N	Ronald	46
448	Fuscia	\N	Scott	24
449	Blue	\N	John	26
450	Red	\N	Ashley	46
451	Maroon	\N	Jimmy	26
452	Orange	\N	Sarah	45
453	Turquoise	\N	William	24
454	Khaki	\N	Nancy	26
455	Purple	\N	Albert	43
456	Crimson	\N	Donna	47
457	Green	\N	Shirley	24
458	Khaki	\N	Phillip	46
459	Purple	\N	Patricia	44
460	Puce	\N	Brandon	26
461	Blue	\N	Melissa	44
462	Teal	\N	Frank	41
463	Turquoise	\N	Jason	47
464	Orange	\N	Doris	24
465	Indigo	\N	Jeremy	41
466	Purple	\N	Juan	26
467	Violet	\N	Victor	45
468	Green	\N	Catherine	26
469	Green	\N	Janice	42
470	Blue	\N	Howard	44
471	Khaki	\N	Roy	47
472	Maroon	\N	Donna	44
473	Crimson	\N	Adam	42
474	Aquamarine	\N	Nicole	41
475	Violet	\N	Donna	46
476	Green	\N	Julie	46
477	Red	\N	Howard	46
478	Crimson	\N	Carlos	44
479	Yellow	\N	Bobby	47
480	Blue	\N	Joyce	24
481	Orange	\N	Gary	24
482	Maroon	\N	Beverly	46
483	Puce	\N	Nicole	43
484	Puce	\N	Laura	41
485	Teal	\N	Walter	45
486	Green	\N	Rebecca	24
487	Fuscia	\N	Cheryl	46
488	Maroon	\N	Rose	46
489	Turquoise	\N	Janet	42
490	Puce	\N	Marilyn	24
491	Red	\N	Walter	24
492	Maroon	\N	Laura	46
493	Pink	\N	Shawn	44
494	Maroon	\N	Billy	44
495	Pink	\N	Thomas	43
496	Fuscia	\N	Steve	43
497	Fuscia	\N	Jacqueline	44
498	Khaki	\N	Charles	47
499	Indigo	\N	Susan	46
500	Teal	\N	Maria	47
501	Puce	\N	Bobby	45
502	Violet	\N	Carolyn	46
503	Pink	\N	Kimberly	46
504	Mauv	\N	Jimmy	47
505	Violet	\N	Janice	44
506	Blue	\N	Theresa	45
507	Red	\N	Craig	45
508	Yellow	\N	Cynthia	41
509	Orange	\N	Joshua	26
510	Khaki	\N	Tammy	43
511	Khaki	\N	Ruth	41
512	Maroon	\N	Jonathan	47
513	Violet	\N	Anna	26
514	Fuscia	\N	Emily	46
515	Green	\N	Theresa	44
516	Orange	\N	Maria	24
517	Mauv	\N	Laura	42
518	Purple	\N	Jeffrey	41
519	Blue	\N	Kenneth	42
520	Green	\N	Janet	41
521	Fuscia	\N	Mark	45
522	Teal	\N	Helen	47
523	Fuscia	\N	Henry	26
524	Crimson	\N	Susan	47
525	Purple	\N	Jimmy	41
526	Purple	\N	Jason	42
527	Mauv	\N	Christine	24
528	Puce	\N	Ronald	42
529	Khaki	\N	Daniel	26
530	Teal	\N	Lisa	41
531	Turquoise	\N	Jonathan	44
532	Orange	\N	Edward	46
533	Puce	\N	Aaron	46
534	Violet	\N	Paul	41
535	Crimson	\N	Lisa	45
536	Crimson	\N	Shawn	44
537	Violet	\N	Keith	47
538	Orange	\N	Jessica	45
539	Blue	\N	Alice	26
540	Aquamarine	\N	Billy	44
541	Yellow	\N	Antonio	45
542	Teal	\N	Thomas	44
543	Green	\N	Victor	24
544	Red	\N	Amy	42
545	Goldenrod	\N	Ashley	45
546	Turquoise	\N	Annie	41
547	Puce	\N	George	44
548	Puce	\N	Stephen	42
549	Green	\N	Stephanie	26
550	Pink	\N	Louise	42
551	Purple	\N	Nicole	47
552	Violet	\N	Billy	26
553	Fuscia	\N	Anthony	47
554	Purple	\N	Harry	24
555	Goldenrod	\N	Marilyn	45
556	Mauv	\N	Ernest	42
557	Aquamarine	\N	Jonathan	45
558	Crimson	\N	Catherine	43
559	Fuscia	\N	Rachel	24
560	Turquoise	\N	Donald	46
561	Fuscia	\N	Craig	43
562	Red	\N	Ann	44
563	Puce	\N	Stephen	41
564	Aquamarine	\N	Carol	42
565	Aquamarine	\N	Janet	42
566	Khaki	\N	Doris	24
567	Mauv	\N	Frank	44
568	Violet	\N	Amanda	42
569	Purple	\N	Kelly	43
570	Teal	\N	Sean	45
571	Mauv	\N	Henry	44
572	Green	\N	Fred	43
573	Violet	\N	David	26
574	Goldenrod	\N	Amanda	45
575	Orange	\N	Jennifer	26
576	Pink	\N	Emily	44
577	Fuscia	\N	Ann	43
578	Red	\N	Amy	42
579	Puce	\N	Theresa	24
580	Indigo	\N	Wayne	26
581	Indigo	\N	Steve	43
582	Fuscia	\N	Wayne	42
583	Blue	\N	Anne	41
584	Aquamarine	\N	Tina	46
585	Green	\N	Catherine	47
586	Red	\N	Angela	46
587	Aquamarine	\N	Judy	47
588	Puce	\N	Matthew	24
589	Teal	\N	Roy	45
590	Fuscia	\N	Amanda	24
591	Blue	\N	Harry	41
592	Turquoise	\N	Juan	47
593	Orange	\N	Donald	26
594	Aquamarine	\N	Amy	43
595	Turquoise	\N	Frances	45
596	Violet	\N	Janice	26
597	Mauv	\N	Julia	26
598	Puce	\N	Karen	46
599	Goldenrod	\N	Deborah	44
600	Violet	\N	Raymond	41
601	Red	\N	Ralph	45
602	Crimson	\N	Dorothy	44
603	Goldenrod	\N	Kimberly	24
604	Puce	\N	Howard	46
605	Green	\N	Alice	45
606	Goldenrod	\N	Joan	43
607	Fuscia	\N	Louise	47
608	Turquoise	\N	Ronald	45
609	Khaki	\N	Roy	46
610	Puce	\N	Edward	41
611	Purple	\N	Debra	47
612	Orange	\N	Catherine	46
613	Fuscia	\N	Julia	47
614	Pink	\N	Douglas	47
615	Violet	\N	Bruce	42
616	Maroon	\N	Frank	24
617	Khaki	\N	Judy	45
618	Fuscia	\N	Louis	41
619	Green	\N	Frank	26
620	Pink	\N	Jose	46
621	Goldenrod	\N	Craig	47
622	Yellow	\N	Martha	24
623	Orange	\N	Kathleen	42
624	Puce	\N	Nicole	26
625	Aquamarine	\N	Jacqueline	45
626	Khaki	\N	Juan	45
627	Aquamarine	\N	Juan	47
628	Aquamarine	\N	Robert	43
629	Pink	\N	Rose	41
630	Aquamarine	\N	Katherine	41
631	Khaki	\N	James	46
632	Turquoise	\N	Ruth	24
633	Violet	\N	Harold	41
634	Red	\N	Denise	45
635	Blue	\N	Eugene	26
636	Purple	\N	Kathleen	24
637	Teal	\N	Sara	47
638	Purple	\N	Andrea	26
639	Mauv	\N	Karen	41
640	Teal	\N	Michael	43
641	Maroon	\N	Anthony	26
642	Yellow	\N	Phyllis	26
643	Blue	\N	Timothy	42
644	Red	\N	Jose	41
645	Mauv	\N	Brian	45
646	Violet	\N	Roy	45
647	Fuscia	\N	Jonathan	43
648	Aquamarine	\N	Anna	42
649	Pink	\N	Frank	26
650	Purple	\N	Deborah	26
651	Turquoise	\N	Frank	26
652	Orange	\N	Roy	46
653	Goldenrod	\N	Wayne	47
654	Mauv	\N	Brandon	42
655	Blue	\N	Sara	47
656	Indigo	\N	Shawn	47
657	Mauv	\N	Terry	41
658	Indigo	\N	Wayne	45
659	Yellow	\N	Stephanie	43
660	Khaki	\N	Donna	41
661	Aquamarine	\N	Sean	42
662	Green	\N	Janice	41
663	Violet	\N	Daniel	43
664	Puce	\N	Douglas	42
665	Teal	\N	Craig	43
666	Yellow	\N	Laura	26
667	Aquamarine	\N	Raymond	45
668	Khaki	\N	Shawn	24
669	Mauv	\N	John	44
670	Green	\N	Daniel	26
671	Yellow	\N	Sandra	45
672	Yellow	\N	Annie	42
673	Teal	\N	Edward	42
674	Pink	\N	Adam	45
675	Turquoise	\N	Samuel	24
676	Goldenrod	\N	Louis	43
677	Khaki	\N	John	26
678	Aquamarine	\N	Elizabeth	41
679	Maroon	\N	Doris	44
680	Indigo	\N	Jason	42
681	Teal	\N	Cynthia	42
682	Green	\N	Richard	41
683	Violet	\N	Gregory	24
684	Red	\N	Sarah	46
685	Green	\N	Barbara	41
686	Aquamarine	\N	Henry	46
687	Green	\N	Bonnie	24
688	Blue	\N	Betty	47
689	Red	\N	Sharon	45
690	Pink	\N	Lillian	41
691	Maroon	\N	Billy	46
692	Yellow	\N	Teresa	46
693	Mauv	\N	Arthur	43
694	Puce	\N	Aaron	44
695	Pink	\N	Jimmy	46
696	Puce	\N	Tammy	44
697	Teal	\N	Joan	46
698	Turquoise	\N	Jean	42
699	Khaki	\N	Kevin	42
700	Maroon	\N	Laura	44
701	Mauv	\N	Richard	44
702	Goldenrod	\N	Fred	45
703	Purple	\N	Annie	24
704	Blue	\N	Robin	44
705	Pink	\N	Carolyn	42
706	Puce	\N	Bonnie	45
707	Mauv	\N	Keith	41
708	Fuscia	\N	Barbara	43
709	Mauv	\N	Emily	41
710	Puce	\N	Andrea	41
711	Green	\N	Richard	24
712	Red	\N	Kelly	45
713	Indigo	\N	Earl	42
714	Violet	\N	Raymond	26
715	Aquamarine	\N	Ruth	24
716	Purple	\N	Cynthia	45
717	Crimson	\N	Christopher	43
718	Fuscia	\N	Joan	45
719	Fuscia	\N	Benjamin	41
720	Khaki	\N	Eugene	46
721	Violet	\N	Tammy	42
722	Mauv	\N	Timothy	41
723	Teal	\N	Dorothy	45
724	Goldenrod	\N	Jerry	41
725	Khaki	\N	Kimberly	45
726	Fuscia	\N	Andrea	46
727	Red	\N	Jerry	42
728	Blue	\N	Roy	45
729	Red	\N	John	43
730	Red	\N	Rose	41
731	Blue	\N	Joyce	47
732	Green	\N	James	24
733	Red	\N	Melissa	46
734	Pink	\N	Daniel	45
735	Teal	\N	Jennifer	41
736	Aquamarine	\N	Nicholas	26
737	Purple	\N	Amanda	43
738	Puce	\N	Beverly	43
739	Blue	\N	Lawrence	42
740	Yellow	\N	Joyce	41
741	Red	\N	Mark	44
742	Orange	\N	Lillian	44
743	Turquoise	\N	Theresa	42
744	Indigo	\N	John	41
745	Red	\N	Craig	26
746	Khaki	\N	Judy	26
747	Orange	\N	Jimmy	43
748	Aquamarine	\N	Shirley	26
749	Green	\N	Samuel	24
750	Pink	\N	Kathryn	47
751	Khaki	\N	Heather	24
752	Puce	\N	Helen	24
753	Yellow	\N	Philip	44
754	Purple	\N	Christine	42
755	Teal	\N	Russell	47
756	Crimson	\N	Nicole	24
757	Violet	\N	Jane	44
758	Aquamarine	\N	Julia	47
759	Teal	\N	Robin	26
760	Fuscia	\N	Anne	44
761	Mauv	\N	Sharon	41
762	Khaki	\N	Carol	44
763	Purple	\N	Michelle	41
764	Green	\N	Phillip	47
765	Mauv	\N	Daniel	46
766	Turquoise	\N	James	44
767	Turquoise	\N	Debra	46
768	Pink	\N	Amanda	43
769	Green	\N	Lori	42
770	Fuscia	\N	Diane	46
771	Mauv	\N	Judy	42
772	Violet	\N	Ernest	46
773	Yellow	\N	Mildred	43
774	Khaki	\N	Alan	42
775	Turquoise	\N	Fred	42
776	Orange	\N	Mildred	24
777	Crimson	\N	Michelle	46
778	Red	\N	Walter	44
779	Puce	\N	Adam	46
780	Teal	\N	Pamela	24
781	Orange	\N	Kelly	41
782	Indigo	\N	Karen	46
783	Aquamarine	\N	Laura	44
784	Puce	\N	Jean	42
785	Teal	\N	Janice	46
786	Turquoise	\N	Jeffrey	46
787	Orange	\N	Julia	43
788	Maroon	\N	Richard	43
789	Maroon	\N	Richard	47
790	Khaki	\N	Carl	43
791	Red	\N	Anna	43
792	Pink	\N	Doris	42
793	Crimson	\N	Heather	43
794	Red	\N	Stephanie	43
795	Mauv	\N	Janet	46
796	Puce	\N	Howard	47
797	Violet	\N	Andrea	26
798	Orange	\N	Anne	44
799	Goldenrod	\N	Carl	46
800	Pink	\N	Ralph	46
801	Khaki	\N	Maria	45
802	Turquoise	\N	Susan	42
803	Puce	\N	Linda	46
804	Aquamarine	\N	Doris	47
805	Green	\N	Ralph	41
806	Fuscia	\N	Christina	44
807	Purple	\N	Melissa	43
808	Teal	\N	Albert	45
809	Maroon	\N	Diane	44
810	Fuscia	\N	Michael	43
811	Turquoise	\N	Laura	41
812	Aquamarine	\N	Brandon	43
813	Red	\N	Charles	47
814	Green	\N	Kathryn	45
815	Yellow	\N	Julie	44
816	Pink	\N	Todd	24
817	Purple	\N	Susan	44
818	Aquamarine	\N	Martha	46
819	Maroon	\N	Mildred	26
820	Indigo	\N	Amy	42
821	Puce	\N	Kenneth	41
822	Purple	\N	Randy	45
823	Aquamarine	\N	Debra	42
824	Teal	\N	Willie	44
825	Purple	\N	Katherine	26
826	Pink	\N	Gary	26
827	Maroon	\N	Phillip	44
828	Pink	\N	Julie	45
829	Puce	\N	Clarence	26
830	Red	\N	Kathy	42
831	Turquoise	\N	Melissa	44
832	Red	\N	Albert	41
833	Indigo	\N	Judy	41
834	Teal	\N	Michelle	26
835	Red	\N	Jean	46
836	Pink	\N	Clarence	46
837	Puce	\N	Doris	26
838	Violet	\N	Martin	43
839	Green	\N	Norma	47
840	Khaki	\N	Anthony	46
841	Blue	\N	Jonathan	24
842	Yellow	\N	Jacqueline	43
843	Teal	\N	Alan	44
844	Crimson	\N	Henry	42
845	Indigo	\N	Amy	26
846	Fuscia	\N	Teresa	24
847	Mauv	\N	Sarah	24
848	Red	\N	Gerald	45
849	Mauv	\N	Teresa	44
850	Mauv	\N	Laura	45
851	Fuscia	\N	Jeremy	24
852	Green	\N	Gary	47
853	Teal	\N	Lori	43
854	Turquoise	\N	Joyce	41
855	Red	\N	Paula	43
856	Fuscia	\N	Ronald	45
857	Yellow	\N	Jose	43
858	Mauv	\N	Teresa	43
859	Teal	\N	Keith	24
860	Red	\N	Ryan	26
861	Mauv	\N	Teresa	46
862	Green	\N	Kathy	41
863	Aquamarine	\N	Antonio	47
864	Fuscia	\N	Brian	43
865	Indigo	\N	Raymond	44
866	Red	\N	Jose	26
867	Aquamarine	\N	James	41
868	Fuscia	\N	Richard	43
869	Fuscia	\N	Diane	47
870	Blue	\N	Marie	43
871	Fuscia	\N	Carl	24
872	Red	\N	Ann	42
873	Fuscia	\N	Martin	43
874	Khaki	\N	Earl	44
875	Green	\N	Melissa	47
876	Aquamarine	\N	Nancy	26
877	Maroon	\N	Kenneth	47
878	Indigo	\N	Jose	45
879	Aquamarine	\N	Shirley	26
880	Blue	\N	Susan	44
881	Purple	\N	Louis	24
882	Fuscia	\N	Aaron	43
883	Purple	\N	Brenda	41
884	Purple	\N	Carlos	45
885	Khaki	\N	Patricia	42
886	Yellow	\N	Paul	43
887	Pink	\N	Sandra	43
888	Maroon	\N	Christopher	42
889	Teal	\N	George	45
890	Red	\N	Roger	46
891	Orange	\N	Denise	24
892	Khaki	\N	Alice	24
893	Indigo	\N	Matthew	42
894	Pink	\N	Samuel	26
895	Crimson	\N	Christopher	45
896	Yellow	\N	Steve	47
897	Pink	\N	Pamela	44
898	Indigo	\N	Anne	45
899	Aquamarine	\N	Joan	41
900	Violet	\N	Andrea	47
901	Maroon	\N	Harry	26
902	Puce	\N	Kelly	45
903	Goldenrod	\N	James	41
904	Orange	\N	Nicholas	43
905	Turquoise	\N	Joan	24
906	Crimson	\N	Donald	45
907	Puce	\N	Ryan	46
908	Yellow	\N	Lawrence	42
909	Teal	\N	Bobby	44
910	Maroon	\N	Evelyn	45
911	Violet	\N	Deborah	44
912	Red	\N	Clarence	43
913	Indigo	\N	Julia	46
914	Fuscia	\N	Douglas	47
915	Goldenrod	\N	Adam	26
916	Goldenrod	\N	Heather	44
917	Fuscia	\N	Louis	47
918	Khaki	\N	Randy	24
919	Yellow	\N	Lori	24
920	Turquoise	\N	Phillip	42
921	Aquamarine	\N	Christine	45
922	Teal	\N	Stephanie	44
923	Fuscia	\N	Shawn	45
924	Fuscia	\N	Brian	26
925	Blue	\N	Carl	46
926	Mauv	\N	James	46
927	Red	\N	Julia	45
928	Pink	\N	Jennifer	26
929	Turquoise	\N	Roger	41
930	Pink	\N	Robert	46
931	Khaki	\N	Philip	26
932	Khaki	\N	Peter	44
933	Mauv	\N	Walter	41
934	Mauv	\N	Kelly	44
935	Blue	\N	Jerry	47
936	Violet	\N	Earl	47
937	Blue	\N	Russell	46
938	Khaki	\N	Ashley	41
939	Puce	\N	Louise	45
940	Turquoise	\N	Roger	46
941	Purple	\N	Philip	26
942	Red	\N	Sean	47
943	Orange	\N	Martin	42
944	Red	\N	Denise	26
945	Teal	\N	Walter	41
946	Blue	\N	Matthew	43
947	Mauv	\N	Ashley	45
948	Puce	\N	Angela	42
949	Fuscia	\N	Louis	42
950	Crimson	\N	Annie	45
951	Maroon	\N	Jose	44
952	Aquamarine	\N	Keith	41
953	Maroon	\N	Lillian	43
954	Red	\N	Brandon	26
955	Khaki	\N	Evelyn	46
956	Teal	\N	Carol	26
957	Purple	\N	Tammy	24
958	Teal	\N	Raymond	43
959	Fuscia	\N	Roger	41
960	Khaki	\N	Steven	44
961	Fuscia	\N	Katherine	46
962	Puce	\N	Kevin	24
963	Red	\N	Bonnie	41
964	Crimson	\N	Lois	44
965	Blue	\N	Gloria	44
966	Yellow	\N	Carolyn	41
967	Mauv	\N	Ralph	43
968	Goldenrod	\N	Evelyn	45
969	Orange	\N	Kimberly	43
970	Crimson	\N	Keith	41
971	Blue	\N	Bruce	26
972	Violet	\N	Howard	47
973	Green	\N	Willie	46
974	Orange	\N	Brian	44
975	Yellow	\N	Susan	45
976	Mauv	\N	Ryan	42
977	Mauv	\N	Harry	42
978	Goldenrod	\N	Teresa	42
979	Blue	\N	Juan	41
980	Orange	\N	Gloria	41
981	Purple	\N	Daniel	44
982	Yellow	\N	Samuel	41
983	Violet	\N	Theresa	43
984	Pink	\N	Stephen	45
985	Mauv	\N	Shawn	26
986	Maroon	\N	Tammy	24
987	Indigo	\N	Betty	43
988	Aquamarine	\N	Fred	45
989	Indigo	\N	Kenneth	47
990	Maroon	\N	Juan	42
991	Turquoise	\N	Christina	45
992	Fuscia	\N	Roy	43
993	Turquoise	\N	Gerald	44
994	Puce	\N	Rose	42
995	Goldenrod	\N	Michelle	24
996	Fuscia	\N	Anne	24
997	Pink	\N	Gloria	47
998	Turquoise	\N	Willie	47
999	Turquoise	\N	Daniel	26
1000	Pink	\N	Sharon	45
\.


--
-- Data for Name: breed; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY breed (id, name, type_id) FROM stdin;
24	Calico	21
26	Border collie	22
41	Schauzer	22
42	Collie	22
43	Sheltie	22
44	Apaloosa	37
45	Clydesdale	37
46	Quarter	37
47	Tabby	21
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('hibernate_sequence', 47, true);


--
-- Data for Name: note; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY note (id, content, date, animal_id) FROM stdin;
\.


--
-- Data for Name: type; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY type (id, name) FROM stdin;
21	Cat
22	Dog
37	Horse
39	Snake
40	Lizard
\.


--
-- Name: animal_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);


--
-- Name: breed_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY breed
    ADD CONSTRAINT breed_pkey PRIMARY KEY (id);


--
-- Name: note_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY note
    ADD CONSTRAINT note_pkey PRIMARY KEY (id);


--
-- Name: type_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY type
    ADD CONSTRAINT type_pkey PRIMARY KEY (id);


--
-- Name: uk_3tg65hx29l2ser69ddfwvhy4h; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY type
    ADD CONSTRAINT uk_3tg65hx29l2ser69ddfwvhy4h UNIQUE (name);


--
-- Name: uk_k7fokt65aja7ald1r3ana2uwc; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY breed
    ADD CONSTRAINT uk_k7fokt65aja7ald1r3ana2uwc UNIQUE (name);


--
-- Name: fk30h5iofrmeyaw49dhel5cbra0; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY breed
    ADD CONSTRAINT fk30h5iofrmeyaw49dhel5cbra0 FOREIGN KEY (type_id) REFERENCES type(id);


--
-- Name: fk5vuppijm6mptl6xm5g9jhegwh; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT fk5vuppijm6mptl6xm5g9jhegwh FOREIGN KEY (breed_id) REFERENCES breed(id);


--
-- Name: fkaro2ok22aqw0go52grtagv2fd; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY note
    ADD CONSTRAINT fkaro2ok22aqw0go52grtagv2fd FOREIGN KEY (animal_id) REFERENCES animal(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

