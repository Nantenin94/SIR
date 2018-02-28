Ce fichier Design.md permet de decrire l'architecture du projet

-JPA
La partie JPA se trouve dans le package Model qui contient 5 classes dont une classe d'héritage.

-JAX-RS
Le JAX-RS est situé  dans le package "rest" qui contient 4 classes liés au Web Service de l'application. Dans ces classes, sont définies : les routes et les fonctions qui leurs sont liées, le type de requête HTTP.

-Le Servlet
Le servlet se trouve dans le package "servlet". Il contient une classe PersonServlet qui contient une méthode POST et une méthode GET. Elles permettent respectivement de créer une personne en renseignant ses attributs et de récupérer l'ensemble des personnes présentes en base de données.



