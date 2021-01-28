-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-01-2021 a las 09:05:09
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `beetv`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actuacion`
--

CREATE TABLE `actuacion` (
  `id` bigint(11) NOT NULL,
  `id_capitulo` bigint(11) NOT NULL,
  `id_personaje` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `capitulo`
--

CREATE TABLE `capitulo` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sinopsis_capitulo` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `fecha_emision` date DEFAULT NULL,
  `duracion` int(11) DEFAULT NULL,
  `id_temporada` bigint(11) NOT NULL,
  `id_file` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `id` bigint(11) NOT NULL,
  `texto` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_usuario` bigint(11) NOT NULL,
  `id_capitulo` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `file`
--

CREATE TABLE `file` (
  `id` bigint(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `file` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `file`
--

INSERT INTO `file` (`id`, `name`, `type`, `file`) VALUES
(1, 'no_photo.jpg', 'image/jpeg', 0x89504e470d0a1a0a0000000d49484452000001c0000001c00800000000104ee7af000008f74944415478daeddd2d97ea581680617e6de9d8e8d86834363a161d1d1b8d65b51811cb74dfbe6bd64085aa02f2b137f77975df6286879c9c7312c2ee43a9db790b000aa0000214400114408002288002085000055000010aa0000a20400114400104288002085000055000010aa0000a204001144001042880022880000550000510a0000aa00002144001042880022880000550000510a0000aa0000214400114408002288002085000055000010aa0000214400114408002288002085000055000010aa0000a20400114400104288002085000055000010aa0000a204001144001042880022880000550000510a0000aa0de17b0ac9bb61f86cbef86a16f9bba0498a2ba1dc6cb64e3d0d600631f79cd70f9a6be2901c6ac684e971f756a0a80f146cefef2407d0d305487f3e5c1ce078089f9de87f00d00f74ff1fd22dc03dc7ee2395c5e6828016e5b7b79b116e08655a7cbcb9d2a805bd55c66a901b8cdc2fdab95dfd0b5fbfaf7b155d5fbb6fbea54d91700030d9f63df4c8e8a55d38fef378ca605aca631c6eecba5c1bebbf3cf2a802bafdda707ce1faccd0fd383e901e0e67edd0f0fa3aa7b23c1dddbf8750facc9cbee6d04776fe2373c7879a11ede4470f7167ee3134bb9667c0bc18480d54c3b9a13bba815c02dd60f4fefa4b46fb09a480758dcaedfc7172eaed7b71f86530170e16ef7cf4e2f5d102a6f3f0e3dc0656b663e643e1dd00dc0352730ddcb435ed1e59ec82403bc395eba39fee68de009e072b54b4c396e47d116e05295cb4c196f054b800b75bdf21e677ba3cbebd5c4007099f6d707ca8c3757d7d77f790f7091cecbcdf7af572767804b7458729c1bb2ee6aef921e80e3cc338debd3e019e0d207e0ec1b264dd2437097f3005c60a238e43c04d300d64bcd40577b813f1cb09f7d0bedb66ef157f893018be5f74aaef7790a80cbcd31163a3cba8c9795b2009e56b8e25365bc289104b05c65af7248b8a59d04b0596591764838862601fcff39e8b8dccb8cf9ee8e4902b8d20cff6a1a0370a145f682177bf6f9d6f23900db7546d0eb31b405b8c8f470d173539feec27c0ec071add961b3d6a1fe670196abddb759a5db4d4b0158af37394c378b4901d8ac77661ab22de55300b6eb5de7e9b24d435300f6ebbdab6db6bd981480c37af76ceeb3ad23d2012e3cb3a8012e3c355cf8db5f55b6ddd07480eff45a000102040810204080002d232c232ce4017e0f682b2d21a0cdece4802e27250774413739a05b2a9203baa92939a0db0ab303bab13739a05beb9303fa724b72405f2fcb0e38f882676e405fb14e0ee82107c9013d66243ba007fd2407f4a8ade4806b3fec2ecd4ff078dce46a2ff087037ae06b76408f5c4e0ee8a1e7d901fdec4072c09b1ffe98f56a4feb873f56c84fef2407f4e357d901fdfc5c72403f00991dd04fb06607f423c8c901fd0c7976c0ebab12ff08be34e5286ffdfa0f80cbf6e990195f580fd6e36599892dc02f4e83b76ffaf3d3c6f6f62f8dd507c0d527327fafbc9f1a46cbe1d31fcae79711f06657fbd791f3c4d4a3f9742467dac34e0d382178191e3c13d69f0fbf947e3901a7042fdd03e368d95ddec42f29e0a4e0a5fbe129ac9ae24bea9715705af032fc40e1305cdec82f2de0c46ae2dfe94cf7e5c5bc7d77e79f551f00d7163c5da61bfb6692a36afaf1ce3f39a5f54b0cf851f497fb0d5dbbaf7fb354f5beed862ffee3bef800b845cd65969accef416ac0fbc3e803251e3ef3034e6c673e5a9bfc0dc80e38b5a3f94043f90170ebf67f3dcbf79f7dfefff7e9018be685f3e0d8d500b79dc574af9e03cf8702e0561d86395611e3b104b809dff932575d0930315f6ac2a480f5e93277c702608eb5dfdd73610330c8eecbf8f7567653d7ffdb23abeafad01e876f07dd530d7085d1f34b8773dfd6f7c7c2bae94e6f368e66032c8e5f2dcb0f3f988a14fbe31788e71ae0a20bf7bbeffdf9f8c05585b2b97f2df10870b99abbc7dec3d784ee6fc19d7cc173a9e1f3ce61737af286a4eade0d32be23bfe6f0f9ca7e74d19cb35f24cc03588f8beca04cefe87405c0999bbc11b49fe374354978f2b8c9799b5a3d0c334df98b76cc7bab4c12c089cb7ee38cf752977dda7b7d7759fd66be9773627f2787e02ea7df38fb86c9c4164f0ac15d4abf456ea5de8f19057719fd16baeef3e991151904e3031e577c573fbdd6b90038f7fa6fd115da21dd7a303ae0fed31ec9b2aff7e96b873dc059dfcf6ef5576c01be30b7dfe049669fa6327b804fd76ff145f6db4fcd58027cb2669b0711dc0a9e003e793adaea411237cfd00e7d974560c0e2bcf2fce5fe4ca606f8faaabadbf0e00fbc9e8f0b586f7a223a641944c302de0ca0ab1f03c724836858c076eb27799e72cc44a302569b3fc9ac18533c4c262ae0b0fd86e4f536ec58007cfacddb681278cc308f090a788e3083b8994795009f9cc46ff6d9afb75b8826073c0739fbf4f10fc15dfc0370c3cb39d79ba21dc0670ec04d7f8eb10d7f08eec21f80559ccfd211e0e37b205da00f53c4b56040c03ad4b0750ebe1d1310b00b35713804ff7df2788045b079c339f6458978804db0997b137b25110ff01cecf7e0aeae4a8c00bfab0ab3069c3c271f007ed331dcdb5586bed33e1ce039de80758a3c864603ac02ee7c1c22df681f0db00df893b645e4796834c053c465731f782d1f0cb008b9777c087c492218e03ee48f829781f74383011e630e56a7b827c1608041dfa963dc936030c0a09b1efbb827c1588075d037aa88bb128c05d8461daa4e619f7a100bb08b3a593886dd0e8d0538449dae1fc27e51291660d88bdf572767803f5a3017613f5a15c01f7cce835db739471d1b42011ea25d8c4f70760e05d8c6ddb13a465d47ecbc4bb93f5ba10087b8770f851dddc30206bb85b60608f0fd01c7a88bad9b9bad00fe60b5fc112c800001fadf0610a0620510a0000a204001144001042880022880000550000510a0000aa00002144001144080022880000550000510a0000aa0000214400114408002288002085000055000010aa0000a20400114408002288002085000055000010aa0000a204001144001042880022880000550000510a0000a204001144001042880022880000550000510a0000aa00002144001144080022880000550000510a0000aa0000214400114408002288002085000055000010aa000ea57ff053535dd3d6e05444c0000000049454e44ae426082);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `likes`
--

CREATE TABLE `likes` (
  `id` bigint(11) NOT NULL,
  `like_type` tinyint(1) DEFAULT NULL,
  `id_usuario` bigint(11) NOT NULL,
  `id_comentario` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `listaseries`
--

CREATE TABLE `listaseries` (
  `id` bigint(11) NOT NULL,
  `id_usuario` bigint(11) NOT NULL,
  `id_serie` bigint(11) NOT NULL,
  `siguiendo` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personaje`
