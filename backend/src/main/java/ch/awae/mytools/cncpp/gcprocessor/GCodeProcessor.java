package ch.awae.mytools.cncpp.gcprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GCodeProcessor {

    private final ProcessingParameters params;

    public GCodeProcessor(ProcessingParameters parameters) {
        this.params = parameters;
    }

    public Stream<String> process(List<RawGcode> fileList) {

        Stream<String> header = createHeader();

        Stream<String> body = fileList.stream()
                .flatMap(RawGcode::getLines)
                .filter(line -> line.startsWith("G0"))
                .map(this::stripFeedrate)
                .filter(line -> line.length() > 3)
                .map(this::addCustomFeedrate);

        Stream<String> footer = createFooter();

        return Stream.concat(header, Stream.concat(body, footer));
    }

    private Stream<String> createHeader() {
        List<String> lines = new ArrayList<>();

        if (params.getHomingMode() == HomingMode.HOME_ALL) {
            lines.add("G28");
            if (params.isDoBedLevelling()) {
                lines.add("G29");
                lines.add("M400");
                lines.add("M500");
            }
            lines.add("G00 X" + params.getZeroX() + " Y" + params.getZeroY());
            lines.add("G92 X0 Y0");
        } else {
            lines.add("G28 Z");
        }
        lines.add("M400");
        lines.add("M420 S");
        lines.add("M300 S440 P500");
        lines.add("G4 S20");
        lines.add("M300 S660 P500");
        lines.add("M3");
        lines.add("G4 S5");

        return lines.stream();
    }

    private Stream<String> createFooter() {
        List<String> lines = new ArrayList<>();

        lines.add("M400");
        lines.add("G00 Z40");
        lines.add("M5");
        lines.add("G4 S2");
        lines.add("M300 S880 P250");

        return lines.stream();
    }

    private String addCustomFeedrate(String line) {
        if (line.startsWith("G00")) {
            return line + " F" + params.getTravelSpeed();
        } else {
            return line + " F" + params.getWorkSpeed();
        }
    }

    private String stripFeedrate(String line) {
        return Stream.of(line.split(" "))
                .filter(token -> !token.startsWith("F"))
                .reduce((a, b) -> a + " " + b)
                .orElse("");
    }
}
