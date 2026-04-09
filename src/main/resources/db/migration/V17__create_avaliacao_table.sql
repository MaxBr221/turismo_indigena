CREATE TABLE avaliacao (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nota INT NOT NULL,
    comentario VARCHAR(255),
    user_id BIGINT NOT NULL,
    ponto_id BIGINT,
    restaurante_id BIGINT,
    data_avaliacao DATETIME NOT NULL,

    -- Criando as chaves estrangeiras (Foreign Keys)
    CONSTRAINT fk_avaliacao_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_avaliacao_ponto FOREIGN KEY (ponto_id) REFERENCES pontoturistico(id),
    CONSTRAINT fk_avaliacao_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id)
);