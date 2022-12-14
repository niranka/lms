PGDMP                         z            lms_db    14.3    14.3                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    57921    lms_db    DATABASE     b   CREATE DATABASE lms_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_India.1252';
    DROP DATABASE lms_db;
                postgres    false            ?            1259    57923    book    TABLE     B  CREATE TABLE public.book (
    book_id bigint NOT NULL,
    author character varying(255) NOT NULL,
    available boolean,
    category character varying(255) NOT NULL,
    isbn character varying(255) NOT NULL,
    publisher character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    std_id bigint
);
    DROP TABLE public.book;
       public         heap    postgres    false            ?            1259    57922    book_book_id_seq    SEQUENCE     y   CREATE SEQUENCE public.book_book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.book_book_id_seq;
       public          postgres    false    210                       0    0    book_book_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.book_book_id_seq OWNED BY public.book.book_id;
          public          postgres    false    209            ?            1259    57949    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            ?            1259    57932    student    TABLE     ?   CREATE TABLE public.student (
    student_id bigint NOT NULL,
    batch character varying(255) NOT NULL,
    course character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    student_name character varying(255) NOT NULL
);
    DROP TABLE public.student;
       public         heap    postgres    false            ?            1259    57931    student_student_id_seq    SEQUENCE        CREATE SEQUENCE public.student_student_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.student_student_id_seq;
       public          postgres    false    212            	           0    0    student_student_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.student_student_id_seq OWNED BY public.student.student_id;
          public          postgres    false    211            ?            1259    57940    userinfo    TABLE     ?   CREATE TABLE public.userinfo (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(20) NOT NULL
);
    DROP TABLE public.userinfo;
       public         heap    postgres    false            f           2604    57926    book book_id    DEFAULT     l   ALTER TABLE ONLY public.book ALTER COLUMN book_id SET DEFAULT nextval('public.book_book_id_seq'::regclass);
 ;   ALTER TABLE public.book ALTER COLUMN book_id DROP DEFAULT;
       public          postgres    false    209    210    210            g           2604    57935    student student_id    DEFAULT     x   ALTER TABLE ONLY public.student ALTER COLUMN student_id SET DEFAULT nextval('public.student_student_id_seq'::regclass);
 A   ALTER TABLE public.student ALTER COLUMN student_id DROP DEFAULT;
       public          postgres    false    211    212    212            ?          0    57923    book 
   TABLE DATA           d   COPY public.book (book_id, author, available, category, isbn, publisher, title, std_id) FROM stdin;
    public          postgres    false    210   ?       ?          0    57932    student 
   TABLE DATA           Q   COPY public.student (student_id, batch, course, email, student_name) FROM stdin;
    public          postgres    false    212   $                  0    57940    userinfo 
   TABLE DATA           A   COPY public.userinfo (id, email, password, username) FROM stdin;
    public          postgres    false    213   h       
           0    0    book_book_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.book_book_id_seq', 2, true);
          public          postgres    false    209                       0    0    hibernate_sequence    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hibernate_sequence', 1, true);
          public          postgres    false    214                       0    0    student_student_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.student_student_id_seq', 2, true);
          public          postgres    false    211            i           2606    57930    book book_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);
 8   ALTER TABLE ONLY public.book DROP CONSTRAINT book_pkey;
       public            postgres    false    210            k           2606    57939    student student_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (student_id);
 >   ALTER TABLE ONLY public.student DROP CONSTRAINT student_pkey;
       public            postgres    false    212            m           2606    57948 %   userinfo uk_mr4rtvun5wakeubj0tvjr98l8 
   CONSTRAINT     a   ALTER TABLE ONLY public.userinfo
    ADD CONSTRAINT uk_mr4rtvun5wakeubj0tvjr98l8 UNIQUE (email);
 O   ALTER TABLE ONLY public.userinfo DROP CONSTRAINT uk_mr4rtvun5wakeubj0tvjr98l8;
       public            postgres    false    213            o           2606    57946    userinfo userinfo_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.userinfo
    ADD CONSTRAINT userinfo_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.userinfo DROP CONSTRAINT userinfo_pkey;
       public            postgres    false    213            p           2606    57950     book fk1nb9862c887lown9k73mcocoh    FK CONSTRAINT     ?   ALTER TABLE ONLY public.book
    ADD CONSTRAINT fk1nb9862c887lown9k73mcocoh FOREIGN KEY (std_id) REFERENCES public.student(student_id);
 J   ALTER TABLE ONLY public.book DROP CONSTRAINT fk1nb9862c887lown9k73mcocoh;
       public          postgres    false    210    212    3179            ?   ?   x???A
?0 ?????Rm?zC<?P?^{	??,M?%???{??? ?ȉ7|6h?/?s???ʒ???Y?????p??m?O?պ????\??	?Ȳ?????;?BnE	xO?ka?c$ڮ0??????S???
iL(      ?   4   x?3?4204?trv???,J??N,rH?M??I?υ?p?TYp??W???? ???          i   x?3???,J??N,r Q%??y?9?险?z????*F?*?*???AUA?.??I?~NU^نzEz?z?N?.^??9ŖUFY??INz??9??zp??b???? ?~"d     