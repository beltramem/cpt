--suppression procedure existante
drop procedure maj_mesure_moyenne;

-- suppression fonction existante
drop function if exists calorie_totale_utilisateur_type_entrainement;
drop function if exists distance_totale_utilisateur_type_entrainement;
drop function if exists get_mesure_moyenne_utilisateur_type_activite;



-- drop table existante
drop table if exists mesure_moyenne;
drop table if exists mesure;
drop table if exists assoc_utilisateur_course;
drop table IF EXISTS  course;
drop table IF EXISTS  entrainement;
drop table IF EXISTS  activite_libre;
drop table IF EXISTS activite_calories;
drop table IF EXISTS  activite_duree;
drop table IF EXISTS activite_distance;
drop table IF EXISTS type_activite;
Drop table IF EXISTS utilisateur;
Drop table IF EXISTS participe_course;

drop function if exists chk_foreignkey_heritage;

CREATE FUNCTION chk_foreignkey_heritage(type_act integer)
returns boolean
AS $$
BEGIN 
	IF EXISTS (select * from type_activite where id=type_act) then
		return TRUE;
	end if;
	return FALSE;
END $$
LANGUAGE plpgsql;


-- création table

CREATE table utilisateur
(
	identifiant varchar(20) PRIMARY KEY,
    mdp varchar(20) not null,
    nom varchar(20) not null,
    prenom varchar(20) not null,
    date_naissance date not null,
    poids real not null,
    taille real not null
);

CREATE table type_activite
(
	id serial PRIMARY KEY,
	nom Varchar(50) not null,
	description text
);

-- INHERITS pour l'héritage
CREATE table activite_distance
(
	distance real not null
) INHERITS(type_activite);

CREATE table activite_duree
(
	duree real
) INHERITS(type_activite);

CREATE table activite_calories
(
	calorie_brulee int
) INHERITS(type_activite);

CREATE table activite_libre
(

)INHERITS(type_activite);

create table entrainement
(
	id serial primary key,
	type_activite int not null,
	etat int not null,
	date date not null
	constraint chk_type_entrainement_exist check (chk_foreignkey_heritage(type_activite)= TRUE )
);

create table course
(
	id serial primary key,
	type_activite int not null,
	etat int not null,
	date date not null
	constraint chk_type_course_exist check (chk_foreignkey_heritage(type_activite)= TRUE )
);

create table mesure
(
	id serial primary key,
	identifiant_utilisateur varchar(20) not null,
	id_course int null,
	id_entrainement int null,
	date date not null,
	vitesse real,
	distance_parcourue real,
	calories_brulees int,
	puissance_developpe real,
	rythme_cardiaque int,
	foreign key (id_course) references course(id),
	foreign key (id_entrainement) references entrainement(id),
	foreign key (identifiant_utilisateur) references utilisateur(identifiant),
	constraint chk_activite_not_null check (id_course is not null or id_entrainement is not null),
	constraint chk_not_course_and_entrainement check (id_course is null or id_entrainement is null)
);

create table mesure_moyenne
(
	identifiant_utilisateur varchar(20)not null,
	type_activite int not null,
	vitesse_moyenne real,
	distance_parcourue_totale real,
	total_calories_brulees real,
	puissance_developpe_moyenne real,
	rythme_cardiaque_moyen real,
	primary key (identifiant_utilisateur,type_activite),
	foreign key (identifiant_utilisateur) references utilisateur(identifiant),
	foreign key (type_activite) references type_activite(id)
);

create table participe_course
(
	utilisateur varchar(20) not null,
	course int not null,
	foreign key (course) references course(id),
	foreign key (utilisateur) references utilisateur(identifiant),
	PRIMARY KEY (utilisateur, course)
)
--creation  fonction



create function get_mesure_moyenne_utilisateur_type_activite(id_utilisateur varchar(20),type_act int) returns setof mesure_moyenne as 'select * from mesure_moyenne where identifiant_utilisateur=id_utilisateur and type_activite=type_act;' language 'sql';



create function distance_totale_utilisateur_type_entrainement(p_id_utilisateur varchar(20),p_type_act int)
returns real
AS $$
DECLARE 
	v_distance_total real DEFAULT 0;
	cur_entrainement cursor(p_id_utilisateur varchar(20),p_type_act int) 
					for select MAX(me.distance_parcourue) as distance_parcourue from mesure me
					join entrainement ent on ent.id=me.id_entrainement
					where ent.identifiant_utilisateur=p_id_utilisateur and ent.type_activite=p_type_act
					group by (me.id_entrainement);
	rec_entrainement record;
	
	cur_course cursor(p_id_utilisateur varchar(20),p_type_act int)
				for select MAX(me.distance_parcourue) as distance_parcourue from mesure me
				join course co on co.id=me.id_course
				where me.identifiant_utilisateur=p_id_utilisateur and co.type_activite=p_type_act
				group by (co.id);
				
	rec_course record;
