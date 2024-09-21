--
-- Base de données : `forum`
--

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `datetime` datetime(6) DEFAULT NULL,
  `message` text NOT NULL,
  `sujet_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `datetime`, `message`, `sujet_id`, `user_id`) VALUES
(1, '2024-09-15 21:46:54.000000', '<p><strong>Dragon Ball</strong> est une série de mangas et d\'animations créée par Akira Toriyama. L\'histoire suit principalement les aventures de Son Goku, un jeune garçon doté d\'une force exceptionnelle et d\'une queue de singe, qui découvre qu\'il est en fait un extraterrestre de la race des Saiyans.</p>', 1, 1),
(2, '2024-09-15 21:47:14.000000', '<p>La série commence avec Goku et ses aventures pour retrouver les sept Dragon Balls, des boules de cristal magiques capables d\'exaucer un souhait lorsqu\'elles sont réunies. Tout au long de son voyage, il rencontre divers amis et rivaux, tels que Bulma, Krillin et Piccolo, et fait face à de puissants ennemis comme le Roi Démon Piccolo, Freezer, Cell et Majin Buu.</p>', 1, 1),
(3, '2024-09-15 21:48:30.000000', '<p>F-Zero est une série de jeux vidéo de course futuristes développée par Nintendo. Le jeu se déroule dans un univers où des pilotes participent à des courses de haute vitesse sur des pistes aériennes et des circuits dangereux. Ces courses se déroulent dans des véhicules appelés \"F-Zero machines\", qui peuvent atteindre des vitesses incroyables.</p>', 2, 1),
(4, '2024-09-15 21:48:40.000000', '<p>L\'un des éléments clés de F-Zero est la vitesse. Les courses sont intenses, avec des virages serrés et des obstacles, et le joueur doit maîtriser la gestion de la vitesse et la stratégie pour éviter les collisions et devancer les concurrents.</p>', 2, 1),
(5, '2024-09-15 21:49:14.000000', '<p>La série est également connue pour son univers riche et ses personnages colorés. Chaque pilote a son propre véhicule et ses propres caractéristiques, et les courses se déroulent dans divers environnements futuristes.</p>', 2, 3),
(6, '2024-09-15 21:49:24.000000', '<p>Le premier jeu, <i>F-Zero</i>, est sorti en 1990 sur la Super Nintendo Entertainment System (SNES). Il a été suivi par plusieurs suites et spin-offs sur différentes consoles Nintendo, comme la Nintendo 64, la GameCube et la Wii U.</p>', 2, 1),
(7, '2024-09-15 21:51:17.000000', '<p><i>Dragon Ball</i> est divisé en plusieurs parties :</p><p><strong>Dragon Ball</strong> : La première partie suit les débuts de Goku, ses premiers amis et ses aventures pour trouver les Dragon Balls et participer à des tournois d\'arts martiaux.</p><p><strong>Dragon Ball Z</strong> : Cette partie se concentre sur les combats plus épiques et les ennemis plus puissants. Elle couvre les arcs narratifs majeurs comme la saga des Saiyans, la saga de Freezer, la saga de Cell et la saga de Majin Buu.</p><p><strong>Dragon Ball GT</strong> : Une suite non basée sur le manga original, où Goku est transformé en enfant et doit retrouver les Dragon Balls à travers l\'univers.</p><p><strong>Dragon Ball Super</strong> : Une continuation directe de <i>Dragon Ball Z</i>, qui introduit de nouveaux personnages, de nouvelles transformations et des univers parallèles.</p>', 1, 3),
(8, '2024-09-15 21:51:32.000000', '<p>La série est connue pour ses scènes de combat spectaculaires, ses transformations épiques comme le Super Saiyan, et son mélange d\'humour, d\'action et de drame. <i>Dragon Ball</i> est devenue une franchise mondiale, influençant de nombreuses œuvres et gagnant une immense popularité dans le monde entier.</p>', 1, 1),
(9, '2024-09-15 21:52:45.000000', '<p><strong>One Piece</strong> est un manga créé par Eiichiro Oda. Il raconte les aventures de Monkey D. Luffy, un jeune pirate au corps élastique grâce à un Fruit du Démon qu\'il a mangé, et de son équipage dans leur quête pour trouver le légendaire trésor connu sous le nom de \"One Piece\" et devenir le Roi des Pirates.</p>', 3, 1),
(10, '2024-09-15 22:00:18.000000', '<p>L\'histoire se déroule dans un monde vaste et fantastique composé de mers, d\'îles et de royaumes divers. Luffy part en mer avec son premier compagnon, Roronoa Zoro, un épéiste aux trois épées, et au fur et à mesure, il recrute d\'autres membres pour son équipage, chacun ayant ses propres rêves et compétences uniques. Parmi les membres importants de l\'équipage figurent Nami, une navigatrice; Usopp, un tireur d\'élite et inventeur; Sanji, un chef cuisinier et combattant; Tony Tony Chopper, un médecin et renne transformé par un Fruit du Démon; Nico Robin, une archéologue; Franky, un cyborg constructeur; Brook, un musicien squelettique; et Jinbe, un homme-poisson et maître du karaté des poissons.</p>', 3, 3),
(11, '2024-09-15 22:00:27.000000', '<p>Luffy et son équipage naviguent à travers les \"Grands Line\", une mer dangereuse et inexplorée, où ils affrontent des ennemis puissants, des pirates rivaux, la marine, et découvrent des secrets cachés sur l\'histoire et la culture de leur monde. Le manga est connu pour ses personnages charismatiques, ses arcs narratifs complexes et ses thèmes d\'aventure, d\'amitié et de liberté.</p>', 3, 1),
(12, '2024-09-15 22:00:34.000000', '<p><i>One Piece</i> est l\'une des séries de mangas les plus populaires et les plus vendues de tous les temps, et elle a également été adaptée en anime, films et divers produits dérivés.</p>', 3, 3),
(13, '2024-09-15 22:01:22.000000', '<p><strong>Fairy Tail</strong> est un manga créé par Hiro Mashima. L\'histoire se déroule dans un monde fantastique où la magie est omniprésente et suit les aventures de Lucy Heartfilia, une jeune mage qui rêve de rejoindre la célèbre guilde de magiciens, Fairy Tail.</p>', 4, 1),
(14, '2024-09-15 22:01:57.000000', '<p>L\'histoire commence lorsque Lucy, qui est une mage ayant la capacité d\'invoquer des esprits célestes, rencontre Natsu Dragneel, un membre de Fairy Tail en quête du dragon qui a disparu, Igneel. Natsu est un mage de feu et a un caractère fougueux et impulsif. Après plusieurs événements, Lucy rejoint Fairy Tail, où elle rencontre d\'autres membres de l\'équipage tels que Gray Fullbuster, un mage de glace souvent en conflit avec Natsu, et Erza Scarlet, une mage avec une capacité unique de changer d\'armure et d\'armes en un instant.</p>', 4, 3),
(15, '2024-09-15 22:02:04.000000', '<p>La série suit les aventures de cette guilde à travers divers arcs narratifs, y compris des missions de guildes, des combats contre des ennemis puissants, et la recherche de trésors légendaires. Les membres de Fairy Tail affrontent des adversaires, résolvent des mystères et participent à des batailles épiques tout en cherchant à protéger leurs amis et leur guilde.</p>', 4, 1),
(16, '2024-09-15 22:02:13.000000', '<p>Les thèmes principaux de <i>Fairy Tail</i> incluent l\'amitié, le courage, et le dépassement des obstacles personnels. Le manga est connu pour ses personnages colorés, ses combats dynamiques, et son esprit d\'aventure. <i>Fairy Tail</i> a également été adapté en anime, avec plusieurs saisons et films.</p>', 4, 3),
(17, '2024-09-15 22:03:01.000000', '<p><i>Mega Man</i> est une série de jeux vidéo développée par Capcom. Créée par Akira Kitamura et sortie pour la première fois en 1987 sur la NES (Nintendo Entertainment System), la série suit les aventures d\'un robot nommé Mega Man, également connu sous le nom de Rockman au Japon.</p>', 5, 1),
(18, '2024-09-15 22:03:12.000000', '<p>L\'histoire se déroule dans un futur où la technologie robotique est omniprésente. Mega Man est un robot créé par le Dr. Light, un scientifique bienveillant. Après que son créateur, le Dr. Light, ait été trahi par son ancien collègue, le Dr. Wily, qui utilise ses robots pour semer le chaos, Mega Man se transforme en un héros et se bat pour arrêter les plans malveillants du Dr. Wily.</p>', 5, 3),
(19, '2024-09-15 22:03:17.000000', '<p>Les jeux <i>Mega Man</i> se caractérisent par leur gameplay de plateforme en 2D, où le joueur contrôle Mega Man à travers divers niveaux, chacun gardé par un Robot Master. Chaque Robot Master a des capacités uniques que Mega Man peut acquérir après les avoir vaincus. Par exemple, après avoir battu le Robot Master \"Guts Man\", Mega Man peut utiliser la capacité de lancer de gros blocs de pierre, connue sous le nom de \"Guts Dozer\".</p>', 5, 1),
(20, '2024-09-15 22:03:35.000000', '<p>La série est connue pour son niveau de difficulté, ses mécaniques de jeu innovantes, et ses musiques mémorables. En plus des jeux principaux, la franchise <i>Mega Man</i> a donné naissance à plusieurs spin-offs, dont <i>Mega Man X</i>, <i>Mega Man Battle Network</i>, <i>Mega Man Legends</i>, et <i>Mega Man Zero</i>. Ces spin-offs explorent différents styles de jeu et des univers alternatifs tout en restant fidèles à l\'esprit de la série originale.</p>', 5, 2);

-- --------------------------------------------------------

--
-- Structure de la table `sujet`
--

CREATE TABLE `sujet` (
  `id` int(11) NOT NULL,
  `datetime` datetime(6) DEFAULT NULL,
  `titre` varchar(255) NOT NULL,
  `auteur_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `sujet`
