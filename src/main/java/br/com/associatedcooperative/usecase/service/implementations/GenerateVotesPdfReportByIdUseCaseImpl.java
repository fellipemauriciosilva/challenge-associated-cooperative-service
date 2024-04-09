package br.com.associatedcooperative.usecase.service.implementations;

import br.com.associatedcooperative.infrastructure.persistence.RulingRepository;
import br.com.associatedcooperative.usecase.service.GenerateVotesPdfReportByIdUseCase;
import br.com.associatedcooperative.usecase.dto.RulingResultResponse;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GenerateVotesPdfReportByIdUseCaseImpl implements GenerateVotesPdfReportByIdUseCase {

    private final RulingRepository rulingRepository;
    private final RulingResultByIdUseCaseImpl rulingResultByIdUseCase;

    @Override
    public ByteArrayInputStream generateVotesPdfReport(Long id) throws IOException {
        var ruling = rulingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        var result = rulingResultByIdUseCase.getRulingResultById(id);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDocument);

        Paragraph title = new Paragraph("Meeting: " + ruling.getMeeting().getName() + "\nVote Counts for Ruling: " + ruling.getDescription())
                .setFontSize(20)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD))
                .setTextAlignment(TextAlignment.CENTER);

        document.add(title);
        document.add(new Paragraph("\n"));

        fillReport(result, document);

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private static void fillReport(RulingResultResponse result, Document document) {
        Table table = new Table(4)
                .useAllAvailableWidth()
                .setTextAlignment(TextAlignment.CENTER);

        table.addHeaderCell("RULING DESCRIPTION");
        table.addHeaderCell("NUMBER OF YES VOTES");
        table.addHeaderCell("NUMBER OF NO VOTES");
        table.addHeaderCell("RULING RESULT");

        table.addCell(result.rulingDescription());
        table.addCell(result.yesVote().toString());
        table.addCell(result.noVote().toString());
        table.addCell(result.result());

        document.add(table);
        document.close();
    }
}