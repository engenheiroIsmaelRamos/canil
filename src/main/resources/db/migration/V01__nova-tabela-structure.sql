/*
* Engine: MySQL
* Version: 01
* Description: Initial database structure. 
*/

/*
* Structure
*/

CREATE TABLE `cao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome_cao` varchar(80) NOT NULL,
  `doc` varchar(20) NOT NULL,
  `raca` varchar(20) NOT NULL,
  `dataNascimento` date NOT NULL,
  `dataMorte` date DEFAULT NULL,
  `sexo` char(1) NOT NULL,
  `origem` varchar(40) NOT NULL,
  `cor` varchar(20) NOT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_cao_Doc` (`doc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `endereco` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cidade` varchar(40) NOT NULL,
  `estado` varchar(40) NOT NULL,
  `pais` varchar(40) NOT NULL,
  `numero` int(11) NOT NULL,
  `cep` int(11) NOT NULL,
  `codigo` int(11) NOT NULL,
  `rua` varchar(200) NOT NULL,
  `bairro` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_endereco_codigo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pessoa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf_cnpj` varchar(20) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cod_endereco` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_pessoa_cpf_cnpj` (`cpf_cnpj`),
  KEY `FK_pessoa_cod_endereco` (`cod_endereco`),
  CONSTRAINT `FK_pessoa_cod_endereco` FOREIGN KEY (`cod_endereco`) REFERENCES `endereco` (`codigo`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `filhote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doc` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_filhote_doc` (`doc`),
  CONSTRAINT `FK_filhote_doc` FOREIGN KEY (`doc`) REFERENCES `cao` (`doc`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `funcionario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf_cnpj` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_proprietario_doc` (`cpf_cnpj`),
  CONSTRAINT `FK_proprietario_doc` FOREIGN KEY (`cpf_cnpj`) REFERENCES `pessoa` (`cpf_cnpj`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `matriz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doc` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_matriz_doc` (`doc`),
  CONSTRAINT `FK_matriz_doc` FOREIGN KEY (`doc`) REFERENCES `cao` (`doc`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `padreador` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doc` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_padreador_doc` (`doc`),
  CONSTRAINT `FK_padreador_doc` FOREIGN KEY (`doc`) REFERENCES `cao` (`doc`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `proprietario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doc_cao` varchar(20) NOT NULL,
  `cpf_cnpj` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_proprietario_doc_cao` (`doc_cao`),
  KEY `FK_proprietario_cpf_cnpj` (`cpf_cnpj`),
  CONSTRAINT `FK_proprietario_cpf_cnpj` FOREIGN KEY (`cpf_cnpj`) REFERENCES `pessoa` (`cpf_cnpj`) ON DELETE CASCADE,
  CONSTRAINT `FK_proprietario_doc_cao` FOREIGN KEY (`doc_cao`) REFERENCES `cao` (`doc`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;