PGDMP     *        
            {         
   Smart_Shop    14.5    15.2 /               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                        0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            !           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            "           1262    17302 
   Smart_Shop    DATABASE     �   CREATE DATABASE "Smart_Shop" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "Smart_Shop";
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            #           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    4            �            1259    17393    image    TABLE     ~   CREATE TABLE public.image (
    id integer NOT NULL,
    file_name character varying(255),
    product_id integer NOT NULL
);
    DROP TABLE public.image;
       public         heap    postgres    false    4            �            1259    17392    image_id_seq    SEQUENCE     �   CREATE SEQUENCE public.image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.image_id_seq;
       public          postgres    false    212    4            $           0    0    image_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;
          public          postgres    false    211            �            1259    17434 
   order_site    TABLE       CREATE TABLE public.order_site (
    id integer NOT NULL,
    count integer NOT NULL,
    number character varying(255),
    status smallint,
    "time" timestamp(6) without time zone,
    total_sum real NOT NULL,
    person_id integer NOT NULL,
    product_id integer NOT NULL
);
    DROP TABLE public.order_site;
       public         heap    postgres    false    4            �            1259    17433    order_site_id_seq    SEQUENCE     �   CREATE SEQUENCE public.order_site_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.order_site_id_seq;
       public          postgres    false    4    218            %           0    0    order_site_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.order_site_id_seq OWNED BY public.order_site.id;
          public          postgres    false    217            �            1259    17383    person    TABLE     x  CREATE TABLE public.person (
    id integer NOT NULL,
    age integer NOT NULL,
    email character varying(255),
    login character varying(100),
    name character varying(255),
    password character varying(255),
    phone character varying(255),
    role character varying(255),
    surname character varying(255),
    CONSTRAINT person_age_check CHECK ((age >= 18))
);
    DROP TABLE public.person;
       public         heap    postgres    false    4            �            1259    17382    person_id_seq    SEQUENCE     �   CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.person_id_seq;
       public          postgres    false    210    4            &           0    0    person_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;
          public          postgres    false    209            �            1259    17400    product    TABLE       CREATE TABLE public.product (
    id integer NOT NULL,
    category smallint NOT NULL,
    color character varying(255),
    description text NOT NULL,
    name text NOT NULL,
    price real NOT NULL,
    CONSTRAINT product_price_check CHECK ((price >= (1)::double precision))
);
    DROP TABLE public.product;
       public         heap    postgres    false    4            �            1259    17417    product_cart    TABLE     m   CREATE TABLE public.product_cart (
    id integer NOT NULL,
    person_id integer,
    product_id integer
);
     DROP TABLE public.product_cart;
       public         heap    postgres    false    4            �            1259    17416    product_cart_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_cart_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.product_cart_id_seq;
       public          postgres    false    216    4            '           0    0    product_cart_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.product_cart_id_seq OWNED BY public.product_cart.id;
          public          postgres    false    215            �            1259    17399    product_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public          postgres    false    4    214            (           0    0    product_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;
          public          postgres    false    213            q           2604    17396    image id    DEFAULT     d   ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);
 7   ALTER TABLE public.image ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212            t           2604    17437    order_site id    DEFAULT     n   ALTER TABLE ONLY public.order_site ALTER COLUMN id SET DEFAULT nextval('public.order_site_id_seq'::regclass);
 <   ALTER TABLE public.order_site ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            p           2604    17386 	   person id    DEFAULT     f   ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);
 8   ALTER TABLE public.person ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    209    210    210            r           2604    17403 
   product id    DEFAULT     h   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    213    214            s           2604    17420    product_cart id    DEFAULT     r   ALTER TABLE ONLY public.product_cart ALTER COLUMN id SET DEFAULT nextval('public.product_cart_id_seq'::regclass);
 >   ALTER TABLE public.product_cart ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216                      0    17393    image 
   TABLE DATA                 public          postgres    false    212   �3                 0    17434 
   order_site 
   TABLE DATA                 public          postgres    false    218   ?8                 0    17383    person 
   TABLE DATA                 public          postgres    false    210   ;:                 0    17400    product 
   TABLE DATA                 public          postgres    false    214   -<                 0    17417    product_cart 
   TABLE DATA                 public          postgres    false    216   �E       )           0    0    image_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.image_id_seq', 36, true);
          public          postgres    false    211            *           0    0    order_site_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.order_site_id_seq', 12, true);
          public          postgres    false    217            +           0    0    person_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.person_id_seq', 4, true);
          public          postgres    false    209            ,           0    0    product_cart_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.product_cart_id_seq', 19, true);
          public          postgres    false    215            -           0    0    product_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.product_id_seq', 12, true);
          public          postgres    false    213            z           2606    17398    image image_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.image DROP CONSTRAINT image_pkey;
       public            postgres    false    212            �           2606    17439    order_site order_site_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.order_site
    ADD CONSTRAINT order_site_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.order_site DROP CONSTRAINT order_site_pkey;
       public            postgres    false    218            x           2606    17391    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    210            �           2606    17422    product_cart product_cart_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT product_cart_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.product_cart DROP CONSTRAINT product_cart_pkey;
       public            postgres    false    216            |           2606    17408    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    214            ~           2606    17410 $   product uk_jmivyxk9rmgysrmsqw15lqr5b 
   CONSTRAINT     _   ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_jmivyxk9rmgysrmsqw15lqr5b UNIQUE (name);
 N   ALTER TABLE ONLY public.product DROP CONSTRAINT uk_jmivyxk9rmgysrmsqw15lqr5b;
       public            postgres    false    214            �           2606    17445 &   order_site fkamejt1sk9jg9xkackdwalmb1h    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_site
    ADD CONSTRAINT fkamejt1sk9jg9xkackdwalmb1h FOREIGN KEY (product_id) REFERENCES public.product(id);
 P   ALTER TABLE ONLY public.order_site DROP CONSTRAINT fkamejt1sk9jg9xkackdwalmb1h;
       public          postgres    false    214    218    3196            �           2606    17440 &   order_site fkb9lnld8esl0jpeog6kqvsly2r    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_site
    ADD CONSTRAINT fkb9lnld8esl0jpeog6kqvsly2r FOREIGN KEY (person_id) REFERENCES public.person(id);
 P   ALTER TABLE ONLY public.order_site DROP CONSTRAINT fkb9lnld8esl0jpeog6kqvsly2r;
       public          postgres    false    3192    210    218            �           2606    17411 !   image fkgpextbyee3uk9u6o2381m7ft1    FK CONSTRAINT     �   ALTER TABLE ONLY public.image
    ADD CONSTRAINT fkgpextbyee3uk9u6o2381m7ft1 FOREIGN KEY (product_id) REFERENCES public.product(id);
 K   ALTER TABLE ONLY public.image DROP CONSTRAINT fkgpextbyee3uk9u6o2381m7ft1;
       public          postgres    false    212    3196    214            �           2606    17428 (   product_cart fkhpnrxdy3jhujameyod08ilvvw    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fkhpnrxdy3jhujameyod08ilvvw FOREIGN KEY (product_id) REFERENCES public.product(id);
 R   ALTER TABLE ONLY public.product_cart DROP CONSTRAINT fkhpnrxdy3jhujameyod08ilvvw;
       public          postgres    false    216    214    3196            �           2606    17423 (   product_cart fksgnkc1ko2i1o9yr2p63ysq3rn    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fksgnkc1ko2i1o9yr2p63ysq3rn FOREIGN KEY (person_id) REFERENCES public.person(id);
 R   ALTER TABLE ONLY public.product_cart DROP CONSTRAINT fksgnkc1ko2i1o9yr2p63ysq3rn;
       public          postgres    false    3192    216    210               X  x����n\7��y��%�I�$
]u��� ��[C?T2m�S�i�>}���]�]�ذ�K^�;����o~�p�~�������ط�m�h�W�qu���vsWo����x?�����x}��Ƿ��yx�/�h�y�X<�j V(&v2��g�n�w7_��������ǗW���ח�����zQ2���gG͙#<�%M�����^x^xm(�%j:ɜ(~S֜�$�=ȶL �>��!��$A<U�b����F;��L7�O{<�s��R~\�lVn$�i��Y��b�x�{����߻���S$<�(�U^��S0]�3�3��FH�y��cW��#RI���|�f��Z���[�O�?���K@W��s6hT�(�t����a�b��+��.G�4zG�j��a���H���]`ޭ����
NR]���ruX�9=���n΍����t�Jh��A~*�L��0�<�it/�-O����!N��fR�I}4�ط�|؇���CјҠ���"0����Ф���>��cO��Ƌ��TnV�����G��F�=7_�n������&]��XQ-��fdeQ.�b�6�>���6�Νp��l,(:�'�>��t����	"x�[=MsO*2��Q�Ou$ÌZuL���%R�]h�;�*�U"^W'��l�ڊ�}hK<��b̈́0�����aGH�cT��k�����|��L$:M�+�	�ʄ_�U��{rü���.����~�� ��
�Q�>(�st���-�L�h�1)/��@x����+��=��s�nY��L�g�<G�Z>�R�ݐ_%��M��m��sK��A�H��4�D�"7�/-�߇����H,��51�7�:�Ie�٭�Q+}���2(����W�dJ�2��u���<'^�PNI}9m�gp�-�B�`��6�(��^�������N��a�8�[kة�3<��H�N����s[;��c=wֻ���EEq���0A֧��.)3��ɳ��x��H

