package br.com.associatedcooperative.infrastructure.task;

public interface Cron {
    String EVERY_5_MINUTES = "0 0/5 * * * ?";
    String EVERY_1_MINUTES = "0 0/1 * * * ?";
}
