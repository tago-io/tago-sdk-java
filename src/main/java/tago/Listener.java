package tago;

import services.Console;

public interface Listener {

    public abstract void call(Object object, Console console);

}
