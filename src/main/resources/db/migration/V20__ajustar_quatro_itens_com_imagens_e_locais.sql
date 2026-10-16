-- V20: Manter exatamente 4 Restaurantes, 4 Pontos Turisticos e 4 Guias (fotos em Restaurantes e Pontos Turisticos)

-- Desativar verificacao de chaves estrangeiras para operacoes seguras
SET FOREIGN_KEY_CHECKS = 0;

-- =======================================================
-- 0. GARANTIR QUE AS COLUNAS DE IMAGEM EXISTEM NAS TABELAS
-- =======================================================

-- Adicionar coluna imagem em restaurantes se nao existir
SET @col_rest = (
    SELECT COUNT(*) 
    FROM information_schema.COLUMNS 
    WHERE TABLE_SCHEMA = DATABASE() 
      AND TABLE_NAME = 'restaurantes' 
      AND COLUMN_NAME = 'imagem'
);
SET @sql_rest = IF(@col_rest = 0, 'ALTER TABLE restaurantes ADD COLUMN imagem VARCHAR(500)', 'SELECT 1');
PREPARE stmt_rest FROM @sql_rest;
EXECUTE stmt_rest;
DEALLOCATE PREPARE stmt_rest;

-- Adicionar coluna imagem em pontoturistico se nao existir
SET @col_ponto = (
    SELECT COUNT(*) 
    FROM information_schema.COLUMNS 
    WHERE TABLE_SCHEMA = DATABASE() 
      AND TABLE_NAME = 'pontoturistico' 
      AND COLUMN_NAME = 'imagem'
);
SET @sql_ponto = IF(@col_ponto = 0, 'ALTER TABLE pontoturistico ADD COLUMN imagem VARCHAR(500)', 'SELECT 1');
PREPARE stmt_ponto FROM @sql_ponto;
EXECUTE stmt_ponto;
DEALLOCATE PREPARE stmt_ponto;


-- =======================================================
-- 1. RESTAURANTES (Apenas os 4 principais com fotos reais)
-- =======================================================

-- Limpar avaliacoes de restaurantes que serao removidos
DELETE FROM avaliacao 
WHERE restaurante_id IS NOT NULL 
  AND restaurante_id NOT IN (
    SELECT id FROM (
        SELECT id FROM restaurantes 
        WHERE nome IN (
            'Mirante Potiguara Bar e Restaurante',
            'Bar e Restaurante Trincheiras',
            'Bar e Restaurante Beira Mar',
            'Restaurante Pousada Tubarão'
        )
    ) AS tmp_rest_valid
);

-- Limpar agendamentos de restaurantes que serao removidos
DELETE FROM agendamento 
WHERE id_restaurantes IS NOT NULL 
  AND id_restaurantes NOT IN (
    SELECT id FROM (
        SELECT id FROM restaurantes 
        WHERE nome IN (
            'Mirante Potiguara Bar e Restaurante',
            'Bar e Restaurante Trincheiras',
            'Bar e Restaurante Beira Mar',
            'Restaurante Pousada Tubarão'
        )
    ) AS tmp_rest_agend
);

-- Excluir restaurantes excedentes
DELETE FROM restaurantes 
WHERE nome NOT IN (
    'Mirante Potiguara Bar e Restaurante',
    'Bar e Restaurante Trincheiras',
    'Bar e Restaurante Beira Mar',
    'Restaurante Pousada Tubarão'
);

-- Atualizar/Inserir Mirante Potiguara
UPDATE restaurantes 
SET nome = 'Mirante Potiguara Bar e Restaurante',
    descricao = 'Gastronomia regional com peixes frescos e carne de sol, com linda vista panoramica no alto da colina da baia.',
    localizacao = 'Rua Dom Pedro II, 50 - Alto da Colina, Baia da Traicao - PB',
    horariofuncionamento = '10:00 - 20:00',
    telefone = '(83) 98761-4262',
    redesociais = 'https://www.instagram.com/mirantepotiguara',
    imagem = 'https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?auto=format&fit=crop&w=800&q=80',
    latitude = '-6.68720',
    longitude = '-34.93550',
    media = 4.9
