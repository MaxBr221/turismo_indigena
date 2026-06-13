-- Como a coluna já se chama 'informacoes', apenas alteramos o tipo e tamanho
ALTER TABLE pontoturistico
ADD COLUMN informacoes VARCHAR(150) NOT NULL;