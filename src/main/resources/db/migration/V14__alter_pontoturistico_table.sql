-- Como a coluna já se chama 'informacoes', apenas alteramos o tipo e tamanho
ALTER TABLE pontoturistico
MODIFY COLUMN informacoes VARCHAR(150) NOT NULL;