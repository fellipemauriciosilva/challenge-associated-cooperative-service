package br.com.associatedcooperative.usecase.service;


import java.io.ByteArrayInputStream;

public interface GenerateVotesPdfReportByIdUseCase {
    ByteArrayInputStream generateVotesPdfReport(Long id) throws java.io.IOException;
}