WHERE nome = 'Mirante Potiguara Bar e Restaurante';

-- Atualizar/Inserir Bar e Restaurante Trincheiras
UPDATE restaurantes 
SET nome = 'Bar e Restaurante Trincheiras',
    descricao = 'Culinaria regional nordestina, camarao empanado, peixe frito da terra e petiscos a beira da Praia das Trincheiras.',
    localizacao = 'Rua Osvaldo Trigueiro, s/n - Praia das Trincheiras, Baia da Traicao - PB',
    horariofuncionamento = '09:00 - 18:00',
    telefone = '(83) 98701-3429',
    redesociais = 'https://www.instagram.com/restaurantetrincheiras',
    imagem = 'https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=800&q=80',
    latitude = '-6.68250',
    longitude = '-34.93180',
    media = 4.8
WHERE nome = 'Bar e Restaurante Trincheiras';

-- Atualizar/Inserir Bar e Restaurante Beira Mar
UPDATE restaurantes 
SET nome = 'Bar e Restaurante Beira Mar',
    descricao = 'Bar e restaurante na orla com opcoes de refeicoes variadas, peixe frito, camarao e cerveja gelada frente ao mar.',
    localizacao = 'Av. Beira Mar, 123 - Centro, Baia da Traicao - PB',
    horariofuncionamento = '10:00 - 22:00',
    telefone = '(83) 99373-3348',
    redesociais = 'https://www.instagram.com/barbeiramar.bt',
    imagem = 'https://images.unsplash.com/photo-1555396273-367ea4eb4db5?auto=format&fit=crop&w=800&q=80',
    latitude = '-6.68950',
    longitude = '-34.93320',
    media = 4.7
WHERE nome = 'Bar e Restaurante Beira Mar';

-- Atualizar/Inserir Restaurante Pousada Tubarão
UPDATE restaurantes 
SET nome = 'Restaurante Pousada Tubarão',
    descricao = 'Tradicional parada na Praia do Forte com moquecas de peixe, lagosta grelhada e ambiente arejado a beira-mar.',
    localizacao = 'Rua Dom Pedro II, 210 - Praia do Forte, Baia da Traicao - PB',
    horariofuncionamento = '08:00 - 21:00',
    telefone = '(83) 98835-2310',
    redesociais = 'https://www.instagram.com/pousadatubaraobt',
    imagem = 'https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=800&q=80',
    latitude = '-6.68110',
    longitude = '-34.93220',
    media = 4.8
WHERE nome = 'Restaurante Pousada Tubarão';


-- =======================================================
-- 2. PONTOS TURISTICOS (Apenas os 4 principais com fotos reais)
-- =======================================================

-- Limpar avaliacoes de pontos que serao removidos
DELETE FROM avaliacao 
WHERE ponto_id IS NOT NULL 
  AND ponto_id NOT IN (
    SELECT id FROM (
        SELECT id FROM pontoturistico 
        WHERE nome IN (
            'Praia do Forte e Canhoes Coloniais',
            'Aldeia Sao Francisco',
            'Falesias do Alto do Tamba',
            'Rio do Gozo'
        )
    ) AS tmp_ponto_valid
);

-- Excluir pontos turisticos excedentes
DELETE FROM pontoturistico 
WHERE nome NOT IN (
    'Praia do Forte e Canhoes Coloniais',
    'Aldeia Sao Francisco',
    'Falesias do Alto do Tamba',
    'Rio do Gozo'
);

-- Atualizar Praia do Forte e Canhoes Coloniais
UPDATE pontoturistico 
SET informacoes = 'Praia de aguas calmas com falesias coloridas e ruinas do forte colonial com canhoes centenarios da resistencia Potiguara.',
    local = 'PRAIA',
    imagem = 'https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=800&q=80',
    latitude = -6.68080,
    longitude = -34.93150,
    media = 4.9
