CREATE TABLE Pacotes (
    codigo INTEGER NOT NULL
                   PRIMARY KEY AUTOINCREMENT,
    sro    TEXT
);


CREATE TABLE Locais (
    codigo     INTEGER NOT NULL
                       PRIMARY KEY AUTOINCREMENT,
    nome_local TEXT
);


CREATE TABLE Eventos (
    codigo   INTEGER NOT NULL
                     PRIMARY KEY AUTOINCREMENT,
    data     DATE,
    detalhe  TEXT,
    acao     TEXT,
    idLocal  INTEGER REFERENCES Locais (codigo),
    idPacote INTEGER REFERENCES Pacotes (codigo)
);

