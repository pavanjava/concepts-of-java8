package com.sparkdev.functions;

import com.sparkdev.model.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Functions {

    Consumer<String> loggerConsumer = (exceptionMessage) -> Logger.getLogger(Functions.class.getName()).info("Exception: " + exceptionMessage);
    Function<List<Integer>, List<Integer>> oddsFunction = (input) -> input.stream().filter(element -> element %2 == 1).toList();

    Supplier<List<Integer>> randomIntListGenerator = () -> new Random().ints(10, 1, 100).boxed().toList();

    Supplier<List<Customer>> customerSupplier = () -> Stream.of(
            new Customer(1,"Pavan Mantha", "pavan.mantha@honeywell.com", Arrays.asList("1234567890","9949493991")),
            new Customer(2,"Arun Boppudi", "arun.boppudi@honeywell.com", Arrays.asList("1234567890","9949493991"))
    ).toList();
    public Consumer<String> getLoggerConsumer() {
        return loggerConsumer;
    }

    public Function<List<Integer>, List<Integer>> getOddsFunction() {
        return oddsFunction;
    }

    public Supplier<List<Integer>> getRandomIntListGenerator() {
        return randomIntListGenerator;
    }

    public Supplier<List<Customer>> getCustomerSupplier() {
        return customerSupplier;
    }
}