--

INSERT INTO `sujet` (`id`, `datetime`, `titre`, `auteur_id`) VALUES
(1, '2024-09-15 21:46:54.000000', 'Dragon Ball', 1),
(2, '2024-09-15 21:48:30.000000', 'F-Zéro sur Super Nintendo', 1),
(3, '2024-09-15 21:52:45.000000', 'One Piece, voyagez avec Monkey D. Luffy !', 1),
(4, '2024-09-15 22:01:22.000000', 'Fairy Tail, un manga de Hiro Mashima', 1),
(5, '2024-09-15 22:03:01.000000', 'MegaMan, une série de jeux vidéo développée par Capcom', 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `password`, `username`) VALUES
(1, '$2a$10$iFp8RTN0gJuFprcbbLu3QemQwGEPqHUi6pLRLJI6Ma004xKGhsUze', 'Haiou'),
(2, '$2a$10$iFp8RTN0gJuFprcbbLu3QemQwGEPqHUi6pLRLJI6Ma004xKGhsUze', '1234'),
(3, '$2a$10$iFp8RTN0gJuFprcbbLu3QemQwGEPqHUi6pLRLJI6Ma004xKGhsUze', 'Moi'),
(4, '$2a$10$SVbSMM2H12BjplY4if.i1eel09Ljka4L5YL0K4YI1tf2AHe.Ds38e', 'azerty');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlbt27jt4dpujby9xe5yn6cyot` (`sujet_id`),
  ADD KEY `FK4o94e1uqq2fa4l2a36atwfvy1` (`user_id`);

--
-- Index pour la table `sujet`
--
ALTER TABLE `sujet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq9g3v9treaa8ob3r7016ghh4o` (`auteur_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `sujet`
--
ALTER TABLE `sujet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK4o94e1uqq2fa4l2a36atwfvy1` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKlbt27jt4dpujby9xe5yn6cyot` FOREIGN KEY (`sujet_id`) REFERENCES `sujet` (`id`);

--
-- Contraintes pour la table `sujet`
--
ALTER TABLE `sujet`
  ADD CONSTRAINT `FKq9g3v9treaa8ob3r7016ghh4o` FOREIGN KEY (`auteur_id`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
