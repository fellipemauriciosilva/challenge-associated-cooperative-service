package br.com.associatedcooperative.domain.ruling;


public enum RulingStatus {
    STARTED("STARTED"),
    FINISHED("FINISHED");

    private final String situacao;

    RulingStatus(String situacao) {
        this.situacao = situacao;
    }

}
