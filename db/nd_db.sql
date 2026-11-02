-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: nd_db
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `defeito`
--

DROP TABLE IF EXISTS `defeito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `defeito` (
  `idDefeito` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`idDefeito`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `defeito`
--

LOCK TABLES `defeito` WRITE;
/*!40000 ALTER TABLE `defeito` DISABLE KEYS */;
INSERT INTO `defeito` VALUES (1,'Aquecimento'),(2,'Descarregamento'),(3,'Erro de câmera'),(4,'Falha de rede'),(5,'Falha de som');
/*!40000 ALTER TABLE `defeito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagem`
--

DROP TABLE IF EXISTS `mensagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensagem` (
  `idMensagem` int NOT NULL AUTO_INCREMENT,
  `idDefeito` int NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `texto` varchar(6000) NOT NULL,
  PRIMARY KEY (`idMensagem`),
  KEY `idDefeito` (`idDefeito`),
  CONSTRAINT `mensagem_ibfk_1` FOREIGN KEY (`idDefeito`) REFERENCES `defeito` (`idDefeito`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem`
--

LOCK TABLES `mensagem` WRITE;
/*!40000 ALTER TABLE `mensagem` DISABLE KEYS */;
INSERT INTO `mensagem` VALUES (1,1,'Superaquecimento','Durante os testes realizados em bancada, seu aparelho se comportou corretamente, não apresentando superaquecimento ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nÉ importante destacar que o aquecimento em smartphones pode ocorrer em situações específicas de uso como jogos pesados, gravações de vídeo em alta resolução ou quando o aparelho é utilizado enquanto carrega.\n\nEsse comportamento é considerado normal, pois o processador e a bateria trabalham de forma mais intensa nesses momentos.\n\nPara evitar situações de aquecimento durante o uso, recomendamos algumas práticas:\n\nEvitar utilizar o aparelho sob exposição direta ao sol ou em ambientes muito quentes.\n\nNão utilizar o celular enquanto estiver em processo de carregamento, principalmente em jogos ou aplicativos pesados.\n\nPreferir sempre carregadores e cabos originais ou certificados.\n\nManter o sistema operacional e aplicativos atualizados, pois correções de software ajudam na otimização do consumo de energia.\n\nFechar aplicativos em segundo plano que não estejam em uso, reduzindo a carga sobre o processador.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(2,1,'Aparelho compacto','Durante os testes realizados em bancada, seu aparelho se comportou corretamente, não apresentando superaquecimento ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nPor se tratar de um aparelho compacto, o espaço interno para dissipação de calor é reduzido. Isso significa que, em usos mais exigentes, a sensação de aquecimento pode ser percebida com maior facilidade, sem que isso represente um defeito ou falha de funcionamento.\n\nPara evitar situações de aquecimento durante o uso, recomendamos algumas práticas:\n\nEvitar utilizar o aparelho sob exposição direta ao sol ou em ambientes muito quentes.\n\nNão utilizar o celular enquanto estiver em processo de carregamento, principalmente em jogos ou aplicativos pesados.\n\nPreferir sempre carregadores e cabos originais ou certificados.\n\nManter o sistema operacional e aplicativos atualizados, pois correções de software ajudam na otimização do consumo de energia.\n\nFechar aplicativos em segundo plano que não estejam em uso, reduzindo a carga sobre o processador.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(3,2,'Falha nos acessórios','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas de carregamento ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi identificado que a dificuldade relatada está relacionada ao cabo e ao carregador utilizados, e não ao aparelho em si. Acessórios danificados, incompatíveis ou não originais podem comprometer o fluxo de energia, causando lentidão ou interrupções no processo de carga.\n\nPara evitar situações desse tipo, recomendamos algumas práticas:\n\nUtilizar sempre cabos e carregadores originais ou certificados pela fabricante.\n\nEvitar o uso de acessórios com sinais de desgaste, como fios descascados ou conectores frouxos.\n\nManter a entrada de carregamento do aparelho limpa e livre de poeira ou umidade.\n\nRealizar testes com diferentes cabos e carregadores para confirmar o bom funcionamento.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(4,2,'Carregamento lento','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando lentidão no carregamento ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nÉ importante destacar que a velocidade de carregamento pode variar conforme o tipo de carregador e cabo utilizados, além das condições de uso durante o processo de carga. No entanto, em nossos testes, o aparelho apresentou desempenho adequado e dentro do padrão estabelecido pelo fabricante.\n\nPara manter o bom funcionamento do carregamento, recomendamos algumas práticas:\n\nUtilizar sempre cabos e carregadores originais ou certificados.\n\nEvitar o uso do aparelho durante o carregamento, principalmente em jogos ou aplicativos pesados.\n\nManter a entrada de carregamento limpa e livre de poeira ou umidade.\n\nSubstituir cabos e carregadores que apresentem sinais de desgaste ou mau contato.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(5,2,'Não carrega até 100%','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas no processo de carregamento. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi verificado que o aparelho carrega totalmente. No entanto, alguns modelos possuem o recurso chamado Proteção de Bateria, que limita a carga em torno de 85% para preservar a vida útil da bateria. Quando esse modo está ativado, pode dar a impressão de que o aparelho não completa o carregamento.\n\nPara que o aparelho alcance 100% de carga, recomendamos desativar o modo Proteção de Bateria seguindo os passos abaixo:\n\nAcesse Configurações do aparelho.\n\nToque em Cuidados com o dispositivo ou Manutenção do dispositivo.\n\nEntre em Bateria.\n\nLocalize a opção Proteção de Bateria.\n\nDesative o recurso para permitir que a carga chegue a 100%.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(6,2,'Não carrega','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas no processo de carregamento. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi verificado que o aparelho está carregando normalmente, sem qualquer anomalia ou lentidão fora dos padrões estabelecidos pelo fabricante.\n\nPara manter o bom funcionamento do carregamento, recomendamos algumas práticas:\n\nUtilizar sempre cabos e carregadores originais ou certificados.\n\nEvitar o uso do aparelho durante o carregamento, principalmente em jogos ou aplicativos pesados.\n\nManter a entrada de carregamento limpa e livre de poeira ou umidade.\n\nSubstituir cabos e carregadores que apresentem sinais de desgaste ou mau contato.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(7,3,'Câmera não abre','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas na abertura da câmera ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi verificado que a câmera abre normalmente, sem apresentar travamentos ou falhas durante o uso.\n\nPara manter o bom funcionamento da câmera, recomendamos algumas práticas:\n\nManter o sistema operacional e aplicativos atualizados, pois correções de software ajudam na estabilidade.\n\nEvitar sobrecarregar o aparelho com muitos aplicativos abertos em segundo plano.\n\nGarantir que o aplicativo de câmera não esteja bloqueado por permissões ou configurações incorretas.\n\nReiniciar o aparelho periodicamente para liberar memória e otimizar o desempenho.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(8,3,'Barulho nas câmeras','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas na câmera ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi verificado que o barulho percebido na câmera é normal. Esse som ocorre devido ao movimento interno do sistema de foco automático, que ajusta as lentes para garantir a melhor qualidade de imagem. Trata-se de um comportamento esperado e não representa defeito ou problema no aparelho.\n\nPara manter o bom funcionamento da câmera, recomendamos algumas práticas:\n\nManter o sistema operacional e aplicativos atualizados.\n\nEvitar impactos ou quedas que possam afetar o módulo da câmera.\n\nUtilizar o aplicativo de câmera oficial do aparelho para melhor desempenho.\n\nReiniciar o aparelho periodicamente para otimizar o funcionamento geral.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(9,3,'Mancha nas câmeras','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas na câmera ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi verificado que a câmera está funcionando normalmente e que não há manchas internas no módulo. Em muitos casos, a percepção de manchas nas fotos pode estar relacionada a sujeira, poeira ou marcas na lente externa, e não a um defeito no aparelho.\n\nPara evitar esse tipo de situação, recomendamos algumas práticas:\n\nLimpar a lente da câmera com um pano macio e apropriado, evitando produtos abrasivos.\n\nUtilizar capinhas e películas que não obstruam ou encubram a área da câmera.\n\nEvitar contato da lente com superfícies ásperas ou oleosas.\n\nManter o aparelho protegido contra poeira e umidade.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(10,4,'Não acessa internet','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas de acesso à internet ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi verificado que o aparelho acessa a internet normalmente, sem apresentar bloqueios ou lentidão fora dos padrões estabelecidos pelo fabricante.\n\nEm situações em que não há conexão, o problema geralmente está relacionado a fatores externos, como:\n\nConfigurações incorretas de rede móvel ou Wi-Fi.\n\nFalhas temporárias na rede da operadora ou do provedor de internet.\n\nSenhas de Wi-Fi incorretas ou roteadores com instabilidade.\n\nPara manter o bom funcionamento da conexão, recomendamos:\n\nConferir se os dados móveis ou Wi-Fi estão ativados.\n\nReiniciar o aparelho e o roteador/modem.\n\nVerificar se há sinal disponível da operadora ou do provedor.\n\nAtualizar o sistema operacional e aplicativos para garantir estabilidade.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(11,4,'Não realiza ligações','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas na realização de chamadas ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi verificado que o aparelho realiza chamadas normalmente, sem apresentar bloqueios ou interrupções durante o processo.\n\nPara manter o bom funcionamento das chamadas, recomendamos algumas práticas:\n\nCertificar-se de que o chip/linha esteja ativo e com sinal disponível.\n\nVerificar se não há restrições de chamadas configuradas no aparelho ou junto à operadora.\n\nManter o sistema operacional e aplicativos atualizados.\n\nReiniciar o aparelho periodicamente para otimizar o desempenho.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(12,4,'IMEI bloqueado','Foi verificado que o aparelho não possui defeito. A dificuldade relatada está relacionada ao bloqueio do IMEI, que impede o uso normal da linha telefônica. Esse bloqueio é realizado pela operadora em casos específicos, como perda, roubo ou pendências contratuais.\n\nPara confirmar o IMEI do aparelho, solicitamos que o cliente digite o código *#06# diretamente no dispositivo. O número exibido poderá ser consultado junto à operadora ou, se desejar, no site oficial de consulta:\nhttps://www.consultaaparelhoimpedido.com.br/public-web/welcome?utm_source=copilot.com\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso, dependendo apenas da regularização junto à operadora.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(13,4,'Falha no whatsapp','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas de funcionamento ou qualquer anomalia fora dos padrões. Todos os parâmetros permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi verificado que o aparelho está com todas as suas funções em dia. A dificuldade relatada está relacionada ao aplicativo WhatsApp, que é um software de terceiros. Por esse motivo, não há como a assistência técnica tomar frente em relação a falhas específicas do aplicativo.\n\nPara corrigir situações desse tipo, recomendamos algumas práticas:\n\nManter o aplicativo atualizado pela loja oficial (Google Play ou App Store).\n\nReiniciar o aparelho após atualização do aplicativo.\n\nVerificar permissões do WhatsApp em Configurações > Aplicativos > WhatsApp.\n\nCaso o problema persista, entrar em contato com o suporte oficial do WhatsApp.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(14,5,'Sem som','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas de áudio ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi verificado que o aparelho emite som normalmente, sem apresentar bloqueios ou falhas nos alto-falantes.\n\nEm situações em que o som não é percebido, o problema geralmente está relacionado a fatores externos, como:\n\nVolume reduzido ou silenciado nas configurações.\n\nModo silencioso ou “Não Perturbe” ativado.\n\nAplicativos de terceiros com falhas de áudio.\n\nAcúmulo de sujeira ou obstrução nos alto-falantes.\n\nPara manter o bom funcionamento do áudio, recomendamos:\n\nConferir o volume e desativar modos silenciosos.\n\nReiniciar o aparelho para liberar memória e corrigir falhas temporárias.\n\nManter os alto-falantes limpos e livres de poeira.\n\nAtualizar o sistema operacional e aplicativos para garantir estabilidade.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(15,5,'Obstrução dos falantes','Durante os testes realizados em bancada, o aparelho se comportou corretamente, não apresentando falhas de áudio ou qualquer anomalia fora dos padrões. Todos os parâmetros de funcionamento permaneceram dentro das especificações de fábrica, garantindo o desempenho esperado.\n\nFoi verificado que o aparelho emite som normalmente. A dificuldade relatada estava relacionada a uma obstrução nos alto-falantes, causada por acúmulo de poeira ou sujeira, e não por defeito no aparelho. Após a limpeza, o funcionamento foi restabelecido dentro dos padrões.\n\nPara evitar situações semelhantes, recomendamos:\n\nManter os alto-falantes limpos e livres de poeira.\n\nEvitar contato com líquidos ou superfícies que possam bloquear a saída de som.\n\nUtilizar capinhas que não cubram ou obstruam a área dos alto-falantes.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem Defeito'),(16,5,'Som chiando/ estourado','Durante os testes realizados em bancada, o aparelho apresentou funcionamento normal do sistema de áudio, sem ocorrência de chiados, estalos ou distorções durante a reprodução de sons. Todos os parâmetros de saída permaneceram dentro das especificações de fábrica, garantindo a qualidade esperada.\n\nFoi verificado que o aparelho reproduz sons de forma clara e contínua, sem interrupções ou falhas perceptíveis.\n\nPara manter o bom desempenho do áudio, recomendamos algumas práticas:\n\nCertificar-se de que o aparelho esteja livre de sujeira ou umidade nas saídas de som.\n\nEvitar o uso em ambientes com excesso de poeira ou líquidos próximos.\n\nManter o sistema operacional e aplicativos atualizados.\n\nReiniciar o aparelho periodicamente para otimizar o desempenho.\n\nO aparelho encontra-se em perfeitas condições de funcionamento e pronto para uso.\n\nAtenciosamente,\nAssistência Reparo Express – Setor Sem defeito.');
/*!40000 ALTER TABLE `mensagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(40) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `administrador` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'adm','202cb962ac59075b964b07152d234b70',1),(2,'user','202cb962ac59075b964b07152d234b70',0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-01 20:26:31
