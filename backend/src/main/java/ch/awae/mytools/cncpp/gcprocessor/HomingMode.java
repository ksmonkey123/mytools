package ch.awae.mytools.cncpp.gcprocessor;

public enum HomingMode {

    HOME_ALL("Home All"),
    HOME_Z("Home Z");

    private final String text;

    HomingMode(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
