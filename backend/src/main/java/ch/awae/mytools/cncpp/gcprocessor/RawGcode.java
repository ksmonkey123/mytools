package ch.awae.mytools.cncpp.gcprocessor;

import java.util.stream.Stream;

public interface RawGcode {

    Stream<String> getLines();

}
