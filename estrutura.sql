-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 08-Abr-2022 às 00:47
-- Versão do servidor: 10.4.17-MariaDB
-- versão do PHP: 7.4.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `motozoom`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `idCliente` int(11) NOT NULL,
  `dataCadastro` date DEFAULT NULL,
  `nome` varchar(45) NOT NULL,
  `cpf` varchar(20) DEFAULT NULL,
  `telefone` varchar(20) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `sexo` enum('M','F') DEFAULT NULL,
  `cep` varchar(15) DEFAULT NULL,
  `estado` varchar(25) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `rua` varchar(45) DEFAULT NULL,
  `numero` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `lancamentos`
--

CREATE TABLE `lancamentos` (
  `idLancamento` int(11) NOT NULL,
  `data` date NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `tipo` varchar(45) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `formaPagamento` varchar(45) DEFAULT NULL,
  `idVenda` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ordem_de_servico`
--

CREATE TABLE `ordem_de_servico` (
  `idOrdemDeServico` int(11) NOT NULL,
  `data` date NOT NULL,
  `valorTotal` decimal(10,2) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  `descricaoVeiculo` varchar(100) DEFAULT NULL,
  `descricaoDefeito` text DEFAULT NULL,
  `pecasReposicao` text DEFAULT NULL,
  `observacoes` text DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `idCliente` int(11) NOT NULL,
  `idVenda` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `idProduto` int(11) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `precoCompra` decimal(10,2) NOT NULL,
  `precoVenda` decimal(10,2) NOT NULL,
  `estoque` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos_vendidos`
--

CREATE TABLE `produtos_vendidos` (
  `idProdutosVendidos` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `descontoUN` decimal(10,2) NOT NULL,
  `valorTotal` decimal(10,2) NOT NULL,
  `idVenda` int(11) NOT NULL,
  `idProduto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto_ordem_de_servico`
--

CREATE TABLE `produto_ordem_de_servico` (
  `idProdutoOS` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `descontoUN` decimal(10,2) NOT NULL,
  `valorTotal` decimal(10,2) NOT NULL,
  `idOrdemDeServico` int(11) NOT NULL,
  `idProduto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `servicos`
--

CREATE TABLE `servicos` (
  `idServico` int(11) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `preco` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `servicos_vendidos`
--

CREATE TABLE `servicos_vendidos` (
  `idServicosVendidos` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `descontoUN` decimal(10,2) NOT NULL,
  `valorTotal` decimal(10,2) NOT NULL,
  `idVenda` int(11) NOT NULL,
  `idServico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `servico_ordem_de_servico`
--

CREATE TABLE `servico_ordem_de_servico` (
  `idServicoOS` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `descontoUN` decimal(10,2) NOT NULL,
  `valorTotal` decimal(10,2) NOT NULL,
  `idOrdemDeServico` int(11) NOT NULL,
  `idServico` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `dataCadastro` date DEFAULT NULL,
  `nome` varchar(45) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `sexo` enum('M','F') DEFAULT NULL,
  `login` varchar(20) DEFAULT NULL,
  `senha` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendas`
--

CREATE TABLE `vendas` (
  `idVenda` int(11) NOT NULL,
  `data` date NOT NULL,
  `valorTotal` decimal(10,2) NOT NULL,
  `desconto` decimal(10,2) NOT NULL,
  `valorRecebido` decimal(10,2) DEFAULT NULL,
  `troco` decimal(10,2) DEFAULT NULL,
  `formaPagamento` varchar(45) NOT NULL,
  `observacoes` text DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `idCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`idCliente`);

--
-- Índices para tabela `lancamentos`
--
ALTER TABLE `lancamentos`
  ADD PRIMARY KEY (`idLancamento`),
  ADD KEY `fk_lancamento_venda` (`idVenda`);

--
-- Índices para tabela `ordem_de_servico`
--
ALTER TABLE `ordem_de_servico`
  ADD PRIMARY KEY (`idOrdemDeServico`),
  ADD KEY `fk_ordem_de_servico_usuario` (`idUsuario`),
  ADD KEY `fk_ordem_de_servico_cliente` (`idCliente`),
  ADD KEY `fk_ordem_de_servico_venda` (`idVenda`);

--
-- Índices para tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`idProduto`);

--
-- Índices para tabela `produtos_vendidos`
--
ALTER TABLE `produtos_vendidos`
  ADD PRIMARY KEY (`idProdutosVendidos`),
  ADD KEY `fk_produtos_vendidos_venda` (`idVenda`),
  ADD KEY `fk_servicos_vendidos_produto` (`idProduto`);

--
-- Índices para tabela `produto_ordem_de_servico`
--
ALTER TABLE `produto_ordem_de_servico`
  ADD PRIMARY KEY (`idProdutoOS`),
  ADD KEY `fk_p_ordem_de_servico` (`idOrdemDeServico`),
  ADD KEY `fk_prduto_ordem_de_servico` (`idProduto`);

--
-- Índices para tabela `servicos`
--
ALTER TABLE `servicos`
  ADD PRIMARY KEY (`idServico`);

--
-- Índices para tabela `servicos_vendidos`
--
ALTER TABLE `servicos_vendidos`
  ADD PRIMARY KEY (`idServicosVendidos`),
  ADD KEY `fk_servicos_vendidos_venda` (`idVenda`),
  ADD KEY `fk_servicos_vendidos_servico` (`idServico`);

--
-- Índices para tabela `servico_ordem_de_servico`
--
ALTER TABLE `servico_ordem_de_servico`
  ADD PRIMARY KEY (`idServicoOS`),
  ADD KEY `fk_ordem_de_servico` (`idOrdemDeServico`),
  ADD KEY `fk_servico_ordem_de_servico` (`idServico`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`);

--
-- Índices para tabela `vendas`
--
ALTER TABLE `vendas`
  ADD PRIMARY KEY (`idVenda`),
  ADD KEY `fk_venda_usuario` (`idUsuario`),
  ADD KEY `fk_venda_cliente` (`idCliente`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `lancamentos`
--
ALTER TABLE `lancamentos`
  MODIFY `idLancamento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `ordem_de_servico`
--
ALTER TABLE `ordem_de_servico`
  MODIFY `idOrdemDeServico` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `idProduto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `produtos_vendidos`
--
ALTER TABLE `produtos_vendidos`
  MODIFY `idProdutosVendidos` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `produto_ordem_de_servico`
--
ALTER TABLE `produto_ordem_de_servico`
  MODIFY `idProdutoOS` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `servicos`
--
ALTER TABLE `servicos`
  MODIFY `idServico` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `servicos_vendidos`
--
ALTER TABLE `servicos_vendidos`
  MODIFY `idServicosVendidos` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `servico_ordem_de_servico`
--
ALTER TABLE `servico_ordem_de_servico`
  MODIFY `idServicoOS` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `vendas`
--
ALTER TABLE `vendas`
  MODIFY `idVenda` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `lancamentos`
--
ALTER TABLE `lancamentos`
  ADD CONSTRAINT `fk_lancamento_venda` FOREIGN KEY (`idVenda`) REFERENCES `vendas` (`idVenda`);

--
-- Limitadores para a tabela `ordem_de_servico`
--
ALTER TABLE `ordem_de_servico`
  ADD CONSTRAINT `fk_ordem_de_servico_cliente` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`),
  ADD CONSTRAINT `fk_ordem_de_servico_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`),
  ADD CONSTRAINT `fk_ordem_de_servico_venda` FOREIGN KEY (`idVenda`) REFERENCES `vendas` (`idVenda`);

--
-- Limitadores para a tabela `produtos_vendidos`
--
ALTER TABLE `produtos_vendidos`
  ADD CONSTRAINT `fk_produtos_vendidos_venda` FOREIGN KEY (`idVenda`) REFERENCES `vendas` (`idVenda`),
  ADD CONSTRAINT `fk_servicos_vendidos_produto` FOREIGN KEY (`idProduto`) REFERENCES `produtos` (`idProduto`);

--
-- Limitadores para a tabela `produto_ordem_de_servico`
--
ALTER TABLE `produto_ordem_de_servico`
  ADD CONSTRAINT `fk_p_ordem_de_servico` FOREIGN KEY (`idOrdemDeServico`) REFERENCES `ordem_de_servico` (`idOrdemDeServico`),
  ADD CONSTRAINT `fk_prduto_ordem_de_servico` FOREIGN KEY (`idProduto`) REFERENCES `produtos` (`idProduto`);

--
-- Limitadores para a tabela `servicos_vendidos`
--
ALTER TABLE `servicos_vendidos`
  ADD CONSTRAINT `fk_servicos_vendidos_servico` FOREIGN KEY (`idServico`) REFERENCES `servicos` (`idServico`),
  ADD CONSTRAINT `fk_servicos_vendidos_venda` FOREIGN KEY (`idVenda`) REFERENCES `vendas` (`idVenda`);

--
-- Limitadores para a tabela `servico_ordem_de_servico`
--
ALTER TABLE `servico_ordem_de_servico`
  ADD CONSTRAINT `fk_ordem_de_servico` FOREIGN KEY (`idOrdemDeServico`) REFERENCES `ordem_de_servico` (`idOrdemDeServico`),
  ADD CONSTRAINT `fk_servico_ordem_de_servico` FOREIGN KEY (`idServico`) REFERENCES `servicos` (`idServico`);

--
-- Limitadores para a tabela `vendas`
--
ALTER TABLE `vendas`
  ADD CONSTRAINT `fk_venda_cliente` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`),
  ADD CONSTRAINT `fk_venda_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
