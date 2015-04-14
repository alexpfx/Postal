CREATE TABLE Eventos (
    codigo   INTEGER NOT NULL
                     PRIMARY KEY AUTOINCREMENT,
    data     DATE,
    detalhe  TEXT,
    acao     TEXT,
    idLocal  INTEGER REFERENCES Locais (codigo),
    idPacote INTEGER REFERENCES Pacotes (codigo)
);
