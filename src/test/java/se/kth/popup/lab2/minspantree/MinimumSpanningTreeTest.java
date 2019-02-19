package se.kth.popup.lab2.minspantree;

import org.junit.jupiter.api.Test;
import se.kth.popup.Kattio;
import se.kth.popup.KattisTester;

import java.util.function.Consumer;

public class MinimumSpanningTreeTest extends KattisTester {

    public MinimumSpanningTreeTest() {
        super("minspantree", MinimumSpanningTreeMain::run);
    }

}
