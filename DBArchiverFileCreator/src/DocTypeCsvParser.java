import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.io.input.BOMInputStream;

import lombok.SneakyThrows;

public class DocTypeCsvParser implements AutoCloseable, Iterable<CSVRecord> {

    private static CSVFormat FORMAT = CSVFormat.newFormat(',') //
        .withAllowMissingColumnNames() //
        .withCommentMarker(null) //
        .withEscape(null) //
        .withHeader("technicalname", "label", "hint", "fieldtype", "order", "isencrypted", "searchfor", "display", "expirationyear") // TODO
        .withIgnoreEmptyLines(false) //
        .withIgnoreHeaderCase(false) //
        .withIgnoreSurroundingSpaces(false) //
        .withNullString(null) //
        .withQuote('"') //
        .withQuoteMode(QuoteMode.MINIMAL) //
        .withRecordSeparator("\r\n") //
        .withTrailingDelimiter(false) //
        .withTrim(false); //

    private CSVParser parser;

    public DocTypeCsvParser(String csv) throws IOException {
        parser = getParser(new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8)));
    }

    @SneakyThrows
    public DocTypeCsvParser(Path path) throws IOException {
        parser = getParser(Files.newInputStream(path));
    }

    public DocTypeCsvParser(InputStream stream) throws IOException {
        parser = getParser(stream);
    }

    @SneakyThrows
    private static CSVParser getParser(InputStream stream) throws IOException {
        return CSVParser.parse(new BOMInputStream(stream), StandardCharsets.UTF_8, FORMAT);
    }

    @Override
    @SneakyThrows
    public void close() throws IOException {
        parser.close();
    }

    public Map<String, Integer> getHeaderMap() {
        return parser.getHeaderMap();
    }

    @Override
    @SneakyThrows
    public Iterator<CSVRecord> iterator() {
        return parser.iterator();
    }

}
