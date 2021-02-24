insert into utilisateur values ('GNUIsNotPenguin','1234','Beltramo','Emeric',NOW(),74.0,183);

insert into activite_distance(nom,description,distance) values ('course 500m','',500);
insert into activite_distance(nom,description,distance) values ('entrainement 500m','',500);
insert into entrainement(type_activite,etat,date,identifiant_utilisateur) values (2,0,NOW(),'GNUIsNotPenguin');
insert into course(type_activite,etat,date) values (1,0,NOW());
insert into mesure (identifiant_utilisateur,date,id_course, id_entrainement,distance_parcourue) values('GNUIsNotPenguin', NOW(),1,null,25);
insert into mesure (identifiant_utilisateur,date,id_course, id_entrainement,distance_parcourue) values('GNUIsNotPenguin', NOW(),null,1,50);



select AVG(vitesse) as vitesse_moyenne, sum(ad.distance) as distance_parcourue_total, sum(ac.calorie_brulee) as total_calories_brulees, AVG(puissance_developpe) as puissance_developpe_moyenne, AVG(rythme_cardiaque) as rythme_cardiaque_moyen
from mesure me
left join entrainement ent on ent.id = me.id_entrainement
left join course co on co.id = me.id_course
left join activite_distance ad on ent.type_activite= ad.id
left join activite_calories ac on ent.type_activite = ac.id
where me.identifiant_utilisateur='GNUIsNotPenguin' and ent.type_activite=2

select MAX(me.distance_parcourue) as distance_parcourue from mesure me
join course co on co.id=me.id_course
join assoc_utilisateur_course auc on auc.id_course=co.id 
where auc.identifiant_utilisateur='GNUIsNotPenguin' and co.type_activite=1
group by (co.id);