BEGIN 
	open cur_entrainement(p_id_utilisateur,p_type_act);
	
	loop 
		fetch cur_entrainement into rec_entrainement;
		exit when not found;
		if rec_entrainement.calories_brulees >= 0 then
		v_distance_total := v_distance_total + rec_entrainement.distance_parcourue;
		end if;
	end loop;
	close cur_entrainement;
	
	open cur_course(p_id_utilisateur,p_type_act);
	
	loop 
		fetch cur_course into rec_course;
		exit when not found;
		if rec_course.distance_parcourue >= 0 then
		v_distance_total := v_distance_total + rec_course.distance_parcourue;
		end if;
	end loop;
	close cur_course;
	
	return v_distance_total; 	
	
end $$
LANGUAGE plpgsql;



create function calorie_totale_utilisateur_type_entrainement(p_id_utilisateur varchar(20),p_type_act int)
returns real
AS $$
DECLARE 
	v_calorie_total real DEFAULT 0;
	cur_entrainement cursor(p_id_utilisateur varchar(20),p_type_act int) 
					for select MAX(me.calories_brulees) as calories_brulees from mesure me
					join entrainement ent on ent.id=me.id_entrainement
					where ent.identifiant_utilisateur=p_id_utilisateur and ent.type_activite=p_type_act
					group by (me.id_entrainement);
	rec_entrainement record;
	
	cur_course cursor(p_id_utilisateur varchar(20),p_type_act int)
				for select MAX(me.calories_brulees) as calories_brulees from mesure me
				join course co on co.id=me.id_course
				where me.identifiant_utilisateur=p_id_utilisateur and co.type_activite=p_type_act
				group by (co.id);
				
	rec_course record;
BEGIN 
	open cur_entrainement(p_id_utilisateur,p_type_act);
	
	loop 
		fetch cur_entrainement into rec_entrainement;
		exit when not found;
		if rec_entrainement.calories_brulees >= 0 then
		v_calorie_total := v_calorie_total + rec_entrainement.calories_brulees;
		end if;
	end loop;
	close cur_entrainement;
	
	open cur_course(p_id_utilisateur,p_type_act);
	
	loop 
		fetch cur_course into rec_course;
		exit when not found;
		if rec_course.calories_brulees >= 0 then
		v_calorie_total := v_calorie_total + rec_course.calories_brulees;
		end if;

	end loop;
	close cur_course;
	
	return v_calorie_total; 	
	
end $$
LANGUAGE plpgsql;


-- creation procedure

CREATE PROCEDURE maj_mesure_moyenne(IN p_identifiant_utilisateur varchar(20),IN p_type_activite int)
LANGUAGE plpgsql
AS
$$
DECLARE
	v_vitesse_moyenne mesure_moyenne.vitesse_moyenne%type;
	v_distance_parcourue_totale mesure_moyenne.distance_parcourue_totale%type;
	v_total_calories_brulees mesure_moyenne.total_calories_brulees%type;
	v_puissance_developpe_moyenne mesure_moyenne.puissance_developpe_moyenne%type;
	v_rythme_cardiaque_moyen mesure_moyenne.rythme_cardiaque_moyen%type;
	
BEGIN
	select distance_totale_utilisateur_type_entrainement(p_identifiant_utilisateur,p_type_activite) into v_distance_parcourue_totale;
	select calorie_totale_utilisateur_type_entrainement(p_identifiant_utilisateur,p_type_activite) into v_total_calories_brulees;

	select AVG(vitesse) as vitesse_moyenne, AVG(puissance_developpe) as puissance_developpe_moyenne, AVG(rythme_cardiaque) as rythme_cardiaque_moyen
	into v_vitesse_moyenne, v_puissance_developpe_moyenne, v_rythme_cardiaque_moyen
	from mesure me
	left join entrainement ent on ent.id = me.id_entrainement
	left join course co on co.id = me.id_course
	where me.identifiant_utilisateur=p_identifiant_utilisateur and (ent.type_activite=p_type_activite or co.type_activite=p_type_activite);
	
	if exists (select get_mesure_moyenne_utilisateur_type_activite(p_identifiant_utilisateur,p_type_activite)) then
		update mesure_moyenne 
		set 
		vitesse_moyenne = v_vitesse_moyenne,
		distance_parcourue_totale = v_distance_parcourue_totale,
		total_calories_brulees = v_total_calories_brulees,
		puissance_developpe_moyenne = v_puissance_developpe_moyenne,
		rythme_cardiaque_moyen = v_rythme_cardiaque_moyen
		where mesure_moyenne.identifiant_utilisateur = p_identifiant_utilisateur and mesure_moyenne.type_activite = p_type_activite;
	else
		insert into mesure_moyenne(identifiant_utilisateur, type_activite, vitesse_moyenne, distance_parcourue_totale, total_calories_brulees, puissance_developpe_moyenne, rythme_cardiaque_moyen) values (p_identifiant_utilisateur, p_type_activite, v_vitesse_moyenne, v_distance_parcourue_totale, v_total_calories_brulees, v_puissance_developpe_moyenne, v_rythme_cardiaque_moyen);
	end if;
	

END$$;
