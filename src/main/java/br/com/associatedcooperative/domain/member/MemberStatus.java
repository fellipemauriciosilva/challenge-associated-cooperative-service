package br.com.associatedcooperative.domain.member;

public enum MemberStatus {
    ABLE_TO_VOTE("Active user"),
    UNABLE_TO_VOTE("Inactive user");
    private final String situation;

    MemberStatus(String situation) {
        this.situation = situation;
    }

}
