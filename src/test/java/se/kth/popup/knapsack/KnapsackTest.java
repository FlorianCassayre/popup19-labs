package se.kth.popup.knapsack;

import org.junit.jupiter.api.Test;
import se.kth.popup.KattisTester;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class KnapsackTest extends KattisTester {

    KnapsackTest() {
        super("knapsack", KnapsackReader::run);
    }

}