--

CREATE TABLE `personaje` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `apellido1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `apellido2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_file` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puntuacionserie`
--

CREATE TABLE `puntuacionserie` (
  `id` bigint(11) NOT NULL,
  `puntuacion` int(11) NOT NULL,
  `id_serie` bigint(11) NOT NULL,
  `id_usuario` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `serie`
--

CREATE TABLE `serie` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sinopsis_serie` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idioma` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `duracion_media` int(11) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `id_genero` bigint(11) NOT NULL,
  `id_file` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temporada`
--

CREATE TABLE `temporada` (
  `id` bigint(11) NOT NULL,
  `id_serie` bigint(11) NOT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `id_file` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `nombre`) VALUES
(1, 'Administrador'),
(2, 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `apellido1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `apellido2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `login` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `validado` tinyint(1) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `id_tipousuario` bigint(11) NOT NULL,
  `id_file` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actuacion`
--
ALTER TABLE `actuacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_personaje_has_capitulo_capitulo1_idx` (`id_capitulo`),
  ADD KEY `fk_personaje_has_capitulo_personaje1_idx` (`id_personaje`);

--
-- Indices de la tabla `capitulo`
--
ALTER TABLE `capitulo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_capitulo_temporada1_idx` (`id_temporada`),
  ADD KEY `fk_capitulo_file1_idx` (`id_file`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_comentario_usuario1_idx` (`id_usuario`),
  ADD KEY `fk_comentario_capitulo1_idx` (`id_capitulo`);

--
-- Indices de la tabla `file`
--
ALTER TABLE `file`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_votos_comentario_usuario1_idx` (`id_usuario`),
  ADD KEY `fk_likes_comentario1_idx` (`id_comentario`);

--
-- Indices de la tabla `listaseries`
--
ALTER TABLE `listaseries`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_serie_has_usuario_usuario1_idx` (`id_usuario`),
  ADD KEY `fk_serie_has_usuario_serie1_idx` (`id_serie`);

--
-- Indices de la tabla `personaje`
--
ALTER TABLE `personaje`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_personaje_file1_idx` (`id_file`);

--
-- Indices de la tabla `puntuacionserie`
--
ALTER TABLE `puntuacionserie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_serie_has_usuario_usuario2_idx` (`id_usuario`),
  ADD KEY `fk_serie_has_usuario_serie2_idx` (`id_serie`);

--
-- Indices de la tabla `serie`
--
ALTER TABLE `serie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_serie_genero1_idx` (`id_genero`),
  ADD KEY `fk_serie_file1_idx` (`id_file`);

--
-- Indices de la tabla `temporada`
--
ALTER TABLE `temporada`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_temporada_file1_idx` (`id_file`),
  ADD KEY `fk_temporada_serie1` (`id_serie`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_usuario_tipousuario_idx` (`id_tipousuario`),
  ADD KEY `fk_usuario_file1_idx` (`id_file`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actuacion`
--
ALTER TABLE `actuacion`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=701;

--
-- AUTO_INCREMENT de la tabla `capitulo`
--
ALTER TABLE `capitulo`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=502;

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT de la tabla `file`
--
ALTER TABLE `file`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `likes`
--
ALTER TABLE `likes`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT de la tabla `listaseries`
--
ALTER TABLE `listaseries`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `personaje`
--
ALTER TABLE `personaje`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=303;

--
-- AUTO_INCREMENT de la tabla `puntuacionserie`
--
ALTER TABLE `puntuacionserie`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT de la tabla `serie`
--
ALTER TABLE `serie`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `temporada`
--
ALTER TABLE `temporada`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=302;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
