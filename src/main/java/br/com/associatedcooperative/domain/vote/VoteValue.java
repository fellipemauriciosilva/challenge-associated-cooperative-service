package br.com.associatedcooperative.domain.vote;

public enum VoteValue {
    YES("YES"),
    NO("NO");
    private final String value;
    VoteValue(String voto) {
        this.value = voto;
    }

}