WHERE nome = 'Praia do Forte e Canhoes Coloniais';

-- Atualizar Aldeia Sao Francisco
UPDATE pontoturistico 
SET informacoes = 'Centro cultural Potiguara com vivencia comunitaria, celebracoes sagradas, danca do Tore e rico artesanato indigena.',
    local = 'ALDEIA',
    imagem = 'https://images.unsplash.com/photo-1596895111956-bf1cf0599ce5?auto=format&fit=crop&w=800&q=80',
    latitude = -6.64938,
    longitude = -34.98100,
    media = 5.0
WHERE nome = 'Aldeia Sao Francisco';

-- Atualizar Falesias do Alto do Tamba
UPDATE pontoturistico 
SET informacoes = 'Mirante imponente no topo das falesias coloridas com vista panoramica exuberante do mar aberto e enseadas virgens.',
    local = 'ALDEIA',
    imagem = 'https://images.unsplash.com/photo-1518495973542-4542c06a5843?auto=format&fit=crop&w=800&q=80',
    latitude = -6.65750,
    longitude = -34.96200,
    media = 4.9
WHERE nome = 'Falesias do Alto do Tamba';

-- Atualizar Rio do Gozo
UPDATE pontoturistico 
SET informacoes = 'Nascente de aguas cristalinas cercada de mata nativa na Aldeia Tracoeira, local sagrado perfeito para banho refrescante.',
    local = 'RIO',
    imagem = 'https://images.unsplash.com/photo-1432405972618-c60b0225b8f9?auto=format&fit=crop&w=800&q=80',
    latitude = -6.69800,
    longitude = -34.96500,
    media = 4.9
WHERE nome = 'Rio do Gozo';


-- =======================================================
-- 3. GUIAS TURISTICOS (Apenas os 4 principais sem fotos)
-- =======================================================

-- Limpar agendamentos de guias que serao removidos
DELETE FROM agendamento 
WHERE id_guide IS NOT NULL 
  AND id_guide NOT IN (
    SELECT id FROM (
        SELECT id FROM guide 
        WHERE nome IN (
            'Ananir Potiguara',
            'Kaue Silva Potiguara',
            'Jaciara Potiguara',
            'Mestre Moacir dos Santos'
        )
    ) AS tmp_guide_agend
);

-- Remover guias excedentes
DELETE FROM guide 
WHERE nome NOT IN (
    'Ananir Potiguara',
    'Kaue Silva Potiguara',
    'Jaciara Potiguara',
    'Mestre Moacir dos Santos'
);

-- Atualizar Ananir Potiguara
UPDATE guide 
SET telefone = '(83) 98834-1290',
    descricao = 'Guia nativo da Aldeia Sao Francisco, especialista em vivencia comunitaria, rituais do Tore e trilhas sagradas ancestrais.'
WHERE nome = 'Ananir Potiguara';

-- Atualizar Kaue Silva Potiguara
UPDATE guide 
SET telefone = '(83) 98145-6721',
    descricao = 'Condutor ambiental de buggy credenciado pelas Falesias do Tamba, Praia de Coqueirinho e banhos no Rio do Gozo.'
WHERE nome = 'Kaue Silva Potiguara';

-- Atualizar Jaciara Potiguara
UPDATE guide 
SET telefone = '(83) 99678-2234',
    descricao = 'Especialista em turismo comunitario, oficinas de artesanato em palha na Aldeia Galego e culinaria tipica Potiguara.'
WHERE nome = 'Jaciara Potiguara';

-- Atualizar Mestre Moacir dos Santos
UPDATE guide 
SET telefone = '(83) 99321-4455',
    descricao = 'Guia experiente com 15 anos de atuacao em ecoturismo, historia colonial dos canhoes e passeios nos manguezais.'
WHERE nome = 'Mestre Moacir dos Santos';

-- Reativar verificacao de chaves estrangeiras
SET FOREIGN_KEY_CHECKS = 1;