�k�LP��$*���=3��pkB�u�8��ԗ�B��i^}��������<�~��ۋ�i^�FIB#܍�6	�%����o�����)i����~�KC�E�ΤĄk���w܋�3q_
         �  x���Kk1�{>���	�͌F����%�&���	������+�m1�G���0�ˏ�|}{��.�����~�����V��X��:w"���؉����Љ���؉�q�)��wc�X�M'��p�mW����]��������o?_��t��E��AV���d�s2�b��cZt�Z)*$��' �z�Y�
�{�����a9�o�`�5]:pf�."��^"a�����j�`����q@�#��
�gv��8�j�.�A3;��0�8��˨���P�J�^���"��z@ճfxϹ2�q畿g?�ّe��du���j�\Q҄�R�T��70��I{��}�n��`�]�ʟb-Uژ��l�����b��ȁO�.�6��v[i8�����8�wQ5�૑dO�#�#��}&��?K� 5p�X���n#aO.3����C&]�ԉۃ)�<���{N���+����2}�r�9~����         �  x����n�@ �{�bH$�e��M�Ā1Sp����S/c�`�I�U��3��j��B��������Y�����f-�-�N�wt���=�S y.��!�pL�Eu-AYV����$�)	�6+Ҧ���d�-�=G^�@��S�8F��r�^�"�#Q��B��M0pc�5A�C-�i�S��pd�eX�:,M7ߍ;7�d
�t^�~X
�$�*��7�e�F��.פ�1Ѿ��)�5�ꡄ�������ۋ�M�&�B|AG7G7��S����σ�Yt��b���T��x���mOx� ����,ǚI��7��K���l��U[�d�v��� �ls����WvZ\կ/S=W/�����^��/th�N����fCE��`gOh�Ի��;��d�y���DI�%Ya�B_��~V/�������=Q��io5o�Ք�G=�c,��Ȭ�!E�f>�b]��o^�1S����������.���z@�         a	  x��Y]o�}��p�"�*�v�OI��Fm���
�--�E(Q�)�@��톎e)�h� @�TtM�?D�/������93w�$7n�!#��ݽw�ܙ3g>|����Ƿ��[��v������jeko��í��,Ղ{��g�U)W��<ج�����Sp;����v��f��~���;k�nq��V
���u܍�I+�+O�q7i�gI=�m�߉G����#J&O�^<��6~c�(�%���\<������x���*y�&~ʗ�x,rF���T
%������l^v��x ������a�x�4�E(tq;i�_����.u�.WhB�(���X	r�a�sv>N9I�t��<bΒf��&$#3�B��<�x�p�BA.�
s�p��qr��q�����Ԭ��[v��Wor�1kI��෥j�ݬ"�z��R���+WV�~�p����/����^ݓq�V�v ��CBA�ЅӞ�S�zA$u�~�������=B(2uS�T�&���08�ۺ�����>9��_�#֝�m�5��q̘x��I�&ea�<��-�KOHZ㩷����i+-��y���D�.�8��4[ߋC��Gt,��j�L4��p�q����Xqy�ٻ�f=���W��?�T�����6�ʧo���lO\��9g�w�_�$��#��P񉘞0K�+y��}��HтE�#�t�.C��g@�
�W.��n�F�ڔ��@c��|���M�D����1x)����)�x����Ζ{�r8T���(��N���gVݍ�������\|3��!r�:3�&C�ls�����\�o��w�=O=�H�O�D���g9�|��4E)�x���kk&Âg��N��4٤�\6�,+�'�f8/�s��
��� �:=�)F]z;�����^5`�_�����go��� |���-���h+�P�>�H��%��T0vCo}.�����3�3���r����0.x*��iOrة�� �19��9��+o_��E�� S��o��5���޽������R�(!D��]g�,������I��T ��R���⯅;��g�7���������`��]�
M^��[�E�>a ��0k��(,1�uS��k���)t���Hbw�X_<7��9D�g%�ar(Y_�Vf��4�4L�{*d�c��y���!^V.��-��d_,�V��u����&��z�����5#���Zie�&qH(i��4�������y�s���F�\v럆����ڪ[��7��}w+ثUK���"pW6?	�KP����r��\w���A�lEt��/���P�~)npZ���l<2A�dC[ɪs�F����f�1[�W^��"*�5�foy�B�U��=�.��ǲ��7$���"��x�w��k��@�ZF��tJ���P�C#F���9>𥛖�|�@�\yhu�0Ͳ��Z�)5�&Z����7��(酾ࢰ5)����'��'>ꬔH������D�w���H�MݎHls�������Hk�	Ь�
�A9x���wB�s���܅���V;3M�Y����|��)[W������	e�+,��Yw\uV�٪l��W"%�j�ݦ����o��~��a�����%zS2�!D���1�T�-�Z��(f��Q��k3�ƒOiX�e3퇼�*0#߳����ݎ`D&���2Ǒv��z�B�*e63�ҭ��\�opb#w������%�C�̭(���W��\�!q5I��k�n�}�n����܇{���� O������ͅL��6��ٻ�n�����l����F��/�:��f�x�x��o�{w��]X^�4|�̤��-������h���~^��#GrQ�KBK�N�e^N���|L��3���dH��v��Ĺ�`u%���������HV���PV�nΦ1'e�Tե�$K<�	��k�(;jC7ꛇ��i���&C�?���<�\�%�`�0�Y�Ug;5��1��R'�-��G��'��NJ�7apD���E�����I�.���Ȇ�t7-t�1���k�d�
#�'A�Sq�{}��S�� kc����^Q����4��2S�0�J7�X���,"�H������te
L���=��}�K���^*��NSN��ΗR
OZ���ޛ���!�U��)8Q�#�����Z9|"���B'Ѿ����M=`.�6,�+`�rt���U#���QL�%�ʹ��٢%�8��#(����h����g�����^P�T�]Z^q�ע�`6�s��-��g�HĹ^O�K��f+��m�K���J^i�pʿ64O�a���!ՙ����>Hy��j��O�':w� �pm�c�a^�/j��Ӓ����x���Ḱ�/�8�K         O   x���v
Q���W((M��L�+(�O)M.�ON,*Q��L�Q(H-*�ϋ3���)�
a�>���
��:
�@�i��� _	�     